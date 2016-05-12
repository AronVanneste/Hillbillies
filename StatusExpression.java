package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class StatusExpression extends BooleanExpression implements IRelation {

	public StatusExpression(UnitExpression unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.passiveUnit = unit;
	}
	
	public UnitExpression getPassiveUnit() {
		return this.passiveUnit;
	}
	
	private UnitExpression passiveUnit;

}
