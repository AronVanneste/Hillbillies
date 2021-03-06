package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckFriendExpression extends RelationExpression {
	/**
	 * The initialization of a CheckFriendExpression
	 * 
	 * @param unit
	 * 		The unit that has to be checked
	 * @param source
	 *  		The column and line of the CheckFriendExpression in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public CheckFriendExpression(UnitExpression friend, SourceLocation source) throws
			IllegalSourceException, IllegalExpressionException {
		super(friend, source);
		
	}
	/**
	 * Checks if two units are in the same faction
	 * 
	 * @return Returns true if the units assigned to the expression are friends
	 */
	@Override
	public Boolean evaluate() {
		if (this.isAssigned()) {
			if (this.getUnit().getFaction() == this.getPassiveUnit().evaluate().getFaction()
					&& this.getUnit().getFaction() != null)
				return true;
		}
		
		return false;
	}
	
	

}
