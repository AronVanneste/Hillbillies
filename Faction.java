package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

public class Faction {
	
	public Faction(World world) throws IllegalWorldException {
		if (!isValidWorld(world))
			throw new IllegalWorldException("World is null");
		this.setWorld(world);
	}
	
	private void setWorld(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public void addUnit(Unit unit) throws IllegalWorldException, IllegalFactionException, 
		IllegalUnitException {
		
		if (!this.isInSameWorld(unit))
			throw new IllegalWorldException("Unit and faction are part of two different worlds");
		if (this.isFull())
			throw new IllegalFactionException("This faction is full");
		if (unit.getFaction() != null)
			throw new IllegalUnitException("Unit already belongs to faction");
		this.unitInFaction.add(unit);
		
	}
	
	public Set<Unit> getUnits() {
		return this.unitInFaction;
	}
	
	protected void removeUnit(Unit unit) {
		this.unitInFaction.remove(unit);
	}
	
	private boolean isInSameWorld(Unit unit) {
		return (this.getWorld() == unit.getWorld() && this.getWorld() != null);
	}
	
	private boolean isFull() {
		return (unitInFaction.size() == maxUnitsInFaction);
	}
	
	private boolean isValidWorld(World world) {
		return world != null;
	}
	
	public int getNbUnit() {
		return this.getUnits().size();
	}
	
	public void terminate() {
		if (!this.isTerminated()) {
			this.removeWorld();
			this.removeUnits();
			this.isTerminated = true;
		}
	}
	
	private void removeWorld() {
		try {
			this.getWorld().removeFaction(this);
			this.setWorld(null);
		} catch (NullPointerException e) {};
	}
	
	private void removeUnits() {
		
		for (Unit unit: this.getUnits()) {
			unit.terminate();
		}
	}
	
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	
	private World world;
	private Set<Unit> unitInFaction = new HashSet<>();
	private int maxUnitsInFaction = 50;
	private boolean isTerminated;


}
