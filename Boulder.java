package hillbillies.model;

public class Boulder {
		
	/**
	 * The initialization of a boulder
	 * 
	 * @param world
	 * 		The world in which the boulder is created
	 * @param position
	 * 		An array of doubles, the position where the boulder is created
	 * 
	 * @post The position of the boulder will equal the given position
	 * 
	 * @post The world of the boulder will equal the given world
	 * 
	 * @post The weight of the boulder will be a random number between 10 and 40
	 * 
	 * @post The world will contain the boulder in its boulderlist
	 * 
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is is not valid
	 * 		
	 */
	public Boulder(World world, double[] position) throws IllegalPositionException, IllegalWorldException {
		
		try { 
			this.setWorld(world);
		} catch (IllegalWorldException invalidWorld) {
			throw invalidWorld;
		}
		
		try {
			this.setPosition(position);
		} catch (IllegalPositionException invalidPosition) {
			throw invalidPosition;
		}
	
		
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		world.addBoulder(this);

	}
	
	
	/**
	 * The initialization of a boulder
	 * 
	 * @param world
	 * 		The world in which the boulder is created
	 * @param position
	 * 		An array of integers, the position where the boulder is created
	 * 
	 * @post The position of the boulder will equal the given position
	 * 
	 * @post The world of the boulder will equal the given world
	 * 
	 * @post The weight of the boulder will be a random number between 10 and 40
	 * 
	 * @post The world will contain the boulder in its boulderlist
	 * 
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is is not valid
	 * 		
	 */
	public Boulder(World world, int[] position) throws IllegalPositionException, IllegalWorldException {
		
		try { 
			this.setWorld(world);
		} catch (IllegalWorldException invalidWorld) {
			throw invalidWorld;
		}
		
		
		try {
			this.setPosition(position);
		} catch (IllegalPositionException invalidPosition) {
			throw invalidPosition;
		}
	
		
		
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		world.addBoulder(this);
	}

	/**
	 * @return Returns true if the given position is within the range of the world of the boulder
	 */
	private boolean isValidPosition(double[] position) {
		return this.getWorld().isValidPosition(position);
	}
	
	/**
	 * @return Returns true if the given position is within the range of the world of the boulder
	 */
	private boolean isValidPosition(int[] position) {
		return this.getWorld().isValidPosition(position);
	}
	

	/**
	 * @return Returns whether or not the world is valid (i.e. not null)
	 */
	private boolean isValidWorld(World world) {
		return (world != null);
	}
	

	/**
	 * @return Returns whether or not the current position of the boulder is passable
	 */
	private boolean isPassable() {
		return (this.getWorld().isPassable(this.getCube()));
	}
	
	/**
	 * @return Returns whether or not the boulder is supported by a solid cube
	 */
	private boolean isSolidBelow() {
		return (this.getWorld().isSupported(this.getCube()));
	}
	
	
	/**
	 * @return Returns whether or not the boulder is active (i.e. is without owner and not terminated)
	 */
	public boolean isActive() {
		return (this.getOwner() == null && !this.isTerminated());
	}
	
	
	/**
	 * @return Returns true if the boulder is not supported
	 */
	private boolean shouldFall() {
		return !(this.isPassable() && this.isSolidBelow());
	}
	

	/**
	 * @return Returns the coordinates of the cube of the boulder
	 */
	public int[] getCube() {
		
		int X = (int) Math.floor(this.getPosition()[0]);
		int Y = (int) Math.floor(this.getPosition()[1]);
		int Z = (int) Math.floor(this.getPosition()[2]);
		
		int[] cube = {X, Y, Z};
		return cube;
		
	}
	
	/**
	 * @param position
	 * 		The new position of the boulder
	 * @post
	 * 		The position of the boulder will be the given position
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position is invalid
	 * 		
	 */
	protected void setPosition(double[] position) throws IllegalPositionException {
		
		if (!isValidPosition(position))
			throw new IllegalPositionException("This is not a valid position");
		else
			this.position = position;
	}
	
	/**
	 * @param position
	 * 		The new position of the boulder
	 * @post
	 * 		The position of the boulder will be the given position
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position is invalid
	 * 		
	 */
	protected void setPosition(int[] position) throws IllegalPositionException {
		
		if (!isValidPosition(position))
			throw new IllegalPositionException("This is not a valid position");
		else {
			double X = ((double) position[0]);
			double Y = ((double) position[1]);
			double Z = ((double) position[2]);
			double[] pos = {X, Y, Z};
			this.position = pos; 
		}
	}
	/**
	 * @return Returns the position as a list of doubles of the boulder
	 */
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * @param world
	 * 		The world in which the boulder is created
	 * @post
	 * 		The world of the boulder will be the given world
	 * @post
	 * 		The boulder will be in the boulderlist of the world
	 *@throws IllegalWorldException
	 *		Throws an IllegalWorldException if the world is not valid
	 */
	private void setWorld(World world) throws IllegalWorldException {
		if (!isValidWorld(world))
			throw new IllegalWorldException("This world is not valid");
		this.world = world;
		world.addBoulder(this);
	}
	
	/**
	 * @return Returns the world of the boulder
	 */
	public World getWorld() {
		return this.world;
	}
	
	
	/**
	 * @return Returns the weight of the boulder
	 */
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * @param owner
	 * 		The new owner of the boulder
	 * @post
	 * 		The owner of the boulder will be the given owner
	 */
	protected void setOwner(Unit owner) throws IllegalStateException {
		if (this.isTerminated())
			throw new IllegalStateException("Terminated");
		this.owner = owner;
		this.changeInWorld();
	}
	
	/**
	 * @return Returns the owner of the boulder
	 */
	public Unit getOwner() {
		return this.owner;
	}
	
	
	
	public void advanceTime(double time) throws IllegalArgumentException, IllegalStateException {
		
		if (this.isTerminated())
			throw new IllegalStateException("Terminated");
		if ((time <= 0) | (time > 0.2))
			throw new IllegalArgumentException("Given time is not valid");
		else {
			if (this.isActive()) {
				if (this.shouldFall())
					this.changePosition(time);
			}	
		}
		
	}
	
	/**
	 * The position of the boulder is updated
	 * 
	 *@param time
	 *		The given time (seconds)
	 *@post
	 *		The new position of the boulder will be (time * ZSpeed) lower than the previous one
	 */
	private void changePosition(double time) {
		
		double[] position = {this.getPosition()[0], this.getPosition()[1],
				this.getPosition()[2] + ZSpeed * time};
		this.setPosition(position);
	}
	
	
	/**
	 * The owner of the boulder will be removed and vice versa
	 * 
	 * @post
	 * 		The boulder will be without owner
	 * @post 		
	 * 		The owner (unit) will not longer carry this boulder
	 */
	protected void removeOwner() {
		try {
			this.getOwner().removeBoulder();
			this.setOwner(null);
		} catch (NullPointerException e) {};
	}
	
	/**
	 * Applies changes in the world or in the boulders status to the boulder
	 * @post
	 * 		If the world of the boulder is terminated, the boulder will terminate as well
	 * 		If the boulder is active, the boulder will be added to the boulder list of the world
	 * 		If the boulder is inactive, the boulder will be removed from the boulder list of the world
	 */
	private void changeInWorld() {
		if (this.getWorld().isTerminated())
			this.terminate();
		else {
			if (this.isActive())
				this.getWorld().addBoulder(this);
			else
				this.getWorld().removeBoulder(this);
		}
	}
	
	/**
	 *  Terminates the boulder, cuts the links attached to it's owner and world
	 *  
	 * @post
	 * 		The boulder is terminated. It has no owner and is not longer part of a world
	 */
	public void terminate() {
		if (!this.isTerminated()) {
			this.removeOwner();
			this.removeWorld();
			this.isTerminated = true;
		}
	}
	
	/**
	 * @return Returns whether or not the boulder is terminated
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * @return Returns whether or not this boulder has an owner
	 */ 
	public boolean isCarriedByUnit() {
		return this.getOwner() != null;
	}
	
	/**
	 * @post
	 * 		The boulder is removed from the boulder list of it's world
	 */
	private void removeWorld() {
		try {
			this.getWorld().removeBoulder(this);
			this.setWorld(null);
		} catch (NullPointerException e) {};
	}
	
	
	

	private double[] position;
	private final int weight;
	private final double ZSpeed = -3.0;
	private World world;
	private Unit owner = null;
	private boolean isTerminated;

}


