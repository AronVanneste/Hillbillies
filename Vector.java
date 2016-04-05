package hillbillies.model;

public class Vector {
	
	private double X;
	private double Y;
	private double Z;
	
	
	/**
	 * @param X
	 * 		The x-axis position of the center of the vector
	 * @param Y
	 * 		The y-axis position of the center of the vector
	 * @param Z
	 * 		The z-axis position of the center of the vector
	 * @post The x part of the vector will have the value X
	 * 		 The y part of the vector will have the value Y
	 * 		 The z part of the vector will have the value Z
	 * 
	 */
	public Vector(double X, double Y, double Z) {
		this.setVector(X,Y,Z);
		
	}

	
	/**
	 * 
	 * @param X
	 * 		The x-axis position of the center of the vector
	 * @param Y
	 * 		The y-axis position of the center of the vector
	 * @param Z
	 * 		The z-axis position of the center of the vector
	 * @post The x part of the vector will have the value X
	 * 		 The y part of the vector will have the value Y
	 * 		 The z part of the vector will have the value Z

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
	
	/**
	 * @param X
	 * 		The new X coordinate of the vector
	 * @post
	 * 		The X coordinate of the vector will be X
	 */
	public void setX(double X) {
		this.X = X;
	}
		
	/**
	 * @param Y
	 * 		The new Y coordinate of the vector
	 * @post
	 * 		The Y coordinate of the vector will be Y
	 */
	public void setY(double Y) {
		this.Y = Y;
	}
	
	/**
	 * @param Z
	 * 		The new Z coordinate of the vector
	 * @post
	 * 		The Z coordinate of the vector will be Z
	 */
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
	
	/**
	 * @return Returns whether or not two vectors are equal (have the same X,Y and Z argument)
	 */
	public boolean equals(Vector vector) {
		if ((this.getX() == vector.getX()) && (this.getY() == vector.getY())
				&& (this.getZ() == vector.getZ()))
			return true;
		else
			return false;
	}
	
}
