 package hillbillies.tests;

import static org.junit.Assert.*;



import org.junit.Assert;

import hillbillies.model.IllegalPositionException;
import hillbillies.model.IllegalTargetException;
import hillbillies.model.Log;
import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.IllegalNameException;
import hillbillies.model.Unit;
import ogp.framework.util.Util;

import org.junit.Test;
import hillbillies.model.Vector;
import hillbillies.model.World;

public class UnitTest {

	@Test
	public void assertPosition() {
		int[] pos = {31, 31, 31};
		double[] sol = {31, 31, 31};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		double[] position = {unit1.getPosition().getX(), unit1.getPosition().getY(), unit1.getPosition().getZ()};
		Assert.assertArrayEquals(sol , position, Util.DEFAULT_EPSILON);

	}
	
	@Test
	public void assertName() {
		int[] pos = {31, 31, 31};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		Assert.assertEquals("Unit", unit1.getName());

	}
	
	@Test
	public void assertWeight() {
		int[] pos = {31, 31, 31};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		Assert.assertEquals(50, unit1.getWeight());

	}
	@Test
	public void assertWeight2() {
		int[] pos = {31, 31, 31};
		Unit unit1 = new Unit("Unit", pos, 1025, 50, 50, 50, false);
		Assert.assertEquals(75, unit1.getWeight());

	}
	@Test
	public void assertWeight3() {
		int[] pos = {31, 31, 31};
		Unit unit1 = new Unit("Unit", pos, -50, 50, 50, 50, false);
		Assert.assertEquals(50, unit1.getWeight());

	}
	@Test
	public void assertWeight4() {
		int[] pos = {31, 31, 31};
		Unit unit1 = new Unit("Unit", pos, 0, 50, 50, 50, false);
		Assert.assertEquals(50, unit1.getWeight());

	}
	@Test
	public void assertStamina() {
		int[] pos = {31, 31, 31};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		Assert.assertEquals(50, unit1.getWeight());

	}
	
	@Test (expected = IllegalPositionException.class)
	public void assertIllegalPosition() {
		int[] pos = {-4, -5, 0};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.getPosition();
	}
	
	@Test 
	public void assertSetToughness() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setToughness(51);
		Assert.assertEquals(51, unit1.getToughness());
	}
	
	@Test 
	public void assertSetToughness2() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setToughness(105);
		Assert.assertEquals(30, unit1.getToughness());
	}
	
	@Test 
	public void assertSetWeight() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setWeight(51);
		Assert.assertEquals(51, unit1.getWeight());
	}
	
	@Test 
	public void assertSetWeight2() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setWeight(105);
		Assert.assertEquals(55, unit1.getWeight());
	}
	
	@Test 
	public void assertSetStamina() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setStamina(51);
		Assert.assertEquals(51, unit1.getStamina());
	}
	
	@Test (expected = AssertionError.class)
	public void assertSetIllegalStamina() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setStamina(-5);
		Assert.assertEquals(50, unit1.getStamina());
	}

	
	@Test 
	public void assertSetHitpoints() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setHitpoints(51);
		Assert.assertEquals(51, unit1.getHitpoints());
	}
	
	@Test (expected = AssertionError.class)
	public void assertSetIllegalHitpoints() {
		int[] pos = {5, 4, 3};
		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit1.setHitpoints(1506540);
		Assert.assertEquals(50, unit1.getHitpoints());
	}

	
	@Test (expected = IllegalNameException.class)
	public void assertIllegalNameCapital() {
		int[] pos = {1,2,3};
		Unit unit = new Unit("aron", pos, 50, 50, 50, 50, false);
		unit.getName();
	}


	@Test (expected = IllegalNameException.class)
	public void assertIllegalNameNumber() {
		int[] pos = {1,2,3};
		Unit unit = new Unit("Aron69", pos, 50, 50, 50, 50, false);
		unit.getName();
	}


	@Test (expected = IllegalNameException.class)
	public void assertIllegalNameNotEnough() {
		int[] pos = {1,2,3};
		Unit unit = new Unit("A", pos, 50, 50, 50, 50, false);
		unit.getName();

	}

	@Test
	public void assertGetMaxHitpoints() {

		int[] pos = {1,2,3};
		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
		int maxHitpoints = (int) Math.ceil(2 * 50 * 50 / 100.0);
		Assert.assertEquals(maxHitpoints,unit.getMaxHitpoints());
	}

	
	@Test 
	public void assertVectorClassEquals() {
		Vector vector1 = new Vector(1,1,1);
		Vector vector2 = new Vector(1,1,1);
		Assert.assertEquals(true, vector1.equals(vector2));
	
	}
	
	@Test
	public void assertIsValidPosition1() {
		int[] pos = {1,2,3};
		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));

	}
	
	@Test
	public void assertIsValidPosition2() {
		int[] pos = {1,2,3};
		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));

	}
	
	
	
	@Test
	public void assertMoveToAdjacent1() {
		int[] pos = {1,2,3};
		int x = 1;
		int y = 0;
		int z = 1;
		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
		unit.moveToAdjacent(x, y, z);
		Vector test = new Vector(2, 2, 4);
		Assert.assertTrue(test.equals(unit.getTarget()));

	}
	
	@Test
	public void assertMoveToAdjacent2() {
		int[] pos = {1,2,3};
		int x = -1;
		int y = 1;
		int z = 1;
		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
		unit.moveToAdjacent(x, y, z);
		Vector test = new Vector(0, 3, 4);
		Assert.assertTrue(test.equals(unit.getTarget()));

	}
	
	@Test
	public void assertMoveToAdjacent3() {
		int[] pos = {1,2,3};
		int x = 0;
		int y = 1;
		int z = -1;
		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
		unit.moveToAdjacent(x, y, z);
		Vector test = new Vector(1, 3, 2);
		Assert.assertTrue(test.equals(unit.getTarget()));

	}
	
	@Test
	public void assertUnitAddedToFaction() {
		int[][][] field = {{{1,0,3},{0,2,2},{3,1,1}},
						   {{1,0,3},{0,2,2},{3,1,1}},
						   {{1,0,3},{0,2,2},{3,1,1}}};
		World world = new World(field, null);
		int[] pos = {0,0,1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		world.addUnit(unit);
		Assert.assertNotNull(unit.getFaction());
		

	}
	
	@Test
	public void assertUnitSpawned() {
		int[][][] field = {{{1,0,3},{0,2,2},{3,1,1}},
						   {{1,0,3},{0,2,2},{3,1,1}},
						   {{1,0,3},{0,2,2},{3,1,1}}};
		World world = new World(field, null);
		world.spawnUnit();
		Assert.assertSame(world.getUnits().size(), 1);
	}
	
	@Test
	public void assertUnitMoveTo() {
		
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] target = {0, 0, 0};
		unit.moveTo(target);
		Vector targetV = new Vector(0,0,0);
		Assert.assertTrue(targetV.equals(unit.getFinalTarget()));


	}
	
	@Test (expected = IllegalStateException.class)
	public void assertUnitMoveToException1() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] target = {0, 0, 0};
		unit.terminate();
		unit.moveTo(target);

	}
	
	@Test (expected = IllegalTargetException.class)
	public void assertUnitMoveToException2() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] target = {-41, 0, 0};
		unit.moveTo(target);

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void assertUnitMoveToException3() {
		
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] target = {2, 0, 0};
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		world.addUnit(unit);
		unit.shouldFall();
		unit.moveTo(target);

	}
	
	@Test
	public void assertUnitWork() {
		
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit.work();
		Assert.assertTrue(unit.isWorking());

	}
	
	@Test (expected = IllegalStateException.class)
	public void assertUnitWorkException() {
		
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit.terminate();
		unit.work();

	}

	@Test (expected = IllegalArgumentException.class)
	public void assertUnitWorkException2() {
		
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit.shouldFall();
		unit.work();

	}
	
	@Test
	public void assertUnitSprinting() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit.setSprinting(true);
		Assert.assertFalse(unit.isSprinting());

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void assertUnitFightException() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] pos2 = {1, 0, 1};
		Unit unit2 = new Unit("Unit", pos2, 50, 50, 50, 50, false);
		unit.fight(unit, unit2);
	}
	
	@Test (expected = IllegalStateException.class)
	public void assertUnitFightException2() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] pos2 = {1, 0, 1};
		Unit unit2 = new Unit("Unit", pos2, 50, 50, 50, 50, false);
		unit.terminate();
		unit.fight(unit, unit2);
	}
	
	@Test 
	public void assertUnitFight() {
		int[] pos = {0, 0, 0};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] pos2 = {1, 0, 0};
		Unit unit2 = new Unit("Unit", pos2, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		world.addUnit(unit);
		world.addUnit(unit2);
		unit.fight(unit, unit2);
		Assert.assertTrue(unit.isAttacking());

	}
	
	@Test 
	public void assertUnitFight2() {
		int[] pos = {0, 0, 0};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[] pos2 = {1, 0, 0};
		Unit unit2 = new Unit("Unit", pos2, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		world.addUnit(unit);
		world.addUnit(unit2);
		unit.fight(unit, unit2);
		Assert.assertTrue(unit2.isAttacked());

	}
	
	@Test 
	public void assertUnitResting() {
		int[] pos = {0, 0, 0};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit.resting();
		Assert.assertTrue(unit.isResting());

	}
	
	@Test (expected = IllegalStateException.class)
	public void assertUnitRestingException() {
		int[] pos = {0, 0, 0};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit.terminate();
		unit.resting();

	}
	
	@Test (expected = IllegalArgumentException.class)
	public void assertUnitRestingException2() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		unit.shouldFall();
		unit.resting();

	}
	
	@Test
	public void assertUnitCarryLog() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		Log log = new Log(world, pos);
		unit.carryLog(log);
		Assert.assertTrue(unit.isCarryingLog());

	}
	
	@Test (expected = NullPointerException.class)
	public void assertUnitCarryLog2() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		Log log = new Log(world, pos);
		unit.carryLog();
		//Assert.assertTrue(unit.isCarryingLog());

	}
	
	@Test 
	public void assertUnitCarryLog3() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		world.addUnit(unit);
		Log log = new Log(world, pos);
		unit.carryLog();
		Assert.assertTrue(unit.isCarryingLog());

	}
	
	@Test 
	public void assertUnitCarryLog4() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		int weight = unit.getWeight();
		World world = new World(field, null);
		world.addUnit(unit);
		Log log = new Log(world, pos);
		unit.carryLog();
		Assert.assertTrue(unit.getWeight() ==  weight + log.getWeight());

	}

	@Test
	public void assertUnitCarryBoulder() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		Boulder boulder = new Boulder(world, pos);
		unit.carryBoulder(boulder);
		Assert.assertTrue(unit.isCarryingBoulder());

	}
	
	@Test (expected = NullPointerException.class)
	public void assertUnitCarryBoulder2() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		Boulder boulder = new Boulder(world, pos);
		unit.carryBoulder();
		//Assert.assertTrue(unit.isCarryingLog());

	}
	
	@Test 
	public void assertUnitCarryBoulder3() {
		int[] pos = {0, 0, 1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		world.addUnit(unit);
		Boulder boulder = new Boulder(world, pos);
		unit.carryBoulder();
		Assert.assertTrue(unit.isCarryingBoulder());

	}

	
	@Test
	public void assertUnitFalling() {
		int[][][] field = {{{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}},
				   {{0,0,0},{0,0,0},{0,0,0}}};
		World world = new World(field, null);
		int[] pos = {0,0,1};
		Unit unit = new Unit("Unit", pos, 50, 50, 50, 50, false);
		world.addUnit(unit);
		unit.shouldFall();
		Assert.assertTrue(unit.isFalling());


	}
	

	
	




	
}
