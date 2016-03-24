package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

public class Faction {
	
	public Faction(World world) {
		this.setWorld(world);
	}
	
	private void setWorld(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public void addUnit(Unit unit) {
		
		if (!this.isInSameWorld(unit))
			throw new IllegalWorldException();
		if (this.isFull() || (unit.getFaction() != null))
			throw new IllegalFactionException();	
		this.unitInFaction.add(unit);
		
	}
	
	public List<Unit> getUnits() {
		return this.unitInFaction;
	}
	
	public void removeUnit(Unit unit) {
		this.unitInFaction.remove(unit);
	}
	
	private boolean isInSameWorld(Unit unit) {
		return (this.getWorld() == Unit.getWorld());
	}
	
	private boolean isFull() {
		return (unitInFaction.size() == maxUnitsInFaction);
	}
	
	public int getNbUnit() {
		return this.getUnits().size();
	}
	
	
	
	private World world;
	private List<Unit> unitInFaction = new ArrayList<>();
	private int maxUnitsInFaction = 50;


}
