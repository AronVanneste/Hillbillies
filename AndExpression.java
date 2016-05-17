package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AndExpression extends BinaryExpression {
	
	/**
	 * Initialistaion of AndExpression
	 * 
	 * @param left
	 * 		The left BooleanExpression
	 * @param right
	 * 		The right BooleanExpression
	 * @param source
	 * 		The column and line of the AndExpression in the task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalExpressionException if the expression is not valid
	 */
	public AndExpression(BooleanExpression left, BooleanExpression right, SourceLocation source) 
			throws IllegalSourceException, IllegalExpressionException {
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