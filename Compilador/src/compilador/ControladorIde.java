package compilador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
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
    public void newFile(JTextArea input, JTextArea output) throws MenuException {
        MenuException e = null;
        if (isEdited(input)) {
            if (getCurrentFile().equals("")) { //arquivo novo nao salvo - salvar como
                e = new MenuException(MenuException.NEW_FILE_NOT_SAVED, input.getText());
            } else { //salvar
                e = new MenuException(MenuException.FILE_NOT_SAVED, getCurrentFile(), input.getText());
            }
        }
        input.setText("");
        output.setText("");
        setCurrentFile("");

        if (e != null) {
            throw e;
        }
    }

    @Override
    public void loadFile(String fileName, JTextArea textArea) throws MenuException {
        validateExtension(fileName);
        validateExists(fileName, true);

        File file = null;
        FileReader fr = null;
        BufferedReader bfr = null;
        StringBuilder content = new StringBuilder();
        try {
            setCurrentFile(fileName);
            file = new File(fileName);
            fr = new FileReader(file);
            bfr = new BufferedReader(fr);
            String line = null;
            while ((line = bfr.readLine()) != null) {
                content.append(line + "\n");
            }
            textArea.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeAllStreams(fr, bfr);
        }

    }

    public void saveFile(String nome_arquivo, String conteudo) throws IOException, MenuException {
        //validateExtension(fileName);
        JFileChooser fileChooser = new JFileChooser();
        Scanner reader = null;
        //filePath = originalPath;
        String originalName = nome_arquivo;
        //String dir = getPath(filePath);
        //fileChooser.setSelectedFile(new File(dir));
        FileNameExtensionFilter extension = new FileNameExtensionFilter("DJT (*.djt)", "djt");
        fileChooser.setFileFilter(extension);
        fileChooser.setSelectedFile(new File(""));// olhar aqui dps

        if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getPath();
            if (!filePath.contains(".djt")) {
                filePath += ".djt";
            }
            File selectedFile = new File(filePath);
            nome_arquivo = selectedFile.getName();

            if (selectedFile.exists()) {
                Object[] options = {"Sim", "Não", "Cancelar"};
                int option = JOptionPane.showOptionDialog(null, nome_arquivo + " já existe, Deseja substituí-lo?", "Confirmar Salvar Como", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (option == 0) {
                    try ( //salvar
                            BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))) {
                        reader = new Scanner(conteudo);
                        while (reader.hasNextLine()) {
                            bw.write(reader.nextLine());
                            bw.newLine();
                        }
                        // jf.setTitle("Compilador - " + fileName);
                    }
                } else { // nao salvar
                    // filePath = originalPath;
                    nome_arquivo = originalName;
                }
            } else {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))) {
                    //Scanner reader;
                    reader = new Scanner(conteudo);
                    while (reader.hasNextLine()) {
                        bw.write(reader.nextLine());
                        bw.newLine();
                    }
                    //jf.setTitle("Compilador - " + fileName);
                }
            }

            /*try {
                    bw = new BufferedWriter(new FileWriter(fileName));
                    reader = new Scanner(content);
                    while (reader.hasNextLine()) {
                        bw.write(reader.nextLine());
                        bw.newLine();
                    }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
	        closeAllStreams(bw, reader);
		}*/
        }
    }

    @Override
    public void compile(String content) {

    }

    @Override
    public void showLog(String log, JTextArea textArea) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean checkFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    @Override
    public boolean isEdited(JTextArea input) {
        if (!getCurrentFile().equals("")) {
            File file = null;
            FileReader fr = null;
            BufferedReader bfr = null;

            try {
                file = new File(getCurrentFile());
                fr = new FileReader(file);
                bfr = new BufferedReader(fr);

                String[] inputSplit = input.getText().split("\n");
                String line = null;
                int countLine = 0;
                while ((line = bfr.readLine()) != null) {
                    if (!inputSplit[countLine].equals(line)) {
                        return true;
                    }
                    countLine++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAllStreams(fr, bfr);
            }
        }
        return false;
    }

    private void validateExtension(String fileName) throws MenuException {
        String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (!extensions.contains(ext)) {
            throw new MenuException(MenuException.FILE_EXTENSION_ERROR);
        }
    }

    private void validateExists(String fileName, boolean exists) throws MenuException {
        if (!(checkFileExists(fileName) == exists)) {
            throw new MenuException(MenuException.FILE_NOT_FOUND);
        }
    }

    private void closeAllStreams(Closeable... cs) {
        for (Closeable c : cs) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getCurrentFile() {
        return this.currentFile;
    }

    private void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public List<String> getExtensions() {
        return extensions;
    }
//ver dps p mudar
    public void verifyEntry(JTextArea entrada, JTextArea saida, String filename) throws IOException, MenuException {
        if (entrada.getText().equals("")) {
            saida.setText("");
        } else {
            Object[] options = {"Sim", "Não", "Cancelar"};
            int option = JOptionPane.showOptionDialog(null, filename + " foi alterado, salvar alterações?", "Deseja salvar as alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
            if (option == 1) { //nao salvar
                entrada.setText("");
                saida.setText("");
            } else if (option == 0) { //salvar
                String entrada_conv = "" + entrada.getText();
                saveFile(filename, entrada_conv);
                entrada.setText("");
                saida.setText("");
            }
        }
    }

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
            System.out.println(nome_arquivo);
            System.out.println(diretorio);
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
}
