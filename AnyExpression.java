package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AnyExpression extends UnitExpression {
	
	public AnyExpression(SourceLocation source) {
		super(source);
	}
	/**
	 * Evaluates the AnyExpression
	 * 
	 * @return Returns the closest unit to the unit assigned to AnyExpression
	 * 
	 * @throws Throws IllegalUnitExpression if the unit is not assigned to a world
	 * 
	 * @throws Throws IllegalArgumentExpression if the AnyExpression has no unit
	 */ 
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
	/**
	 * 
	 * @return Returns whether or not a unit is assigned to the expression
	 */ 
	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;

}
