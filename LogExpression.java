package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class LogExpression extends PositionExpression {
	
	/**
	 * Initialization of a LogExpression
	 * 
	 * @param sourceLocation
	 * 		The column and line of the LogExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public LogExpression(SourceLocation sourceLocation) throws IllegalSourceException {
		super(sourceLocation);
	}
	/**
	 * Evaluates the logExpression
	 * 
	 * @return Returns the position of the closest log to the unit assigned to the Expression
	 * 
	 * @throws IllegalUnitException if the unit does not belong to a world
	 * 
	 * @throws IllegalArgumentException if the expression is not assigned to a unit
	 */
	@Override
	public int[] evaluate() throws IllegalUnitException, IllegalArgumentException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld().getClosestLog(this.getUnit()).getCubeInt();
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		
		throw new IllegalArgumentException("Not assigned to a Unit");
			

	}
	
	

	
	

}
