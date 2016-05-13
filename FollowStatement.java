package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class FollowStatement extends UnitStatement {

	public FollowStatement(UnitExpression unit, SourceLocation source) {
		super(unit, source);
	}
	/**
	 * Executes the followStatement
	 * 
	 * @post The unit assigned to the expression follows the given unit
	 */
	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().follow(this.getPassiveUnit().evaluate());
		else
			return;
	
		
		
	}
	
	
	
	
	

}
