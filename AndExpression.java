package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AndExpression extends BinaryExpression {

	public AndExpression(E<?> left, E<?> right, SourceLocation source) {
		super(left, right, source);
	}
	
	public AndExpression(BooleanExpression left, BooleanExpression right, SourceLocation source) {
		super(left, right, source);
	}

	@Override
	public Boolean evaluate() {
		return (this.getRightExpression().evaluate()
				&& this.getLeftExpression().evaluate());
	}

}
