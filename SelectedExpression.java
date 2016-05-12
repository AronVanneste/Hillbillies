package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class SelectedExpression extends PositionExpression {
	
	

	public SelectedExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public int[] evaluate() {
		return this.selectedPosition;
		
	}
	
	public void setSelectedPos(int[] pos) {
		this.selectedPosition = pos;
	}
	
	
	
	private int[] selectedPosition;

}



