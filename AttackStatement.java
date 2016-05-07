package hillbillies.model;

public class AttackStatement extends UnitStatement {

	public AttackStatement(Unit unit) {
		super(unit);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().fight(this.getUnit(), this.getPassiveUnit());
		else
			return;
		
	}

}
