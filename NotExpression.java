package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class NotExpression extends UnaryExpression {
	/**
	 * Initialization of a NotExpression
	 * 
	 * @param expression
	 * 		The expression of NotExpression
	 * @param source
	 *  	The column and line of the NotExpression in its Task
	 */
	public NotExpression(BooleanExpression expression, SourceLocation source) {
		super(expression, source);
	}
	/**
	 * Evaluates the notExpression
	 * 
	 * @return Returns the opposite of the evaluation of the expression
	 */
	@Override
	public Boolean evaluate() {
		return (!this.getExpression().evaluate());
	}
	
	

}
