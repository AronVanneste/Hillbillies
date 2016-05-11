package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class OrExpression extends BinaryExpression {

	public OrExpression(BooleanExpression left, BooleanExpression right, SourceLocation source) {
		super(left, right, source);
	}

	@Override
	public Boolean evaluate() {
		return (this.getRightExpression().evaluate() || 
				this.getLeftExpression().evaluate());
	}

}
