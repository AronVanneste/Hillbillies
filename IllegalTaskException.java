package hillbillies.model;

public class IllegalTaskException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalTaskException(String message) { 
		super(message);
	}
	
	public IllegalTaskException() { 
		super();
	}

}
