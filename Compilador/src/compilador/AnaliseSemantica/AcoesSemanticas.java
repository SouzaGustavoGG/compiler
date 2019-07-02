package compilador.AnaliseSemantica;

import java.util.ArrayList;

public class AcoesSemanticas {
    
    public static void fimPrograma_1(EstruturaControle ec, TabelaSimbolos ts, ArrayList<String> codigo, String identificador){
        ts.insereIdentificador(new EstruturaTabelaSimbolos(identificador, 0));
        codigo.add(ec.getPonteiro()+"; STP; 0\n");
    }
    
    public static void palavraReservadaUnchangeable_2(EstruturaControle ec){
        ec.setContexto("constante");
        ec.setVit(0);
    }
    
    public static void terminoDeclConstVariavel_3(EstruturaControle ec, ArrayList<String> codigo){
        ec.setVp(ec.getVp()+ec.getVit());
        
        int tipo = ec.getTipo();
        if(tipo==1 || tipo==5){
            codigo.add(ec.getPonteiro()+"; ALI; "+ec.getVp()+"\n");
            ec.incrementaPonteiro(1);
        }else{
            if(tipo==2 || tipo==6){
                codigo.add(ec.getPonteiro()+"; ALR; "+ec.getVp()+"\n");
                ec.incrementaPonteiro(1);
            }else{
                if(tipo==3 || tipo==7){
                    codigo.add(ec.getPonteiro()+"; ALS; "+ec.getVp()+"\n");
                    ec.incrementaPonteiro(1);
                }else{
                    codigo.add(ec.getPonteiro()+"; ALB; "+ec.getVp()+"\n");
                    ec.incrementaPonteiro(1);
                }
            }
        }
        
        if(tipo>=1 && tipo<=4){
            ec.setVp(0);
            ec.setVit(0);
        }
    }
    
    public static void valorNaDeclaracaoContante_4(EstruturaControle ec, ArrayList<String> codigo, String valor){
        int tipo = ec.getTipo();
        
        if(tipo==5){
            codigo.add(ec.getPonteiro()+"; LDI; "+valor+"\n");
            ec.incrementaPonteiro(1);
        }else{
            if(tipo==6){
                codigo.add(ec.getPonteiro()+"; LDR; "+valor+"\n");
                ec.incrementaPonteiro(1);
            }else{
                if(tipo==7){
                    codigo.add(ec.getPonteiro()+"; LDS; "+valor+"\n");
                    ec.incrementaPonteiro(1);
                }
            }
        }
        
        codigo.add(ec.getPonteiro()+"; STC; "+ec.getVp()+"\n");
        ec.incrementaPonteiro(1);
        ec.setVp(0);
    }
    
    public static void palavraReservadaChangeable_5(EstruturaControle ec){
        ec.setContexto("variavel");
    }
    
    public static void palavraReservadaInteger_6(EstruturaControle ec){
        if(ec.getContexto().equals("variavel")){
            ec.setTipo(1);
        }else{
            ec.setTipo(5);
        }
    }
    
    public static void palavraReservadaFloat_7(EstruturaControle ec){
        if(ec.getContexto().equals("variavel")){
            ec.setTipo(2);
        }else{
            ec.setTipo(6);
        }
    }
    
    public static void palavraReservadaString_8(EstruturaControle ec){
        if(ec.getContexto().equals("variavel")){
            ec.setTipo(3);
        }else{
            ec.setTipo(7);
        }
    }
    
    public static void palavraReservadaLogic_9(EstruturaControle ec) throws ErroSemantico{
        if(ec.getContexto().equals("variavel")){
            ec.setTipo(4);
        }else{
            throw new ErroSemantico("Tipo Logic não pode ser declarado para constantes.");
        }
    }
    
    public static void identificadorConstante_10(EstruturaControle ec, TabelaSimbolos ts, String identificador) throws ErroSemantico{
        if(ts.existIdentificadorTabelaSimbolos(identificador)){
            throw new ErroSemantico("O identificador ("+identificador+") já foi declarado");
        }else{
            ec.incrementaVT(1);
            ec.incrementaVP(1);
            ts.insereIdentificador(new EstruturaTabelaSimbolos(identificador, ec.getTipo(), ec.getVt()));
        }
    }
    
    public static void identificadorVariavel_11(EstruturaControle ec, TabelaSimbolos ts, String identificador) throws ErroSemantico{
        if(ec.getContexto().equals("variavel")){
            if(ts.existIdentificadorTabelaSimbolos(identificador)){
                throw new ErroSemantico("O identificador ("+identificador+") já foi declarado");
            }else{
                ec.setVariavelIndexada(false);
                ec.setArmazenamentoVariavel(identificador);
            }
        }else{
            ec.setVariavelIndexada(false);
            ec.setArmazenamentoVariavel(identificador);
        }
    }
    
    public static void identificadorVariavelETamanhoIndexado_12(EstruturaControle ec, TabelaSimbolos ts, ArrayList<String> codigo) throws ErroSemantico{
        if(ec.getContexto().equals("variavel")){
            if(!ec.isVariavelIndexada()){
                ec.incrementaVT(1);
                ec.incrementaVP(1);
                ts.insereIdentificador(new EstruturaTabelaSimbolos(ec.getArmazenamentoVariavel(), ec.getTipo(), ec.getVt()));
            }else{
                ec.incrementaVIT(ec.getArmazenaValorInteiro());
                ts.insereIdentificador(new EstruturaTabelaSimbolos(ec.getArmazenamentoVariavel(), ec.getTipo(), ec.getVt()+1, ec.getArmazenaValorInteiro()));
                ec.incrementaVT(ec.getArmazenaValorInteiro());
            }
        }else{
            if(ec.getContexto().equals("entrada dados")){
                if(ts.existIdentificadorTabelaSimbolos(ec.getArmazenamentoVariavel()) && (ts.buscaDadosDoIdentificador(ec.getArmazenamentoVariavel()).getCategoria()<=4 && ts.buscaDadosDoIdentificador(ec.getArmazenamentoVariavel()).getCategoria()>=1)){
                    EstruturaTabelaSimbolos estruturaTabelaSimbolos = ts.buscaDadosDoIdentificador(ec.getArmazenamentoVariavel());
                    
                    if(estruturaTabelaSimbolos.getAtributo2()<0){
                        if(!ec.isVariavelIndexada()){
                            codigo.add(ec.getPonteiro()+" ;REA ;"+ estruturaTabelaSimbolos.getCategoria()+"\n");
                            ec.incrementaPonteiro(1);
                            codigo.add(ec.getPonteiro()+" ;STR ;"+(estruturaTabelaSimbolos.getAtributo1()<0 ? "-" : ""+ estruturaTabelaSimbolos.getAtributo1())+"\n");
                            ec.incrementaPonteiro(1);
                        }else{
                            throw new ErroSemantico("O identificador de variavel ("+ec.getArmazenamentoVariavel()+") não é um array");
                        }
                    }else{
                        if(ec.isVariavelIndexada()){
                            codigo.add(ec.getPonteiro()+" ;REA ;"+ estruturaTabelaSimbolos.getCategoria()+"\n");
                            ec.incrementaPonteiro(1);
                            codigo.add(ec.getPonteiro()+" ;STR ;"+(estruturaTabelaSimbolos.getAtributo1()+ec.getArmazenaValorInteiro()-1)+"\n");
                            ec.incrementaPonteiro(1);
                        }else{
                            throw new ErroSemantico("O identificador de variavel array ("+ec.getArmazenamentoVariavel()+") necessita de indice");
                        }
                    }
                }else{
                    throw new ErroSemantico("O identificador ("+ec.getArmazenamentoVariavel()+") não foi declarado ou o mesmo é uma constante");
                }
            }else{
                if(ec.getContexto().equals("atribuição")){
                    if(ts.existIdentificadorTabelaSimbolos(ec.getArmazenamentoVariavel()) && (ts.buscaDadosDoIdentificador(ec.getArmazenamentoVariavel()).getCategoria()<=4 && ts.buscaDadosDoIdentificador(ec.getArmazenamentoVariavel()).getCategoria()>=1)){
                        EstruturaTabelaSimbolos estruturaTabelaSimbolos = ts.buscaDadosDoIdentificador(ec.getArmazenamentoVariavel());
                    
                        if(estruturaTabelaSimbolos.getAtributo2()<0){
                            if(!ec.isVariavelIndexada()){
                                ec.getListaAtributos().add(estruturaTabelaSimbolos.getAtributo1());
                            }else{
                                throw new ErroSemantico("O identificador de variavel ("+ec.getArmazenamentoVariavel()+") não é um array");
                            }
                        }else{
                            if(ec.isVariavelIndexada()){
                                ec.getListaAtributos().add(estruturaTabelaSimbolos.getAtributo1()+ec.getArmazenaValorInteiro()-1);
                            }else{
                                throw new ErroSemantico("O identificador de variavel array ("+ec.getArmazenamentoVariavel()+") necessita de indice");
                            }
                        }
                    }else{
                        throw new ErroSemantico("O identificador ("+ec.getArmazenamentoVariavel()+") não foi declarado ou o mesmo é uma constante");  
                    }
                }
            }
        }
    }
    
    public static void constanteIntComoTamanhoArray_13(EstruturaControle ec, int valor){
        ec.setArmazenaValorInteiro(valor);
        ec.setVariavelIndexada(true);
    }
    
    public static void comandoAtribuicao_14(EstruturaControle ec){
        ec.setContexto("atribuição");
    }
    
    public static void fimComandoAtribuicao_15(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;STR ;"+ec.getListaAtributos().get(0)+"\n");
        ec.getListaAtributos().remove(0);
        ec.incrementaPonteiro(1);
    }
    
    public static void comandoEntradaDados_16(EstruturaControle ec){
        ec.setContexto("entrada dados");
    }
    
    public static void mensagemEmComandoSaida_17(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;WRT ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void identificadorComandoSaida_18(EstruturaControle ec, TabelaSimbolos ts, ArrayList<String> codigo, String identificador) throws ErroSemantico{
        if(ts.existIdentificadorTabelaSimbolos(identificador) && (ts.buscaDadosDoIdentificador(identificador).getCategoria()<=7 && ts.buscaDadosDoIdentificador(identificador).getCategoria()>=1)){
            ec.setVariavelIndexada(false);
            ec.setArmazenamentoVariavel(identificador);
        }else{
            throw new ErroSemantico("O identificador ("+identificador+") não foi declarado");
        }
    }
    
    public static void variavelArrayComandoDeSaida_19(EstruturaControle ec, TabelaSimbolos ts, ArrayList<String> codigo) throws ErroSemantico{
        EstruturaTabelaSimbolos estruturaTabelaSimbolos = ts.buscaDadosDoIdentificador(ec.getArmazenamentoVariavel());
        
        if(!ec.isVariavelIndexada()){
            if(estruturaTabelaSimbolos.getAtributo2()<0){
                codigo.add(ec.getPonteiro()+" ;LDV ;"+ estruturaTabelaSimbolos.getAtributo1()+"\n");
                ec.incrementaPonteiro(1);
            }else{
                throw new ErroSemantico("O identificador de variavel array ("+ec.getArmazenamentoVariavel()+") necessita de indice");
            }
        }else{
            if(estruturaTabelaSimbolos.getAtributo2()>0){
                codigo.add(ec.getPonteiro()+" ;LDV ;"+(estruturaTabelaSimbolos.getAtributo1()+ec.getArmazenaValorInteiro()-1)+"\n");
                ec.incrementaPonteiro(1);
            }else{
                throw new ErroSemantico("O identificador de variavel ("+ec.getArmazenamentoVariavel()+") não é um array");
            }
        }
    }
    
    public static void constanteInteiraComandoSaida_20(EstruturaControle ec, ArrayList<String> codigo, String valor){
        codigo.add(ec.getPonteiro()+" ;LDI ;"+valor+"\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void constanteRealComandoSaida_21(EstruturaControle ec, ArrayList<String> codigo, String valor){
        codigo.add(ec.getPonteiro()+" ;LDR ;"+valor+"\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void constanteLiteralComandoSaida_22(EstruturaControle ec, ArrayList<String> codigo, String valor){
        codigo.add(ec.getPonteiro()+" ;LDS ;"+valor+"\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void fimComandoSelecao_23(EstruturaControle ec, ArrayList<String> codigo){
        ec.getPilhaDeDesvio().remove(ec.getPilhaDeDesvio().size()-1);
        for(int i=codigo.size()-1;i>=0;i--){
            String comando = codigo.get(i);
            if(comando.contains("<---comando-nao-finalizado--->")){
                codigo.set(i, comando.replace("<---comando-nao-finalizado--->", ""+ec.getPonteiro()));
                break;
            }
        }
    }
    
    public static void palavraReservadaTrue_24(EstruturaControle ec, ArrayList<String> codigo, TabelaSimbolos ts){
        codigo.add(ec.getPonteiro()+" ;JMF ;<---comando-nao-finalizado--->\n");
        ec.incrementaPonteiro(1);
        ec.getPilhaDeDesvio().add(ec.getPonteiro()-1);
    }
    
    public static void palavraReservadaUntrue_25(EstruturaControle ec, ArrayList<String> codigo, TabelaSimbolos ts){
        codigo.add(ec.getPonteiro()+" ;JMT ;<---comando-nao-finalizado--->\n");
        ec.incrementaPonteiro(1);
        ec.getPilhaDeDesvio().add(ec.getPonteiro()-1);
    }
    
    public static void palavraReservadaTrueOuUntrue_26(EstruturaControle ec, ArrayList<String> codigo, TabelaSimbolos ts){
        ec.getPilhaDeDesvio().remove(ec.getPilhaDeDesvio().size()-1);
        for(int i=codigo.size()-1;i>=0;i--){
            String comando = codigo.get(i);
            if(comando.contains("<---comando-nao-finalizado--->")){
                codigo.set(i, comando.replace("<---comando-nao-finalizado--->", ""+(ec.getPonteiro()+1)));
                break;
            }
        }
        codigo.add(ec.getPonteiro()+" ;JMP ;<---comando-nao-finalizado--->\n");
        ec.incrementaPonteiro(1);
        ec.getPilhaDeDesvio().add(ec.getPonteiro()-1);
    }
    
    public static void comandoRepeticao_27(EstruturaControle ec){
        ec.getPilhaDeDesvio().add(ec.getPonteiro());
    }
    
    public static void fimComandoRepeticao_28(EstruturaControle ec, ArrayList<String> codigo){
        Integer endereco = ec.getPilhaDeDesvio().get(ec.getPilhaDeDesvio().size()-1);
        ec.getPilhaDeDesvio().remove(ec.getPilhaDeDesvio().size()-1);
        codigo.add(ec.getPonteiro()+" ;JMT ;"+endereco.toString()+"\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void inicioComandoRepeticao_29(EstruturaControle ec){
        ec.getPilhaDeDesvio().add(ec.getPonteiro());
    }
    
    public static void comandoRepeticao_30(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;JMF ;<---comando-nao-finalizado--->\n");
        ec.incrementaPonteiro(1);
        ec.getPilhaDeDesvio().add(ec.getPonteiro()-1);
    }
    
    public static void fimComandoRepeticao_31(EstruturaControle ec, ArrayList<String> codigo){
        ec.getPilhaDeDesvio().remove(ec.getPilhaDeDesvio().size()-1);
        for(int i=codigo.size()-1;i>=0;i--){
            String comando = codigo.get(i);
            if(comando.contains("<---comando-nao-finalizado--->")){
                codigo.set(i, comando.replace("<---comando-nao-finalizado--->", ""+(ec.getPonteiro()+1)));
                break;
            }
        }
        
        Integer endereco = ec.getPilhaDeDesvio().get(ec.getPilhaDeDesvio().size()-1);
        ec.getPilhaDeDesvio().remove(ec.getPilhaDeDesvio().size()-1);
        codigo.add(ec.getPonteiro()+" ;JMP ;"+endereco.toString()+"\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoRelacionalIgual_32(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;EQL ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoRelacionalDiferente_33(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;DIF ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoRelacionalMenor_34(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;SMR ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoRelacionalMaior_35(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;BGR ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoRelacionalMenorIgual_36(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;SME ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoRelacionalMaiorIgual_37(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;BGE ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoAritmeticaAdicao_38(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;ADD ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoAritmeticaSubtracao_39(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;SUB ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoLogicaOU_40(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;OR ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoAritmeticaMultiplicao_41(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;MUL ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoAritmeticaDivReal_42(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;DIV ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoAritmeticaDivInteira_43(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;DIVI ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoAritmeticaRestoDivInteira_44(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;DMOD ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoLogicaE_45(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;AND ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoAritmeticaPotencia_46(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;PWR ;0\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void constanteLogicaTrue_47(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;LDB ;TRUE\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void constanteLogicaUntrue_48(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;LDB ;UNTRUE\n");
        ec.incrementaPonteiro(1);
    }
    
    public static void operacaoLogicaNao_49(EstruturaControle ec, ArrayList<String> codigo){
        codigo.add(ec.getPonteiro()+" ;NOT ;0\n");
        ec.incrementaPonteiro(1);
    }
}
