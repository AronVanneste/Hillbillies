package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class MoveStatement extends ActionStatement {
	
	public MoveStatement(E<?> position, SourceLocation source) {
		super(source);
		if (!(position instanceof PositionExpression))
			throw new IllegalArgumentException();
		this.position = (PositionExpression) position;
	}
	
	public PositionExpression getPosition() {
		return this.position;
	}
	
	
	private final PositionExpression position;

}
