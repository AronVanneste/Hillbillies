package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CarriesItemExpression extends StatusExpression {
	/**
	 * Initialisation of a CarriesItemExpression
	 * 
	 * @param unit
	 * 		The unit assigned to the expression
	 * 
	 * @param source
	 *  		The column and line of the CarriesItemExpression in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public CarriesItemExpression(UnitExpression unit, SourceLocation source) throws 
			IllegalSourceException, IllegalExpressionException {
		super(unit, source);
	}
	/**
	 * Evaluates the expression
	 * 
	 * @return Returns whether or not the unit assigned to the expression if carrying a boulder or log
	 */ 
	@Override
	public Boolean evaluate() {
		return (this.getPassiveUnit().evaluate().isCarryingBoulder() | 
				this.getPassiveUnit().evaluate().isCarryingLog());
	}

}