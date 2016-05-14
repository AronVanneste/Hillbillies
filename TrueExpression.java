package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class TrueExpression extends BooleanExpression {
	
	/**
	 * 
	 * @param sourceLocation
	 *		The column and line of the TrueExpression in its Task
	 */
	public TrueExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	/**
	 * Evaluates the trueExpression
	 * 
	 * @return Returns true
	 */
	@Override
	public Boolean evaluate() {
		return true;
	}

}
