package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AttackStatement extends UnitStatement {

	public AttackStatement(UnitExpression unit, SourceLocation source) {
		super(unit, source);
	}
	/**
	 * Executes the attackstatement, lets the unit attack an other unit
	 * 
	 * @post The unit of the AttacksStatement is attacking the given unit
	 */ 
	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().fight(this.getUnit(), this.getPassiveUnit().evaluate());
		else
			return;
		
	}

}


