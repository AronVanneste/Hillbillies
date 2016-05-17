package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AssignStatement extends S implements IExpressionCheck {
	
	/**
	 * Initialisation of an AssignStatement
	 * 
	 * @param name
	 * 		The name of the Statement
	 * @param e
	 * 		The expression
	 * @param source
	 * 		The column and line of the AssignStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalNameException
	 * 		Throws IllegalSourceException if the name is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the expression is not valid
	 */
	public AssignStatement(String name, E<?> e, SourceLocation source) throws IllegalNameException, 
			IllegalExpressionException, IllegalSourceException {
		super(source);
		if (!isValidName(name))
			throw new IllegalNameException();
		if (!isValidExpression(e))
			throw new IllegalExpressionException();
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
	/**
	 * 
	 * @param name
	 * 		The name assigned to the expression
	 * @return Return whether or not the given name is valid
	 */
	public boolean isValidName(String name) {
		return (name != null);
	}
	
	private final String name;
	private final E<?> expression;

}