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
	
	@Override
	public void setUnit(Unit unit) {
		this.performer = unit;
	}

	@Override
	public Unit getUnit() {
		return this.performer;
	}

	@Override
	public boolean isAssigned() {
		return (this.performer != null);
	}
	
	private Unit performer;
	
	private int[] selectedPosition;

}



