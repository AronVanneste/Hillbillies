package hillbillies.model;

public interface ITerminate {
	
	/**
	 * @post
	 * 		The object is terminated
	 */
	public abstract void terminate();
	
	/**
	 * 
	 * @return Returns whether or not an object is terminated
	 */
	public abstract boolean isTerminated();

}