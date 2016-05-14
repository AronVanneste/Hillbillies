package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class MoveStatement extends ActionStatement implements IExpressionCheck {
	
	/**
	 * Initialization of a MoveStatement
	 * 
	 * @param position
	 * 		The target of the MoveStatement
	 * @param source
	 * 		The column and line of the MoveStatement in its Task
	 */
	public MoveStatement(PositionExpression position, SourceLocation source) throws 
			IllegalSourceException, IllegalExpressionException {
		super(source);
		if (!isValidExpression(position))
			throw new IllegalExpressionException();
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
