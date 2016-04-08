package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

public class Faction {
	
	/**
	 * @param world
	 * 		The world where the boulder is created
	 * @post
	 * 		The world of the faction will be the given world
	 * @throws IllegalWorldException
	 * 		Throws IllegalWorldException if the world is null
	 */
	public Faction(World world) throws IllegalWorldException {
		if (!isValidWorld(world))
			throw new IllegalWorldException("World is null");
		this.setWorld(world);
	}
	
	/**
	 * @param world
	 * 		The world of the faction
	 * @post
	 * 		The world if the faction will be the given world
	 */
	private void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * @return Returns the world of the faction
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * @param unit
	 * 		The unit that has to be added to the faction
	 * @post
	 * 		The unit will be added to the faction
	 * @throws IllegalWorldException
	 * 		Throws IllegalWorldException if the unit and faction are part of two different worlds
	 * @throws IllegalFactionException
	 * 		Throws IllegalFactionException if the faction is full
	 * @throws IllegalUnitException
	 * 		Throws IllegalUnitException if the unit already belongs to a faction
	 */
	public void addUnit(Unit unit) throws IllegalWorldException, IllegalFactionException, 
		IllegalUnitException, IllegalStateException {
		
		if (!this.isInSameWorld(unit))
			throw new IllegalWorldException("Unit and faction are part of two different worlds");
		if (this.isFull())
			throw new IllegalFactionException("This faction is full");
		if (!isValidUnit(unit))
			throw new IllegalUnitException("Unit already belongs to faction");
		if (this.isTerminated())
			throw new IllegalStateException("Faction is terminated");
		this.unitInFaction.add(unit);
		
	}
	
	/**
	 * @return Returns a set of all the units belonging to the faction
	 */
	public Set<Unit> getUnits() {
		return this.unitInFaction;
	}
	
	/**
	 * @param unit
	 * 		The unit that has to be removed from the faction
	 * @post
	 * 		The unit will no longer belong to the faction
	 */
	protected void removeUnit(Unit unit) {
		this.unitInFaction.remove(unit);
	}
	
	/**
	 * @param unit
	 * 		the unit that has to be compared with the faction
	 * @return Returns true if both the unit and the faction belong to the same world and if their world is not null
	 */
	private boolean isInSameWorld(Unit unit) {
		return (this.getWorld() == unit.getWorld() && this.getWorld() != null);
	}
	
	/**
	 * @return Returns whether or not the faction has reached its maximum capacity
	 */
	private boolean isFull() {
		return (unitInFaction.size() == maxUnitsInFaction);
	}
	
	/**
	 * @return Returns whether or not the world is valid (i.e. not null)
	 */
	private boolean isValidWorld(World world) {
		return world != null;
	}
	
	private boolean isValidUnit(Unit unit) {
		return unit != null && !unit.isTerminated();
	}
	
	/**
	 * @return Returns the number of units in the faction
	 */
	public int getNbUnit() {
		return this.getUnits().size();
	}
	
	/**
	 * @post
	 * 		The faction is terminated
	 * @post
	 * 		The faction is removed from the factionlist of its world
	 * @post
	 * 		The units of the faction are terminated??????
	 */
	public void terminate() {
		if (!this.isTerminated()) {
			this.removeWorld();
			this.removeUnits();
			this.isTerminated = true;
		}
	}
	
	/**
	 * @post
	 * 		The faction is removed from the factionlist of its world
	 * @post
	 * 		The world of the faction is null
	 */
	private void removeWorld() {
		
		try {
			this.getWorld().removeFaction(this);
			this.setWorld(null);
		} catch (NullPointerException e) {};
	}
	
	/**
	 * @post
	 * 		All the units of the faction will be terminated
	 */
	private void removeUnits() {
		
		for (Unit unit: this.getUnits()) {
			unit.terminate();
		}
	}
	
	/**
	 * @return Returns whether or not the fcation is terminated
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	
	private World world;
	private Set<Unit> unitInFaction = new HashSet<>();
	private int maxUnitsInFaction = 50;
	private boolean isTerminated;


}
