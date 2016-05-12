package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class TerrainExpression extends BooleanExpression implements IPerform {

	public TerrainExpression(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}
	
	public PositionExpression getPosition() {
		return this.position;
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
	private PositionExpression position;
	
	

}
