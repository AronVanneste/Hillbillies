package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class ActionStatement extends S implements IPerform {
	
	/**
	 * Initialisation of Actionstatement
	 * 
	 * @param source
	 * 		The line and column of the ActionStatement in the task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public ActionStatement(SourceLocation source) throws IllegalSourceException {
		super(source);
	}


	

}