package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class StatusExpression extends BooleanExpression  {

	public StatusExpression(Unit unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.passiveUnit = unit;
	}
	
	public Unit getPassiveUnit() {
		return this.passiveUnit;
	}
	
	private Unit passiveUnit;

}
