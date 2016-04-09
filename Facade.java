package hillbillies.part2.facade;

import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {
	
	public Facade() {};

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		return new Unit(name, initialPosition, weight, agility, strength, toughness, enableDefaultBehavior);
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getPositionList();
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getCubeInt();
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getName();
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		// TODO Auto-generated method stub
		unit.setName(newName);
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getWeight();
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		unit.setWeight(newValue);
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getStrength();
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		unit.setStrength(newValue);
		
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getAgility();
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		unit.setAgility(newValue);
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getToughness();
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		unit.setToughness(newValue);
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getMaxHitpoints();
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getHitpoints();
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getMaxStamina();
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getStamina();
	}


	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		// TODO Auto-generated method stub
		unit.moveToAdjacent(dx, dy, dz);
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getSpeed();
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return (!unit.isFalling() && unit.getSpeed() > 0);
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		unit.setSprinting(true);
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		unit.setSprinting(false);
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.isSprinting();
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getOrientation();
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		// TODO Auto-generated method stub
		unit.moveTo(cube);
	}


	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.isWorking();
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		// TODO Auto-generated method stub
		attacker.fight(attacker, defender);
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.isAttacking();
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		unit.resting();
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.isResting();
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		// TODO Auto-generated method stub
		unit.setDefaultBehavior(value);
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getDefaultBehavior();
	}

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		// TODO Auto-generated method stub
		return new World(terrainTypes, modelListener);
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getNbX();
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getNbY();
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getNbZ();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		// TODO Auto-generated method stub
		world.advanceTime(dt);
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		int[] position = {x, y, z};
		return world.getTerrainType(position);
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		// TODO Auto-generated method stub
		int[] position = {x, y, z};
		world.setTerrainType(value, position);
		
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		return world.isSolidConnectedToBorder(x, y, z);
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		return world.spawnUnit(enableDefaultBehavior);
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		// TODO Auto-generated method stub
		world.addUnit(unit);
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getUnits();
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.isCarryingLog();
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.isCarryingBoulder();
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return !unit.isTerminated();
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getExperiencePoints();
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		unit.workAt(x, y, z);
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		return unit.getFaction();
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		// TODO Auto-generated method stub
		return faction.getUnits();
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getActiveFactions();
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		// TODO Auto-generated method stub
		return boulder.getPosition();
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getBoulders();
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		// TODO Auto-generated method stub
		return log.getPosition();
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getLogs();
	}

}
