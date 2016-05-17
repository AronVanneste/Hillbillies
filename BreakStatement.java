package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class BreakStatement extends EvaluateStatement {
	
	/**
	 * Initialisation of a BreakStatement
	 * 
	 * @param source
	 * 		The column and line of the BreakStatement in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public BreakStatement(SourceLocation source) throws IllegalSourceException {
		super(source);
	}

	/**
	 * Executes the BreakStatement
	 * @post
	 * 		A breakException is thrown 
	 * @throws BreakException
	 * 		Always throws a BreakException
	 */
	public void execute() throws BreakException {
		throw new BreakException();

		
	}


}