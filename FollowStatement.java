package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class FollowStatement extends UnitStatement {

	public FollowStatement(UnitExpression unit, SourceLocation source) {
		super(unit, source);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().follow(this.getPassiveUnit().evaluate());
		else
			return;
	
		
		
	}
	
	
	
	
	

}
