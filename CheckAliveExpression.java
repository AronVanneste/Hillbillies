package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckAliveExpression extends StatusExpression {
	
	public CheckAliveExpression(UnitExpression unit, SourceLocation source) {
		super(unit, source);
	}
	/**
	 * Checks whether the unit assigned to the expression is terminated
	 * 
	 * @return Returns whether or not the unit is terminated
	 */ 
	@Override
	public Boolean evaluate() {
		return !this.getPassiveUnit().evaluate().isTerminated();
	}
	
	

}
