package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class MoveToStatement extends MoveStatement {
	/**
	 * Initialisation of a MoveToStatement
	 * 
	 * @param position
	 * 		The position of the moveToStatement
	 * @param source
	 * 		The column and line of the moveToStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public MoveToStatement(PositionExpression position, SourceLocation source) throws 
			IllegalSourceException, IllegalExpressionException {
		super(position, source);
	}
	
	/**
	 * Executes the MoveToStatement
	 * 
	 * @post The unit starts moving to it's destination, if it has one
	 */
	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().moveTo((int[]) this.getPosition().evaluate());
		else
			return;
	}

}