package hillbillies.model;

public class BreakException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public BreakException(String message) { 
		super(message);
	}
		
	public BreakException() { 
		super();
	}

	

}
