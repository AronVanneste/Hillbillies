package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class ActionStatement extends S implements IPerform {
	
	/**
	 * Initialisation of Actionstatement
	 * 
	 * @param source
	 * 		The line and column of the ActionStatement in the task
	 */
	public ActionStatement(SourceLocation source) {
		super(source);
	}


	

}
