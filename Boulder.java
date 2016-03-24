package hillbillies.model;

public class Boulder {
	
	// INITIALISATION
	// If position is a double[]
	public Boulder(World world, double[] position) throws IllegalArgumentException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException("This is not a valid position");
		if (!isValidWorld(world))
			throw new NullPointerException("World is null");
		
		this.setPosition(position);
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		this.setWorld(world);
		world.addToBoulderList(this);

	}
	
	// If position is an int[]
	public Boulder(World world, int[] position) throws IllegalArgumentException, NullPointerException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException("This is not a valid position");
		if (!isValidWorld(world))
			throw new NullPointerException("World is null");
		
		double[] Pos = {(double) (position[0]), (double) (position[1]), (double) (position[2])};
		this.setPosition(Pos);
		this.weight = ((int) Math.round(Math.random() * 40 + 10));
		this.setWorld(world);
		world.addToBoulderList(this);
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
		return (world.isPassable(this.getCube()));
	}
	
	//isActive
	public boolean isActive() {
		if (this.getOwner() != null)
			return true;
		else
			return false;
	}
	
	//shouldFall
	private boolean shouldFall() {
		
		if (this.isPassable())
			return false;
		else
			return true;
	}
	

	//GETCUBE
	public int[] getCube() {
		
		int X = (int) Math.floor(this.getPosition()[0]);
		int Y = (int) Math.floor(this.getPosition()[1]);
		int Z = (int) Math.floor(this.getPosition()[2]);
		
		int[] Cube = {X, Y, Z};
		return Cube;
		
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
	private void setWorld(World world) {
		this.world = world;
		world.addToBoulderList(this);
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
	public void advanceTime(double time) throws IllegalArgumentException {
		
		if ((time <= 0) | (time > 0.2))
			throw new IllegalArgumentException();
		else {
			if (this.isActive()) {
				if (this.shouldFall())
					this.changePosition(time);
			}	
		}
		
	}
	
	// HELPFUNCTIONS
	// changePosition
	private void changePosition(double time) {
		
		double[] position = {this.getPosition()[0], this.getPosition()[1],
				this.getPosition()[2] + ZSpeed * time};
		this.setPosition(position);
	}
	
	//removeOwner
	public void removeOwner() {
		this.setOwner(null);
	}
	
	//changeInWorld
	private void changeInWorld() {
		if (this.isActive())
			world.addToBoulderList(this);
		else
			world.removeBoulderFromList(this);
	}

	// VARIABLES 
	private double[] position;
	private final int weight;
	private final double ZSpeed = -3.0;
	private World world;
	private Unit owner = null;

}


// NOG TE DOEN
//	Functies om Boulder/Log toe te wijzen aan Unit. Als dit gebeurt, moet ook de Boulder een owner toegewezen krijgen.
//	Nadat de Unit de Boulder weggooit, moet de connectie tussen beide verdwijnen. Een unit met een boulder heeft een
//	ander gewicht dat niet meer moet voldoen aan de opgelegde voorwaarden.
//	Als de unit de boulder dan loslaat, moet de boulder de positie van de unit krijgen
