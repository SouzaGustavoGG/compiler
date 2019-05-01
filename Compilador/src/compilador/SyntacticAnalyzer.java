package compilador;

import java.io.StringReader;

import compilador.lexico.ParseException;
import compilador.lexico.Parser;

public class SyntacticAnalyzer {

	private static SyntacticAnalyzer instance = null;
	
	private SyntacticAnalyzer() {
		
	}
	
	public static SyntacticAnalyzer getInstance() {
		if(instance == null) {
			instance = new SyntacticAnalyzer();
		}
		return instance;
	}
	
    
    public String analyze(String text) {
    	Parser parser = new Parser(new StringReader(text));
    	StringBuilder output = new StringBuilder();
    	try {
			parser.executarAnaliseSintatica();
			output.append("Success!");
		} catch (ParseException e) {
			output.append(e.getMessage());
		}
    	return output.toString();
    }
}
