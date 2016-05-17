package hillbillies.model;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class Faction {
	
	/**
	 * The initialization of a faction
	 * 
	 * @param world
	 * 		The world where the faction is created
	 * @post
	 * 		The world of the faction will be the given world
	 * @throws IllegalWorldException
	 * 		Throws IllegalWorldException if the world is null
	 * @throws IllegalSchedulerException
	 * 		Throws IllegalSchedulerException if the scheduler is not valid
	 */
	public Faction(World world, Scheduler scheduler) throws IllegalWorldException, 
			IllegalSchedulerException {
		if (!isValidWorld(world))
			throw new IllegalWorldException("World is null");
		if (!isValidScheduler(scheduler))
			throw new IllegalSchedulerException("Scheduler is invalid");
		this.setWorld(world);
		this.setScheduler(scheduler);
	}
	
	/**
	 * The initialization of a faction
	 * 
	 * @param world
	 * 		The world where the faction is created
	 * @post
	 * 		The world of the faction will be the given world
	 * @throws IllegalWorldException
	 * 		Throws IllegalWorldException if the world is null
	 */
	public Faction(World world) throws IllegalWorldException {
		if (!isValidWorld(world))
			throw new IllegalWorldException("World is null");
		this.setWorld(world);
		this.setScheduler(new Scheduler(this));
	}

	
	/**
	 * @param world
	 * 		The world of the faction
	 * @post
	 * 		The world of the faction will be the given world
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
	 * @param scheduler
	 * 	The scheduler of the faction
	 * @post
	 * 	The scheduler of the faction will be the given scheduler
	 */
	private void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
		this.getScheduler().setFaction(this);
	}
	/**
	 * @return Returns the scheduler of the faction
	 */
	public Scheduler getScheduler() {
		return this.scheduler;
	}
	
	/**
	 * Adds a unit to the faction
	 * 
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
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the Faction is terminated
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
		if (unit.getWorld() == null)
			try {
				this.unitInFaction.add(unit);
				unit.setFaction(this);
				this.getWorld().addUnit(unit);
			} catch (IllegalWorldException w) {
				throw w;
			} catch (IllegalUnitException u) {
				throw u;
			} catch (IllegalStateException s) {
				throw s;
			}
		if (unit.getWorld() == this.getWorld())
			this.unitInFaction.add(unit);
	}
	
	/**
	 * Gives a tasks to a given unit
	 * 
	 * @param unit
	 * 	The unit that has to perform a task
	 * 
	 * @post
	 * 	The unit has the task assigned and will start executing it
	 * 
	 * @throws Throws IllegalUnitException if the unit is not part of the faction
	 * 
	 * @throws IllegalArgumentException if the given unit is null
	 * 
	 * @throws NoSuchElementException if there's no valid task for the unit
	 */
	public void giveTaskToUnit(Unit unit) throws IllegalUnitException, IllegalArgumentException, 
			NoSuchElementException {
		if (!isValidUnit(unit))
			throw new IllegalArgumentException("Unit is null");
		if (!this.getUnits().contains(unit))
			throw new IllegalUnitException("Unit is not part of this faction");
		try {
			this.getScheduler().assignTaskToUnit(unit);
		} catch (NoSuchElementException exc) {
			throw exc;
		}
		
		
		
	}
	
	/**
	 * @return Returns a set of all the units belonging to the faction
	 */
	public Set<Unit> getUnits() {
		return this.unitInFaction;
	}
	
	/**
	 * Removes a unit from the faction
	 * 
	 * @param unit
	 * 		The unit that has to be removed from the faction
	 * @post
	 * 		The unit will no longer belong to the faction
	 */
	protected void removeUnit(Unit unit) {
		this.unitInFaction.remove(unit);
	}
	
	/**
	 * Checks whether a faction and a unit are in the same world
	 * @param unit
	 * 		the unit that has to be compared with the faction
	 * @return Returns true if both the unit and the faction belong to the same world and if their world is not null
	 */
	private boolean isInSameWorld(Unit unit) {
		return (this.getWorld() == unit.getWorld() && this.getWorld() != null)
				| unit.getWorld() == null;
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
	public boolean isValidWorld(World world) {
		return world != null;
	}
	
	/**
	 * @return Returns false is the scheduler is null or it belongs to another faction
	 */
	public boolean isValidScheduler(Scheduler scheduler) {
		return scheduler != null && scheduler.getFaction() == null;
	}
	
	/**
	 * 
	 * @param unit
	 * 			The given unit
	 * @return True is the unit is not null and is not terminated else false
	 */
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
	 * Terminates faction, the links to it's world and units are cut
	 * 
	 * @post
	 * 		The faction is terminated
	 * @post
	 * 		The faction is removed from the faction list of its world
	 * @post
	 * 		The units of the faction are terminated
	 */
	public void terminate() {
		if (!this.isTerminated()) {
			this.removeWorld();
			this.removeUnits();
			this.isTerminated = true;
		}
	}
	
	/**
	 * Removes faction from the world
	 * 
	 * @post
	 * 		The faction is removed from the faction list of its world
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
	 * Removes all units from the faction
	 * 
	 * @post
	 * 		All the units of the faction will be terminated
	 */
	private void removeUnits() {
		
		for (Unit unit: this.getUnits()) {
			unit.terminate();
		}
	}
	
	/**
	 * @return Returns whether or not the faction is terminated
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	
	private World world;
	private Set<Unit> unitInFaction = new HashSet<>();
	private int maxUnitsInFaction = 50;
	private boolean isTerminated;
	private Scheduler scheduler;


}