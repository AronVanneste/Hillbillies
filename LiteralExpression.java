package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class LiteralExpression extends PositionExpression {
	
	/**
	 * Initialization of a LiteralExpression
	 * 
	 * @param x
	 * 		The x-coordinate
	 * @param y
	 * 		The y-coordinate
	 * @param z
	 * 		The z-coordinate
	 * @param source
	 *  		The line and column of the LiteralExpression in its Task
	 */
	public LiteralExpression(int x, int y, int z, SourceLocation source) {
		super(source);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * Evaluates the LiteralExpression
	 * 
	 * @return Returns the position of the LiteralExpression
	 */
	@Override
	public int[] evaluate() {
		int[] pos = {x, y, z};
		return pos;
		
	}
	
	/**
	 * 
	 * @return Returns the x-coordinate
	 */
	public int getX() {
		return this.x;
	}
	/**
	 * 
	 * @return Returns the y-coordinate
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * 
	 * @return Returns the z-coordinate
	 */
	public int getZ() {
		return this.z;
	}
	
	
	
	private final int x;
	private final int y; 
	private final int z;
	
}



