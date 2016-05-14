package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class BooleanExpression extends E<Boolean> implements IExpressionCheck {

	/**
	 * Initialization of a BooleanExpression
	 * 
	 * @param sourceLocation
	 * 		The column and line of the BooleanExpression in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public BooleanExpression(SourceLocation sourceLocation) throws IllegalSourceException {
		super(sourceLocation);
	}
	
}
