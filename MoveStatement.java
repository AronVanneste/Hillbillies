package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class MoveStatement extends ActionStatement {
	/**
	 * Initialization of a MoveStatement
	 * 
	 * @param position
	 * 		The target of the MoveStatement
	 * @param source
	 * 		The column and line of the MoveStatement in its Task
	 */
	public MoveStatement(E<?> position, SourceLocation source){
		super(source);
		if (!(position instanceof PositionExpression))
			throw new IllegalArgumentException();
		this.position = (PositionExpression) position;
	}
	/**
	 * 
	 * @return Returns the position of the MoveToStatement
	 */
	public PositionExpression getPosition() {
		return this.position;
	}
	
	
	private final PositionExpression position;

}
