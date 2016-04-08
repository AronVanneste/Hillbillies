package hillbillies.model;

public class IllegalTargetException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IllegalTargetException(String message) { 
		super(message);
	}
	
	public IllegalTargetException() { 
		super();
	}

}
