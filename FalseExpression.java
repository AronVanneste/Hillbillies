package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class FalseExpression extends BooleanExpression {

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
