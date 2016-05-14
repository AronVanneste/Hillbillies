package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WorkStatement extends MoveStatement {
	/**
	 * Initializes a WorkStatement
	 * 
	 * @param position
	 * 		The position of th WorkStatement
	 * @param source
	 *		The column and line of the WorkStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws ExpressionException if the expression is not valid
	 */
	public WorkStatement(PositionExpression position, SourceLocation source) throws
			IllegalExpressionException, IllegalSourceException {
		super(position, source);
	}
	/**
	 * Executes the Workstatement
	 */
	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().workAt((int[]) this.getPosition().evaluate());
		else
			return;
	}
	
	

}
