package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AttackStatement extends UnitStatement {
	/**
	 * Initialisation of an AttackStatement
	 * 
	 * @param unit
	 * 		The unit that has to be attacked by the unit assigned to the AttackStatement
	 * @param source
	 * 		The column and line of the AttackStatament in its task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public AttackStatement(UnitExpression unit, SourceLocation source) throws 
			IllegalExpressionException, IllegalSourceException {
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


