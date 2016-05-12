package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class S implements IPerform {
	
	public S(SourceLocation source) {
		this.source = source;
	}
	
	public abstract void execute();
	
	public SourceLocation getSource() {
		return this.source;
	}
	
	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
	}


	@Override
	public Unit getUnit() {
		return this.unit;
	}


	@Override
	public boolean isAssigned() {
		// TODO Auto-generated method stub
		return this.getUnit() != null;
	}
	
	private SourceLocation source;
	private Unit unit;

}
