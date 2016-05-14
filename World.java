package hillbillies.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World implements ITerminate {
	
	/**
	 * The initialization of a new world
	 * 
	 * @param terrainTypes
	 * 		The type of terrain at every coordinate in the world
	 * @param modelListener
	 * 		 The given modelListener of this world
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
	
	public void advanceTime(double time) throws IllegalArgumentException, IllegalStateException {
		
		if ((time <= 0) | (time > 0.2))
			throw new IllegalArgumentException();
		if (this.isTerminated)
			throw new IllegalStateException("World is terminated");
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
	 * A cave-in is applied to the given position and a raw material is thrown with a chance of 0.25
	 * 
	 * @param position
	 * 		The position where the cave-in should take place
	 * @post
	 * 		The terrain type of the position changes to air
	 * @post
	 * 		With a chance of 0.25 a raw material is thrown depending on the initial terrain type
	 */
	private void cave(int[] position) {
		
		TerrainType previousTerrainType = this.getTerrainType(position);
		this.setTerrainType(TerrainType.AIR, position);
		
		double P = Math.random();
		if (P <= 0.25)
			this.throwRawMaterial(position, previousTerrainType);
		
	}
	
	/**
	 * 
	 * A cave in is applied to a given position and a raw material is thrown.
	 * 
	 * @param position
	 * 		The position where the cave in should happen
	 * @post
	 * 		The terrain type of the position changes to air
	 * @post
	 * 		 A raw material is thrown depending on the initial terrain type
	 */
	protected void caveAndThrow(int[] position) {
		TerrainType previousTerrainType = this.getTerrainType(position);
		this.setTerrainType(TerrainType.AIR, position);
		
		this.throwRawMaterial(position, previousTerrainType);

	}
	
	/**
	 * A raw material is thrown at a given position
	 * 
	 * @param position
	 * 		The position where a raw material is thrown
	 * @param terrainType
	 * 		The terrain type at position
	 * @post
	 *		If the terrain type is 1 a new boulder is created at that position
	 * @post
	 * 		If the terrain type is 2 a new log is created at that position
	 */
	private void throwRawMaterial(int[] position, TerrainType terrainType) {
		
		if (terrainType == TerrainType.ROCK)
			new Boulder(this, position);
		else if (terrainType == TerrainType.TREE)
			new Log(this, position);

		
		
	}
	
	/**
	 * @param terrainType
	 * 		The new terrainType
	 * @param position
	 * 		The position where the terrainType is set
	 * 
	 */
	public void setTerrainType(TerrainType terrainType, int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		int[][][] terrain = this.getTerrainTypeWorld();
		terrain[X][Y][Z] = terrainType.getInt();
		this.setTerrainTypeWorld(terrain);

	}
	
	/**
	 * @param position
	 * 		The position of which the terrainType should be returned
	 * @return Returns the terrain type at a given position
	 */
	public TerrainType getTerrainType(int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return this.getTerrainType(X, Y, Z);
	}
	

	/**
	 * @param X
	 * 		The X coordinate of the position of which the terrain type should be returned
	 * @param Y
	 * 		The Y coordinate of the position of which the terrain type should be returned
	 * @param Z
	 * 		The Z coordinate of the position of which the terrain type should be returned
	 * @return Returns the terrain type at a given position
	 */
	public TerrainType getTerrainType(int X, int Y, int Z) {
		int type = this.getTerrainTypeWorld()[X][Y][Z];
		
		if (type == TerrainType.AIR.getInt())
			return TerrainType.AIR;
		else if (type == TerrainType.ROCK.getInt())
			return TerrainType.ROCK;
		else if (type == TerrainType.TREE.getInt())
			return TerrainType.TREE;
		else 
			return TerrainType.WORKSHOP;
		
	}
	
	/**
	 * Returns the terrainType at a certain position
	 * 
	 * @param X
	 * 		The X-coordinate of the position
	 * @param Y
	 *  	The Y-coordinate of the position
	 * @param Z
	 *  	The Z-coordinate of the position
	 * @return Returns the terrainType at a certain position
	 */
	public int getTypeTerrain(int X, int Y, int Z) {
		return getTerrainType(X, Y, Z).getInt();
	}
	
	/**
	 * Returns a valid position next to a given position
	 * 
	 * @param position
	 * 		The position of which a cube next to it is searched
	 * @return Returns a valid position next to a given position
	 */
	public int[] getNextPosition(int[] position) {
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		
		int[] pos1 = {X+1,Y,Z};
		int[] pos2 = {X-1,Y,Z};
		int[] pos3 = {X,Y+1,Z};
		int[] pos4 = {X,Y-1,Z};
		int[] pos5 = {X,Y,Z+1};
		int[] pos6 = {X,Y,Z-1};
		
		Set<int[]> cubes = new HashSet<int[]>(Arrays.asList(pos1, pos2, pos3, pos4, pos5, pos6));
		
		for (int[]pos:cubes){
			if(isValidPosition(pos))
				return pos;
		}
		return null;
	}
	


	
	/**
	 * @post
	 * 		The 3-dimensional field in the connectedToBorder class will equal the 3D field this world
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
	
	/**
	 * Checks whether a given position is a valid spawn position
	 * 
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
	 * Checks whether a given position is a valid spawn position
	 * 
	 * @param position
	 * 		The position of which is checked if it's a valid spawn position
	 * @return Returns true if the position is a valid spawn position (i.e., it's supported and it's passable)
	 * 
	 */
	private boolean isValidSpawnPosition(int[] position) {
		return (isPassable(position) && isSupported(position));		
	}
	
	/**
	 * Checks whether a given position is supported
	 * 
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
	 * Checks whether a given position is supported
	 * 
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
	 * Checks whether a given position is passable
	 * 
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
	 * Checks whether a given position is passable
	 * 
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
	
	/**
	 * Checks whether a given terraintype is passable
	 * 
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is a passable one
	 */
	private boolean isPassable(int terrainType) {
		return ((terrainType == TerrainType.AIR.getInt()) || (terrainType == TerrainType.WORKSHOP.getInt()));
	}
	
	/**
	 * Checks whether a given position is a workshop
	 * 
	 * @param position
	 * 		The position of which is checked if it's a workshop
	 * @return Returns whether or not the position is a workshop (i.e., has terrainType 3)
	 */
	protected boolean isWorkshop(int[] position) {
		return this.isWorkshop(position[0], position[1], position[2]);
	}
	
	/**
	 * Checks whether a given position is a workshop
	 * 
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
	 * Checks whether a given terraintype is a workshop
	 * 
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is a workshop
	 */
	private boolean isWorkshop(int terrainType) {
		return (terrainType == TerrainType.WORKSHOP.getInt());
	}
	
	/**
	 * Checks whether a given position is wood
	 * 
	 * @param position
	 * 		The position of which is checked if it's wood
	 * @return Returns whether or not the position is wood (i.e., has terrainType 2)
	 */
	protected boolean isWood(int[] position) {
		return this.isWood(position[0], position[1], position[2]);
	}
	
	/**
	 * Checks whether a given position is a workshop
	 * 
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
	 * Checks whether a given terraintype is a workshop
	 * 
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is wood
	 */
	private boolean isWood(int terrainType) {
		return (terrainType == TerrainType.TREE.getInt());
	}

	/**
	 * Checks whether a given position is rock
	 * 
	 * @param position
	 * 		The position of which is checked if it's rock
	 * @return Returns whether or not the position is rock (i.e., has terrainType 1)
	 */
	protected boolean isRock(int[] position) {
		return this.isRock(position[0], position[1], position[2]);
	}
	
	/**
	 * Checks whether a given position is rock
	 * 
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
	 * Checks whether a given terraintype is rock
	 * 
	 * @param terrainType
	 * 		The terrainType
	 * @return Returns whether or not the terrainType is rock
	 */
	private boolean isRock(int terrainType) {
		return (terrainType == TerrainType.ROCK.getInt());
	}
	
	
	
	/**
	 * Checks whether a given position is valid
	 * 
	 * @param position
	 * 		The position of which is checked if it's valid
	 * @return Returns whether or not the position is valid (i.e., if the position is in world & the position is passable)
	 */
	protected boolean isValidPosition(int[] position) {
		return (isPositionInWorld(position) && isPassable(position));
	}
	
	/**
	 * Checks whether a given position is valid
	 * 
	 * @param position
	 * 		The position of which is checked if it's valid
	 * @return Returns whether or not the position is valid (i.e., if the position is in world & the position is passable)
	 */
	protected boolean isValidPosition(double[] position) {
		return (isPositionInWorld(position) && isPassable(getCube(position)));
	}
	
	
	/**
	 * Checks whether a given position is in the world
	 * 
	 * @param position
	 * 		The position of which is checked if it's in the world
	 * @return Returns whether or not the position is the the borders of the world
	 */
	public boolean isPositionInWorld(int[] position) {
		return ((position[0] < this.getNbX()) && (position[1] < this.getNbY())
				&& (position[2] < this.getNbZ()) && (position[0] >= 0 && position[1] >= 0 &&
				position[2] >= 0));
	}
	
	/**
	 * Checks whether a given position is in the world
	 * 
	 * @param position
	 * 		The position of which is checked if it's in theworld
	 * @return Returns whether or not the position is the the borders of the world
	 */
	public boolean isPositionInWorld(double[] position) {
		return (((int) position[0] <= this.getNbX()) && ((int) position[1] <= this.getNbY())
				&& ((int) position[2] <= this.getNbZ())&& (position[0] >= 0 && position[1] >= 0 &&
				position[2] >= 0));
	}
	
	/**
	 * Check if the given position is a solid connected to the borders of the world
	 * 
	 * @param X
	 * 		The X coordinate of the position
	 * @param Y
	 * 		The Y coordinate of the position
	 * @param Z
	 * 		The Z coordinate of the position
	 * 
	 * @return True if the given position is solid and connected to 
	 * 		   the border of the world
	 */
	public boolean isSolidConnectedToBorder(int x, int y, int z) {
		return connectedToBorder.isSolidConnectedToBorder(x, y, z);
	}
	
	/**
	 * Checks whether is unit is valid
	 * 
	 * @param unit
	 * 		The unit of which is checked if it's valid
	 * @return Returns true if the unit is not null and the unit has a valid position
	 */
	public boolean isValidUnit(Unit unit) {
		return (unit != null && isValidPosition(unit.getPositionList()) && !unit.isTerminated());
	}
	
	/**
	 * Checks whether two cubes are adjacent
	 * 
	 * @param cube1
	 * 		The first cube
	 * @param cube2
	 * 		The second cube
	 * @return Returns true if the two cubes are adjacent
	 */
	protected boolean areAdjacentCubes(int[] cube1, int[] cube2) {
		return ((Math.abs(cube1[0] - cube2[0]) <= 1) && 
				(Math.abs(cube1[1] - cube2[1]) <= 1) &&
				(Math.abs(cube1[2] - cube2[2]) <= 1));
	}
	
	/**
	 * Checks whether's a log available at a given position of the world
	 * 
	 * @param position
	 * 		The position where is checked if there's a log available
	 * @return Return true if there's a uncarried log at the position
	 */
	protected boolean logAvailable(int[] position) {
		for (Log log: this.getLogs()) {
			if (log.getCubeInt()[0] == position[0] && log.getCubeInt()[1] == position[1]
					&& log.getCubeInt()[2] == position[2])
				return true;
		}
		return false;
	}
	
	/**
	 * Checks whether's a boulder available at a given position of the world
	 * 
	 * @param position
	 * 		The position where is checked if there's a boulder available
	 * @return Return true if there's a uncarried boulder at the position
	 */
	protected boolean boulderAvailable(int[] position) {
		for (Boulder boulder: this.getBoulders()) {
			if (boulder.getCubeInt()[0] == position[0] && boulder.getCubeInt()[1] == position[1]
					&& boulder.getCubeInt()[2] == position[2])
				return true;
		}
		return false;
	}
	
	/**
	 * Returns the coordinates of the cube of the position
	 * 
	 * @param position
	 * 		The position of which it's cube coordinates will be returned
	 * @return Returns the coordinates (integers) of the cube of the position
	 */
	private int[] getCube(double[] position) {
		int X = (int) Math.floor(position[0]);
		int Y = (int) Math.floor(position[1]);
		int Z = (int) Math.floor(position[2]);
		int[] cube = {X, Y, Z};
		return cube;

	}
	
	/**
	 * @param terrainType
	 * 		The 3D-matrix with the terrainTypes at all positions of the world
	 * @post
	 * 		The terraintypes of the world shall equal terrainType
	 * @post
	 * 		A 3D field with the dimension of the given terrainType is created in the 
	 *      ConnectedToBorder class
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
	
	
	/**
	 * @return Returns the number of cubes in the X direction
	 */
	public int getNbX() {
		return this.NbX;
	}
	
	/**
	 * @return Returns the number of cubes in the Y direction
	 */
	public int getNbY() {
		return this.NbY;
	}
	
	/**
	 * @return Returns the number of cubes in the Z direction
	 */
	public int getNbZ() {
		return this.NbZ;
	}
	
	
	
	/**
	 * Adds cubes to the list of cubes that have to be caved
	 * 
	 * @param solidToPassable
	 * 		List of all coordinates that has to be caved
	 * @post
	 * 		solidToPassable is added to listToCave
	 */
	private void addToListToCave(List<int[]> solidToPassable) {
		this.toCave.addAll(solidToPassable);
	}
	
	public Unit getClosestUnit(Predicate<Unit> condition, Unit performer) {
		List<Unit> unitList = new ArrayList<>();
		this.getUnits().stream().filter(condition).forEach(unitList::add);
		
		double minDistance = Double.MAX_VALUE;
		Unit closestUnit = null;
		
		for (Unit unit: unitList) {
			double distance = getDistanceBetweenUnits(unit, performer);
			if (distance < minDistance) {
				minDistance = distance;
				closestUnit = unit;
			}
				
		}
		
		return closestUnit;
	}
	
	/**
	 * 
	 * @param unit
	 * 		The unit in the world for which the closest boulder is returned
	 * @return Returns the closest boulder to the given unit
	 */
	public Boulder getClosestBoulder(Unit unit) {
		
		double minDistance = Double.MAX_VALUE;
		Boulder closestBoulder = null;
		
		for (Boulder boulder: this.getBoulders()) {
			double distance = getDistanceBetweenRawAndUnit(unit, boulder);
			if (distance < minDistance) {
				minDistance = distance;
				closestBoulder = boulder;
			}
		}
		
		return closestBoulder;
	}
	
	/**
	 * 
	 * @param unit
	 * 		The unit in the world for which the closest log is returned
	 * @return Returns the closest log to the given unit
	 */
	public Log getClosestLog(Unit unit) {
		
		double minDistance = Double.MAX_VALUE;
		Log closestLog = null;
		
		for (Log log: this.getLogs()) {
			double distance = getDistanceBetweenRawAndUnit(unit, log);
			if (distance < minDistance) {
				minDistance = distance;
				closestLog = log;
			}
		}
		
		return closestLog;
	}
	
	/**
	 * Returns the closest cube to the given unit with a certain terraintype
	 * @param terrain
	 * 		The terrainType
	 * @param unit
	 * 		The unit
	 * @return  Returns the closest cube to the given unit with a certain terraintype
	 */
	public int[] getClosestTerrainType(TerrainType terrain, Unit unit) {
		
		int[] position = null;
		double minDistance = Double.MAX_VALUE;
		
		for (int x = 0; x < getNbX(); x++) {
			for (int y = 0; y < getNbY(); y++) {
				for (int z = 0; z < getNbZ(); z++) {
					if (this.getTerrainTypeWorld()[x][y][z] == terrain.getInt()) {
						int[] currentPos = {x, y, z};
						double distance = getDistanceBetweenPosAndUnit(unit, currentPos);
						if (distance < minDistance) {
							minDistance = distance;
							position = currentPos;
						}
					}
				}
			}
		}
		
		return position;
	}
	/**
	 * Returns the distance between a Cube and unit
	 * 
	 * @param unit
	 * 		The unit
	 * @param pos
	 * 		The cube
	 * @return Returns the distance between a Cube and unit
	 */
	public double getDistanceBetweenPosAndUnit(Unit unit, int[] pos) {
		
		double X = Math.pow(unit.getPosition().getX() - (double) pos[0], 2);
		double Y = Math.pow(unit.getPosition().getY() - (double) pos[1], 2);
		double Z = Math.pow(unit.getPosition().getZ() - (double) pos[2], 2);
		
		return Math.sqrt(X + Y + Z);

	}
	
	/**
	 * Returns the distance between a Raw and unit
	 * 
	 * @param unit
	 * 		The unit
	 * @param Raw
	 * 		The raw material
	 * @return Returns the distance between a Raw and unit
	 */
	public
	
	public double getDistanceBetweenRawAndUnit(Unit unit, Raw raw) {
		
		double X = Math.pow(unit.getPosition().getX() - raw.getPosition()[0], 2);
		double Y = Math.pow(unit.getPosition().getY() - raw.getPosition()[1], 2);
		double Z = Math.pow(unit.getPosition().getZ() - raw.getPosition()[2], 2);
		
		return Math.sqrt(X + Y + Z);

	}
	/**
	 * Returns the distance between two units
	 * 
	 * @param unit1
	 * 		The first unit
	 * @param unit2
	 * 		The second unit
	 * @return Returns the distance between two units
	 */
	public double getDistanceBetweenUnits(Unit unit1, Unit unit2) {
		
		double X = Math.pow(unit1.getPosition().getX() - unit2.getPosition().getX(), 2);
		double Y = Math.pow(unit1.getPosition().getY() - unit2.getPosition().getY(), 2);
		double Z = Math.pow(unit1.getPosition().getZ() - unit2.getPosition().getZ(), 2);
		
		return Math.sqrt(X + Y + Z);

	}
	
	
	
	/**
	* @return Returns the set with all cubes that has to be caved
	*/
	public Set<int[]> getListToCave() {
		return this.toCave;
	}
	
	/**
	 * Adds a raw to the world
	 * @param raw
	 * 		The raw to be added
	 */
	protected void addRaw(Raw raw) {
		if (raw instanceof Boulder)
			this.addBoulder((Boulder) raw);
		else if (raw instanceof Log) 
			this.addLog((Log) raw);
	}
	
	/**
	 * Adds boulder to the boulderInWorld set
	 * 
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
			if (boulder.getCubeInt()[0] == cubeInt[0] && boulder.getCubeInt()[1] == cubeInt[1] 
					&& boulder.getCubeInt()[2] == cubeInt[2]) {
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
			if (log.getCubeInt()[0] == cubeInt[0] && log.getCubeInt()[1] == cubeInt[1]
					&& log.getCubeInt()[2] == cubeInt[2]) {
				return log;
			}
		return null;
	}
	
	/**
	 * Returns a valid enemy of a given unit
	 * 
	 * @param unit
	 * 		The unit of which an valid enemy will be returned
	 * @return
	 * 		Returns a valid enemy of the given unit, if it has none, it'll return null
	 * 		
	 */
	protected Unit getEnemy(Unit unit) {
		
		for (Unit enemy: this.getUnits()) {
			if (areAdjacentCubes(unit.getCubeInt(), enemy.getCubeInt()) &&
					unit.getFaction() != enemy.getFaction())
				return enemy;
		}
		return null;
		
	}
	
	
	
	/**
	 * Adds log to the logInWorld set
	 * 
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
	 * Tries to add faction to the activeFaction set
	 * 
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
	 * @return Returns the number of active factions in the world
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
	 * Removes faction from world
	 * 
	 * @param faction
	 * 		The faction that has to be removed
	 * @post
	 * 		The faction in not in the activeFactionSet of the world
	 */
	protected void removeFaction(Faction faction) {
		this.activeFactions.remove(faction);
	}
	
	/**
	 * Checks whether both units are in the world
	 * 
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
	 * Adds unit to world
	 * 
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
	 * Tries to add unit to world
	 * 
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
		if (this.isTerminated())
			throw new IllegalStateException("The world is terminated");
		
		if (unit.getFaction() == null)
			unit.setFaction(this.getRightFaction());
		
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
	 * A new unit is spawned in the world
	 * 
	 * @post
	 * 		A new unit is added to the world, and is assigned to a faction
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the world is full
	 */
	public Unit spawnUnit() throws IllegalArgumentException, IllegalStateException {
		try {
			return this.spawnUnit(true);
		} catch (IllegalArgumentException nbUnits) {
			throw nbUnits;
		} catch (IllegalStateException terminated) {
			throw terminated;
		}
	}

	/**
	 * A new unit is spawned in the world, with a valid faction and a valid position
	 * @param defaultBehavior
	 * 			A boolean to indicate if the unit should activate defaultBehavior
	 * @post
	 * 		A new unit is added to the world, and is assigned to a faction
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the world is full
	 */
	public Unit spawnUnit(boolean defaultBehavior) throws IllegalArgumentException, IllegalStateException {
		if (this.getNbUnits() >= 100)
			throw new IllegalArgumentException("This world has reached his maximum"
					+ "number of units");
		if (this.isTerminated())
			throw new IllegalStateException("World is terminated");
		Unit unit = createRandomUnit(defaultBehavior);
		unit.setFaction(this.getRightFaction());
		unit.getFaction().addUnit(unit);
		this.addUnit(unit);
		return unit;
	}
	
	/**
	 * @return Returns a new random unit
	 */
	private Unit createRandomUnit(boolean defaultBehavior) {
		
		int randomStrength = (int) Math.round(Math.random() * 75 + 25);
		int randomAgility = (int) Math.round(Math.random() * 75 + 25);
		int randomToughness = (int) Math.round(Math.random() * 75 + 25);
		int minWeight = (int) Math.round((randomStrength + randomAgility) / 2);
		int randomWeight = (int) Math.round(Math.random() * (100 - minWeight) + minWeight);
		String randomName = "SpawnedUnit";
		int[] position = createRandomSpawnPosition();
		
		return new Unit(randomName, position, randomWeight, randomAgility, randomStrength, 
				randomToughness, defaultBehavior);
	}
	

	/**
	 * @return Returns the coordinates of a random valid spawn position
	 */
	public int[] createRandomSpawnPosition() {
		
		int X = ((int) Math.round(Math.random() * (this.getNbX() - 1)));
		int Y = ((int) Math.round(Math.random() * (this.getNbY() - 1)));
		int Z = ((int) Math.round(Math.random() * (this.getNbZ() - 1)));
		
		if (isValidSpawnPosition(X, Y, Z)) {
			int[] pos = {X, Y, Z};
			return pos;
		} 
		
		return createRandomSpawnPosition();
		
	}
	
	/**
	 * Returns all objects occupying the cube 
	 * 
	 * @param cube
	 * 		The cube that has to be examined
	 * @return Returns all objects occupying the cube 
	 */
	public List<Object> getAllObjectsOccupyingCube(int[] cube) {
		
		Set<Boulder> BoulderList = this.getBoulders();
		Set<Log> LogList = this.getLogs();
		Set<Unit> UnitList = this.getUnits();
		List<Object> allObjectsOccupyingCube = new ArrayList<>();;
		
		for(Boulder boulder: BoulderList) {
			if (boulder.getCubeInt()[0] == cube[0] && boulder.getCubeInt()[1] == cube[1]
					&& boulder.getCubeInt()[2] == cube[2]) {
				allObjectsOccupyingCube.add(boulder);
			}
		}
		
		for(Log log: LogList) {
			if (log.getCubeInt()[0] == cube[0] && log.getCubeInt()[1] == cube[1]
					&& log.getCubeInt()[2] == cube[2]) {
				allObjectsOccupyingCube.add(log);
			}
		}
		
		for(Unit unit: UnitList) {
			if (unit.getCubeInt()[0] == cube[0] && unit.getCubeInt()[1] == cube[1] &&
					unit.getCubeInt()[2] == cube[2]) {
				allObjectsOccupyingCube.add(unit);
			}
		}
		
		return allObjectsOccupyingCube;
	}
	
	/**
	 * Removes a raw from the world
	 * @param raw
	 * 		The raw to be removed
	 */
	protected void removeRaw(Raw raw) {
		if (raw instanceof Boulder) 
			removeBoulder((Boulder) raw);
		else if (raw instanceof Log)
			removeLog((Log) raw);
	}
	
	/**
	 * Removes boulder from world
	 * 
	 * @param boulder
	 * 		The boulder that has to be removed
	 * @post
	 * 		The boulder is removed from the boulderInWorldSet
	 */
	protected void removeBoulder(Boulder boulder) {
		this.boulderInWorld.remove(boulder);
	}
	
	/**
	 * 
	 * Removes log from world
	 * 
	 * @param log
	 * 		The log that has to be removed
	 * @post
	 * 		The log is removed from the logInWorldSet
	 */
	protected void removeLog(Log log) {
		this.logInWorld.remove(log);
	}
	
	/**
	 * Removes unit from world
	 * 
	 * @param unit
	 * 		The unit that has to be removed
	 * @post
	 * 		The unt is removed from the unitInWorldSet
	 */
	protected void removeUnit(Unit unit) {
		this.unitsInWorld.remove(unit);
	}
	
	/**
	 * Terminates world
	 * 
	 * @post
	 * 		The world and all its factions are terminated
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
