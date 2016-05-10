package hillbillies.model;

public class IllegalSchedulerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalSchedulerException(String message) { 
		super(message);
	}
	
	public IllegalSchedulerException() { 
		super();
	}

}