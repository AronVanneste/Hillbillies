package hillbillies.model;

public class AssignStatement extends S {
	
	public AssignStatement(String name, E<?> e) {
		this.name = name;
		this.expression = e;
	}

	@Override
	public void execute() {
		this.getExpression().evaluate();
	}
		
	public String getName() {
		return this.name;
	}
	
	private E<?> getExpression() {
		return this.expression;
	}
	
	private final String name;
	private final E<?> expression;

}
