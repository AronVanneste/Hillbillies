package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class IfStatement extends EvaluateStatement implements IPerform {

	public IfStatement(E<?> condition, S ifBody, S elseBody, SourceLocation source) 
			throws IllegalArgumentException {
		super(source);
		if (!(condition instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.condition = (BooleanExpression) condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	
	@Override
	public void execute() {
		if (this.getCondition().evaluate())
			this.getIfBody().execute();
		else
			this.getElseBody().execute();
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
		
	}


	@Override
	public Unit getUnit() {
		return this.unit;
	}


	@Override
	public boolean isAssigned() {
		// TODO Auto-generated method stub
		return this.getUnit() != null;
	}

	
	private final BooleanExpression condition;
	private final S ifBody;
	private final S elseBody;
	private Unit unit;
	
}
