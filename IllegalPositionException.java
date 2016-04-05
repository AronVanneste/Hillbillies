package hillbillies.model;

public class IllegalPositionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalPositionException(String message) { 
		super(message);
	}
	
	public IllegalPositionException() { 
		super();
	}

}
