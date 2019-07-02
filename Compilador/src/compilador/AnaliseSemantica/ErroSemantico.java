package compilador.AnaliseSemantica;

public class ErroSemantico extends Exception {

    public ErroSemantico() {
    }

    public ErroSemantico(String msg) {
        super(msg);
    }
}
