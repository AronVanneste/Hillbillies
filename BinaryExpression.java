package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class BinaryExpression extends BooleanExpression {
	
	public BinaryExpression(BooleanExpression left, BooleanExpression right, 
			SourceLocation sourceLocation) {
		super(sourceLocation);
		this.left = left;
		this.right = right;
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
