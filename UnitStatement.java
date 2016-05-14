package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnitStatement extends ActionStatement implements IRelation, 
			IExpressionCheck {
	
	/**
	 * Initializes a UnitStatement
	 * 
	 * @param unit
	 * 		The unit assigned to the Statement
	 * @param source
	 * 	 * @param sourceLocation
	 * 		The column and line of the UnitStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public UnitStatement(UnitExpression unit, SourceLocation source) throws IllegalExpressionException, 
			IllegalSourceException {
		super(source);
		if (!isValidExpression(unit))
			throw new IllegalExpressionException();
		this.passiveUnit = unit;
		
	}
	
	/**
	 * 
	 * @return Returns the unit assigned to the statement
	 */
	public UnitExpression getPassiveUnit() {
		return this.passiveUnit;
	}
	
	
	private final UnitExpression passiveUnit;

}
