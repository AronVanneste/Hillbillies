package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WorkshopExpression extends PositionExpression {
	

	public WorkshopExpression(SourceLocation sourceLocation) throws IllegalSourceException {
		super(sourceLocation);
	}

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
