package hillbillies.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	// INITIALISATION
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) {
		
		this.setTerrainTypeWorld(terrainTypes);
		this.setTerrainChangeListener(modelListener);
		this.setDimension();
		
		
	}
	
	// ADVANCE TIME
	public void advanceTime(double time) {
		
		for (int i = 0; i < this.getListToCave().size(); i++) {
			int[] position = this.getListToCave().get(i);
			this.cave(position);
		this.changeSolidToPassable();
			
		}
		
	}
	
	// CAVE
	public void cave(int[] position) {
		
		this.setTerrainType(0, position);
		
		double P = Math.random();
		
		if (P <= 0.25)
			new Boulder(this, position);
		else if (P <= 0.50)
			new Log(this, position);
		
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
	public void changeSolidToPassable() {
		
		
		
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
	// isPassable
	public boolean isPassable(int[] position) {
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		return this.isPassable(this.getTerrainTypeWorld()[X][Y][Z]);

	}
	
	// isPassable
	public boolean isPassable(int terrainType) {
		return ((terrainType == 0) || (terrainType == 1));
	}
	
	// isValidPosition
	public boolean isValidPosition(int[] position) {
		return ((position[0] <= this.getNbX()) && (position[1] <= this.getNbY())
				&& (position[2] <= this.getNbZ()));
	}

	
	
	// SET and GET TERRAINTYPEWORLD
	public void setTerrainTypeWorld(int[][][] terrainType) {
		this.terrainTypes = terrainType;
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
	public void setDimension() {
		this.setNbX(this.getTerrainTypeWorld().length);
		this.setNbY(this.getTerrainTypeWorld()[0].length);
		this.setNbZ(this.getTerrainTypeWorld()[0][0].length);

	}
	
	
	// SET and GET NbX, NbY, NbZ
	public void setNbX(int X) {
		this.NbX = X;
	}
	
	public int getNbX() {
		return this.NbX;
	}
	
	
	public void setNbY(int Y) {
		this.NbY = Y;
	}
	
	
	public int getNbY() {
		return this.NbY;
	}
	
	public void setNbZ(int Z) {
		this.NbZ = Z;
	}
	
	public int getNbZ() {
		return this.NbZ;
	}
	
	
	// ADD TO LIST TO CAVE
	public void addToListToCave(List<int[]> solidToPassable) {
		this.toCave.addAll(solidToPassable);
	}
	
	// GET LIST TO CAVE
	public List<int[]> getListToCave() {
		return this.toCave;
	}
	
	// ADD TO AND GET BOULDER LIST
	public void addToBoulderList(Boulder boulder) {
		if (!this.getBoulderList().contains(boulder))
			this.boulderInWorld.add(boulder);
	}
	
	public List<Boulder> getBoulderList() {
		return this.boulderInWorld;
	}
	
	
	// REMOVE FROM BOULDER LIST
	public void removeBoulderFromList(Boulder boulder) {
		this.boulderInWorld.remove(boulder);
	}
	


	
	
	
	private int[][][] terrainTypes;
	private TerrainChangeListener modelListener;
	private ConnectedToBorder connectedToBorder;
	private int NbX;
	private int NbY;
	private int NbZ;
	private List<int[]> toCave = new ArrayList<>();
	private List<Boulder> boulderInWorld = new ArrayList<>();
	private List<Log> logInWorld = new ArrayList<>();
	

	

}
