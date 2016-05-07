package hillbillies.model;

public class WhileStatement extends EvaluateStatement {

	public WhileStatement(E<?> condition, S body) throws IllegalArgumentException {
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
	
	private final BooleanExpression condition;
	private final S body;

}