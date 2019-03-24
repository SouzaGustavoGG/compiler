package compilador;

import java.io.IOException;

import javax.swing.JTextArea;

public interface IdeInterface {

	public void newFile(JTextArea input, JTextArea output) throws MenuException;
	public void loadFile(String fileName, JTextArea textArea) throws MenuException; 
	public void saveFile(String fileName, String content) throws IOException, MenuException;
	public void compile(String content);
	public void showLog(String log, JTextArea textArea);
	public boolean checkFileExists(String fileName);
	public boolean isEdited(JTextArea input);
	
}
