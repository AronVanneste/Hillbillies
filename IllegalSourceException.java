package hillbillies.model;

public class IllegalSourceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalSourceException(String message) { 
		super(message);
	}
	
	public IllegalSourceException() { 
		super();
	}

}
