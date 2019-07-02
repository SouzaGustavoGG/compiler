package compilador.AnaliseSemantica;

import java.util.HashMap;

public class TabelaSimbolos {
    
    private HashMap<String, EstruturaTabelaSimbolos> tabela;

    public TabelaSimbolos() {
        this.tabela = new HashMap<String, EstruturaTabelaSimbolos>();
    }
  
    public boolean existIdentificadorTabelaSimbolos(String identificador){
        return !(tabela.isEmpty() || !tabela.containsKey(identificador));
    }
    
    public EstruturaTabelaSimbolos buscaDadosDoIdentificador(String identificador){
        return tabela.get(identificador);
    }
    
    public boolean insereIdentificador(EstruturaTabelaSimbolos dadosIdentificador){
        if(!tabela.containsKey(dadosIdentificador.getIdentificador())){
            tabela.put(dadosIdentificador.getIdentificador(), dadosIdentificador);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean deletaIdentificador(String identificador){
        if(!tabela.containsKey(identificador)){
            return false;
        }else{
            tabela.remove(identificador);
            return true;
        }
    }
}
