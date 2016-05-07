package hillbillies.model;


import hillbillies.model.World;

public class Log extends Raw {

	public Log(World world, double[] position) throws IllegalPositionException, IllegalWorldException {
		super(world, position);
	}
	
	public Log(World world, int[] position) throws IllegalPositionException, IllegalWorldException {
		super(world, position);
	}
	
}


