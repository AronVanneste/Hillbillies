package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class ThisExpression extends UnitExpression {
	
	public ThisExpression(SourceLocation source) {
		super(source);
	}

	@Override
	public Unit evaluate() throws IllegalArgumentException {
		if (this.isAssigned()) {
			return this.getUnit();
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
