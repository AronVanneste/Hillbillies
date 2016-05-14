package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckSolidExpression extends TerrainExpression {
	
	/**
	 * Initialisation of a CheckSolidExpression
	 * 
	 * @param pos
	 * 		The position that has to be checked
	 * @param sourceLocation
	 *   		The column and line of the CheckSolidExpression in its Task
	 */
	public CheckSolidExpression(PositionExpression pos, SourceLocation sourceLocation) {
		super(pos, sourceLocation);
	}
	/**
	 * Evaluates the CheckSolidExpression
	 * 
	 * @return Returns whether or not the position assigned to the expression is solid
	 * 
	 * @throws Throws IllegalArgumentException if the expression is not assigned to a unit
	 * 
	 * @throws Throws IllegalUnitException if the unit does not belong to a world
	*/
	@Override
	public Boolean evaluate() throws IllegalArgumentException, IllegalUnitException {
		if (this.isAssigned()) {
			try {
				return !this.getUnit().getWorld().isPassable(this.getPosition().evaluate());
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		throw new IllegalArgumentException("Not assigned to a unit");
	}
	

}
