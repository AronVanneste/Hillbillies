package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class IfStatement extends EvaluateStatement {
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
	 */
	
	public IfStatement(BooleanExpression condition, S ifBody, S elseBody, SourceLocation source) {
		super(source);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	/**
	 * Evaluates the IfStatement
	 * 
	 * @post If the condition is true, the ifBody will be executed, else the elseBody
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
	/**
	 * 
	 * @param unit
	 * 		The unit of the IfStatement
	 */
	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
		this.getIfBody().setUnit(unit);
		this.getElseBody().setUnit(unit);
	}

	/**
	 * 
	 * @return Returns the unit of the IfStatement
	 */
	@Override
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * 
	 * @return Returns whether or not the IfStatement has a unit assigned
	 */
	@Override
	public boolean isAssigned() {
		return this.getUnit() != null;
	}
		
	


	

	
	private final BooleanExpression condition;
	private final S ifBody;
	private final S elseBody;
	private Unit unit;
	
}
