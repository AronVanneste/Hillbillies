package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class IfStatement extends EvaluateStatement {

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
	
	public BooleanExpression getCondition() {
		return this.condition;
	}
	
	public S getIfBody() {
		return this.ifBody;
	}
	
	public S getElseBody() {
		return this.elseBody;
	}
	
	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
		this.getIfBody().setUnit(unit);
		this.getElseBody().setUnit(unit);
	}


	@Override
	public Unit getUnit() {
		return this.unit;
	}


	@Override
	public boolean isAssigned() {
		return this.getUnit() != null;
	}
		
	


	

	
	private final BooleanExpression condition;
	private final S ifBody;
	private final S elseBody;
	private Unit unit;
	
}
