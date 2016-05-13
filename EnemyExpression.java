package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class EnemyExpression extends UnitExpression {
	
	public EnemyExpression(SourceLocation source) {
		super(source);
	}
	/**
	 * Evaluates the EnemyExpression
	 * 
	 * @return Returns the closest enemy
	 * 
	 * @throws Throws IllegalUnitException if the unit does not belong to a world
	 * 
	 * @throws IllegalArgumentException if the expression has no unit assigned
	 */
	@Override
	public Unit evaluate() throws IllegalArgumentException, IllegalUnitException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld()
						.getClosestUnit((Unit unit) -> (this.getUnit().getFaction() != unit.getFaction()
						&& this.getUnit() != null && unit.getFaction() != null), 
								this.getUnit());
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
