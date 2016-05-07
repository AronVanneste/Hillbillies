package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class OrExpression extends BinaryExpression {

	public OrExpression(E<?> left, E<?> right, SourceLocation source) {
		super(left, right, source);
	}

	@Override
	public Boolean evaluate() {
		return (this.getRightExpression().evaluate() || 
				this.getLeftExpression().evaluate());
	}

}
