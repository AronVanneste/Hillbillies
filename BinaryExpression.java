package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class BinaryExpression extends BooleanExpression {
	
	public BinaryExpression(E<?> left, E<?> right, SourceLocation sourceLocation) throws 
			IllegalArgumentException {
		super(sourceLocation);
		if (!(left instanceof BooleanExpression && right instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.left = (BooleanExpression) left;
		this.right = (BooleanExpression) right;
	}
	
	public BinaryExpression(BooleanExpression left, BooleanExpression right, 
			SourceLocation sourceLocation) {
		super(sourceLocation);
		this.left = (BooleanExpression) left;
		this.right = (BooleanExpression) right;
	}
	
	public BooleanExpression getLeftExpression() {
		return this.left;
	}
	
	public BooleanExpression getRightExpression() {
		return this.right;
	}
	
	private final BooleanExpression left;
	private final BooleanExpression right;

}
