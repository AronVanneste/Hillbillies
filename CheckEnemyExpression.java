package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckEnemyExpression extends RelationExpression {
	/**
	 * The initialization of a CheckEnemyExpression
	 * 
	 * @param unit
	 * 		The unit that has to be checked
	 * @param source
	 *  		The column and line of the CheckEnemyExpression in its Task
	 */
	public CheckEnemyExpression(UnitExpression unit, SourceLocation source) throws
			IllegalSourceException, IllegalExpressionException {
		super(unit, source);
	}
	/**
	 * Checks whether or not as unit is an enemy of the unit assigned to the expression
	 * 
	 * @return Returns whether or not the units are enemys
	 */
	@Override
	public Boolean evaluate() {
		if (this.isAssigned()) {
			if (this.getUnit().getFaction() != this.getPassiveUnit().evaluate().getFaction()
					&& this.getUnit().getFaction() != null
					&& this.getPassiveUnit().evaluate().getFaction() != null)
				return true;
		}
		
		return false;
	}
	
	
}