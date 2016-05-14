package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class UnitPositionExpression extends PositionExpression implements IRelation {
	/**
	 * Initializes a UnitPositionExpression
	 * 
	 * @param unit
	 * 		The unitExpression assigned to the unitPositionExpression
	 * @param sourceLocation
	 * 		The column and line of the UnitPositionExpression in its Task
	 * 
	 */
	public UnitPositionExpression(UnitExpression unit, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.passUnit = unit;
	}

	/**
	 * 
	 * @return Returns the UnitExpression assigned to the unitExpression
	 */
	@Override
	public UnitExpression getPassiveUnit() {
		return this.passUnit;
	}
	/**
	 * 
	 * @return Returns the position of its unit
	 */
	@Override
	public int[] evaluate() {
		return this.getPassiveUnit().evaluate().getCubeInt();
	}
	
	private final UnitExpression passUnit;

}
