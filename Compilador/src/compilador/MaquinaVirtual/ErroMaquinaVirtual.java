package compilador.MaquinaVirtual;

public class ErroMaquinaVirtual extends Exception {

    public ErroMaquinaVirtual() {
    }

    public ErroMaquinaVirtual(String msg) {
        super("Runtime ERROR: "+msg);
    }
}
