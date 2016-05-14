package hillbillies.model;

public interface IPerform {
	/**
	 * 
	 * @param unit
	 * 		The unit assigned
	 */
	public abstract void setUnit(Unit unit);
	
	/**
	 * 
	 * @return Returns the assigned unit
	 */
	public abstract Unit getUnit();
	
	/**
	 * 
	 * @return Returns whether or not it has a unit assigned
	 */
	public abstract boolean isAssigned();
	

}