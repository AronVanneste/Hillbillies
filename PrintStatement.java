package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class PrintStatement extends S {
	
	public PrintStatement(E<?> expression, SourceLocation source) {
		super(source);
		this.expression = expression;
	}
	/**
	 * Evaluates the print statement
	 * 
	 * @post Prints the evaluation of the expression that assigned to the PrintExpression
	 */
	@Override
	public void execute() {
		System.out.println(this.getExpression().evaluate());
	}
	
	public E<?> getExpression() {
		return this.expression;
	}
	
	
	
	private final E<?> expression;




}
