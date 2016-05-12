package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnitStatement extends ActionStatement implements IRelation {
	
	public UnitStatement(UnitExpression unit, SourceLocation source) throws IllegalUnitException {
		super(source);
		if (!isValidUnit(unit))
			throw new IllegalUnitException();
		this.passiveUnit = unit;
		
	}
	
	public UnitExpression getPassiveUnit() {
		return this.passiveUnit;
	}
	
	public boolean isValidUnit(UnitExpression unit) {
		return unit != null;
	}
	
	private final UnitExpression passiveUnit;

}
