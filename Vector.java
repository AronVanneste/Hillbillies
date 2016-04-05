package hillbillies.model;

public class Vector {
	
	private double X;
	private double Y;
	private double Z;
	
	public Vector(double X, double Y, double Z) {
		this.setVector(X,Y,Z);
		
	}

	
	/**
	 * 
	 * @param X
	 * 		The x-axis position of the center of the unit
	 * @param Y
	 * 		The y-axis position of the center of the unit
	 * @param Z
	 * 		The z-axis position of the center of the unit
	 * @post The x part of the vector will have the value X
	 * 		 The y part of the vector will have the value Y
	 * 		 The z part of the vector will have the value Z
	 * 		| new.getX == X
	 * 		| new.getY == Y
	 * 		| new.getZ == Z 
	 * @throws IllegalArgumentException
	 * 		 If one of given X, Y or Z is negative or larger than 50.
	 * 		| (!isValidPosition(X,Y,Z))
	 * 
	 */
	
	public void setVector(double X, double Y, double Z) {
		this.setX(X);
		this.setY(Y);
		this.setZ(Z);
	}
	
	
	public void setX(double X) {
		this.X = X;
	}
		
	public void setY(double Y) {
		this.Y = Y;
	}
	public void setZ(double Z) {
		this.Z = Z;
	}
	/**
	 * 
	 * @return Returns the first value of the vector
	 */
	public double getX() {
		return this.X;
	}
	
	/**
	 * 
	 * @return Returns the second value of the vector
	 */
	public double getY() {
		return this.Y;
	}
	/**
	 * 
	 * @return Returns the third value of the vector
	 */
	public double getZ() {
		return this.Z;
	}
	
	public boolean equals(Vector vector) {
		if ((this.getX() == vector.getX()) && (this.getY() == vector.getY())
				&& (this.getZ() == vector.getZ()))
			return true;
		else
			return false;
	}
	
}
