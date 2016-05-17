package hillbillies.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Vector {	
	/**
	 * This is a Value Class
	 */
	
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
	public Vector(double X, double Y, double Z) {
		this.X = X;
		this.Y = Y;
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
	 * 
	 * @param vector
	 * 		The vector of which is checked if it equals this
	 * @return Returns true if the two vectors are the same
	 */
	public boolean equals(Vector vector) {
		double EPSILON = Math.pow(10,-10);
		if ((this.getX() - vector.getX() < EPSILON) && (this.getY() - vector.getY()< EPSILON)
				&& (this.getZ() - vector.getZ()) < EPSILON)
			return true;
		else
			return false;
	}
	/**
	 * Sums two vectors
	 * @param vector
	 * 		The vector to be added to this
	 * @return Returns the sum of vector and this
	 */
	public Vector sum(Vector vector) {
		return new Vector(this.getX() + vector.getX(), this.getY() + vector.getY(),
					this.getZ() + vector.getZ());
	}
	

	/**
	 * Adds values to the x, y and z coordinate
	 * 
	 * @param x
	 * 		The x-value to be added
	 * @param y
	 * 	 	The y-value to be added
	 * @param z
	 * 		The z-value to be added
	 * @return Returns the this-vector added with the x,y,z values
	 */
	public Vector sum(int x, int y, int z) {
		return new Vector(this.getX() + x, this.getY() + y,
				this.getZ() + z);
	}
	
	/**
	 * Adds values to the x, y and z coordinate
	 * 
	 * @param x
	 * 		The x-value to be added
	 * @param y
	 * 	 	The y-value to be added
	 * @param z
	 * 		The z-value to be added
	 * @return Returns the this-vector added with the x,y,z values
	 */
	public Vector sum(double x, double y, double z) {
		return new Vector(this.getX() + x, this.getY() + y,
				this.getZ() + z);
	}
	/**
	 * Subtracts values to the x, y and z coordinate
	 * 
	 * @param x
	 * 		The x-value to be subtracted
	 * @param y
	 * 	 	The y-value to be subtracted
	 * @param z
	 * 		The z-value to be subtracted
	 * @return Returns the this-vector subtracted with the x,y,z values
	 */
	public Vector subtract(Vector v) {
		return new Vector(this.getX() - v.getX(), this.getY() - v.getY(), 
				this.getZ() - v.getZ());
	}
	
	/**
	 * Subtracts values to the x, y and z coordinate
	 * 
	 * @param x
	 * 		The x-value to be subtracted
	 * @param y
	 * 	 	The y-value to be subtracted
	 * @param z
	 * 		The z-value to be subtracted
	 * @return Returns the this-vector subtracted with the x,y,z values
	 */
	public Vector subtract(int x, int y, int z) {
		return new Vector(this.getX() - x, this.getY() - y,
				this.getZ() - z);
	}
	
	/**
	 * Subtracts values to the x, y and z coordinate
	 * 
	 * @param x
	 * 		The x-value to be subtracted
	 * @param y
	 * 	 	The y-value to be subtracted
	 * @param z
	 * 		The z-value to be subtracted
	 * @return Returns the this-vector subtracted with the x,y,z values
	 */
	public Vector subtract(double x, double y, double z) {
		return new Vector(this.getX() - x, this.getY() - y,
				this.getZ() - z);
	}
	
	/**
	 * Returns the distance between two vectors
	 * 
	 * @param v
	 * 		The seconds vector to be compared
	 * @return Returns the distance between this and v
	 */
	public double distance(Vector v) {
		double P = Math.pow(this.getX() - v.getX(), 2) + Math.pow(this.getY() - v.getY(), 2)
			+ Math.pow(this.getZ() - v.getZ(), 2);
		return Math.sqrt(P);
	}
	
	private final double X;
	private final double Y;
	private final double Z;

	
}