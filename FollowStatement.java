package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class FollowStatement extends UnitStatement {

	/**
	 * Initializes a FollowStatement
	 * 
	 * @param unit
	 * 		The unit that has to be followed
	 * @param source
	 *  		The column and line of the FollowStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public FollowStatement(UnitExpression unit, SourceLocation source) throws
			IllegalSourceException, IllegalExpressionException {
		super(unit, source);
	}

	
	/**
	 * Executes the followStatement
	 * 
	 * @post The unit assigned to the expression follows the given unit
	 */
	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().follow(this.getPassiveUnit().evaluate());
		else
			return;
	
		
		
	}
	
	
	
	
	

}