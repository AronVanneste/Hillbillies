package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WorkshopExpression extends PositionExpression {
	
	/**
	 * Initializes a workShopExpression
	 * 
	 * @param sourceLocation
	 * 		The column and line of the WorkShopExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public WorkshopExpression(SourceLocation sourceLocation) throws IllegalSourceException {
		super(sourceLocation);
	}
	/**
	 * Evaluates the WorkShopExpression
	 * 
	 * @return Returns the closest workshop to the unit assigned to the expression
	 * 
	 * @throws IllegalUnitException
	 * 		Throws IllegalUnitException if the unit does not belong to a world
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the Expression is not assigned to a unit
	 */
	@Override
	public int[] evaluate() throws IllegalUnitException, IllegalArgumentException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld().getClosestTerrainType(TerrainType.WORKSHOP, 
						this.getUnit());
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		
		throw new IllegalArgumentException("Not assigned to a unit");
		
	}

	
}