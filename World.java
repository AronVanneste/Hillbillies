package hillbillies.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;

public class World {
	
	public World(int[][][] terrainTypes, TerrainChangeListener modelListener) {
		
		this.setTerrainTypeWorld(terrainTypes);
		this.modelListener = modelListener;
		this.setDimension();
		
		
	}
	
	public void advanceTime(double time) {
		
		for (int i = 0; i < this.getListToCave().size(); i++) {
			int[] position = this.getListToCave().get(i);
			this.cave(position);
		}
	}
	
	public void cave(int [] position) {
		this.setTerrainType(0,position);
	}
	
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
	
	int[] XList = this.getTerrainTypeWorld()[0][0];
	
	public boolean isPassable(int terrainType) {
		return ((terrainType == 0) || (terrainType == 1));
	}
	
	public void setTerrainTypeWorld(int[][][] terrainType) {
		this.terrainTypes = terrainType;
	}
	
	public int[][][] getTerrainTypeWorld() {
		return this.terrainTypes;
	}
	
	public void setTerrainType(int terraintype,int[] position) {
		this.terrainTypes[position[0]][position[1]][position[2]] = terraintype;
	}
	
	public void setDimension() {
		this.setNbX(this.getTerrainTypeWorld().length);
		this.setNbY(this.getTerrainTypeWorld()[0].length);
		this.setNbZ(this.getTerrainTypeWorld()[0][0].length);

	}
	
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
	
	public void addToListToCave(List<int[]> solidToPassable) {
		this.toCave.addAll(solidToPassable);
	}
	
	public List<int[]> getListToCave() {
		return this.toCave;
	}

	
	
	
	private int[][][] terrainTypes;
	private TerrainChangeListener modelListener;
	private ConnectedToBorder connectedToBorder;
	private int NbX;
	private int NbY;
	private int NbZ;
	private List<int[]> toCave = new ArrayList<>();;
	

	

}
