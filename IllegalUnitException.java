package hillbillies.model;

public class IllegalUnitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalUnitException(String message) { 
		super(message);
	}
	
	public IllegalUnitException() { 
		super();
	}

}
