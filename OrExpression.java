package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class OrExpression extends BinaryExpression {
	/**
	 * Initialization of an OrExpression
	 * 
	 * @param left
	 * 		The left Expression
	 * @param right
	 * 		The right Expression
	 * @param source
	 *  	The column and line of the OrExpression in its Task
	 */
	public OrExpression(BooleanExpression left, BooleanExpression right, SourceLocation source) {
		super(left, right, source);
	}
	/**
	 * Evaluates the OrExpression
	 * 
	 * @return Returns whether or not the left or right expression evaluate to true
	 */
	@Override
	public Boolean evaluate() {
		return (this.getRightExpression().evaluate() || 
				this.getLeftExpression().evaluate());
	}

}