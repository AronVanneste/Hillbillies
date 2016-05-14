package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class ThisExpression extends UnitExpression {
	/**
	 *Initialization of a ThisExpression
	 * @param source
	 *		The column and line of the ThisExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceExpression if the source is not valid
	 */
	public ThisExpression(SourceLocation source) throws IllegalSourceException {
		super(source);
	}
	/**
	 * Evaluates the ThisExpression
	 * 
	 * @return Returns the unit assigned to the Expression
	 * 
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentExpression if it has no unit assigned
	 */
	@Override
	public Unit evaluate() throws IllegalArgumentException {
		if (this.isAssigned()) {
			return this.getUnit();
		}
		throw new IllegalArgumentException("Expression not assigned to Unit");
		
	}

}
