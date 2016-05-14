package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class PrintStatement extends S implements IExpressionCheck {
	
	/**
	 * Initialization of a PrintStatement
	 * 
	 * @param expression
	 * 		The expression that has to be printed
	 * @param source
	 *   	The column and line of the PrintExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public PrintStatement(E<?> expression, SourceLocation source) throws IllegalSourceException, 
			IllegalExpressionException {
		super(source);
		if (!isValidExpression(expression))
			throw new IllegalExpressionException();
		this.expression = expression;
	}

	/**
	 * Executes the print statement
	 * 
	 * @post Prints the evaluation of the expression that assigned to the PrintExpression
	 */
	@Override
	public void execute() {
		System.out.println(this.getExpression().evaluate());
	}
	
	/**
	 * 
	 * @return Returns the expression of the PrintStatement
	 */
	public E<?> getExpression() {
		return this.expression;
	}
	
	
	
	private final E<?> expression;




}
