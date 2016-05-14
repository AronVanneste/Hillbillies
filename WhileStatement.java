package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WhileStatement extends EvaluateStatement implements IExpressionCheck, 
		IStatementCheck {

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
	
	
		
	
	private final BooleanExpression condition;
	private final S body;

}