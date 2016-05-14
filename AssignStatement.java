package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AssignStatement extends S {
	/**
	 * Initialisation of an AssignStatement
	 * 
	 * @param name
	 * 		The name of the Statement
	 * @param e
	 * 		The expression
	 * @param source
	 * 		The column and line of the AssignStatement in its Task
	 */
	public AssignStatement(String name, E<?> e, SourceLocation source) {
		super(source);
		this.name = name;
		this.expression = e;
	}
	/**
	 * Executes the AssignStatement
	 * 
	 * @post Its Expression is evaluated
	 */
	@Override
	public void execute() {
		this.getExpression().evaluate();
	}
	
	/**
	 * 
	 * @return Returns the name assigned to the expression 
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 
	 * @return Returns the expression assigned to the AssignStatement
	 */
	public E<?> getExpression() {
		return this.expression;
	}
	
	private final String name;
	private final E<?> expression;

}
