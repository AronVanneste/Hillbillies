package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class EnemyExpression extends UnitExpression {
	/**
	 * Initialization of an EnemyExpression
	 * 
	 * @param source
	 * 		The column and line of the EnemyExpression in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public EnemyExpression(SourceLocation source) throws IllegalSourceException {
		super(source);
	}
	/**
	 * Evaluates the EnemyExpression
	 * 
	 * @return Returns the closest enemy
	 * 
	 * @throws Throws IllegalUnitException if the unit does not belong to a world
	 * 
	 * @throws IllegalArgumentException if the expression has no unit assigned
	 */
	@Override
	public Unit evaluate() throws IllegalArgumentException, IllegalUnitException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld()
						.getClosestUnit((Unit unit) -> (this.getUnit().getFaction() != unit.getFaction()
						&& this.getUnit() != null && unit.getFaction() != null), 
								this.getUnit());
			} catch (NullPointerException e){
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		throw new IllegalArgumentException("Expression not assigned to Unit");
		
	}
	
	
}