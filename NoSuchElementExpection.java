package hillbillies.model;

public class NoSuchElementExpection extends RuntimeException {

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
