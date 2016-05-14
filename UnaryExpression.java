package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnaryExpression extends BooleanExpression {
	/**
	 * Initialization of a UnaryExpression
	 * 
	 * @param expression
	 * 		The expression in the UnaryExpression
	 * @param sourceLocation
	 *		The column and line of the TerrainExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws ExpressionException if the expression is not valid
	 */
	public UnaryExpression(BooleanExpression expression, SourceLocation sourceLocation) throws 
			IllegalSourceException, IllegalExpressionException {
		super(sourceLocation);
		if (!isValidExpression(expression))
			throw new IllegalExpressionException();
		this.expression = expression;
		
	}
	
	/**
	 * 
	 * @return Returns the expression of the UnaryExpression
	 */
	public BooleanExpression getExpression() {
		return this.expression;
	}
	
	private final BooleanExpression expression;

}
