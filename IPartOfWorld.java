package hillbillies.model;

public interface IPartOfWorld {
	
	public abstract World getWorld();
	
	public abstract boolean isValidPosition(int[] position);
	
	public abstract boolean isValidPosition(double[] position);
	
	public default boolean isValidWorld(World world) {
		return (world != null);
	}
	
	public abstract boolean isPassable(int[] cube);
	
	public abstract int[] getCubeInt();

}
