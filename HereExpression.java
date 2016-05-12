package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class HereExpression extends PositionExpression implements IPerform {

	public HereExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public Unit getUnit() {
		return this.unit;
	}

	@Override
	public boolean isAssigned() {
		return this.getUnit() != null;
	}

	@Override
	public int[] evaluate() {
		return this.getUnit().getCubeInt();
	}
	
	private Unit unit;

}
