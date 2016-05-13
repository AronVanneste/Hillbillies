package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AnyExpression extends UnitExpression {
	
	public AnyExpression(SourceLocation source) {
		super(source);
	}
	/**
	 * Evaluates the AnyExpression
	 * 
	 * @return Returns The closest unit to the unit assigned to the anyExpression
	 * 
	 * @throws Throws IllegalUnitException if the unit doesn't belong to a world
	 * 
	 * @throws Throws IllegalArgumentException if the expression is not assigned to a unit
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

	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;

}
