package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckFriendExpression extends RelationExpression {
	
	public CheckFriendExpression(Unit friend, SourceLocation source) {
		super(friend, source);
		
	}

	@Override
	public Boolean evaluate() {
		if (this.isAssigned()) {
			if (this.getUnit().getFaction() == this.getPassiveUnit().getFaction()
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
