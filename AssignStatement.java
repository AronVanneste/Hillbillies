package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AssignStatement extends S {
	
	public AssignStatement(String name, E<?> e, SourceLocation source) {
		super(source);
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
	
	public E<?> getExpression() {
		return this.expression;
	}
	
	private final String name;
	private final E<?> expression;

}
