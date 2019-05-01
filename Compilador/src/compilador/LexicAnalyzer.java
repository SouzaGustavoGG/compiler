package compilador;

import java.io.StringReader;
import compilador.lexico.Parser;

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
            for(String a: compilador.lexico.Dados.getDadosErros())
                output.append(a).append("\n");
            output.append("Número de erros: ").append(compilador.lexico.Dados.getErros()).append("\n");
            compilador.lexico.Dados.limpar();
        }catch(Exception e){}


        return output.toString();

    }

}
