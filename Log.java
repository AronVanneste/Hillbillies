package hillbillies.model;


import hillbillies.model.Unit;
import hillbillies.model.World;

public class Log {
	
	// INITIALISATION
	// If position is a double[]
	/**
	 * 
	 * @param world
	 * 		The world where the log is created
	 * @param position
	 * 		A list of doubles, the position where the log is created
	 * 
	 * @post The position of the log will equal the given position, except when the position is not valid
	 * 
	 * @post The world of the log will equal the given world, except when the world is null
	 * 
	 * @post The weight of the log will be a random number between 10 and 40
	 * 
	 * @post The world will contain the log in its logslist
	 * 
	 * @throws IllegalArgumentException
	 * 		Throws an IllegalArgumentException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is null
	 * 		
	 */
	public Log(World world, double[] position) throws IllegalPositionException, IllegalWorldException {

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
		world.addLog(this);

	}
	
	// If position is an int[]
	/**
	 * 
	 * @param world
	 * 		The world where the log is created
	 * @param position
	 * 		A list of doubles, the position where the log is created
	 * 
	 * @post The position of the log will equal the given position, except when the position is not valid
	 * 
	 * @post The world of the log will equal the given world, except when the world is null
	 * 
	 * @post The weight of the log will be a random number between 10 and 40
	 * 
	 * @post The world will contain the log in its logslist
	 * 
	 * @throws IllegalArgumentException
	 * 		Throws an IllegalArgumentException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is null
	 * 		
	 */
	public Log(World world, int[] position) throws IllegalPositionException, IllegalWorldException {


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
		world.addLog(this);
	}

	// CHECKERS
	// If position is a double[]
	/**
	 * @return Returns true if the given position is within the range of the world of the log
	 */
	private boolean isValidPosition(double[] position) {
		return this.getWorld().isValidPosition(this.getCube());
	}
	
	// If position is an int[]
	/**
	 * @return Returns true if the given position is within the range of the world of the log
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
	 * @return Returns whether or not the current position of the log is passable
	 */
	private boolean isPassable() {
		return (this.getWorld().isPassable(this.getCube()));
	}
	
	/**
	 * @return Returns whether or not the log is supported by a solid cube
	 */
	private boolean isSolidBelow() {
		return (this.getWorld().isSupported(this.getCube()));
	}
	
	//isActive
	/**
	 * @return Returns wheter or not the log is active (i.e. is ownerless)
	 */
	public boolean isActive() {
		return (this.getOwner() == null && !this.isTerminated());
	}
	
	//shouldFall
	/**
	 * @return Returns true if the log is not supported
	 */
	private boolean shouldFall() {
		return !(this.isPassable() && this.isSolidBelow());
	}
	

	//GETCUBE
	/**
	 * @return Returns the coordinates of the cube of the log
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
	 * 		The new position of the log
	 * @post
	 * 		The position of the log will be the given position, if it's a valid position
	 * @throws IllegalArgumentException
	 * 		Throws an IllegalArgumentException if the given position wou
	 *  of the world of the log
	 * 		
	 */
	protected void setPosition(double[] position) throws IllegalPositionException {
		
		if (!isValidPosition(position))
			throw new IllegalPositionException("This is not a valid position");
		else
			this.position = position;
	}
	
	// SET and GET POSITION
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
	 * @return Returns the position as a list of doubles of the log
	 */
	public double[] getPosition() {
		return this.position;
	}
	
	// SET AND GET WORLD
	/**
	 * @param world
	 * 		The world where the log is located
	 * @post
	 * 		The world of the log will be the given world, if it's not null
	 * @post
	 * 		The log will be in the loglist of the world
	 *@throws IllegalWorldException
	 *		Throws an IllegalWorldException if the world is not valid
	 */
	private void setWorld(World world) throws IllegalWorldException {
		if (!isValidWorld(world))
			throw new IllegalWorldException("This world is not valid");
		this.world = world;
		world.addLog(this);
	}
	
	/**
	 * @return Returns the world of the log
	 */
	public World getWorld() {
		return this.world;
	}
	
	// GET WEIGHT
	/**
	 * @return Returns the weight of the log
	 */
	public int getWeight() {
		return this.weight;
	}
	
	// SET and GET Owner
	/**
	 * @param owner
	 * 		The new owner of the log
	 * @post
	 * 		The owner of the log will be the given owner
	 * @post
	 * 		The log could be terminated if its world is terminated
	 * 		and the log is added to the log list of the world if it's active
	 * 		and deleted from the list if it's inactive ????????
	 */
	protected void setOwner(Unit owner) throws IllegalStateException {
		if (this.isTerminated())
			throw new IllegalStateException("Terminated");
		this.owner = owner;
		this.changeInWorld();
	}
	
	/**
	 * @return Returns the owner of the log
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
	 *@param time
	 *		The time within the new position of the log, according to it's ZSpeed, will be calculated
	 *@post
	 *		The new position of the log will be time*ZSpeed lower than the previous one
	 */
	private void changePosition(double time) {
		
		double[] position = {this.getPosition()[0], this.getPosition()[1],
				this.getPosition()[2] + ZSpeed * time};
		this.setPosition(position);
	}
	
	//removeOwner
	/**
	 * @post
	 * 		The log will be ownerless
	 * 		The owner will lose that log
	 */
	protected void removeOwner() {
		try {
			this.getOwner().removeLog();
			this.setOwner(null);
		} catch (NullPointerException e) {};
	}
	
	//changeInWorld
	/**
	 * @post
	 * 		If the world of the log would collapse, the log will be terminated
	 * 		If the log is active (and not terminated) the log will be added to the loglist of the world
	 * 		If the log is unactive (and not terminated) the log will be removed from the loglist of the world ???????
	 */
	private void changeInWorld() {
		if (this.getWorld().isTerminated())
			this.terminate();
		else {
			if (this.isActive())
				this.getWorld().addLog(this);
			else
				this.getWorld().removeLog(this);
		}
	}
	
	/**
	 * @post
	 * 		The log is terminated, it has no owner, no world
	 */
	public void terminate() {
		if (!this.isTerminated()) {
			this.removeOwner();
			this.removeWorld();
			this.isTerminated = true;
		}
	}
	
	/**
	 * 
	 * @return Returns whether or not the log is terminated
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * @return Returns whether or not the logs owner is null
	 */
	public boolean isCarriedByUnit() {
		return this.getOwner() != null;
	}
	
	/**
	 * @post
	 * 		The log is removed from the loglist of it's world, if it has a world
	 */
	private void removeWorld() {
		try {
			this.getWorld().removeLog(this);
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


