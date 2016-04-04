package hillbillies.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) {
		
		this.setTerrainTypeWorld(terrainTypes);
		this.setTerrainChangeListener(modelListener);
		this.NbX = (this.getTerrainTypeWorld().length);
		this.NbY = (this.getTerrainTypeWorld()[0].length);
		this.NbZ = (this.getTerrainTypeWorld()[0][0].length);

		
		
	}
	
	public void advanceTime(double time) throws IllegalArgumentException {
		
		if ((time <= 0) | (time > 0.2))
			throw new IllegalArgumentException();
		else {
			for (int[] position: this.getListToCave()) {
				this.cave(position);
				this.changeSolidToPassable();
			}
			for (Unit unit: this.getUnits()) 
				unit.advanceTime(time);
			for (Log log: this.getLogs())
				log.advanceTime(time);
			for (Boulder boulder: this.getBoulders())
				boulder.advanceTime(time);
		}
		
	}
	
	private void cave(int[] position) {
		
		int previousTerrainType = this.getTerrainType(position);
		this.setTerrainType(0, position);
		
		double P = Math.random();
		if (P <= 0.25)
			this.throwRawMaterial(position, previousTerrainType);
		
	}
	
	protected void caveAndThrow(int[] position) {
		int previousTerrainType = this.getTerrainType(position);
		this.setTerrainType(0, position);
		
		this.throwRawMaterial(position, previousTerrainType);

	}
	
	private void throwRawMaterial(int[] position, int terrainType) {
		
		if (terrainType == 1)
			new Boulder(this, position);
		else if (terrainType == 2)
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
		return this.getTerrainType(X, Y, Z);
	}
	
	private int getTerrainType(int X, int Y, int Z) {
		return this.getTerrainTypeWorld()[X][Y][Z];
		
	}
	
	private void changeSolidToPassable() {
		
		for (int x = 0; x < this.getNbX(); x++) {
			for (int y = 0; y < this.getNbY(); y++) {
				for (int z = 0; z < this.getNbZ(); z++) {
					if (isPassable(x,y,z))
						this.addToListToCave((connectedToBorder.changeSolidToPassable(x, y, z)));
					
				}
			}
		}
		
		
	}
	
	// CHECKER
	
	private boolean isValidSpawnPosition(int X, int Y, int Z) {
		int[] position = {X, Y, Z};
		return isValidSpawnPosition(position);	
	}
	
	
	private boolean isValidSpawnPosition(int[] position) {
		return (isPassable(position) && isSupported(position));		
	}
	
	private boolean isSupported(int[] position) {
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return isSupported(X, Y, Z);
	}
	
	private boolean isSupported(int X, int Y, int Z) {
		return ((Z == 0) || (isPassable(X, Y, Z-1)));
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
	

	protected boolean isWorkshop(int[] position) {
		return this.isWorkshop(position[0], position[1], position[2]);
	}
	
	
	protected boolean isWorkshop(int X, int Y, int Z) {
		return this.isWorkshop(this.getTerrainTypeWorld()[X][Y][Z]);
	}
	
	private boolean isWorkshop(int terrainType) {
		return (terrainType == 3);
	}
	
	protected boolean isWood(int[] position) {
		return this.isWood(position[0], position[1], position[2]);
	}
	
	
	protected boolean isWood(int X, int Y, int Z) {
		return this.isWood(this.getTerrainTypeWorld()[X][Y][Z]);
	}
	
	private boolean isWood(int terrainType) {
		return (terrainType == 2);
	}

	protected boolean isRock(int[] position) {
		return this.isRock(position[0], position[1], position[2]);
	}
	
	
	protected boolean isRock(int X, int Y, int Z) {
		return this.isRock(this.getTerrainTypeWorld()[X][Y][Z]);
	}
	
	private boolean isRock(int terrainType) {
		return (terrainType == 2);
	}
	
	
	
	// isValidPosition
	public boolean isValidPosition(int[] position) {
		return (isPositionInWorld(position) && isPassable(position));
	}
	
	public boolean isValidPosition(double[] position) {
		return (isPositionInWorld(position) && isPassable(getCube(position)));
	}
	
	
	public boolean isPositionInWorld(int[] position) {
		return ((position[0] <= this.getNbX()) && (position[1] <= this.getNbY())
				&& (position[2] <= this.getNbZ()));
	}
	
	public boolean isPositionInWorld(double[] position) {
		return (((int) position[0] <= this.getNbX()) && ((int) position[1] <= this.getNbY())
				&& ((int) position[2] <= this.getNbZ()));
	}
	
	protected boolean logAvailable(int[] position) {
		for (Log log: this.getLogs()) {
			if (log.getCube() == position)
				return true;
		}
		return false;
	}
	
	protected boolean boulderAvailable(int[] position) {
		for (Boulder boulder: this.getBoulders()) {
			if (boulder.getCube() == position)
				return true;
		}
		return false;
	}


	private int[] getCube(double[] position) {
		int X = (int) Math.floor(position[0]);
		int Y = (int) Math.floor(position[1]);
		int Z = (int) Math.floor(position[2]);
		int[] cube = {X, Y, Z};
		return cube;

	}
	
	// SET and GET TERRAINTYPEWORLD
	private void setTerrainTypeWorld(int[][][] terrainType) {
		this.terrainTypes = terrainType;
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

	
	
	// SET and GET NbX, NbY, NbZ
	
	public int getNbX() {
		return this.NbX;
	}
	

	
	
	public int getNbY() {
		return this.NbY;
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
	protected void addBoulder(Boulder boulder) {
		if (!this.getBoulders().contains(boulder))
			this.boulderInWorld.add(boulder);
	}
	
	public Set<Boulder> getBoulders() {
		return this.boulderInWorld;
	}
	
	public Boulder getBoulder(int[] cubeInt) {
		for (Boulder boulder: this.getBoulders())
			if (boulder.getCube() == cubeInt) {
				return boulder;
			}
		return null;
	}
	
	public Log getLog(int[] cubeInt) {
		for (Log log: this.getLogs())
			if (log.getCube() == cubeInt) {
				return log;
			}
		return null;
	}
	
	
	
	// REMOVE FROM BOULDER LIST
	
	protected void addLog(Log log) {
		if (!this.getLogs().contains(log))
			this.logInWorld.add(log);
	}
	
	public Set<Log> getLogs() {
		return this.logInWorld;
	}
	
	
	protected void addFaction(Faction faction) throws IllegalFactionException {
		
		if (this.getNbFaction() >= 5) 
			throw new IllegalFactionException();
		if (!this.getFactions().contains(faction))
			this.activeFactions.add(faction);
		
	}
	
	public int getNbFaction() {
		return this.getFactions().size();
	}
	
	public Set<Faction> getFactions() {
		return this.activeFactions;
	}
	
	protected void removeFaction(Faction faction) {
		this.activeFactions.remove(faction);
	}
	
	protected boolean areBothInThisWorld(Unit unit1, Unit unit2) {
		return (this.getUnits().contains(unit1) && this.getUnits().contains(unit2));
	}
	
	protected void addToUnitList(Unit unit) {
		
		if (!this.getUnits().contains(unit))
			this.unitsInWorld.add(unit);
	}
	
	public Set<Unit> getUnits() {
		return this.unitsInWorld;
	}
	
	public int getNbUnits() {
		return this.getUnits().size();
	}
	
	public void addUnit(Unit unit) throws IllegalWorldException, IllegalArgumentException, IllegalUnitException {
		
		if (worldIsFull())
			throw new IllegalWorldException("World is full");
		if (unit == null)
			throw new IllegalArgumentException("Unit is null");
		if (!isPositionInWorld(unit.getPositionList()))
			throw new IllegalUnitException("The unit's position is not in this world");
		this.addToUnitList(unit);
		
		
		
	}
	
	private boolean worldIsFull() {
		return this.getUnits().size() >= 100;
	}
	
	
	private Faction getRightFaction() {
		try {
			Faction faction = new Faction(this);
			this.addFaction(faction);
			return faction;
		} catch (IllegalFactionException e) {
			int smallestNbUnits = 51;
			Faction smallestFaction = null;
			for (Faction faction: this.getFactions()) {
				if (faction.getNbUnit() <= smallestNbUnits) {
					smallestFaction = faction;
					smallestNbUnits = smallestFaction.getNbUnit();
				}
			}
			return smallestFaction;
		}
	}
	
	
	public void spawnUnit() throws IllegalArgumentException {
		if (this.getNbUnits() >= 100)
			throw new IllegalArgumentException("This world has reached his maximum"
					+ "number of units");
		Unit unit = createRandomUnit();
		unit.setFaction(this.getRightFaction());
		unit.getFaction().addUnit(unit);
	}
	
	private Unit createRandomUnit() {
		
		int randomStrength = (int) Math.round(Math.random() * 75 + 25);
		int randomAgility = (int) Math.round(Math.random() * 75 + 25);
		int randomToughness = (int) Math.round(Math.random() * 75 + 25);
		int minWeight = (int) Math.round((randomStrength + randomAgility) / 2);
		int randomWeight = (int) Math.round(Math.random() * (100 - minWeight) + minWeight);
		String randomName = "Unit" + Integer.toString((int) Math.round(Math.random() * 100));
		int[] position = createRandomSpawnPosition();
		
		return new Unit(randomName, position, randomWeight, randomAgility, randomStrength, 
				randomToughness, true);
	}
	
	public int[] createRandomSpawnPosition() {
		
		int X = (int) Math.round(Math.random() * this.getNbX());
		int Y = (int) Math.round(Math.random() * this.getNbY());
		int Z = (int) Math.round(Math.random() * this.getNbZ());
		
		if (isValidSpawnPosition(X, Y, Z)) {
			int[] pos = {X, Y, Z};
			return pos;
		} 
		
		return createRandomSpawnPosition();
		
	}
	
	public List<Object> getAllObjectsOccupyingCube(int[] cube) {
		
		Set<Boulder> BoulderList = this.getBoulders();
		Set<Log> LogList = this.getLogs();
		Set<Unit> UnitList = this.getUnits();
		List<Object> allObjectsOccupyingCube = new ArrayList<>();;
		
		for(Boulder boulder: BoulderList) {
			if (boulder.getCube() == cube) {
				allObjectsOccupyingCube.add(boulder);
			}
		}
		
		for(Log log: LogList) {
			if (log.getCube() == cube) {
				allObjectsOccupyingCube.add(log);
			}
		}
		
		for(Unit unit: UnitList) {
			if (unit.getCubeInt() == cube) {
				allObjectsOccupyingCube.add(unit);
			}
		}
		
		return allObjectsOccupyingCube;
	}
	
	protected void removeBoulder(Boulder boulder) {
		this.boulderInWorld.remove(boulder);
	}
	
	protected void removeLog(Log log) {
		this.logInWorld.remove(log);
	}
	
	protected void removeUnit(Unit unit) {
		this.unitsInWorld.remove(unit);
	}
	
	public void terminate() {
		if (!this.isTerminated()) {
			for (Faction faction: this.getFactions())
				faction.terminate();
			this.isTerminated = true;
		}
			
			
	}
	
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	

	private int[][][] terrainTypes;
	private TerrainChangeListener modelListener;
	private ConnectedToBorder connectedToBorder;
	private final int NbX;
	private final int NbY;
	private final int NbZ;
	private Set<int[]> toCave = new HashSet<>();
	private Set<Boulder> boulderInWorld = new HashSet<>();
	private Set<Log> logInWorld = new HashSet<>();
	private Set<Faction> activeFactions = new HashSet<>();
	private Set<Unit> unitsInWorld = new HashSet<>();
	private boolean isTerminated;


	

	

}
