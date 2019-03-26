package compilador.lexico;

import java.util.ArrayList;

public class Dados {
    private static ArrayList<String> dados = new ArrayList<String>();
    private static int erros = 0;
    private static int oks = 0;
    
    public static void addDado(String tipoToken, String token, int linha, int coluna,  int id){
        oks += 1;
        dados.add(tipoToken + " -> '" + token + "' na linha " + linha + ", coluna " + coluna + "| ID: " + id);
    }
    
    public static void addError(String tipoToken, String token, int linha, int coluna, int id){
        erros += 1;
        dados.add("ERRO -> " + tipoToken + " '" + token + "' na linha " + linha + ", coluna " + coluna + "| ID: " + id);
    }
    
    public static void removeErro(){
        erros -= 1;
        dados.remove(dados.size()-1);
    }

    public static void limpar(){
        erros = 0;
        oks = 0;
        dados.clear();
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
}
