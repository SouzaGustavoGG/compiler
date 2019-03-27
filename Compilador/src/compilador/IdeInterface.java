package compilador;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

import javax.swing.JTextArea;

public interface IdeInterface {

	public String novoArquivo(JTextArea entrada, JTextArea saida, JFrame tela, String nome_arquivo, String diretorio) throws MenuException;
	public ArrayList<String> salvar(String nome_arquivo, String diretorio, JTextArea entrada,JFrame tela); 
	public ArrayList<String> salvarComo( JTextArea entrada, String nome_arquivo, String diretorio, JFrame tela) throws IOException, MenuException;
	public boolean arquivoAlterado(JTextArea entrada, String nome_arquivo);
        public ArrayList<String> abrir(JTextArea entrada, JTextArea saida, String nome_arquivo, String diretorio, JFrame tela);
        public void sair(JTextArea entrada, String nome_arquivo, String diretorio, JFrame tela);
	
}
