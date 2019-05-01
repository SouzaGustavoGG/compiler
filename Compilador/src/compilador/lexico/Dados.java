package compilador.lexico;

import java.util.ArrayList;

public class Dados {
    private static int erros = 0;
    private static int oks = 0;
    private static ArrayList<String> dados = new ArrayList<String>();
    private static ArrayList<String> dadosErros = new ArrayList<String>();
    
    static void addDado(String tipoToken, String token, int linha, int coluna, int id){
        oks += 1;
        dados.add(tipoToken + " -> '" + token + "' na linha " + linha + ", coluna " + coluna + " | ID: " + id);
    }
    
    static void addError(String tipoToken, String token, int linha, int coluna, int id){
        erros += 1;
        dadosErros.add("ERRO -> " + tipoToken + " '" + token + "' na linha " + linha + ", coluna " + coluna + " | ID: " + id);
    }

    static void removeLastError(){
        erros -= 1;
        dados.remove(dados.size()-1);
    }

    public static void limpar(){
        erros = 0;
        oks = 0;
        dados.clear();
        dadosErros.clear();
    }

    public static int getErros() {
        return erros;
    }

    public static int getOks() {
        return oks;
    }

    public static ArrayList<String> getDados() {
        return dados;
    }

    public static ArrayList<String> getDadosErros() {
        return dadosErros;
    }
}
