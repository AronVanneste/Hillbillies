package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WhileStatement extends EvaluateStatement {

	public WhileStatement(BooleanExpression condition, S body, SourceLocation source) 
			throws IllegalArgumentException {
		super(source);
		this.condition = condition;
		this.body = body;
	}
	
	
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
	
	public BooleanExpression getCondition() {
		return this.condition;
	}
	
	public S getBody() {
		return this.body;
	}
	
	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
		this.getBody().setUnit(unit);
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
	private final S body;
	private Unit unit;

}