package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckEnemyExpression extends RelationExpression {
	
	public CheckEnemyExpression(UnitExpression unit, SourceLocation source) {
		super(unit, source);
	}
	/**
	 * Checks whether or not as unit is an enemy of the unit assigned to the expression
	 * 
	 * @return Returns whether or not the units are enemys
	 */
	@Override
	public Boolean evaluate() {
		if (this.isAssigned()) {
			if (this.getUnit().getFaction() != this.getPassiveUnit().evaluate().getFaction()
					&& this.getUnit().getFaction() != null
					&& this.getPassiveUnit().evaluate().getFaction() != null)
				return true;
		}
		
		return false;
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
