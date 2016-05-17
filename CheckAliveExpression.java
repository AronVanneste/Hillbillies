package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CheckAliveExpression extends StatusExpression {
	/**
	 * Initialization of a CheckAliveExpression
	 * 
	 * @param unit
	 * 		The unit assigned to the Expression
	 * @param source
	 *  		The column and line of the CheckAliveExpression in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public CheckAliveExpression(UnitExpression unit, SourceLocation source) throws 
			IllegalSourceException, IllegalExpressionException {
		super(unit, source);
	}
	/**
	 * Checks whether the unit assigned to the expression is terminated
	 * 
	 * @return Returns whether or not the unit is terminated
	 */ 
	@Override
	public Boolean evaluate() {
		return !this.getPassiveUnit().evaluate().isTerminated();
	}
	
	

}