package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnitStatement extends ActionStatement implements IRelation, 
			IExpressionCheck {
	
	public UnitStatement(UnitExpression unit, SourceLocation source) throws IllegalExpressionException, 
			IllegalSourceException {
		super(source);
		if (!isValidExpression(unit))
			throw new IllegalExpressionException();
		this.passiveUnit = unit;
		
	}
	
	public UnitExpression getPassiveUnit() {
		return this.passiveUnit;
	}
	
	
	private final UnitExpression passiveUnit;

}
