package semantics;

public class UndefinedIdException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -645517798994565101L;
	public String invalidId;
	
	public UndefinedIdException(String invalidString) {
		this.invalidId = invalidString;
	}
}
