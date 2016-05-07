package hillbillies.model;

public class FollowStatement extends UnitStatement {

	public FollowStatement(Unit unit) {
		super(unit);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().follow(this.getPassiveUnit());
		else
			return;
	
		
		
	}
	
	
	
	
	

}
