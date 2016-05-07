package hillbillies.model;

public class Boulder extends Raw {

	public Boulder(World world, double[] position) throws IllegalPositionException, 
				IllegalWorldException {
		super(world, position);
	}
	
	public Boulder(World world, int[] position) throws IllegalPositionException, 
				IllegalWorldException {
		super(world, position);
	}
	
}