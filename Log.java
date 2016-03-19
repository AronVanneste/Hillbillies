package hillbillies.model;

public class Log {
	/**
	 * 
	 * @param world
	 * 			The world where the log is created
	 * @param position
	 * 			The position where the log will occur
	 * @throws IllegalArgumentException
	 * 			Throws an IllegalArgumentException if the position is not valid
	 */
	public Log(World world, double[] position) throws IllegalArgumentException{
		if (!world.isValidPosition(position))
			throw new IllegalArgumentException("This is not a valid position");
		else {
			this.position = position;}
		this.weight = (int) Math.round(Math.random() * 40 + 10);
		this.setWorld(world);
	}
	
	
	/**
	 * @param world
	 * 			Assigns the log to a certain world
	 */
	public void setWorld(World world) {
		this.world = world;
	}
	/**
	 * 
	 * @return
	 * 		Returns the world where the log is located
	 */
	public World getWorld() {
		return this.world;
	}
	/**
	 * 
	 * @param position
	 * 		Changes the position of the log
	 */
	public void setPosition(double[] position) {
		this.position = position;
	}
	/**
	 * 
	 * @return
	 * 		Returns the position of the log
	 */
	public double[] getPosition() {
		return this.position;
	}

	public void advanceTime(double time, World world) {
		
		for (Log log : world.getAllLogs()) {
				double[] position = log.getPosition();
		
				if (!world.isSupported(position)) {
					double speed = this.getZSpeed();
					double[] originalPosition = this.getPosition();
					double[] newPosition = {originalPosition[0], originalPosition[1], originalPosition[2] + speed * time};
					this.setPosition(newPosition);
					}
		}
	}
	/**
	 * 
	 * @return
	 * 		Returns the speed at which the log will fall if it's not supported
	 */
	public double getZSpeed() {
		return this.ZSpeed;
	}
	/**
	 * 
	 * @return
	 * 		Returns the coordinates of the cube where the log is located
	 */
	public int[] getCube() {
		double[] position = this.getPosition();
		double X = position[0];
		double Y = position[1];
		double Z = position[2];	
		
		int XCube = (int) Math.floor(X);
		int YCube = (int) Math.floor(Y);
		int ZCube = (int) Math.floor(Z);
		int[] cube = {XCube,YCube,ZCube};
		return cube;
	}
	/**
	 * 
	 * @return
	 * 		Returns wheter the log is carried or just lays on the ground
	 */
	public boolean getCarried() {
		return this.carried;
	}
	/**
	 * 
	 * @param carried
	 * 		Changes the carried status of the log
	 */
	public void setCarried(boolean carried) {
		this.carried = carried;
	}
	
	
	private double[] position;
	private final int weight;
	private World world;
	private double ZSpeed = -3.0;
	private boolean carried;
}
