package hillbillies.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	/**
	 * @param terrainTypes
	 * 		The type of terrain at every coordinate in the world
	 * @param modelListener
	 * 		 ???
	 * @post
	 * 		The NbX of the world is the X-length of the world
	 * @post
	 * 		The NbY of the world is the Y-length of the world
	 * @post
	 * 		The NbZ of the world is the Z-length of the world
	 * 
	 */
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
			for (Faction faction: this.getActiveFactions()) {
				if (faction.getNbUnit() == 0)
					this.removeFaction(faction);
			}
				
		}
		
	}
	
	/**
	 * @param position
	 * 		The position where the cave in should happen
	 * @post
	 * 		The terraintype of the position changes to air
	 * @post
	 * 		With a chance of 0,25 a raw material is thrown depending on the initial terraintype
	 */
	private void cave(int[] position) {
		
		int previousTerrainType = this.getTerrainType(position);
		this.setTerrainType(0, position);
		
		double P = Math.random();
		if (P <= 0.25)
			this.throwRawMaterial(position, previousTerrainType);
		
	}
	
	/**
	 * @param position
	 * 		The position where the cave in should happen
	 * @post
	 * 		The terraintype of the position changes to air
	 * @post
	 * 		 A raw material is thrown depending on the initial terraintype
	 */
	protected void caveAndThrow(int[] position) {
		int previousTerrainType = this.getTerrainType(position);
		this.setTerrainType(0, position);
		
		this.throwRawMaterial(position, previousTerrainType);

	}
	
	/**
	 * @param position
	 * 		The position where a raw material is thrown
	 * @param terrainType
	 * 		The terraintype at position
	 * @post
	 *		If the terraintype is 1 a new boulder is created at that position
	 * @post
	 * 		If the terraintype is 2 a new log is created at that position
	 */
	private void throwRawMaterial(int[] position, int terrainType) {
		
		if (terrainType == 1)
			new Boulder(this, position);
		else if (terrainType == 2)
			new Log(this, position);

		
		
	}
	
	// SET TERRAIN TYPE FOR ONE CUBE
	
	/**
	 * @param terrainType
	 * 		The new terrainType
	 * @param position
	 * 		The position where the terrainType is set
	 * 
	 */
	public void setTerrainType(int terrainType, int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		int[][][] terrain = this.getTerrainTypeWorld();
		terrain[X][Y][Z] = terrainType;
		this.setTerrainTypeWorld(terrain);

	}
	
	/**
	 * @param position
	 * 		The position of which the terrainType will be returned
	 * @return Returns the terraintype at a given position
	 */
	public int getTerrainType(int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return this.getTerrainType(X, Y, Z);
	}
	
	/**
	 * @param X
	 * 		The X coordinate of the position of which the terraintype will be returned
	 * @param Y
	 * 		The Y coordinate of the position of which the terraintype will be returned
	 * @param Z
	 * 		The Z coordinate of the position of which the terraintype will be returned
	 * @return Returns the terraintype at a given position
	 */
	private int getTerrainType(int X, int Y, int Z) {
		return this.getTerrainTypeWorld()[X][Y][Z];
		
	}
}
	/**
	 * @post
	 * 		??????
	 */
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
	/**
	 * @param X
	 * 		The X coordinate of the position 
	 * @param Y
	 * 		The Y coordinate of the position 
	 * @param Z
	 * 		The Z coordinate of the position
	 * @return Returns true if the position is a valid spawn position	
	 */
	private boolean isValidSpawnPosition(int X, int Y, int Z) {
		int[] position = {X, Y, Z};
		return isValidSpawnPosition(position);	
	}
	
	/**
	 * @param position
	 * 		The position of which is checked if it's a valid spawn position
	 * @return Returns true if the position is a valid spawn position (i.e., it's supported and it's passable)
	 * 
	 */
	private boolean isValidSpawnPosition(int[] position) {
		return (isPassable(position) && isSupported(position));		
	}
	
	/**
	 * @param position
	 * 		The position of which is returned whether or not it's supported
	 * @return Returns whether or not the position is supported
	 * 
	 */
	protected boolean isSupported(int[] position) {
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return isSupported(X, Y, Z);
	}
	
	/**
	 * @param X
	 * 		The X coordinate of the position
	 * @param Y
	 * 		The Y coordinate of the position
	 * @param Z
	 * 		The Z coordinate of the position
	 * @return Returns whether or not the position is supported (i.e., it's at the lowest level or it has a solid cube below)
	 * 
	 */
	private boolean isSupported(int X, int Y, int Z) {
		return ((Z == 0) || (!isPassable(X, Y, Z-1)));
	}
	
	// isPassable
	
	/**
	 * @param position
	 * 		The position of which is checked if it's passable
	 * @return Returns whether or not the position is passable (i.e., has terrainType 0 or 1)
	 */
	public boolean isPassable(int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return this.isPassable(X, Y, Z);
	}
	
	/**
	 * @param X
	 * 		The X coordinate of the position
	 * @param Y
	 * 		The Y coordinate of the position
	 * @param Z
	 * 		The Z coordinate of the position
	 * 
	 * @return Returns whether or not the position is passable (i.e., has terrainType 0 or 1)
	 */
	public boolean isPassable(int X, int Y, int Z) {
		return this.isPassable(this.getTerrainTypeWorld()[X][Y][Z]);

	}
	
	// isPassable
	/**
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is a passable one
	 */
	
	private boolean isPassable(int terrainType) {
		return ((terrainType == 0) || (terrainType == 1));
	}
	
	/**
	 * @param position
	 * 		The position of which is checked if it's a workshop
	 * @return Returns whether or not the position is a workshop (i.e., has terrainType 3)
	 */
	protected boolean isWorkshop(int[] position) {
		return this.isWorkshop(position[0], position[1], position[2]);
	}
	
	/**
	 * @param X
	 * 		The X coordinate of the position
	 * @param Y
	 * 		The Y coordinate of the position
	 * @param Z
	 * 		The Z coordinate of the position
	 * 
	 * @return Returns whether or not the position is a workshop (i.e., has terrainType 3)
	 */
	protected boolean isWorkshop(int X, int Y, int Z) {
		return this.isWorkshop(this.getTerrainTypeWorld()[X][Y][Z]);
	}
	
	/**
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is a workshop
	 */
	private boolean isWorkshop(int terrainType) {
		return (terrainType == 3);
	}
	
	/**
	 * @param position
	 * 		The position of which is checked if it's wood
	 * @return Returns whether or not the position is wood (i.e., has terrainType 2)
	 */
	protected boolean isWood(int[] position) {
		return this.isWood(position[0], position[1], position[2]);
	}
	
	/**
	 * @param X
	 * 		The X coordinate of the position
	 * @param Y
	 * 		The Y coordinate of the position
	 * @param Z
	 * 		The Z coordinate of the position
	 * 
	 * @return Returns whether or not the position is wood (i.e., has terrainType 2)
	 */
	protected boolean isWood(int X, int Y, int Z) {
		return this.isWood(this.getTerrainTypeWorld()[X][Y][Z]);
	}
	
	/**
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is wood
	 */
	private boolean isWood(int terrainType) {
		return (terrainType == 2);
	}
	/**
	 * @param position
	 * 		The position of which is checked if it's rock
	 * @return Returns whether or not the position is rock (i.e., has terrainType 1)
	 */
	protected boolean isRock(int[] position) {
		return this.isRock(position[0], position[1], position[2]);
	}
	
	/**
	 * @param X
	 * 		The X coordinate of the position
	 * @param Y
	 * 		The Y coordinate of the position
	 * @param Z
	 * 		The Z coordinate of the position
	 * 
	 * @return Returns whether or not the position is rock (i.e., has terrainType 1)
	 */
	protected boolean isRock(int X, int Y, int Z) {
		return this.isRock(this.getTerrainTypeWorld()[X][Y][Z]);
	}
	
	/**
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is rock
	 */
	private boolean isRock(int terrainType) {
		return (terrainType == 1);
	}
	
	// isValidPosition
	
	/**
	 * @param position
	 * 		The position of which is checked if it's valid
	 * @return Returns whether or not the position is valid (i.e., if the position is in world & the position is passable)
	 */
	protected boolean isValidPosition(int[] position) {
		return (isPositionInWorld(position) && isPassable(position));
	}
	
	/**
	 * @param position
	 * 		The position of which is checked if it's valid
	 * @return Returns whether or not the position is valid (i.e., if the position is in world & the position is passable)
	 */
	protected boolean isValidPosition(double[] position) {
		return (isPositionInWorld(position) && isPassable(getCube(position)));
	}
	
	/**
	 * @param position
	 * 		The position of which is checked if it's in th eworld
	 * @return Returns whether or not the position is the the borders of the world
	 */
	public boolean isPositionInWorld(int[] position) {
		return ((position[0] <= this.getNbX()) && (position[1] <= this.getNbY())
				&& (position[2] <= this.getNbZ()));
	}
	
	/**
	 * @param position
	 * 		The position of which is checked if it's in th eworld
	 * @return Returns whether or not the position is the the borders of the world
	 */
	public boolean isPositionInWorld(double[] position) {
		return (((int) position[0] <= this.getNbX()) && ((int) position[1] <= this.getNbY())
				&& ((int) position[2] <= this.getNbZ()));
	}
	
	/**
	 * @param unit
	 * 		The unit of which is checked if it's valid
	 * @return Returns true if the unit is not null and the unit has a valid position
	 */
	public boolean isValidUnit(Unit unit) {
		return (unit != null && isValidPosition(unit.getPositionList()));
	}
	
	/**
	 * @param position
	 * 		The position where is checked if there's a log available
	 * @return Return true if there's a uncarried log at the position
	 */
	protected boolean logAvailable(int[] position) {
		for (Log log: this.getLogs()) {
			if (log.getCube() == position)
				return true;
		}
		return false;
	}
	
	/**
	 * @param position
	 * 		The position where is checked if there's a boulder available
	 * @return Return true if there's a uncarried boulder at the position
	 */
	protected boolean boulderAvailable(int[] position) {
		for (Boulder boulder: this.getBoulders()) {
			if (boulder.getCube() == position)
				return true;
		}
		return false;
	}

	/**
	 * @param position
	 * 		The position of which it's cube coordinates will be returned
	 * @return Returns the coordinates (int's !) of the cube of the position
	 */
	private int[] getCube(double[] position) {
		int X = (int) Math.floor(position[0]);
		int Y = (int) Math.floor(position[1]);
		int Z = (int) Math.floor(position[2]);
		int[] cube = {X, Y, Z};
		return cube;

	}
	
	// SET and GET TERRAINTYPEWORLD
	
	/**
	 * @param terrainType
	 * 		The 3D-matrix with the terrainTypes at all positions of the world
	 * @post
	 * 		The terraintypes of the world shall equal terrainType
	 * @post
	 * 		??????
	 */		
	private void setTerrainTypeWorld(int[][][] terrainType) {
		this.terrainTypes = terrainType;
		this.connectedToBorder = new ConnectedToBorder(this.getNbX(), this.getNbY(), this.getNbZ());

	}
	
	/**
	 * @return Returns a 3D-matrix with the terrainTypes of the world 
	 */
	public int[][][] getTerrainTypeWorld() {
		return this.terrainTypes;
	}
	
	// SET and GET modelListener
	
	/**
	 * @param modelListener
	 * 		The modellistener of the world
	 * @post
	 * 		The modellistener of the world is the given modellistener
	 */
	public void setTerrainChangeListener(TerrainChangeListener modelListener) {
		this.modelListener = modelListener;
	}
	/**
	 * @return Returns the terrainchangelistener of the world
	 */
	public TerrainChangeListener getTerrainChangeListener() {
		return this.modelListener;
	}
	
	//SET DIMENSION

	
	
	// SET and GET NbX, NbY, NbZ
	
	/**
	 * @return Returns the nb of cubes in the X direction
	 */
	public int getNbX() {
		return this.NbX;
	}
	
	/**
	 * @return Returns the nb of cubes in the Y direction
	 */
	public int getNbY() {
		return this.NbY;
	}
	
	/**
	 * @return Returns the nb of cubes in the Z direction
	 */
	public int getNbZ() {
		return this.NbZ;
	}
	
	
	// ADD TO LIST TO CAVE
	
	/**
	 * @param solidToPassable
	 * 		List of all coordinates that has to be caved
	 * @post
	 * 		solidToPassable is added to listToCave
	 */
	private void addToListToCave(List<int[]> solidToPassable) {
		this.toCave.addAll(solidToPassable);
	}
	
	// GET LIST TO CAVE
	/**
	 * @return Returns the set with all cubes that has to be caved
	 */
	public Set<int[]> getListToCave() {
		return this.toCave;
	}
	
	// ADD TO AND GET BOULDER LIST
	/**
	 * @param boulder
	 * 		The boulder that has to be added to the boulder set
	 * @post
	 * 		The boulder is in the boulderInWorld set
	 */
	protected void addBoulder(Boulder boulder) {
		if (!this.getBoulders().contains(boulder))
			this.boulderInWorld.add(boulder);
	}
	
	/**
	 * @return Returns a set with all the boulders in the world
	 */
	public Set<Boulder> getBoulders() {
		return this.boulderInWorld;
	}
	
	/**
	 * @param cubeInt
	 * 		The cube that has to be examined
	 * @return Returns the boulder at a given cube, returns null if there is no boulder
	 */
	public Boulder getBoulder(int[] cubeInt) {
		for (Boulder boulder: this.getBoulders())
			if (boulder.getCube() == cubeInt) {
				return boulder;
			}
		return null;
	}
	
	/**
	 * @param cubeInt
	 * 		The cube that has to be examined
	 * @return Returns the log at a given cube, returns null if there is no log
	 */
	public Log getLog(int[] cubeInt) {
		for (Log log: this.getLogs())
			if (log.getCube() == cubeInt) {
				return log;
			}
		return null;
	}
	
	
	
	// REMOVE FROM BOULDER LIST
	
	/**
	 * @param log
	 * 		The log that has to be added to the log set
	 * @post
	 * 		The log is in the logInWorld set
	 */
	protected void addLog(Log log) {
		if (!this.getLogs().contains(log))
			this.logInWorld.add(log);
	}
	
	/**
	 * @return Returns a set with all the logs in the world
	 */
	public Set<Log> getLogs() {
		return this.logInWorld;
	}
	
	/**
	 * @param faction
	 * 		The faction that has to be added to the world
	 * @post
	 * 		The faction will be in the factionlist of the world, if the maximum nb of faction in not reached
	 * @throws IllegalFactionException
	 * 		If the number of factions in the world exceeds five
	 */
	protected void addFaction(Faction faction) throws IllegalFactionException {
		
		if (this.getNbFaction() >= 5) 
			throw new IllegalFactionException();
		if (!this.getActiveFactions().contains(faction))
			this.activeFactions.add(faction);
		
	}
	/**
	 * @return Returns the nb of active factions in the world
	 */
	public int getNbFaction() {
		return this.getActiveFactions().size();
	}
	/**
	 * @return returns a set with all the active factions of the world
	 */
	public Set<Faction> getActiveFactions() {
		return this.activeFactions;
	}
	
	/**
	 * @param faction
	 * 		The faction that has to be removed
	 * @post
	 * 		The faction in not in the activeFactionSet of the world
	 */
	protected void removeFaction(Faction faction) {
		this.activeFactions.remove(faction);
	}
	/**
	 * @param unit 1
	 * 		The first unit that has to be compared
	 * @param unit 2
	 * 		The second unit that has to be compared
	 * @return
	 * 		Returns true if both units are in the world
	 */
	protected boolean areBothInThisWorld(Unit unit1, Unit unit2) {
		return (this.getUnits().contains(unit1) && this.getUnits().contains(unit2));
	}
	
	/**
	 * @param unit
	 * 		The unit that has to be added to the unit list
	 * @post
	 * 		The unitsInWorldSet contains unit
	 */
	protected void addToUnitList(Unit unit) {
		
		if (!this.getUnits().contains(unit))
			this.unitsInWorld.add(unit);
	}
	
	/**
	 * @return Returns a set with all the units in the world
	 */
	public Set<Unit> getUnits() {
		return this.unitsInWorld;
	}
	
	/**
	 * @return Returns the nb of units in the world
	 */
	public int getNbUnits() {
		return this.getUnits().size();
	}
	
	/**
	 * @param unit
	 * 		The unit that has to be added to the world
	 * @post
	 * 		The unitsInWorldSet contains unit
	 * @throws IllegalWorldException
	 * 		Throws IllegalWorldException if the world is full
	 * @throws IllegalUnitException
	 * 		Throws IllegalUnitException if the unit is not valid
	 * 
	 */
	public void addUnit(Unit unit) throws IllegalWorldException, IllegalUnitException {
		
		if (worldIsFull())
			throw new IllegalWorldException("World is full");
		if (!isValidUnit(unit))
			throw new IllegalUnitException("Unit cannot be added to this world");
		this.addToUnitList(unit);
		
		
		
	}
	
	/**
	 * @return Returns whether or not the world has reached it's maximum capacity or has exceeded it
	 */
	private boolean worldIsFull() {
		return this.getUnits().size() >= 100;
	}
	
	/**
	 * @return Returns the faction to which a unit should be assigned
	 */
	private Faction getRightFaction() {
		try {
			Faction faction = new Faction(this);
			this.addFaction(faction);
			return faction;
		} catch (IllegalFactionException e) {
			int smallestNbUnits = 51;
			Faction smallestFaction = null;
			for (Faction faction: this.getActiveFactions()) {
				if (faction.getNbUnit() <= smallestNbUnits) {
					smallestFaction = faction;
					smallestNbUnits = smallestFaction.getNbUnit();
				}
			}
			return smallestFaction;
		}
	}
	
	/**
	 * @post
	 * 		A new unit is added to the world, and is assigned to a faction
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the world is full
	 */
	public void spawnUnit() throws IllegalArgumentException {
		if (this.getNbUnits() >= 100)
			throw new IllegalArgumentException("This world has reached his maximum"
					+ "number of units");
		Unit unit = createRandomUnit();
		unit.setFaction(this.getRightFaction());
		unit.getFaction().addUnit(unit);
	}
	
	/**
	 * @return Returns a new random unit
	 */
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
	
	/**
	 * @return Returns the coordinates of a random valid spawn position
	 */
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
	/**
	 * @param cube
	 * 		The cube that has to be examined
	 * @return Returns all objects occypying the cube 
	 */
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
	
	/**
	 * @param boulder
	 * 		The boulder that has to be removed
	 * @post
	 * 		The boulder is removed from the boulderInWorldSet
	 */
	protected void removeBoulder(Boulder boulder) {
		this.boulderInWorld.remove(boulder);
	}
	
	/**
	 * @param log
	 * 		The log that has to be removed
	 * @post
	 * 		The log is removed from the logInWorldSet
	 */
	protected void removeLog(Log log) {
		this.logInWorld.remove(log);
	}
	
	/**
	 * @param unit
	 * 		The unit that has to be removed
	 * @post
	 * 		The unt is removed from the unitInWorldSet
	 */
	protected void removeUnit(Unit unit) {
		this.unitsInWorld.remove(unit);
	}
	
	/**
	 * @post
	 * 		The world and all it's factions are terminated
	 */
	public void terminate() {
		if (!this.isTerminated()) {
			for (Faction faction: this.getActiveFactions())
				faction.terminate();
			this.isTerminated = true;
		}
			
			
	}
	
	/**
	 * @return Returns whether or not the world is terminated
	 */
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
