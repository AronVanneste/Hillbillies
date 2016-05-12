package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckAliveExpression extends StatusExpression {
	
	public CheckAliveExpression(UnitExpression unit, SourceLocation source) {
		super(unit, source);
	}

	@Override
	public Boolean evaluate() {
		return !this.getPassiveUnit().evaluate().isTerminated();
	}
	
	

}
