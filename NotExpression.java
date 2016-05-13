package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class NotExpression extends UnaryExpression {

	public NotExpression(BooleanExpression expression, SourceLocation source) {
		super(expression, source);
	}
	/**
	 * Evaluates the notExpression
	 * 
	 * @return Returs the opposite of the expression
	 */
	@Override
	public Boolean evaluate() {
		return (!this.getExpression().evaluate());
	}
	
	

}
