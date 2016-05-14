package hillbillies.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Vector {	
	
	
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
	
	public boolean equals(Vector vector) {
		if ((this.getX() == vector.getX()) && (this.getY() == vector.getY())
				&& (this.getZ() == vector.getZ()))
			return true;
		else
			return false;
	}
	
	public Vector sum(Vector vector) {
		return new Vector(this.getX() + vector.getX(), this.getY() + vector.getY(),
					this.getZ() + vector.getZ());
	}
	
	public Vector sum(int x, int y, int z) {
		return new Vector(this.getX() + x, this.getY() + y,
				this.getZ() + z);
	}
	
	public Vector sum(double x, double y, double z) {
		return new Vector(this.getX() + x, this.getY() + y,
				this.getZ() + z);
	}
	
	public Vector subtract(Vector v) {
		return new Vector(this.getX() - v.getX(), this.getY() - v.getY(), 
				this.getZ() - v.getZ());
	}
	
	public Vector subtract(int x, int y, int z) {
		return new Vector(this.getX() - x, this.getY() - y,
				this.getZ() - z);
	}
	
	public Vector subtract(double x, double y, double z) {
		return new Vector(this.getX() - x, this.getY() - y,
				this.getZ() - z);
	}
	
	public double distance(Vector v) {
		double P = Math.pow(this.getX() - v.getX(), 2) + Math.pow(this.getY() - v.getY(), 2)
			+ Math.pow(this.getZ() - v.getZ(), 2);
		return Math.sqrt(P);
	}
	
	private final double X;
	private final double Y;
	private final double Z;

	
}
