package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckPassableExpression extends TerrainExpression {
	/**
	 * Initialisation of a CheckPassableExpression
	 * 
	 * @param pos
	 * 		The position that has to be checked
	 * @param sourceLocation
	 *   		The column and line of the CheckPassableExpression in its Task
	 */
	public CheckPassableExpression(PositionExpression pos, SourceLocation sourceLocation) 
			throws IllegalSourceException, IllegalExpressionException {
		super(pos, sourceLocation);
	}
	/**
	 * Returns whether or not the position of the expression is passable
	 * 
	 * @return Returns whether or not the position of the expression is passable
	 */
	@Override
	public Boolean evaluate() throws IllegalArgumentException, IllegalUnitException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld().isPassable(this.getPosition().evaluate());
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		throw new IllegalArgumentException("Not assigned to a unit");
	}
		

}