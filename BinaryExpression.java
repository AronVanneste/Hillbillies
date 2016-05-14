package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class BinaryExpression extends BooleanExpression {
	/**
	 * Initialization of a BinaryExpression
	 * 
	 * @param left
	 * 		The left expression
	 * 
	 * @param right
	 * 		The right expression
	 * @param sourceLocation
	 * 		The column and line of the BinaryExpression in its Task
	 */
	public BinaryExpression(BooleanExpression left, BooleanExpression right, 
			SourceLocation sourceLocation) throws IllegalSourceException, 
			IllegalExpressionException {
		super(sourceLocation);
		if (!isValidExpression(left) | !isValidExpression(right))
			throw new IllegalExpressionException();
		this.left = left;
		this.right = right;
	}
	/**
	 * 
	 * @return returns the leftExpression
	 */
	public BooleanExpression getLeftExpression() {
		return this.left;
	}
	/**
	 * 
	 * @return Returns the rightExpression
	 */
	
	public BooleanExpression getRightExpression() {
		return this.right;
	}
	
	private final BooleanExpression left;
	private final BooleanExpression right;

}