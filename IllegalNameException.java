package hillbillies.model;

public class IllegalNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalNameException(String message) { 
		super(message);
	}
	
	public IllegalNameException() { 
		super();
	}

}
