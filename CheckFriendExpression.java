package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckFriendExpression extends RelationExpression {
	
	public CheckFriendExpression(UnitExpression friend, SourceLocation source) {
		super(friend, source);
		
	}
	/**
	 * Checks if two units are in the same faction
	 * 
	 * @return Returns true if the units assigned to the expression are friends
	 */
	@Override
	public Boolean evaluate() {
		if (this.isAssigned()) {
			if (this.getUnit().getFaction() == this.getPassiveUnit().evaluate().getFaction()
					&& this.getUnit().getFaction() != null)
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
