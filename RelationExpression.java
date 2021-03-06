package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class RelationExpression extends BooleanExpression implements IPerform, 
		IRelation {
	/**
	 * Initialization of a RelationExpression
	 * 
	 * @param unit
	 * 		The unit of the RelationExpression
	 * @param sourceLocation
	 *   	The column and line of the RelationExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public RelationExpression(UnitExpression unit, SourceLocation sourceLocation) throws 
			IllegalSourceException, IllegalExpressionException {
		super(sourceLocation);
		if (!isValidExpression(unit))
			throw new IllegalExpressionException();
		this.unitToPerformTaskOn = unit;
	}
	
	/**
	 * 
	 * @return Returns the unit of the RelationExpression
	 */
	public UnitExpression getPassiveUnit() {
		return this.unitToPerformTaskOn;
	}
	
	/**
	 * 
	 * @param unit The unit that has to be checked
	 */
	@Override
	public void setUnit(Unit unit) {
		this.performer = unit;
	}
	/**
	 * 
	 * @return Returns the unit that has to be checked
	 */
	@Override
	public Unit getUnit() {
		return this.performer;
	}
	/**
	 * 
	 * @return Returns whether or not a unit is yet assigned to the CheckFriendExpression
	 */
	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;
	private final UnitExpression unitToPerformTaskOn;
}