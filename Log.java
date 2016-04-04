package hillbillies.model;


import hillbillies.model.Unit;
import hillbillies.model.World;

public class Log {
	
	// INITIALISATION
	// If position is a double[]
	public Log(World world, double[] position) throws IllegalArgumentException, IllegalWorldException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException("This is not a valid position");
		if (!isValidWorld(world))
			throw new IllegalWorldException("World is null");
		
		this.setPosition(position);
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		this.setWorld(world);
		world.addLog(this);

	}
	
	// If position is an int[]
	public Log(World world, int[] position) throws IllegalArgumentException, IllegalWorldException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException("This is not a valid position");
		if (!isValidWorld(world))
			throw new IllegalWorldException("World is null");
		
		double[] Pos = {(double) (position[0]), (double) (position[1]), (double) (position[2])};
		this.setPosition(Pos);
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		this.setWorld(world);
		world.addLog(this);
	}

	// CHECKERS
	// If position is a double[]
	private boolean isValidPosition(double[] position) {
		return this.getWorld().isValidPosition(this.getCube());
	}
	
	// If position is an int[]
	private boolean isValidPosition(int[] position) {
		return this.getWorld().isValidPosition(this.getCube());
	}
	
	//World
	private boolean isValidWorld(World world) {
		return (world != null);
	}
	
	//isPassable
	private boolean isPassable() {
		return (this.getWorld().isPassable(this.getCube()));
	}
	
	
	private boolean isSolidBelow() {
		return (this.getCube()[2] == 0 | (!this.getWorld().isPassable(this.getCube()[0], this.getCube()[1],
				this.getCube()[2] - 1)));
	}
	
	//isActive
	public boolean isActive() {
		return this.getOwner() == null;
	}
	
	//shouldFall
	private boolean shouldFall() {
		return !(this.isPassable() && this.isSolidBelow());
	}
	

	//GETCUBE
	public int[] getCube() {
		
		int X = (int) Math.floor(this.getPosition()[0]);
		int Y = (int) Math.floor(this.getPosition()[1]);
		int Z = (int) Math.floor(this.getPosition()[2]);
		
		int[] cube = {X, Y, Z};
		return cube;
		
	}
	
	// SET and GET POSITION
	public void setPosition(double[] position) throws IllegalArgumentException {
		
		if (!isValidPosition(position))
			throw new IllegalArgumentException("This is not a valid position");
		else
			this.position = position;
	}
	
	public double[] getPosition() {
		return this.position;
	}
	
	// SET AND GET WORLD
	private void setWorld(World world) throws IllegalWorldException {
		if (!isValidWorld(world))
			throw new IllegalWorldException("This world is not valid");
		this.world = world;
		world.addLog(this);
	}
	
	public World getWorld() {
		return this.world;
	}
	
	// GET WEIGHT
	public int getWeight() {
		return this.weight;
	}
	
	// SET and GET Owner
	public void setOwner(Unit owner) {
		this.owner = owner;
		this.changeInWorld();
	}
	
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
	private void changePosition(double time) {
		
		double[] position = {this.getPosition()[0], this.getPosition()[1],
				this.getPosition()[2] + ZSpeed * time};
		this.setPosition(position);
	}
	
	//removeOwner
	public void removeOwner() {
		try {
			this.getOwner().removeLog();
			this.setOwner(null);
		} catch (NullPointerException e) {};
	}
	
	//changeInWorld
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
	
	public void terminate() {
		if (!this.isTerminated()) {
			this.removeOwner();
			this.removeWorld();
			this.isTerminated = true;
		}
	}
	
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	public boolean isCarriedByUnit() {
		return this.getOwner() != null;
	}
	
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


