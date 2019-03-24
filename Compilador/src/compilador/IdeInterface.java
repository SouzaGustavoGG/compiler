package compilador;

public interface IdeInterface {

	public void newFile(String fileName);
	public void loadFile(String fileName);
	public void saveFile(String fileName, String content);
	public void compile(String content);
	public void showLog(String log);
	
}
