package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckAliveExpression extends StatusExpression {
	
	public CheckAliveExpression(Unit unit, SourceLocation source) {
		super(unit, source);
	}

	@Override
	public Boolean evaluate() {
		return !this.getPassiveUnit().isTerminated();
	}
	
	

}
