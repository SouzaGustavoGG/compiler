package compilador;

import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.JTextArea;

/**
 * @author gustavo
 *
 */
public class ControladorIde implements IdeInterface{
	
	private final List<String> extensions = new ArrayList<>(Arrays.asList(".TXT",".DJT",".CMP")); 
	private String currentFile = "";
	
	public ControladorIde() {

	}

        public void verifyEntry(JTextArea entrada, JTextArea saida, String filename) throws IOException, MenuException{
            if (entrada.getText().equals("")) {
                saida.setText("");
            } else {
                Object[] options = {"Sim", "Não", "Cancelar"};
                int option = JOptionPane.showOptionDialog(null, filename + " foi alterado, salvar alterações?", "Deseja salvar as alterações?", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[2]);
                if (option == 1) { //nao salvar
                    entrada.setText("");
                    saida.setText("");
                } else if (option == 0){ //salvar
                    String entrada_conv = ""+entrada.getText();
                    saveFile(filename,entrada_conv);
                    entrada.setText("");
                    saida.setText("");
                }
            }
        }
        
	@Override
	public void newFile(JTextArea input, JTextArea output) throws MenuException {
		if(isEdited(input)) {
			if(getCurrentFile().equals("")) { //arquivo novo nao salvo - salvar como
				throw new MenuException(MenuException.NEW_FILE_NOT_SAVED, input.getText());
			} else { //salvar
				throw new MenuException(MenuException.FILE_NOT_SAVED, getCurrentFile(), input.getText());
			}
		} 
		input.setText("");
		output.setText("");
		setCurrentFile("");
                
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
			while((line = bfr.readLine()) != null) {
				content.append(line + "\n");
			}
			textArea.setText(content.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeAllStreams(fr, bfr);
		}
		
		
	}

	public void saveFile(String fileName, String content) throws IOException, MenuException {
		//validateExtension(fileName);
		JFileChooser fileChooser = new JFileChooser();
                Scanner reader = null;
                //filePath = originalPath;
                String originalName = fileName;
                //String dir = getPath(filePath);
                //fileChooser.setSelectedFile(new File(dir));
                fileChooser.setSelectedFile(new File(""));// olhar aqui dps
                
                if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
		
                File selectedFile = new File(filePath);
                fileName = selectedFile.getName();
                
                if (selectedFile.exists()) {
                Object[] options = {"Sim", "Não", "Cancelar"};
                    int option = JOptionPane.showOptionDialog(null, fileName + " já existe, Deseja substituí-lo?", "Confirmar Salvar Como", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                    if (option == 0) { try ( //salvar
                            BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))
                            ) {
                            reader = new Scanner(content);
                        while (reader.hasNextLine()) {
                            bw.write(reader.nextLine());
                            bw.newLine();
                        }
                        // jf.setTitle("Compilador - " + fileName);
                    }
                    } else { // nao salvar
                       // filePath = originalPath;
                        fileName = originalName;
                    }
                } else {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))) {
                        //Scanner reader;
                        reader = new Scanner(content);
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
		// TODO Auto-generated method stub
		
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
		if(!getCurrentFile().equals("")) {
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
				while((line = bfr.readLine()) != null) {				
					if(!inputSplit[countLine].equals(line)) {
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
		if(!extensions.contains(ext)) {
			throw new MenuException(MenuException.FILE_EXTENSION_ERROR);
		}
	}
	
	private void validateExists(String fileName, boolean exists) throws MenuException {
		if(!(checkFileExists(fileName) == exists)) {
			throw new MenuException(MenuException.FILE_NOT_FOUND);
		}
	}
	
	private void closeAllStreams(Closeable ... cs) {
		for(Closeable c : cs ) {
			if(c != null) {
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

}
