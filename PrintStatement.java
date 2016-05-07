package hillbillies.model;

public class PrintStatement extends S {
	
	public PrintStatement(E<?> expression) {
		this.expression = expression;
	}

	@Override
	public void execute() {
		System.out.println(this.getExpression().evaluate());
	}
	
	public E<?> getExpression() {
		return this.expression;
	}
	
	
	
	private final E<?> expression;

}
