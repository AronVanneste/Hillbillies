package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class StatusExpression extends BooleanExpression implements IRelation {

	public StatusExpression(UnitExpression unit, SourceLocation sourceLocation) throws 
			IllegalSourceException, IllegalExpressionException {
		super(sourceLocation);
		if (!isValidExpression(unit))
			throw new IllegalExpressionException();
		this.passiveUnit = unit;
	}
	
	public UnitExpression getPassiveUnit() {
		return this.passiveUnit;
	}
	
	private final UnitExpression passiveUnit;

}
