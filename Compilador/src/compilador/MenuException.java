package compilador;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gustavo
 *
 */
public class MenuException extends Exception{
	

	private static final long serialVersionUID = 1L;
	
	public static final String FILE_ALREADY_EXISTS = "Arquivo já existe.";
	public static final String FILE_NOT_FOUND = "Arquivo não encontrado.";
	public static final String FILE_NOT_SAVED = "Arquivo editado não salvo.";
	public static final String NEW_FILE_NOT_SAVED = "Novo arquivo editado não salvo.";
	public static final String FILE_EXTENSION_ERROR = "Extensão do arquivo desconhecida. Extensões permitidas: [.TXT, .DJT, .CMP]";
	
	private List<String> optional;
	
	public MenuException(String message) {
		super(message);
	}
	
	public MenuException(String message, String ... optional) {
		super(message);
		
		this.optional = new ArrayList<>();
		for(String o : optional) {
			this.optional.add(o);
		}
	}

	public List<String>  getOptional() {
		return optional;
	}

}
