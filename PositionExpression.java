package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class PositionExpression extends E<int[]> implements IPerform {

	public PositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	@Override
	public void setUnit(Unit unit) {
		this.performer = unit;
	}

	@Override
	public Unit getUnit() {
		return this.performer;
	}

	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;


}
