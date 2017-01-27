package parser;

import interfaces.IDBMS;

public class Use extends SQLKeywords {

	private String dbName;
	private String[] splittedCommand;

	@Override
	public void excute(final IDBMS dbms, final String[] splittedCommand) throws Exception {
		this.splittedCommand = splittedCommand;
		validate();
		String[] temp = { dbName };
		isValidName(temp);
		dbms.useDB(dbName);
	}

	private void validate() {
		if (splittedCommand.length == 3 && !splittedCommand[1].contains(",")) {
			dbName = splittedCommand[1];
			return;
		}
		throw new RuntimeException("Invalid use command.");
	}

}
