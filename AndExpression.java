package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AndExpression extends BinaryExpression {
	
	public AndExpression(BooleanExpression left, BooleanExpression right, SourceLocation source) {
		super(left, right, source);
	}
	/**
	 * 
	 * Evaluates the AndExpression
	 * 
	 * @return Returns true if and only if both expressions are true
	 */ 
	@Override
	public Boolean evaluate() {
		return (this.getRightExpression().evaluate()
				&& this.getLeftExpression().evaluate());
	}

}
