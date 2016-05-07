package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckEnemyExpression extends RelationExpression {
	
	public CheckEnemyExpression(Unit unit, SourceLocation source) {
		super(unit, source);
	}

	@Override
	public Boolean evaluate() {
		if (this.isAssigned()) {
			if (this.getUnit().getFaction() != this.getPassiveUnit().getFaction()
					&& this.getUnit().getFaction() != null
					&& this.getPassiveUnit().getFaction() != null)
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
