package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnitStatement extends ActionStatement implements IRelation {
	
	public UnitStatement(Unit unit, SourceLocation source) throws IllegalUnitException {
		super(source);
		this.passiveUnit = unit;
		
	}
	
	public Unit getPassiveUnit() {
		return this.passiveUnit;
	}
	
	public boolean isValidUnit(Unit unit) {
		return unit != null;
	}
	
	private final Unit passiveUnit;

}
