package hillbillies.model;

public abstract class MoveStatement extends ActionStatement {
	
	public MoveStatement(E<?> position) {
		if (!(position instanceof PositionExpression))
			throw new IllegalArgumentException();
		this.position = (PositionExpression) position;
	}
	
	public PositionExpression getPosition() {
		return this.position;
	}
	
	
	private final PositionExpression position;

}
