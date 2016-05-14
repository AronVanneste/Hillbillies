package hillbillies.model;

public class NoSuchElementExcection extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchElementExpection(String message) { 
		super(message);
	}
	
	public NoSuchElementExpection() { 
		super();
	}

}
