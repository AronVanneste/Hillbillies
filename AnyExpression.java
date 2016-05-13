package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AnyExpression extends UnitExpression {
	
	public AnyExpression(SourceLocation source) {
		super(source);
	}

	@Override
	public Unit evaluate() throws IllegalUnitException, IllegalArgumentException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld()
						.getClosestUnit((Unit unit) -> (true), this.getUnit());
			} catch (NullPointerException e){
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		throw new IllegalArgumentException("Expression not assigned to Unit");
		
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
