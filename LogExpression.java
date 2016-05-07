package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class LogExpression extends PositionExpression {
	

	public LogExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public int[] evaluate() throws IllegalUnitException, IllegalArgumentException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld().getClosestLog(this.getUnit()).getCube();
			} catch (NullPointerException e) {
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		
		throw new IllegalArgumentException("Not assigned to a Unit");
			

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
