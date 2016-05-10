package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class LiteralExpression extends PositionExpression {
	
	
	public LiteralExpression(int x, int y, int z, SourceLocation source) {
		super(source);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int[] evaluate() {
		int[] pos = {x, y, z};
		return pos;
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getZ() {
		return this.z;
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
	
	private final int x;
	private final int y; 
	private final int z;
	
}


