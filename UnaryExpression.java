package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnaryExpression extends BooleanExpression {
	
	public UnaryExpression(E<?> expression, SourceLocation sourceLocation) throws 
			IllegalArgumentException {
		super(sourceLocation);
		if (!(expression instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.expression = (BooleanExpression) expression;
		
	}
	
	public BooleanExpression getExpression() {
		return this.expression;
	}
	
	private final BooleanExpression expression;

}
