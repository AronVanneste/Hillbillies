package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class SelectedExpression extends PositionExpression {
	
	
	/**
	 * Initializes a SelectedExpression
	 * 
	 * @param sourceLocation
	 * 		The column and row of the Expression in its Task
	 * 
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the source is not valid
	 */
	public SelectedExpression(SourceLocation sourceLocation) throws IllegalSourceException {
		super(sourceLocation);
	}

	/**
	 * Evaluates the SelectedExpression
	 * 
	 * @return Returns the position of the selectedExpression
	 */
	@Override
	public int[] evaluate() {
		return this.selectedPosition;
		
	}
	
	/**
	 * 
	 * @param pos
	 * 		The position of the SelectedExpression
	 */
	public void setSelectedPos(int[] pos) {
		this.selectedPosition = pos;
	}
	
	
	
	private int[] selectedPosition;

}



