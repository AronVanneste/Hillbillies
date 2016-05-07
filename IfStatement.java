package hillbillies.model;

public class IfStatement extends EvaluateStatement {

	public IfStatement(E<?> condition, S ifBody, S elseBody) throws IllegalArgumentException {
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
	
	private final BooleanExpression condition;
	private final S ifBody;
	private final S elseBody;

}
