package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class StatusExpression extends BooleanExpression implements IRelation {
	/**
	 * Initializes a StatusExpression
	 * 
	 * @param unit
	 * 		The unit assigned to the StatusExpression
	 * @param sourceLocation
	 * 		The column and line of the StatusExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public StatusExpression(UnitExpression unit, SourceLocation sourceLocation) throws 
			IllegalSourceException, IllegalExpressionException {
		super(sourceLocation);
		if (!isValidExpression(unit))
			throw new IllegalExpressionException();
		this.passiveUnit = unit;
	}
	
	/**
	 * 
	 * @return Returns the passiveUnit of the StatusExpression
	 */
	public UnitExpression getPassiveUnit() {
		return this.passiveUnit;
	}
	
	private final UnitExpression passiveUnit;

}
