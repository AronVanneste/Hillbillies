package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WhileStatement extends EvaluateStatement implements IPerform {

	public WhileStatement(E<?> condition, S body, SourceLocation source) 
			throws IllegalArgumentException {
		super(source);
		if (!(condition instanceof BooleanExpression))
			throw new IllegalArgumentException();
		this.condition = (BooleanExpression) condition;
		this.body = body;
	}
	
	
	@Override
	public void execute() {
		while (this.getCondition().evaluate())
			this.getBody().execute();
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
	private final S body;
	private Unit unit;

}