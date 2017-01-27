package parser;

import interfaces.IDBMS;

public class Parser {

	private IDBMS dbms;
	private Encryptor encryptor;
	private String[] splittedCommand;
	private SQLChecker sqlChecker;

	public Parser(final IDBMS dbms) throws Exception {
		encryptor = new Encryptor();
		sqlChecker = new SQLChecker();
		this.dbms = dbms;
	}

	public void execute(String s) throws Exception {
		s = s.trim();
		s = encryptor.encodeQoutes(s);
		s = SQLRegex(s);
		splittedCommand = s.split(" ");
		sqlChecker.excute(splittedCommand, dbms);
	}

	private String SQLRegex(String s) {
		s = s.trim();
		if (s.length() != 0 && s.charAt(s.length() - 1) != ';') {
			s = s + ";";
		}
		s = s.replace(";", " ;");
		s = s.replaceAll("\\(", " \\( ");
		s = s.replaceAll("\\)", " \\) ");
		s = s.replace("*", " * ");
		while (true) {
			String temp = new String(s);
			s = s.replaceAll("  ", " ");
			s = s.replaceAll(" ,", ",");
			s = s.replaceAll(", ", ",");
			s = s.replaceAll(" =", "=");
			s = s.replaceAll("= ", "=");
			s = s.replaceAll(" >", ">");
			s = s.replaceAll("> ", ">");
			s = s.replaceAll(" <", "<");
			s = s.replaceAll("< ", "<");
			if (temp.equals(s)) {
				break;
			}
		}
		s = s.trim();
		s = encryptor.encodeBrackets(s);
		return s;
	}

}