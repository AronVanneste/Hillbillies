package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class NextToExpression extends PositionExpression {
	
	/**Initialization of a NextToExpression
	 * 
	 * @param position
	 * 		The position of the NextToExpression
	 * @param sourceLocation
	 *  		The column and line of the NextToExpression in its Task
	 */
	public NextToExpression(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}
	/**
	 * Evaluates the NextToExpression
	 * 
	 *@return Returns a position next to the position of the unit assigned to the expression that's passable
	 * 
	 * @throws IllegalArgumentException if the expression has no unit assigned
	 * 
	 * @throws IllegalUnitException if the unit does not belong to a world
	 */
	@Override
	public int[] evaluate() throws IllegalArgumentException, IllegalUnitException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld().getNextPosition(this.getPosition().evaluate());
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		
		throw new IllegalArgumentException("Not assigned to a unit");
		
	}

	/**
	 * 
	 * @return Returns the position of the NextToExpression
	 */
	public PositionExpression getPosition() {
		return this.position;
	}
	
	private final PositionExpression position;

}