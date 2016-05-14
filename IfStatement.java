package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class IfStatement extends EvaluateStatement implements IExpressionCheck, IStatementCheck {

	/**
	 * Initialization of an IfStatement
	 * 
	 * @param condition
	 * 		The condition on which the statement depends
	 * @param ifBody
	 * 		The body to be executed when the condition evaluates to true
	 * @param elseBody
	 * 		The body to be executed when the condition evaluates to false
	 * @param source
	 *  		The line and column of the IfStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 * @throws IllegalStatementException
	 * 		Throws IllegalStatementException if the statement is not valid
	 */
	public IfStatement(BooleanExpression condition, S ifBody, S elseBody, SourceLocation source) 
			throws IllegalSourceException, IllegalStatementException, IllegalExpressionException {
		super(source);
		if (!isValidStatement(ifBody) | !isValidStatement(elseBody))
			throw new IllegalStatementException();
		if (!isValidExpression(condition))
			throw new IllegalExpressionException();
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	/**
	 * Evaluates the IfStatement
	 * 
	 * @post If the condition is true, the ifBody will be executed, else the elseBody
	 * 
	 * @throws BreakException
	 * 		Throws BreakException if there's a BreakStatement in the if or else-body
	 */
	@Override
	public void execute() throws BreakException {
		if (this.getCondition().evaluate()) {
			this.getIfBody().setUnit(this.getUnit());
			try {
				this.getIfBody().execute();
			} catch (BreakException brk) {
				throw brk;
			}
		}
		else {
			this.getElseBody().setUnit(this.getUnit());
			try {
				this.getElseBody().execute();
			} catch (BreakException brk2) {
				throw brk2;
			}
		}
	}
	
	/**
	 * 
	 * @return Returns the condition of the IfStatement
	 */
	public BooleanExpression getCondition() {
		return this.condition;
	}
	
	/**
	 * 
	 * @return Returns the IfBody of the IfStatement
	 */
	public S getIfBody() {
		return this.ifBody;
	}
	
	/**
	 * 
	 * @return Returns the ElseBody of the IfStatement
	 */
	public S getElseBody() {
		return this.elseBody;
	}
	
	

	

	
	private final BooleanExpression condition;
	private final S ifBody;
	private final S elseBody;
	
}
