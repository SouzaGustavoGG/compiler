package compilador;

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

	@Override
	public void newFile(JTextArea input, JTextArea output) throws MenuException {
		MenuException e = null;
		if(isEdited(input)) {
			if(getCurrentFile().equals("")) { //arquivo novo nao salvo - salvar como
				e = new MenuException(MenuException.NEW_FILE_NOT_SAVED, input.getText());
			} else { //salvar
				e = new MenuException(MenuException.FILE_NOT_SAVED, getCurrentFile(), input.getText());
			}
		} 
		input.setText("");
		output.setText("");
		setCurrentFile("");
		
		if ( e != null ) {
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

	@Override
	public void saveFile(String fileName, String content) throws IOException, MenuException {
		validateExtension(fileName);
		
        BufferedWriter bw = null;
        Scanner reader = null;
		try {
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
