package hillbillies.model;

public interface IExpressionCheck {
	
	public default boolean isValidExpression(E<?> expression) {
		return (expression != null);
	}

}
