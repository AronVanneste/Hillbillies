package hillbillies.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	// INITIALISATION
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) {
		
		this.setTerrainTypeWorld(terrainTypes);
		this.setTerrainChangeListener(modelListener);
		
		
	}
	
	// ADVANCE TIME
	public void advanceTime(double time) throws IllegalArgumentException {
		
		if ((time <= 0) | (time > 0.2))
			throw new IllegalArgumentException();
		else {
			for (int[] position: this.getListToCave()) {
				this.cave(position);
				this.changeSolidToPassable();
			}
		}
		
	}
	
	// CAVE
	private void cave(int[] position) {
		
		this.setTerrainType(0, position);
		
		double P = Math.random();
		if (P <= 0.25)
			this.throwRawMaterial(position);
		
	}
	
	private void throwRawMaterial(int[] position) {
		
		if (this.getTerrainType(position) == 1)
			new Boulder(this, position);
		else if (this.getTerrainType(position) == 2)
			new Log(this, position);

		
		
	}
	
	// SET TERRAIN TYPE FOR ONE CUBE
	public void setTerrainType(int terrainType, int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		int[][][] terrain = this.getTerrainTypeWorld();
		terrain[X][Y][Z] = terrainType;
		this.setTerrainTypeWorld(terrain);

	}
	
	public int getTerrainType(int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return this.getTerrainTypeWorld()[X][Y][Z];
	}
	
	// CHANGE SOLID TO CUBE AND GET ALL CUBES TO CAVE 
	private void changeSolidToPassable() {
		
		for (int x = 0; x < this.getNbX(); x++) {
			for (int y = 0; y < this.getNbY(); y++) {
				for (int z = 0; z < this.getNbZ(); z++) {
					if (isPassable(this.getTerrainTypeWorld()[x][y][z]))
						this.addToListToCave((connectedToBorder.changeSolidToPassable(x, y, z)));
					
				}
			}
		}
		
		
	}
	
	// CHECKER
	
	public boolean isValidSpawnPosition(int X, int Y, int Z) {
		int[] position = {X, Y, Z};
		return (isPassable(position) && isSupported(position));		
	}
	
	
	public boolean isValidSpawnPosition(int[] position) {
		return (isPassable(position) && isSupported(position));		
	}
	
	public boolean isSupported(int[] position) {
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		
		return ((Z == 0) | (isPassable(X, Y, Z-1)));
		

		
	}
	
	// isPassable
	
	public boolean isPassable(int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return this.isPassable(X, Y, Z);
	}

	public boolean isPassable(int X, int Y, int Z) {
		return this.isPassable(this.getTerrainTypeWorld()[X][Y][Z]);

	}
	
	// isPassable
	private boolean isPassable(int terrainType) {
		return ((terrainType == 0) || (terrainType == 1));
	}
	
	// isValidPosition
	public boolean isValidPosition(int[] position) {
		return ((position[0] <= this.getNbX()) && (position[1] <= this.getNbY())
				&& (position[2] <= this.getNbZ()));
	}

	
	
	// SET and GET TERRAINTYPEWORLD
	private void setTerrainTypeWorld(int[][][] terrainType) {
		this.terrainTypes = terrainType;
		this.setDimension();
		this.connectedToBorder = new ConnectedToBorder(this.getNbX(), this.getNbY(), this.getNbZ());

	}
	
	
	public int[][][] getTerrainTypeWorld() {
		return this.terrainTypes;
	}
	
	// SET and GET modelListener
	public void setTerrainChangeListener(TerrainChangeListener modelListener) {
		this.modelListener = modelListener;
	}
	
	public TerrainChangeListener getTerrainChangeListener() {
		return this.modelListener;
	}
	
	//SET DIMENSION
	private void setDimension() {
		this.setNbX(this.getTerrainTypeWorld().length);
		this.setNbY(this.getTerrainTypeWorld()[0].length);
		this.setNbZ(this.getTerrainTypeWorld()[0][0].length);

	}
	
	
	// SET and GET NbX, NbY, NbZ
	private void setNbX(int X) {
		this.NbX = X;
	}
	
	public int getNbX() {
		return this.NbX;
	}
	
	
	private void setNbY(int Y) {
		this.NbY = Y;
	}
	
	
	public int getNbY() {
		return this.NbY;
	}
	
	private void setNbZ(int Z) {
		this.NbZ = Z;
	}
	
	public int getNbZ() {
		return this.NbZ;
	}
	
	
	// ADD TO LIST TO CAVE
	private void addToListToCave(List<int[]> solidToPassable) {
		this.toCave.addAll(solidToPassable);
	}
	
	// GET LIST TO CAVE
	public Set<int[]> getListToCave() {
		return this.toCave;
	}
	
	// ADD TO AND GET BOULDER LIST
	protected void addToBoulderList(Boulder boulder) {
		if (!this.getBoulderList().contains(boulder))
			this.boulderInWorld.add(boulder);
	}
	
	public Set<Boulder> getBoulderList() {
		return this.boulderInWorld;
	}
	
	
	// REMOVE FROM BOULDER LIST
	protected void removeBoulderFromList(Boulder boulder) {
		this.boulderInWorld.remove(boulder);
	}
	
	protected void addToLogList(Log log) {
		if (!this.getLogList().contains(log))
			this.logInWorld.add(log);
	}
	
	public Set<Log> getLogList() {
		return this.logInWorld;
	}
	
	protected void removeLogFromList(Log log) {
		this.logInWorld.remove(log);
	}
	
	protected void addToFactionList(Faction faction) {
		
		if (this.getNbFaction() < 5) {	
			if (!this.getFactionList().contains(faction))
				this.activeFactions.add(faction);
		}
	}
	
	public int getNbFaction() {
		return this.getFactionList().size();
	}
	
	public Set<Faction> getFactionList() {
		return this.activeFactions;
	}
	
	protected void removeFactionFromList(Faction faction) {
		this.activeFactions.remove(faction);
	}
	
	protected boolean areBothInThisWorld(Unit unit1, Unit unit2) {
		if ((this.getUnitList().contains(unit1)) && (this.getUnitList().contains(unit2)))
			return true;
		return false;
	}
	
	protected void addToUnitList(Unit unit) {
		
		if (!this.getUnitList().contains(unit))
			this.unitsInWorld.add(unit);
	}
	
	public Set<Unit> getUnitList() {
		return this.unitsInWorld;
	}
	
	public void addUnit(Unit unit) throws IllegalWorldException, IllegalArgumentException {
		
		if (worldIsFull())
			throw new IllegalWorldException("World is full");
		if (unit == null)
			throw new IllegalArgumentException("Unit is null");
		
		this.addToUnitList(unit);
		this.addToRightFaction(unit);
		
		
		
	}
	
	private boolean worldIsFull() {
		return this.getUnitList().size() >= 50;
	}
	
	private void addToRightFaction(Unit unit) {
		
		if (this.getFactionList().size() < 5) {
			Faction newFaction = new Faction(this);
			newFaction.addUnit(unit);
			unit.setFaction(newFaction);		
		}
		
		else {
			Faction smallestFaction = null;
			int smallestNbUnits = 51;
	
			
			for (Faction faction : this.getFactionList()) {
				if (faction.getNbUnit() <= smallestNbUnits)
					smallestFaction = faction;
				
			}
			
	
			smallestFaction.addUnit(unit);
			unit.setFaction(smallestFaction);
		}	
		
	}
	
	public void createFaction() {
		
	}
	
	public void spawnUnit() throws IllegalArgumentException {
		
		Unit unit = createRandomUnit();
		this.addToRightFaction(unit);
		
		
		
	}
	
	public Unit createRandomUnit() {
		
		int randomStrength = (int) Math.round(Math.random() * 75 + 25);
		int randomAgility = (int) Math.round(Math.random() * 75 + 25);
		int randomToughness = (int) Math.round(Math.random() * 75 + 25);
		int minWeight = (int) Math.round((randomStrength + randomAgility) / 2);
		int randomWeight = (int) Math.round(Math.random() * (100 - minWeight) + minWeight);
		String randomName = "Unit" + Integer.toString((int) Math.round(Math.random() * 100));
		int[] position = createRandomSpawnPosition();
		
		return new Unit(randomName, position, randomWeight, randomAgility, randomStrength, randomToughness,
				true);
	}
	
	public int[] createRandomSpawnPosition() {
		
		int X = (int) Math.round(Math.random() * 50);
		int Y = (int) Math.round(Math.random() * 50);
		int Z = (int) Math.round(Math.random() * 50);
		
		if (isValidSpawnPosition(X, Y, Z)) {
			int[] pos = {X, Y, Z};
			return pos;
		} 
		
		return createRandomSpawnPosition();
		
	}
	
	public List<Object> getAllObjectsOccupyingCube(int[] cube) {
		
		Set<Boulder> BoulderList = this.getBoulderList();
		Set<Log> LogList = this.getLogList();
		Set<Unit> UnitList = this.getUnitList();
		List<Object> AllObjectsOccupyingCube = null;
		
		for(Boulder boulder:BoulderList) {
			int[] position = boulder.getCube();
			if (position == cube) {
				AllObjectsOccupyingCube.add(boulder);
			}
		}
		
		for(Log log:LogList) {
			int[] position = log.getCube();
			if (position == cube) {
				AllObjectsOccupyingCube.add(log);
			}
		}
		
		for(Unit unit:UnitList) {
			int[] position = unit.getCube();
			if (position == cube) {
				AllObjectsOccupyingCube.add(unit);
			}
		}
	}
	
	public void removeBoulderFromWorld(Boulder boulder) {
		boulderInWorld.remove(boulder);
	}
	
	public void removeLogFromWorld(Log log) {
		logInWorld.remove(log);
	}
	
	
	

	private int[][][] terrainTypes;
	private TerrainChangeListener modelListener;
	private ConnectedToBorder connectedToBorder;
	private int NbX;
	private int NbY;
	private int NbZ;
	private Set<int[]> toCave = new HashSet<>();
	private Set<Boulder> boulderInWorld = new HashSet<>();
	private Set<Log> logInWorld = new HashSet<>();
	private Set<Faction> activeFactions = new HashSet<>();
	private Set<Unit> unitsInWorld = new HashSet<>();

	

	

}
