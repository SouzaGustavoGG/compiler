package compilador;

import java.io.StringReader;
import compilador.linguagem20191.Parser;

public class LexicAnalyzer {

    private static LexicAnalyzer instance = null;
    protected LexicAnalyzer() {
    }

    public static LexicAnalyzer getInstance() {
        if(instance == null) {
            instance = new LexicAnalyzer();
        }
        return instance;
    }

    public String analyze(String text){
        Parser parser = new Parser(new StringReader(text));
        StringBuilder output = new StringBuilder();
        try{
            parser.executarAnalise();
            for(String a: compilador.linguagem20191.Dados.getDadosErros())
                output.append(a).append("\n");
            compilador.linguagem20191.Dados.limpar();
        }catch(Exception e){}


        return output.toString();

    }

}
