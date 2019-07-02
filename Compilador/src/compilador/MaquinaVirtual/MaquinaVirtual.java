package compilador.MaquinaVirtual;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaquinaVirtual {

    public boolean necessitaLeitura;

    private class Registro {

        String valor;
        int tipo;

        public Registro(String valor, int tipo) {
            this.valor = valor;
            this.tipo = tipo;
        }
    }
    public String dadosDeSaida;
    public Object dadoDeEntrada;
    public String execucaoCodObjeto;
    ArrayList<String> codigo;
    String[][] codigoObjeto;
    Vector<Registro> pilhaDeDados = new Stack<Registro>();
    int ponteiro;
    int topo;
    public boolean finalExecucao;
    public boolean valorLido;

    public MaquinaVirtual(ArrayList<String> codigoObjeto) {
        ponteiro = 1;
        topo = -1;
        finalExecucao = false;
        necessitaLeitura = false;
        valorLido = true;
        codigo = codigoObjeto;
        this.codigoObjeto = transformaCodObjetoTable(codigoObjeto);

        Thread t = new Thread() {

            @Override
            public void run() {
                while (!finalExecucao) {
                    execute();
                }
            }
        };

        t.start();
    }

    private String[][] transformaCodObjetoTable(ArrayList<String> codigo) {
        String[][] codTabela = new String[codigo.size()][3];
        int i = 0;
        for (String instrucao : codigo) {
            String[] cod = instrucao.split(";", 3);
            codTabela[i] = cod;
            i++;
        }

        return codTabela;
    }

    public void execute() {
        while (!finalExecucao) {
            String instrucao = codigoObjeto[ponteiro - 1][1].trim();
            String parametro = codigoObjeto[ponteiro - 1][2].trim();

            if (instrucao.equals("ADD")) {
                execucaoCodObjeto += codigo.get(ponteiro - 1);
                try {
                    add();
                } catch (ErroMaquinaVirtual ex) {
                    finalExecucao = true;
                    dadosDeSaida += "\n\n" + ex.getMessage();
                }
            } else {
                if (instrucao.equals("DIV")) {
                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                    try {
                        div();
                    } catch (ErroMaquinaVirtual ex) {
                        finalExecucao = true;
                        dadosDeSaida += "\n\n" + ex.getMessage();
                    }
                } else {
                    if (instrucao.equals("MUL")) {
                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                        try {
                            mul();
                        } catch (ErroMaquinaVirtual ex) {
                            finalExecucao = true;
                            dadosDeSaida += "\n\n" + ex.getMessage();
                        }
                    } else {
                        if (instrucao.equals("SUB")) {
                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                            try {
                                sub();
                            } catch (ErroMaquinaVirtual ex) {
                                finalExecucao = true;
                                dadosDeSaida += "\n\n" + ex.getMessage();
                            }
                        } else {
                            if (instrucao.equals("DIVI")) {
                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                try {
                                    idiv();
                                } catch (ErroMaquinaVirtual ex) {
                                    finalExecucao = true;
                                    dadosDeSaida += "\n\n" + ex.getMessage();
                                }
                            } else {
                                if (instrucao.equals("DMOD")) {
                                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                                    try {
                                        mod();
                                    } catch (ErroMaquinaVirtual ex) {
                                        finalExecucao = true;
                                        dadosDeSaida += "\n\n" + ex.getMessage();
                                    }
                                } else {
                                    if (instrucao.equals("PWR")) {
                                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                                        try {
                                            pwr();
                                        } catch (ErroMaquinaVirtual ex) {
                                            finalExecucao = true;
                                            dadosDeSaida += "\n\n" + ex.getMessage();
                                        }
                                    } else {
                                        if (instrucao.equals("ALB")) {
                                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                                            try {
                                                alb(Integer.parseInt(parametro));
                                            } catch (ErroMaquinaVirtual ex) {
                                                finalExecucao = true;
                                                dadosDeSaida += "\n\n" + ex.getMessage();
                                            }
                                        } else {
                                            if (instrucao.equals("ALI")) {
                                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                try {
                                                    ali(Integer.parseInt(parametro));
                                                } catch (ErroMaquinaVirtual ex) {
                                                    finalExecucao = true;
                                                    dadosDeSaida += "\n\n" + ex.getMessage();
                                                }
                                            } else {
                                                if (instrucao.equals("ALR")) {
                                                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                    try {
                                                        alr(Integer.parseInt(parametro));
                                                    } catch (ErroMaquinaVirtual ex) {
                                                        finalExecucao = true;
                                                        dadosDeSaida += "\n\n" + ex.getMessage();
                                                    }
                                                } else {
                                                    if (instrucao.equals("ALS")) {
                                                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                        try {
                                                            als(Integer.parseInt(parametro));
                                                        } catch (ErroMaquinaVirtual ex) {
                                                            finalExecucao = true;
                                                            dadosDeSaida += "\n\n" + ex.getMessage();
                                                        }
                                                    } else {
                                                        if (instrucao.equals("LDB")) {
                                                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                            try {
                                                                ldb(parametro);
                                                            } catch (ErroMaquinaVirtual ex) {
                                                                finalExecucao = true;
                                                                dadosDeSaida += "\n\n" + ex.getMessage();
                                                            }
                                                        } else {
                                                            if (instrucao.equals("LDI")) {
                                                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                try {
                                                                    ldi(parametro);
                                                                } catch (ErroMaquinaVirtual ex) {
                                                                    finalExecucao = true;
                                                                    dadosDeSaida += "\n\n" + ex.getMessage();
                                                                }
                                                            } else {
                                                                if (instrucao.equals("LDR")) {
                                                                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                    try {
                                                                        ldr(parametro);
                                                                    } catch (ErroMaquinaVirtual ex) {
                                                                        finalExecucao = true;
                                                                        dadosDeSaida += "\n\n" + ex.getMessage();
                                                                    }
                                                                } else {
                                                                    if (instrucao.equals("LDS")) {
                                                                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                        try {
                                                                            lds(parametro);
                                                                        } catch (ErroMaquinaVirtual ex) {
                                                                            finalExecucao = true;
                                                                            dadosDeSaida += "\n\n" + ex.getMessage();
                                                                        }
                                                                    } else {
                                                                        if (instrucao.equals("LDV")) {
                                                                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                            ldv(Integer.parseInt(parametro));
                                                                        } else {
                                                                            if (instrucao.equals("STR")) {
                                                                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                try {
                                                                                    str(Integer.parseInt(parametro));
                                                                                } catch (ErroMaquinaVirtual ex) {
                                                                                    finalExecucao = true;
                                                                                    dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                }
                                                                            } else {
                                                                                if (instrucao.equals("AND")) {
                                                                                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                    try {
                                                                                        and();
                                                                                    } catch (ErroMaquinaVirtual ex) {
                                                                                        finalExecucao = true;
                                                                                        dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                    }
                                                                                } else {
                                                                                    if (instrucao.equals("NOT")) {
                                                                                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                        try {
                                                                                            not();
                                                                                        } catch (ErroMaquinaVirtual ex) {
                                                                                            finalExecucao = true;
                                                                                            dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                        }
                                                                                    } else {
                                                                                        if (instrucao.equals("OR")) {
                                                                                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                            try {
                                                                                                or();
                                                                                            } catch (ErroMaquinaVirtual ex) {
                                                                                                finalExecucao = true;
                                                                                                dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                            }
                                                                                        } else {
                                                                                            if (instrucao.equals("BGE")) {
                                                                                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                try {
                                                                                                    bge();
                                                                                                } catch (ErroMaquinaVirtual ex) {
                                                                                                    finalExecucao = true;
                                                                                                    dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                }
                                                                                            } else {
                                                                                                if (instrucao.equals("BGR")) {
                                                                                                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                    try {
                                                                                                        bgr();
                                                                                                    } catch (ErroMaquinaVirtual ex) {
                                                                                                        finalExecucao = true;
                                                                                                        dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                    }
                                                                                                } else {
                                                                                                    if (instrucao.equals("DIF")) {
                                                                                                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                        try {
                                                                                                            dif();
                                                                                                        } catch (ErroMaquinaVirtual ex) {
                                                                                                            finalExecucao = true;
                                                                                                            dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                        }
                                                                                                    } else {
                                                                                                        if (instrucao.equals("EQL")) {
                                                                                                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                            try {
                                                                                                                eql();
                                                                                                            } catch (ErroMaquinaVirtual ex) {
                                                                                                                finalExecucao = true;
                                                                                                                dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                            }
                                                                                                        } else {
                                                                                                            if (instrucao.equals("SME")) {
                                                                                                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                try {
                                                                                                                    sme();
                                                                                                                } catch (ErroMaquinaVirtual ex) {
                                                                                                                    finalExecucao = true;
                                                                                                                    dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                                }
                                                                                                            } else {
                                                                                                                if (instrucao.equals("SMR")) {
                                                                                                                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                    try {
                                                                                                                        smr();
                                                                                                                    } catch (ErroMaquinaVirtual ex) {
                                                                                                                        finalExecucao = true;
                                                                                                                        dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    if (instrucao.equals("JMF")) {
                                                                                                                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                        jmf(Integer.parseInt(parametro));
                                                                                                                    } else {
                                                                                                                        if (instrucao.equals("JMP")) {
                                                                                                                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                            jmp(Integer.parseInt(parametro));
                                                                                                                        } else {
                                                                                                                            if (instrucao.equals("JMT")) {
                                                                                                                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                                jmt(Integer.parseInt(parametro));
                                                                                                                            } else {
                                                                                                                                if (instrucao.equals("STP")) {
                                                                                                                                    execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                                    stp();
                                                                                                                                } else {
                                                                                                                                    if (instrucao.equals("WRT")) {
                                                                                                                                        execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                                        wrt();
                                                                                                                                    } else {
                                                                                                                                        if (instrucao.equals("REA")) {
                                                                                                                                            execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                                            try {
                                                                                                                                                valorLido = false;
                                                                                                                                                while (!valorLido) {
                                                                                                                                                    necessitaLeitura = true;
                                                                                                                                                    try {
                                                                                                                                                        Thread.sleep(1000);
                                                                                                                                                    } catch (InterruptedException ex) {
                                                                                                                                                        Logger.getLogger(MaquinaVirtual.class.getName()).log(Level.SEVERE, null, ex);
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                valorLido = true;
                                                                                                                                                necessitaLeitura = false;
                                                                                                                                                rea(Integer.parseInt(parametro));
                                                                                                                                            } catch (ErroMaquinaVirtual ex) {
                                                                                                                                                finalExecucao = true;
                                                                                                                                                dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                                                            }
                                                                                                                                        } else {
                                                                                                                                            if (instrucao.equals("STC")) {
                                                                                                                                                execucaoCodObjeto += codigo.get(ponteiro - 1);
                                                                                                                                                try {
                                                                                                                                                    stc(Integer.parseInt(parametro));
                                                                                                                                                } catch (ErroMaquinaVirtual ex) {
                                                                                                                                                    finalExecucao = true;
                                                                                                                                                    dadosDeSaida += "\n\n" + ex.getMessage();
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                System.out.println(instrucao + "-------------------------------------------------Erro Grave---------------------------------------------------");
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void add() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de soma em literais ou valores logicos");
        }

        Object resultado = ((tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor))
                + (tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor)));

        if (tpTopoMenos1==1 && tpTopo==1) {
            pilhaDeDados.set(topo - 1, new Registro((""+((Float) resultado).intValue()).toString(), 1));
        } else {
            pilhaDeDados.set(topo - 1, new Registro(((Float) resultado).toString(), 2));
        }

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void mul() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de multiplicação em literais ou valores logicos");
        }

        Object resultado = ((tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor))
                * (tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor)));

        if (tpTopoMenos1==1 && tpTopo==1) {
            pilhaDeDados.set(topo - 1, new Registro((""+((Float) resultado).intValue()).toString(), 1));
        } else {
            pilhaDeDados.set(topo - 1, new Registro(((Float) resultado).toString(), 2));
        }

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void sub() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de subtração em literais ou valores logicos");
        }

        Object resultado = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                - (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));

        if (tpTopoMenos1==1 && tpTopo==1) {
            pilhaDeDados.set(topo - 1, new Registro((""+((Float) resultado).intValue()).toString(), 1));
        } else {
            pilhaDeDados.set(topo - 1, new Registro(((Float) resultado).toString(), 2));
        }

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void div() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de divisão em literais ou valores logicos");
        }

        if (Float.parseFloat(pilhaDeDados.get(topo).valor) == 0.0) {
            throw new ErroMaquinaVirtual("Não é possivel realizar divisão por zero");
        }

        Object resultado = Float.parseFloat(pilhaDeDados.get(topo - 1).valor) / Float.parseFloat(pilhaDeDados.get(topo).valor);

        pilhaDeDados.set(topo - 1, new Registro(((Float) resultado).toString(), 2));

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void idiv() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de divisão em literais ou valores logicos");
        }

        if (Float.parseFloat(pilhaDeDados.get(topo).valor) == 0.0) {
            throw new ErroMaquinaVirtual("Não é possivel realizar divisão por zero");
        }

        Object resultado = (int) (Float.parseFloat(pilhaDeDados.get(topo - 1).valor) / Float.parseFloat(pilhaDeDados.get(topo).valor));

        pilhaDeDados.set(topo - 1, new Registro(("" + ((Integer) resultado).intValue()), 1));

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void mod() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de resto da divisão inteira em literais ou valores logicos");
        }

        if (new Float(pilhaDeDados.get(topo).valor).intValue() == 0) {
            throw new ErroMaquinaVirtual("Não é possivel realizar divisão por zero");
        }

        Object resultado = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                % (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));

        pilhaDeDados.set(topo - 1, new Registro(("" + ((Float) resultado).intValue()), 1));

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void alb(int deslocamento) throws ErroMaquinaVirtual {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilhaDeDados.add(new Registro("UNTRUE", 4));
        }

        topo += deslocamento;
        ponteiro++;
    }

    public void ali(int deslocamento) throws ErroMaquinaVirtual {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilhaDeDados.add(new Registro("0", 1));
        }

        topo += deslocamento;
        ponteiro++;
    }

    public void alr(int deslocamento) throws ErroMaquinaVirtual {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilhaDeDados.add(new Registro("0.0", 2));
        }

        topo += deslocamento;
        ponteiro++;
    }

    public void als(int deslocamento) throws ErroMaquinaVirtual {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilhaDeDados.add(new Registro("", 3));
        }

        topo += deslocamento;
        ponteiro++;
    }

    public void and() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 4 && tpTopoMenos1 == 4) {
            boolean valor = (pilhaDeDados.get(topo - 1).valor.trim().equals("UNTRUE") ? false : true)
                    && (pilhaDeDados.get(topo).valor.trim().equals("UNTRUE") ? false : true);
            String resultado = (valor == true ? "TRUE" : "UNTRUE");
            pilhaDeDados.set(topo - 1, new Registro(resultado, 4));

            pilhaDeDados.remove(topo);
            topo--;
            ponteiro++;
        } else {
            throw new ErroMaquinaVirtual("Operação logica E (AND) só pode ser executada com valores logicos");
        }
    }

    public void or() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 4 && tpTopoMenos1 == 4) {
            boolean valor = (pilhaDeDados.get(topo - 1).valor.trim().equals("UNTRUE") ? false : true)
                    || (pilhaDeDados.get(topo).valor.trim().equals("UNTRUE") ? false : true);
            String resultado = (valor == true ? "TRUE" : "UNTRUE");
            pilhaDeDados.set(topo - 1, new Registro(resultado, 4));

            pilhaDeDados.remove(topo);
            topo--;
            ponteiro++;
        } else {
            throw new ErroMaquinaVirtual("Operação logica OU (OR) só pode ser executada com valores logicos");
        }
    }

    public void bge() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de maior igual em literais ou valores logicos");
        }

        boolean valor = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                >= (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));
        String resultado = (valor == true ? "TRUE" : "UNTRUE");
        pilhaDeDados.set(topo - 1, new Registro(resultado, 4));

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void bgr() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de maior em literais ou valores logicos");
        }

        boolean valor = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                > (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));
        String resultado = (valor == true ? "TRUE" : "UNTRUE");
        pilhaDeDados.set(topo - 1, new Registro(resultado, 4));

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void sme() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de menor igual em literais ou valores logicos");
        }

        boolean valor = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                <= (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));
        String resultado = (valor == true ? "TRUE" : "UNTRUE");
        pilhaDeDados.set(topo - 1, new Registro(resultado, 4));

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void smr() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de menor em literais ou valores logicos");
        }

        boolean valor = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                < (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));
        String resultado = (valor == true ? "TRUE" : "UNTRUE");
        pilhaDeDados.set(topo - 1, new Registro(resultado, 4));

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void stp() {
        finalExecucao = true;
    }

    public void wrt() {
        dadosDeSaida += "" + pilhaDeDados.get(topo).valor + "\n";
        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void str(int endereco) throws ErroMaquinaVirtual {
        if(pilhaDeDados.get(endereco - 1).tipo != pilhaDeDados.get(topo).tipo){
            throw new ErroMaquinaVirtual("Não é possivel atribuir valor a uma variavel de tipo diferente");
        }else{
            pilhaDeDados.get(endereco - 1).tipo = pilhaDeDados.get(topo).tipo;
            pilhaDeDados.get(endereco - 1).valor = pilhaDeDados.get(topo).valor;
            pilhaDeDados.remove(topo);
            topo--;
            ponteiro++;
        }
    }

    public void lds(String constante) throws ErroMaquinaVirtual {
        topo++;
        pilhaDeDados.add(new Registro(constante, 3));
        ponteiro++;
    }

    public void ldr(String constante) throws ErroMaquinaVirtual {
        topo++;
        pilhaDeDados.add(new Registro(constante, 2));
        ponteiro++;
    }

    public void ldi(String constante) throws ErroMaquinaVirtual {
        topo++;
        pilhaDeDados.add(new Registro(""+(new Float(constante).intValue()), 1));
        ponteiro++;
    }

    public void ldb(String constante) throws ErroMaquinaVirtual {
        topo++;
        pilhaDeDados.add(new Registro(constante, 4));
        ponteiro++;
    }

    public void ldv(int endereco) {
        topo++;
        pilhaDeDados.add(new Registro(pilhaDeDados.get(endereco - 1).valor, pilhaDeDados.get(endereco - 1).tipo));
        ponteiro++;
    }

    public void jmt(int endereco) {
        if (pilhaDeDados.get(topo).valor.equals("TRUE")) {
            ponteiro = endereco;
        } else {
            ponteiro++;
        }

        pilhaDeDados.remove(topo);
        topo--;
    }

    public void jmp(int endereco) {
        ponteiro = endereco;
    }

    public void not() throws ErroMaquinaVirtual {
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 4) {
            boolean valor = !(pilhaDeDados.get(topo).valor.trim().equals("UNTRUE") ? false : true);
            String resultado = (valor == true ? "TRUE" : "UNTRUE");
            pilhaDeDados.set(topo, new Registro(resultado, 4));

            ponteiro++;
        } else {
            throw new ErroMaquinaVirtual("Operação logica NÃO (NOT) só pode ser executada com valores logicos");
        }
    }

    public void jmf(int endereco) {
        if (pilhaDeDados.get(topo).valor.equals("UNTRUE")) {
            ponteiro = endereco;
        } else {
            ponteiro++;
        }

        pilhaDeDados.remove(topo);
        topo--;
    }

    public void eql() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        boolean resultComp;

        tpTopoMenos1 = (tpTopoMenos1 == 5 ? 1 : (tpTopoMenos1 == 6 ? 2 : (tpTopoMenos1 == 7 ? 3 : tpTopoMenos1)));
        tpTopo = (tpTopo == 5 ? 1 : (tpTopo == 6 ? 2 : (tpTopo == 7 ? 3 : tpTopo)));

        if (tpTopo == 3 && tpTopoMenos1 == 3) {
            resultComp = pilhaDeDados.get(topo).valor.replace("\"", "").equals(pilhaDeDados.get(topo - 1).valor.replace("\"", ""));
        } else {
            if (tpTopo == 4 && tpTopoMenos1 == 4) {
                resultComp = (pilhaDeDados.get(topo - 1).valor.trim().equals("UNTRUE") ? false : true)
                        == (pilhaDeDados.get(topo).valor.trim().equals("UNTRUE") ? false : true);
            } else {
                try {
                    resultComp = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                            == (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));
                } catch (Exception ex) {
                    throw new ErroMaquinaVirtual("Não é possivel executar a operação de igualdade com tipos diferentes.");
                }
            }
        }



        pilhaDeDados.get(topo - 1).valor = (resultComp == true ? "TRUE" : "UNTRUE");
        pilhaDeDados.get(topo - 1).tipo = 4;

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;

    }

    public void dif() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        boolean resultComp;

        tpTopoMenos1 = (tpTopoMenos1 == 5 ? 1 : (tpTopoMenos1 == 6 ? 2 : (tpTopoMenos1 == 7 ? 3 : tpTopoMenos1)));
        tpTopo = (tpTopo == 5 ? 1 : (tpTopo == 6 ? 2 : (tpTopo == 7 ? 3 : tpTopo)));

        if (tpTopo == 3 && tpTopoMenos1 == 3) {
            resultComp = !pilhaDeDados.get(topo).valor.replace("\"", "").equals(pilhaDeDados.get(topo - 1).valor.replace("\"", ""));
        } else {
            if (tpTopo == 4 && tpTopoMenos1 == 4) {
                resultComp = (pilhaDeDados.get(topo - 1).valor.trim().equals("UNTRUE") ? false : true)
                        != (pilhaDeDados.get(topo).valor.trim().equals("UNTRUE") ? false : true);
            } else {
                try {
                    resultComp = ((tpTopoMenos1 == 1 ? Integer.parseInt(pilhaDeDados.get(topo - 1).valor) : Float.parseFloat(pilhaDeDados.get(topo - 1).valor))
                            != (tpTopo == 1 ? Integer.parseInt(pilhaDeDados.get(topo).valor) : Float.parseFloat(pilhaDeDados.get(topo).valor)));
                } catch (Exception ex) {
                    throw new ErroMaquinaVirtual("Não é possivel executar a operação de igualdade com tipos diferentes.");
                }
            }
        }

        pilhaDeDados.get(topo - 1).valor = (resultComp == true ? "TRUE" : "UNTRUE");
        pilhaDeDados.get(topo - 1).tipo = 4;

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void rea(int tipo) throws ErroMaquinaVirtual {
        topo++;
        tipo = (tipo == 5 ? 1 : (tipo == 6 ? 2 : (tipo == 7 ? 3 : tipo)));

        switch (tipo) {
            case 1:
                try {
                    Integer.parseInt((String) dadoDeEntrada);
                } catch (Exception ex) {
                    throw new ErroMaquinaVirtual("O valor lido não é um inteiro");
                }
                break;
            case 2:
                try {
                    Float.parseFloat((String) dadoDeEntrada);
                } catch (Exception ex) {
                    throw new ErroMaquinaVirtual("O valor lido não é um numero real");
                }
                break;
            case 3:
                if (!(dadoDeEntrada instanceof String)) {
                    throw new ErroMaquinaVirtual("O valor lido não é um literal");
                }
                break;
            default:
                throw new ErroMaquinaVirtual("Valor lido é invalido");
        }

        pilhaDeDados.add(new Registro((String) (dadoDeEntrada), tipo));
        ponteiro++;
    }

    public void stc(int deslocamento) throws ErroMaquinaVirtual {
        for (int i = (topo - deslocamento); i <= topo - 1; i++) {
            pilhaDeDados.set(i, new Registro(pilhaDeDados.get(topo).valor, pilhaDeDados.get(topo).tipo));
        }

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }

    public void pwr() throws ErroMaquinaVirtual {
        int tpTopoMenos1 = pilhaDeDados.get(topo - 1).tipo;
        int tpTopo = pilhaDeDados.get(topo).tipo;

        if (tpTopo == 3 || tpTopo == 4 || tpTopo == 7 || tpTopoMenos1 == 3 || tpTopoMenos1 == 4 || tpTopoMenos1 == 7) {
            throw new ErroMaquinaVirtual("Não é possivel realizar operação de subtração em literais ou valores logicos");
        }

        Object resultado = Math.pow(Double.parseDouble(pilhaDeDados.get(topo - 1).valor), Double.parseDouble(pilhaDeDados.get(topo).valor));

        if (tpTopoMenos1==1 && tpTopo==1) {
            pilhaDeDados.set(topo - 1, new Registro((""+((Double) resultado).intValue()).toString(), 1));
        } else {
            pilhaDeDados.set(topo - 1, new Registro((""+((Double) resultado).floatValue()), 2));
        }

        pilhaDeDados.remove(topo);
        topo--;
        ponteiro++;
    }
}
