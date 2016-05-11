package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class ActionStatement extends S implements IPerform {
	

	public ActionStatement(SourceLocation source) {
		super(source);
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
