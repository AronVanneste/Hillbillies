package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class RelationExpression extends BooleanExpression implements PerformInterface, 
		RelationInterface {

	public RelationExpression(Unit unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.unitToPerformTaskOn = unit;
	}
	
	public Unit getPassiveUnit() {
		return this.unitToPerformTaskOn;
	}
	
	
	private final Unit unitToPerformTaskOn;
}
