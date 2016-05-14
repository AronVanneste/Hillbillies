package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class HereExpression extends PositionExpression implements IPerform {
	
	/**
	 * Initialization of a HereExpression
	 * 
	 * @param sourceLocation
	 * 		The line and column of the HereExpression in its Task
	 */
	public HereExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	/**
	 * 
	 * @param unit
	 * 		The unit of the HereExpression
	 */
	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	/**
	 * 
	 * @return Returns the unit of the HereExpression
	 */
	@Override
	public Unit getUnit() {
		return this.unit;
	}
	/**
	 * 
	 * @return Returns whether or not the HereExpression has a unit assigned
	 */
	@Override
	public boolean isAssigned() {
		return this.getUnit() != null;
	}
	/**
	 * Evaluates the HereExpression
	 * 
	 * @return Returns the coordinates (int[]) of the unit assigned to th expression
	 */
	@Override
	public int[] evaluate() {
		return this.getUnit().getCubeInt();
	}
	
	private Unit unit;

}