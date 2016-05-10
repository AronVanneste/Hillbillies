package hillbillies.model;

public class IllegalStatementException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalStatementException(String message) { 
		super(message);
	}
	
	public IllegalStatementException() { 
		super();
	}

}
