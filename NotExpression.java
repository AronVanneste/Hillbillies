package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class NotExpression extends UnaryExpression {

	public NotExpression(E<?> expression, SourceLocation source) {
		super(expression, source);
	}

	@Override
	public Boolean evaluate() {
		return (!this.getExpression().evaluate());
	}
	
	

}
