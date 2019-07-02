package compilador.AnaliseSemantica;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class EstruturaControle {
    private String contexto;
    private int vt;
    private int vp;
    private int vit;
    private int tipo;
    private int ponteiro;
    private boolean variavelIndexada;
    private Vector<Integer> pilhaDeDesvio;
    private ArrayList<Integer> listaAtributos;
    private String armazenamentoVariavel;
    private int armazenaValorInteiro;
    
    public EstruturaControle(){
        inicializaEstruturaControle();
    }
    
    public void inicializaEstruturaControle(){
        contexto = "";
        armazenamentoVariavel = "";
        vt = 0;
        vp = 0;
        vit = 0;
        tipo = -1;
        ponteiro = 1;
        variavelIndexada = false;
        armazenaValorInteiro = 0;
        pilhaDeDesvio = new Stack<Integer>();
        listaAtributos = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getListaAtributos() {
        return listaAtributos;
    }

    public void setListaAtributos(ArrayList<Integer> listaAtributos) {
        this.listaAtributos = listaAtributos;
    }
    
    public int getArmazenaValorInteiro() {
        return armazenaValorInteiro;
    }

    public void setArmazenaValorInteiro(int armazenaValorInteiro) {
        this.armazenaValorInteiro = armazenaValorInteiro;
    }

    public String getArmazenamentoVariavel() {
        return armazenamentoVariavel;
    }

    public void setArmazenamentoVariavel(String armazenamentoVariavel) {
        this.armazenamentoVariavel = armazenamentoVariavel;
    }

    public void incrementaVT(int valor){
        vt += valor;
    }
    
    public void decrementaVT(int valor){
        vt -= valor;
    }
    
    public void incrementaVP(int valor){
        vp += valor;
    }
    
    public void decrementaVP(int valor){
        vp -= valor;
    }
    
    public void incrementaVIT(int valor){
        vit += valor;
    }
    
    public void decrementaVIT(int valor){
        vit -= valor;
    }
    
    public void incrementaPonteiro(int valor){
        ponteiro += valor;
    }
    
    public void decrementaPonteiro(int valor){
        ponteiro -= valor;
    }
    
    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Vector<Integer> getPilhaDeDesvio() {
        return pilhaDeDesvio;
    }

    public void setPilhaDeDesvio(Vector<Integer> pilhaDeDesvio) {
        this.pilhaDeDesvio = pilhaDeDesvio;
    }

    public int getPonteiro() {
        return ponteiro;
    }

    public void setPonteiro(int ponteiro) {
        this.ponteiro = ponteiro;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean isVariavelIndexada() {
        return variavelIndexada;
    }

    public void setVariavelIndexada(boolean variavelIndexada) {
        this.variavelIndexada = variavelIndexada;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getVp() {
        return vp;
    }

    public void setVp(int vp) {
        this.vp = vp;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }
}
