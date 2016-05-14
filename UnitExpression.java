package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnitExpression extends E<Unit> implements IPerform {

	public UnitExpression(SourceLocation sourceLocation) throws IllegalSourceException {
		super(sourceLocation);
	}
	
	/**
	 * Sets a given unit to the expression
	 * 
	 * @param unit
	 * 		The unit that belongs to the expression
	 */
	@Override
	public void setUnit(Unit unit) {
		this.performer = unit;
	}
	/**
	 * 
	 * @return Returns the unit of the expression
	 */
	@Override
	public Unit getUnit() {
		return this.performer;
	}
	/**
	 * 
	 * @return Returns whether or not a unit is assigned to the expression
	 */ 
	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;


}
