package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class RelationExpression extends BooleanExpression implements IPerform, 
		IRelation {

	public RelationExpression(UnitExpression unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToPerformTaskOn = unit;
	}
	
	public UnitExpression getPassiveUnit() {
		return this.unitToPerformTaskOn;
	}
	
	
	private final UnitExpression unitToPerformTaskOn;
}
