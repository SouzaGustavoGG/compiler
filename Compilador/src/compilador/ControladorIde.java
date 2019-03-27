package compilador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author gustavo
 *
 */
public class ControladorIde implements IdeInterface {

    private final List<String> extensions = new ArrayList<>(Arrays.asList(".TXT", ".DJT", ".CMP"));
    private String currentFile = "";

    public ControladorIde() {

    }

    @Override
    public String novoArquivo(JTextArea entrada, JTextArea saida, JFrame tela, String nome_arquivo, String diretorio) {
        if (nome_arquivo.equals("novo.djt")) {
            if ("".equals(entrada.getText())) {
                nome_arquivo = "novo.djt";
                tela.setTitle("Compilador - " + nome_arquivo);
                saida.setText("");
            } else {
                Object[] alternativas = {"Sim", "Não", "Cancelar"};
                int opcao = JOptionPane.showOptionDialog(null, nome_arquivo + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, alternativas, alternativas[2]);
                if (opcao == 0) //salvar
                {
                    salvar(nome_arquivo, diretorio, entrada, tela);
                }
            }
        } else {
                if (arquivoAlterado(entrada, diretorio)) {
                    Object[] alternativas = {"Sim", "Não", "Cancelar"};
                    int opcao = JOptionPane.showOptionDialog(null, nome_arquivo + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, alternativas, alternativas[2]);
                    if (opcao == 0) {//salvar
                        salvar(nome_arquivo, diretorio, entrada, tela);
                        diretorio = ("");
                    }else if (opcao == 1){
			diretorio = ("");
                    }
		}else{
                    diretorio = ("");
		}
        }
        entrada.setText("");
        saida.setText("");
	nome_arquivo = "novo.djt";
        tela.setTitle("Compilador - "+ nome_arquivo);
                
        return nome_arquivo;
    }
    
    @Override
    public boolean arquivoAlterado(JTextArea entrada, String nome_arquivo) {
        try {
            boolean isModificado = false;
            //Inicializações
            FileReader arquivo = new FileReader(nome_arquivo);
            BufferedReader leArquivo = new BufferedReader(arquivo);
            Scanner leitor = new Scanner(entrada.getText());
            ArrayList<String> entradaLinhas = new ArrayList<>();
            ArrayList<String> arquivoOriginalLinhas = new ArrayList<>();
            preencheArrays(arquivoOriginalLinhas, entradaLinhas, arquivo, leArquivo, leitor);
            if (tamanhoDiferente(arquivoOriginalLinhas, entradaLinhas, isModificado) || conteudoDiferente(arquivoOriginalLinhas, entradaLinhas, isModificado)) {
                isModificado = true;
		return isModificado;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControladorIde.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControladorIde.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
    private boolean conteudoDiferente(ArrayList<String> original, ArrayList<String> atual, boolean modificado) {
        for (int i = 0; i < original.size(); i++) {
            if (!original.get(i).equals(atual.get(i))) {
                modificado = true;
                return modificado;
            }
        }
        modificado = false;
        return modificado;
    }

    private boolean tamanhoDiferente(ArrayList<String> original, ArrayList<String> atual, boolean modificado) {
        if (original.size() != atual.size()) {
            modificado = true;
            return modificado;
        }
        modificado = false;
        return modificado;
    }

    private void preencheArrays(ArrayList<String> original, ArrayList<String> atual, FileReader arquivo, BufferedReader leArquivo, Scanner leitor) throws IOException {
        String linha;
        linha  = leArquivo.readLine();
        original.add (linha);
        while (linha!= null) {
            linha = leArquivo.readLine();
            if (linha != null) {
                original.add(linha);
            }
        }
        arquivo.close ();
        while (leitor.hasNextLine () ) {
                atual.add(leitor.nextLine());
        }
    }
    
    @Override
    public ArrayList<String> salvar(String nome_arquivo, String diretorio, JTextArea entrada,JFrame tela){
        String caminho;
        ArrayList<String> salvar = new ArrayList<>();
	caminho = retornaCaminho(diretorio);
        try {
            if (nome_arquivo.equalsIgnoreCase("novo.djt")) {
		
		ArrayList<String> salvar_como = salvarComo(entrada, nome_arquivo, diretorio, tela);
                nome_arquivo = salvar_como.get(0);
                diretorio = salvar_como.get(1);
		salvar.add(nome_arquivo);
		salvar.add(diretorio);
            } else {
                BufferedWriter buff_escrita = new BufferedWriter(new FileWriter(caminho));
                Scanner leitor = new Scanner(entrada.getText());
                while (leitor.hasNextLine()) {
                    buff_escrita.write(leitor.nextLine());
                    buff_escrita.newLine();
                }
                buff_escrita.close();
                tela.setTitle("Compilador - " + nome_arquivo);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorIde.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salvar;
    }
    
    	private String retornaCaminho(String diretorio) {
        File extensao;
        extensao = new File(".");
        String caminho;
        caminho = "";
        if (diretorio.equals("")) {
            caminho = extensao.getAbsolutePath();
        } else {
            extensao = new File(diretorio);
            caminho = extensao.getAbsolutePath();
        }
        return caminho;
    }
        
    public ArrayList<String> salvarComo( JTextArea entrada, String nome_arquivo, String diretorio, JFrame tela){
        ArrayList<String> salvar_como = new ArrayList<>();
        String caminho;
	String caminho_original;
        String nome_original;
	File arquivo_selecionado;
        JFileChooser fileChooser = new JFileChooser();
  	caminho_original = diretorio;
        nome_original = nome_arquivo;
        FileNameExtensionFilter extensao = new FileNameExtensionFilter("DJT (*.djt)", "djt");
	caminho = retornaCaminho(diretorio);
        fileChooser.setSelectedFile(new File(caminho));
        fileChooser.setFileFilter(extensao);
        fileChooser.setSelectedFile(new File(diretorio));

        try {
            if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                diretorio = file.getPath();
                if (!diretorio.contains(".djt")) {
                    diretorio += ".djt";
                }

                arquivo_selecionado = new File(diretorio);
                nome_arquivo = arquivo_selecionado.getName();
		salvar_como.add(nome_arquivo);
		salvar_como.add(diretorio);

                if (arquivo_selecionado.exists()) {
                    Object[] alternativas = {"Sim", "Não", "Cancelar"};
                    int opcao = JOptionPane.showOptionDialog(null, nome_arquivo + " já existe, Deseja substituí-lo?", "Confirmar Salvar Como", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, alternativas, alternativas[0]);
                    if (opcao == 0) {
                        substitui(tela, nome_arquivo, entrada, arquivo_selecionado);
                    } else {
                        diretorio = caminho_original;
                        nome_arquivo = nome_original;
			salvar_como.add(nome_arquivo);
			salvar_como.add(diretorio);
                    }
                } else {
                    substitui(tela, nome_arquivo, entrada, arquivo_selecionado);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorIde.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salvar_como;
    }
    
    private void substitui(JFrame tela, String nome_arquivo, JTextArea entrada, File arquivo_selecionado) throws IOException{
	BufferedWriter buff_escrita = new BufferedWriter(new FileWriter(arquivo_selecionado));
        Scanner leitor = new Scanner(entrada.getText());
        while (leitor.hasNextLine()) {
            buff_escrita.write(leitor.nextLine());
            buff_escrita.newLine();
        }
        buff_escrita.close();
        tela.setTitle("Compilador - " + nome_arquivo);
    }
    
    public void exit(JTextArea entrada, String nome_arquivo, String diretorio, JFrame tela) {
        boolean isArquivo;
        if (nome_arquivo.equals("novo.djt")) {
            isArquivo = false;
            if ("".equals(entrada.getText())) {
                System.exit(0);
            } else {
                salvaAlteracoes(nome_arquivo, isArquivo, diretorio, tela, entrada);
            }
        } else {
            isArquivo = true;
            if (arquivoAlterado(entrada, nome_arquivo)) {
                salvaAlteracoes(nome_arquivo, isArquivo, diretorio, tela, entrada);
            } else {
                System.exit(0);
            }
        }
    }
	
	
	
    private void salvaAlteracoes(String nome_arquivo, boolean isArquivo, String diretorio, JFrame tela, JTextArea entrada){
	Object[] alternativas = {"Sim", "Não", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(null, nome_arquivo + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, alternativas, alternativas[2]);
		if(opcao==0){
			if(isArquivo){//se o arquivo foi modificado
				salvar(nome_arquivo, diretorio, entrada, tela);
			}else{
				salvarComo(entrada, nome_arquivo, diretorio, tela);
			}
                        System.exit(0);
		} else if (opcao == 1) //nao salvar, apenas sair
                {
			System.exit(0);
                } 
    }
}
