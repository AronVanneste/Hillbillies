package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class FriendExpression extends UnitExpression {
	
	public FriendExpression(SourceLocation source) {
		super(source);
	}
	/**
	 * Evaluates the FriendExpression
	 * 
	 * @return Returns the unit that's in the same faction closest to the unit assigned to the expression
	 */
	@Override
	public Unit evaluate() throws IllegalArgumentException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld()
						.getClosestUnit((Unit unit) -> (this.getUnit().getFaction() == unit.getFaction()), 
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

