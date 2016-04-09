package hillbillies.model;

public class Boulder {
	
	// INITIALISATION
	// If position is a double[]
	
	/**
	 * The initialization of a boulder
	 * 
	 * @param world
	 * 		The world where the boulder is created
	 * @param position
	 * 		A list of doubles, the position where the boulder is created
	 * 
	 * @post The position of the boulder will equal the given position, except when the position is not valid
	 * 
	 * @post The world of the boulder will equal the given world, except when the world is null
	 * 
	 * @post The weight of the boulder will be a random number between 10 and 40
	 * 
	 * @post The world will contain the boulder in its boulderlist
	 * 
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is null
	 * 		
	 */
	public Boulder(World world, double[] position) throws IllegalPositionException, IllegalWorldException {
		
		try {
			this.setPosition(position);
		} catch (IllegalPositionException invalidPosition) {
			throw invalidPosition;
		}
	
		try { 
			this.setWorld(world);
		} catch (IllegalWorldException invalidWorld) {
			throw invalidWorld;
		}
		
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		world.addBoulder(this);

	}
	
	// If position is an int[]
	
	/**
	 * The initialization of a boulder
	 * 
	 * @param world
	 * 		The world where the boulder is created
	 * @param position
	 * 		A list of doubles, the position where the boulder is created
	 * 
	 * @post The position of the boulder will equal the given position, except when the position is not valid
	 * 
	 * @post The world of the boulder will equal the given world, except when the world is null
	 * 
	 * @post The weight of the boulder will be a random number between 10 and 40
	 * 
	 * @post The world will contain the boulder in its boulderlist
	 * 
	 * @throws IllegalPositionException
	 * 		Throws an IllegalArgumentException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is null
	 * 		
	 */
	public Boulder(World world, int[] position) throws IllegalPositionException, IllegalWorldException {
		try {
			this.setPosition(position);
		} catch (IllegalPositionException invalidPosition) {
			throw invalidPosition;
		}
	
		try { 
			this.setWorld(world);
		} catch (IllegalWorldException invalidWorld) {
			throw invalidWorld;
		}
		
		
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		world.addBoulder(this);
	}

	// CHECKERS
	// If position is a double[]
	
	/**
	 * @return Returns true if the given position is within the range of the world of the boulder
	 */
	
	private boolean isValidPosition(double[] position) {
		return this.getWorld().isValidPosition(this.getCube());
	}
	
	// If position is an int[]
	/**
	 * @return Returns true if the given position is within the range of the world of the boulder
	 */
	private boolean isValidPosition(int[] position) {
		return this.getWorld().isValidPosition(this.getCube());
	}
	
	//World
	
	/**
	 * @return Returns whether or not the world is valid (i.e. not null)
	 */
	private boolean isValidWorld(World world) {
		return (world != null);
	}
	
	//isPassable
	
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
	
	//isActive
	
	/**
	 * @return Returns wheter or not the boulder is active (i.e. is ownerless)
	 */
	public boolean isActive() {
		return (this.getOwner() == null && !this.isActive());
	}
	
	//shouldFall
	
	/**
	 * @return Returns true if the boulder is not supported
	 */
	private boolean shouldFall() {
		return !(this.isPassable() && this.isSolidBelow());
	}
	

	//GETCUBE
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
	
	// SET and GET POSITION
	/**
	 * @param position
	 * 		The new position of the boulder
	 * @post
	 * 		The position of the boulder will be the given position, if it's a valid position
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position wou
	 *  of the world of the boulder
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
	 * 		The new position of the log
	 * @post
	 * 		The position of the log will be the given position, if it's a valid position
	 * @throws IllegalArgumentException
	 * 		Throws an IllegalArgumentException if the given position wou
	 *  of the world of the log
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
	
	// SET AND GET WORLD
	/**
	 * @param world
	 * 		The world where the boulder is located
	 * @post
	 * 		The world of the boulder will be the given world, if it's not null
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
	
	// GET WEIGHT
	
	/**
	 * @return Returns the weight of the boulder
	 */
	public int getWeight() {
		return this.weight;
	}
	
	// SET and GET Owner
	/**
	 * @param owner
	 * 		The new owner of the boulder
	 * @post
	 * 		The owner of the boulder will be the given owner
	 * @post
	 * 		The boulder could be terminated if its world is terminated
	 * and the boulder is added to the boulder list of the world if it's active
	 * and deleted from the list if it's unactive ????????
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
	
	
	
	// ADVANCE TIME
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
	
	// HELPFUNCTIONS
	// changePosition
	// In tegenstelling tot bij Unit moet hier nooit de positie worden teruggezet. De maximale afstand die 
	// de boulder kan afleggen is ZSpeed * time en time is maximaal 0.2, dus maximale afstand = -3 * 0.2 = -0.6.
	// M.a.w., de boulder/log blijft in dezelfde cube of zakt er 1. Hij kan er dus nooit twee zakken in 1 stap en
	// terechtkomen in een cube die niet bestaat.
	
	/**
	 * The position of the boulder is updated
	 * 
	 *@param time
	 *		The time within the new position of the boulder, according to it's ZSpeed, will be calculated
	 *@post
	 *		The new position of the boulder will be time*ZSpeed lower than the previous one
	 */
	private void changePosition(double time) {
		
		double[] position = {this.getPosition()[0], this.getPosition()[1],
				this.getPosition()[2] + ZSpeed * time};
		this.setPosition(position);
	}
	
	//removeOwner
	
	/**
	 * The owner of the boulder will be removed and vice versa
	 * 
	 * @post
	 * 		The boulder will be ownerless
	 * 		The owner will lose that boulder
	 */
	protected void removeOwner() {
		try {
			this.getOwner().removeBoulder();
			this.setOwner(null);
		} catch (NullPointerException e) {};
	}
	
	//changeInWorld
	/**
	 * Applies changes in the world or in the boulders status to the boulder
	 * 
	 * @post
	 * 		If the world of the boulder would collapse, the boulder will be terminated
	 * 		If the boulder is active (and not terminated) the boulder will be added to the boulderlist of the world
	 * 		If the boulder is unactive (and not terminated) the boulder will be removed from the boulderlist of the world ???????
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
	 * Terminates the boulder, cuts the links attached to it's owner and world
	 * 
	 * @post
	 * 		The boulder is terminated, it has no owner, no world
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
	 * @return Returns whether or not the unit it's owner is null
	 */
	public boolean isCarriedByUnit() {
		return this.getOwner() != null;
	}
	
	/**
	 * The boulder will be removed from the world
	 * 
	 * @post
	 * 		The boulder is removed from the boulderlist of it's world, if it has a world
	 */
	private void removeWorld() {
		try {
			this.getWorld().removeBoulder(this);
			this.setWorld(null);
		} catch (NullPointerException e) {};
	}
	
	
	

	// VARIABLES 
	private double[] position;
	private final int weight;
	private final double ZSpeed = -3.0;
	private World world;
	private Unit owner = null;
	private boolean isTerminated;

}


