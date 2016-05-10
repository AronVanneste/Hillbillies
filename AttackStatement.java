package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AttackStatement extends UnitStatement {

	public AttackStatement(Unit unit, SourceLocation source) {
		super(unit, source);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().fight(this.getUnit(), this.getPassiveUnit());
		else
			return;
		
	}

}
