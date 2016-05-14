package hillbillies.model;


import hillbillies.model.World;

public class Log extends Raw {
	/**
	 * The initialization of a log
	 * 
	 * @param world
	 * 		The world in which the log is created
	 * @param position
	 * 		An array of doubles, the position where the log is created
	 * 
	 * @post The position of the log will equal the given position
	 * 
	 * @post The world of the log will equal the given world
	 * 
	 * @post The weight of the log will be a random number between 10 and 40
	 * 
	 * @post The world will contain the log in its log list
	 * 
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is null
	 * 		
	 */
	public Log(World world, double[] position) throws IllegalPositionException, IllegalWorldException {
		super(world, position);
	}
	/**
	 * The initialization of a log
	 * 
	 * @param world
	 * 		The world in which the log is created
	 * @param position
	 * 		An array of doubles, the position where the log is created
	 * 
	 * @post The position of the log will equal the given position
	 * 
	 * @post The world of the log will equal the given world
	 * 
	 * @post The weight of the log will be a random number between 10 and 40
	 * 
	 * @post The world will contain the log in its log list
	 * 
	 * @throws IllegalPositionException
	 * 		Throws an IllegalPositionException if the given position is not valid
	 * 
	 * @throws IllegalWorldException
	 * 		Throws an IllegalWorldException if the given world is null
	 * 		
	 */
	public Log(World world, int[] position) throws IllegalPositionException, IllegalWorldException {
		super(world, position);
	}
	
}