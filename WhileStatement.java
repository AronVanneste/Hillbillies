package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WhileStatement extends EvaluateStatement implements IExpressionCheck, 
		IStatementCheck {
	/**
	 * Initializes a WhileStatement
	 * 
	 * @param condition
	 * 		The condition assigned to the whileStatement
	 * @param body
	 * 		The body of the WhileStatement
	 * @param source
	 * 		The column and line of the WhileStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public WhileStatement(BooleanExpression condition, S body, SourceLocation source) 
			throws IllegalSourceException, IllegalExpressionException {
		super(source);
		if (!isValidExpression(condition))
			throw new IllegalExpressionException();
		if (!isValidStatement(body))
			throw new IllegalStatementException();
		this.condition = condition;
		this.body = body;
	}
	
	/**
	 * Executes the whileStatement
	 */
	@Override
	public void execute() {
		while (this.getCondition().evaluate()) {
			try {
				this.getBody().execute();
			} catch (BreakException e) {
				break;
			}
		}
	}
	/**
	 * Returns the condition of the whileStatement
	 * 
	 * @return Returns the condition of the whileStatement
	 */
	public BooleanExpression getCondition() {
		return this.condition;
	}
	
	/**
	 * 
	 * @return Returns the body of the whileStatement
	 */
	public S getBody() {
		return this.body;
	}
	
	
		
	
	private final BooleanExpression condition;
	private final S body;

}