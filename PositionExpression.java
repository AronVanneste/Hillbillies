package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class PositionExpression extends E<int[]> implements IPerform {
	
	/**
	 * Initialization of a PositionExpression
	 * 
	 * @param sourceLocation
	 *   	The column and line of the PositionExpression in its Task
	 */
	public PositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	/**
	 * @param unit
	 * 		The unit of the PositionExpression
	 */
	@Override
	public void setUnit(Unit unit) {
		this.performer = unit;
	}
	/**
	 * @return Returns the unit of the PositionExpression
	 */
	@Override
	public Unit getUnit() {
		return this.performer;
	}
	/**
	 * @return Returns whether or not the PositionExpression has a unit assigned
	 */
	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;


}
