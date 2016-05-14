package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class BoulderExpression extends PositionExpression {
	

	/**
	 * Initialisation of a BoulderExpression
	 * 
	 * @param sourceLocation
	 * 		The column and line of the BoulderExpression in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public BoulderExpression(SourceLocation sourceLocation) throws IllegalSourceException {
		super(sourceLocation);
	}

	
	/**
	 * Evaluates the given BoulderExpression
	 * 
	 * @return Returns the closest boulder to the unit
	 * 
	 * @throws Throws IllegalArgumentException if the expression has no assigned unit
	 * 
	 * @throws Throws IllegalUnitException if the assigned unit does not belong to a world
	 */
	@Override
	public int[] evaluate() throws IllegalArgumentException, IllegalUnitException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld().getClosestBoulder(this.getUnit()).getCubeInt();
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		
		throw new IllegalArgumentException("Not assigned to a Unit");
			

	}

	

}
