package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class TerrainExpression extends BooleanExpression implements IPerform {
	/**
	 * Initializes a TerrainExpression
	 * 
	 * @param position
	 * 		The position of the Expression
	 * @param sourceLocation
	 *		The column and line of the TerrainExpression in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceExpression if the source is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalExpressionException if the position is not valid
	 */
	public TerrainExpression(PositionExpression position, SourceLocation sourceLocation) 
			throws IllegalSourceException, IllegalExpressionException {
		super(sourceLocation);
		if (!isValidExpression(position))
			throw new IllegalExpressionException();
		this.position = position;
	}
	
	/**
	 * 
	 * @return Returns the position of the TerrainExpression
	 */
	public PositionExpression getPosition() {
		return this.position;
	}
	/**
	 * Assigns a unit to the TerrainExpression
	 * @param unit
	 * 		The unit to be assigned
	 */
	@Override
	public void setUnit(Unit unit) {
		this.performer = unit;
	}
	
	/**
	 * 
	 * @return Returns the assigned unit
	 */
	@Override
	public Unit getUnit() {
		return this.performer;
	}
	
	/**
	 * 
	 * @return Returns whether or not the TerrainExpression has a performer
	 */
	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;
	private final PositionExpression position;
	
	

}