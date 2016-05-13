package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckPassableExpression extends TerrainExpression {

	public CheckPassableExpression(PositionExpression pos, SourceLocation sourceLocation) {
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
