package hillbillies.model;

public class IllegalExpressionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalExpressionException(String message) { 
		super(message);
	}
	
	public IllegalExpressionException() { 
		super();
	}

}
