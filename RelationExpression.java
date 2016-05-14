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
	 */
	public RelationExpression(UnitExpression unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToPerformTaskOn = unit;
	}
	
	/**
	 * 
	 * @return Returns the unit of the RelationExpression
	 */
	public UnitExpression getPassiveUnit() {
		return this.unitToPerformTaskOn;
	}
	
	
	private final UnitExpression unitToPerformTaskOn;
}
