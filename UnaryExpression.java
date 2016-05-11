package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnaryExpression extends BooleanExpression {
	
	public UnaryExpression(BooleanExpression expression, SourceLocation sourceLocation) throws 
			IllegalArgumentException {
		super(sourceLocation);
		this.expression = expression;
		
	}
	
	public BooleanExpression getExpression() {
		return this.expression;
	}
	
	private final BooleanExpression expression;

}
