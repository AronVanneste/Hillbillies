package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class NextToExpression extends PositionExpression {
	

	public NextToExpression(PositionExpression position, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.position = position;
	}

	@Override
	public int[] evaluate() throws IllegalArgumentException, IllegalUnitException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld().getNextPosition(this.getPosition().evaluate());
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		
		throw new IllegalArgumentException("Not assigned to a unit");
		
	}
	
	
	
	public PositionExpression getPosition() {
		return this.position;
	}
	
	private final PositionExpression position;

}
