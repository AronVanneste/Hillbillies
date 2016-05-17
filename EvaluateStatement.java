package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class EvaluateStatement extends S {

	/**
	 * Initializes an EvaluateStatement
	 * 
	 * @param source
	 *  		The column and line of the EvauateStatement in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public EvaluateStatement(SourceLocation source) throws IllegalSourceException {
		super(source);
	}
	


}