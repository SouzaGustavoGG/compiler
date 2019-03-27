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
import javax.swing.filechooser.FileFilter;
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
                zeraDados(entrada, saida, nome_arquivo, tela);
            } else {
                int opcao = opcaoAlteracoes(nome_arquivo);
                if (opcao == 0) 
                {
                    salvar(nome_arquivo, diretorio, entrada, tela);
                    zeraDados(entrada, saida, nome_arquivo, tela);
                }else if (opcao == 1){
                    zeraDados(entrada, saida, nome_arquivo, tela);
                }
            }
        } else {
                if (arquivoAlterado(entrada, diretorio)) {
                    int opcao = opcaoAlteracoes(nome_arquivo);
                    if (opcao == 0) {
                        salvar(nome_arquivo, diretorio, entrada, tela);
                        zeraDados(entrada, saida, nome_arquivo, tela);
                        diretorio = ("");
                    }else if (opcao == 1){
                        zeraDados(entrada, saida, nome_arquivo, tela);
			diretorio = ("");
                    }
		}else{
                    zeraDados(entrada, saida, nome_arquivo, tela);
                    diretorio = ("");
		}
        }
        
        return nome_arquivo;
    }
    
    public void zeraDados(JTextArea entrada, JTextArea saida, String nome_arquivo, JFrame tela){  
        entrada.setText("");
        saida.setText("");
	nome_arquivo = "novo.djt";
        tela.setTitle("Compilador - "+ nome_arquivo);
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
                    Object[] alternativas= {"Sim", "Não", "Cancelar"};
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
    
    public void sair(JTextArea entrada, String nome_arquivo, String diretorio, JFrame tela) {
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
            if (arquivoAlterado(entrada, nome_arquivo)==true) {
                salvaAlteracoes(nome_arquivo, isArquivo, diretorio, tela, entrada);
            } else {
                System.exit(0);
            }
        }
    }
	
    private void salvaAlteracoes(String nome_arquivo, boolean isArquivo, String diretorio, JFrame tela, JTextArea entrada){
	        int opcao = opcaoAlteracoes(nome_arquivo);
                if(opcao==0){
			if(isArquivo == true){//se o arquivo foi modificado
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
    
    private ArrayList<String> abrirArquivoValido(JFileChooser fileChooser, ArrayList<String> obj_aberto, String caminho, String diretorio, JFileChooser file_chooser, String novo_arquivo, JTextArea entrada, JTextArea saida, JFrame tela){
        if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile() != null) {
                try {
                    obj_aberto = abreSelecionado(caminho, diretorio, fileChooser, novo_arquivo,entrada, saida, tela);
                    return obj_aberto;
                } catch (IOException ex) {
                    Logger.getLogger(ControladorIde.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return obj_aberto;
    }
    
    private ArrayList<String> abreSelecionado(String caminho, String diretorio, JFileChooser file_chooser, String novo_arquivo, JTextArea entrada, JTextArea saida, JFrame tela ) throws IOException{
	ArrayList<String> obj_aberto = new ArrayList<>();
	File arquivo_selecionado;
	caminho = file_chooser.getSelectedFile().getAbsolutePath();
        diretorio = file_chooser.getSelectedFile().toString();
        arquivo_selecionado = new File(diretorio);
        novo_arquivo = arquivo_selecionado.getName();
        setEntrada(arquivo_selecionado.toString(), entrada);
        saida.setText("");
        tela.setTitle("Compilador - " + novo_arquivo);
	obj_aberto.add(novo_arquivo);
	obj_aberto.add(caminho);
	return obj_aberto;
    }
    
    private void setEntrada(String nome_arquivo, JTextArea entrada) throws IOException {
        BufferedReader buff_Leitor = new BufferedReader(new FileReader(nome_arquivo));
        try {
            StringBuilder string_builder = new StringBuilder();
            String linha = buff_Leitor.readLine();

            while (linha != null) {
                string_builder.append(linha);
                string_builder.append("\n");
                linha = buff_Leitor.readLine();
            }
            entrada.setText(string_builder.toString());
        } finally {
            buff_Leitor.close();
        }
    }
    
    private int opcaoAlteracoes(String nome_arquivo){
	Object[] alternativas = {"Sim", "Não", "Cancelar"};
                int opcao;
                opcao = JOptionPane.showOptionDialog(null, nome_arquivo + " foi alterado, salvar alterações?", "Salvar Alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, alternativas, alternativas[2]);
		
		return opcao;
    }
        
    public ArrayList<String> abrir(JTextArea entrada, JTextArea saida, String nome_arquivo, String diretorio, JFrame tela) {
        ArrayList<String> obj_aberto = new ArrayList<>();
	String novo_arquivo = nome_arquivo;
        String caminho = "";	
        JFileChooser file_chooser = new JFileChooser();
        FileNameExtensionFilter djt = new FileNameExtensionFilter("DJT (*.djt)", "djt");
        FileFilter filtro = file_chooser.getFileFilter();
        caminho = retornaCaminho(diretorio);
        file_chooser.setSelectedFile(new File(caminho));
        file_chooser.setFileFilter(djt);
        if (nome_arquivo.equals("novo.djt")) {
            if ("".equals(entrada.getText())) {
                obj_aberto = abrirArquivoValido(file_chooser, obj_aberto, caminho, diretorio, file_chooser, novo_arquivo, entrada, saida, tela);
            } else {
                int opcao = opcaoAlteracoes(nome_arquivo);
                if (opcao == 1) //nao salvar
                {
                    obj_aberto = abrirArquivoValido(file_chooser, obj_aberto, caminho, diretorio, file_chooser, novo_arquivo, entrada, saida, tela);
                } else if (opcao == 0){ //nao salvar
                    salvar(caminho + nome_arquivo, diretorio, entrada, tela);
                    obj_aberto = abrirArquivoValido(file_chooser, obj_aberto, caminho, diretorio, file_chooser, novo_arquivo, entrada, saida, tela);
                }
            }
        } else {
            if (arquivoAlterado(entrada, caminho)) {
                int opcao = opcaoAlteracoes(nome_arquivo);
                if (opcao == 0){ //salvar
                    salvar(caminho, diretorio, entrada, tela);
                    obj_aberto = abrirArquivoValido(file_chooser, obj_aberto, caminho, diretorio, file_chooser, novo_arquivo, entrada, saida, tela);
                } else if (opcao == 1){ //não salvar
                   obj_aberto =  abrirArquivoValido(file_chooser, obj_aberto, caminho, diretorio, file_chooser, novo_arquivo, entrada, saida, tela);
                }
            } else {
               obj_aberto =  abrirArquivoValido(file_chooser, obj_aberto, caminho, diretorio, file_chooser, novo_arquivo, entrada, saida, tela);
            }
        }
        return obj_aberto;
    }
	
}
