package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class TerrainExpression extends BooleanExpression implements IPerform {

	public TerrainExpression(PositionExpression position, SourceLocation sourceLocation) 
			throws IllegalSourceException, IllegalExpressionException {
		super(sourceLocation);
		if (!isValidExpression(position))
			throw new IllegalExpressionException();
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
	private final PositionExpression position;
	
	

}
