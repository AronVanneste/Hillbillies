package hillbillies.model;

public class Boulder extends Raw {
	/**
	 * Initialization of a Boulder
	 * 
	 * @param world
	 * 		The world of the Boulder
	 * @param position
	 * 		The position of the Boulder
	 * @throws IllegalPositionException
	 * 		Throws IllegalPositionException if the given position is not valid
	 * @throws IllegalWorldException
	 * 		Throws IllegalWorldException if the given world is null
	 */
	public Boulder(World world, double[] position) throws IllegalPositionException, 
				IllegalWorldException {
		super(world, position);
	}
	/**
	 * Initialization of a Boulder
	 * 
	 * @param world
	 * 		The world of the Boulder
	 * @param position
	 * 		The position of the Boulder
	 * @throws IllegalPositionException
	 * 		Throws IllegalPositionException if the given position is not valid
	 * @throws IllegalWorldException
	 * 		Throws IllegalWorldException if the given world is null
	 */
	public Boulder(World world, int[] position) throws IllegalPositionException, 
				IllegalWorldException {
		super(world, position);
	}
	
}