package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class FriendExpression extends UnitExpression {
	/**
	 * Initialization of a FriendExpression
	 * 
	 * @param source
	 *  		The column and line of the EnemyExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public FriendExpression(SourceLocation source) throws IllegalSourceException {
		super(source);
	}
	/**
	 * Evaluates the FriendExpression
	 * 
	 * @return Returns the unit that's in the same faction closest to the unit assigned to the expression
	 * 
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the expression has no unit assigned
	 */
	@Override
	public Unit evaluate() throws IllegalArgumentException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld()
						.getClosestUnit((Unit unit) -> (this.getUnit().getFaction() == unit.getFaction()), 
								this.getUnit());
			} catch (NullPointerException e){
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		throw new IllegalArgumentException("Expression not assigned to Unit");
		
	}
	
}
