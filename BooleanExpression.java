package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class BooleanExpression extends E<Boolean> {

	/**
	 * Initialization of a BooleanExpression
	 * 
	 * @param sourceLocation
	 * 		The column and line of the BooleanExpression in its Task
	 * 		
	 */
	public BooleanExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
}
