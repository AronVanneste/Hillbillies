package hillbillies.model;

public class NoStatementsLeftException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoStatementsLeftException(String message) { 
		super(message);
	}
	
	public NoStatementsLeftException() { 
		super();
	}

	

}
