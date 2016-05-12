package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class UnitPositionExpression extends PositionExpression implements IRelation {

	public UnitPositionExpression(UnitExpression unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.passUnit = unit;
	}


	@Override
	public UnitExpression getPassiveUnit() {
		return this.passUnit;
	}

	@Override
	public int[] evaluate() {
		return this.getPassiveUnit().evaluate().getCubeInt();
	}
	
	private final UnitExpression passUnit;

}
