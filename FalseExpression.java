package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class FalseExpression extends BooleanExpression {
	/**
	 * Initializes a FalseExpression
	 * 
	 * @param sourceLocation
	 *  		The column and line of the FalseExpression in its Task
	 */
	public FalseExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	/**
	 * Evaluates the falseExpression
	 * 
	 * @return Returns false
	 */
	@Override
	public Boolean evaluate() {
		return false;
	}
	
	

}