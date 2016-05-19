 package hillbillies.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

import hillbillies.model.IllegalPositionException;
import hillbillies.model.IllegalSourceException;
import hillbillies.model.IllegalTargetException;
import hillbillies.model.LiteralExpression;
import hillbillies.model.Log;
import hillbillies.model.MoveToStatement;
import hillbillies.model.NotExpression;
import hillbillies.model.Raw;
import hillbillies.model.S;
import hillbillies.model.Scheduler;
import hillbillies.model.SequenceStatement;
import hillbillies.model.T;
import hillbillies.model.TerrainType;
import hillbillies.model.TrueExpression;
import hillbillies.model.AnyExpression;
import hillbillies.model.BooleanExpression;
import hillbillies.model.Boulder;
import hillbillies.model.BoulderExpression;
import hillbillies.model.E;
import hillbillies.model.Faction;
import hillbillies.model.FollowStatement;
import hillbillies.model.IfStatement;
import hillbillies.model.IllegalNameException;
import hillbillies.model.Unit;
import hillbillies.model.UnitExpression;
import ogp.framework.util.Util;

import org.junit.Test;
import hillbillies.model.Vector;
import hillbillies.model.WhileStatement;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.part3.programs.SourceLocation;
import javafx.concurrent.Task;

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
	
//	@Test
//	public void assertUnitSpawned() {
//		int[][][] field = {{{1,0,3},{0,2,2},{3,1,1}},
//						   {{1,0,3},{0,2,2},{3,1,1}},
//						   {{1,0,3},{0,2,2},{3,1,1}}};
//		World world = new World(field, null);
//		world.spawnUnit();
//		Assert.assertSame(world.getUnits().size(), 1);
//	}
	
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
	
	@Test (expected = ClassCastException.class)
	public void assertTypeSafety() {
		SourceLocation source = new SourceLocation(1, 5);
		E<?> expression = new BoulderExpression(source);
		NotExpression not = new NotExpression((BooleanExpression) expression, source);
	}
	
	@Test (expected = IllegalSourceException.class)
	public void assertSourceException() {
		new NotExpression(null, null);
	}
	
	
	
	World createRandomWorld() {
		int[][][] world = {{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}},{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}},{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}},{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}},{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},{0, 0, 0, 0, 0}}}; 
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				for (int z = 0; z < 5; z++) {
					int P = (int) Math.round(Math.random() * 3);
					world[x][y][z] = P;
				}
			}
		}
		TerrainChangeListener t = null;
		return new World(world, t);
	}
	
	int[] getPassableCube(World w) {
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
					int[] position = {x, y, 0};
					if (w.isPassable(position))
						return position;
				}
			}
		return null;
	}
	
	S createRandomStatement() {
		World world = createRandomWorld();
		for (int i = 0; i < 3; i++) {
			world.spawnUnit();
		}
		int[] pos = getPassableCube(world);
		Unit unit = world.getUnits().iterator().next();
		SourceLocation source = new SourceLocation((int) Math.round(Math.random() * 100), 
				(int) Math.round(Math.random() * 100));
		S s = new MoveToStatement(new LiteralExpression(pos[0], pos[1], pos[2], source), source);
		s.setUnit(unit);
		return s;

	}
	
	S createRandomFollowStatement() {
		World world = createRandomWorld();
		for (int i = 0; i < 3; i++) {
			world.spawnUnit();
		}
		Unit unit = world.getUnits().iterator().next();
		SourceLocation source = new SourceLocation((int) Math.round(Math.random() * 100), 
				(int) Math.round(Math.random() * 100));
		AnyExpression unitE = new AnyExpression(source);
		unitE.setUnit(unit);
		FollowStatement s = new FollowStatement(unitE, source);
		s.setUnit(unit);
		return s;
	}
	
	T createRandomTask() {
		int[] pos = {(int) Math.round(Math.random() * 4), (int) Math.round(Math.random() * 4),
					(int) Math.round(Math.random() * 4)};
		return new T("Name", (int) Math.round(Math.random() * 100 - 50), 
				createRandomStatement(), pos);
	}
	
	T createRandomTask(int priority) {
		int[] pos = {(int) Math.round(Math.random() * 4), (int) Math.round(Math.random() * 4),
					(int) Math.round(Math.random() * 4)};
		return new T("Name", priority, 
				createRandomStatement(), pos);
	}

	@Test
	public void assertSequenceIterator() {
		List<S> s = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			s.add(createRandomStatement());
		}
		SequenceStatement seq = new SequenceStatement(s, new SourceLocation(5, 8));
		Iterator<S> iter = seq.iterator();
		int check = 0;
		while (iter.hasNext()) {
			check += 1;
			iter.next();
		}
		Assert.assertEquals(seq.getStatements().size(), check);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void assertAddSchedulerToFaction1() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		Scheduler sd = new Scheduler(f);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void assertAddSchedulerToFaction2() {
		World w = createRandomWorld();
		Scheduler sd = new Scheduler(new Faction(w));
		Faction f = new Faction(w, sd);
	}
	
	@Test
	public void assertAddSchedulerToFaction3() {
		World w = createRandomWorld();
		Scheduler sd = new Scheduler();
		Faction f = new Faction(w, sd);
		Assert.assertEquals(sd, f.getScheduler());
	}
	
	@Test (expected = IllegalPositionException.class)
	public void assertAddTask() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		Scheduler s = f.getScheduler();
		int[] pos = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		T t = new T("Name", 50, createRandomStatement(), pos);
		s.addTask(t);
	}
	
	@Test
	public void assertUpdateTask() {
		Scheduler s = new Scheduler();
		int[] pos = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		T t = new T("Name", 50, createRandomStatement(), pos);
		s.addTask(t);
		Assert.assertEquals(1, s.getTasks().size());
	}
	
	@Test
	public void assertUpdateTask2() {
		World w = createRandomWorld();
		Scheduler s = new Scheduler();
		int[] pos = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		T t = new T("Name", 50, createRandomStatement(), pos);
		s.addTask(t);
		Faction f = new Faction(w, s);
		Assert.assertEquals(0, s.getTasks().size());
	}
	
	
	@Test
	public void assertSchedulerLambdas() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		for (int i = 0; i < 5; i++) {
			s.addTask(createRandomTask());
		}
		for (int i = 0; i < 2; i++) {
			s.addTask(createRandomTask(200));
		}
		Assert.assertEquals(2, s.getAllTaskPriorityLargerThan(150).size());
	}
	
	@Test
	public void assertSchedulerLambdas2() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		for (int i = 0; i < 5; i++) {
			s.addTask(createRandomTask());
		}
		for (int i = 0; i < 2; i++) {
			s.addTask(createRandomTask(-200));
		}
		Assert.assertEquals(2, s.getAllTaskPrioritySmallerThan(-150).size());
	}
	
	@Test
	public void assertSchedulerLambdas3() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		for (int i = 0; i < 5; i++) {
			s.addTask(createRandomTask());
		}
		for (int i = 0; i < 2; i++) {
			s.addTask(createRandomTask(-200));
		}
		List<T> tasks = s.getAllTasksSatisfyingCondition((T t) -> 
				(t.getActivity() instanceof MoveToStatement));
		Assert.assertEquals(7, tasks.size());
	}
	
	@Test
	public void assertSchedulerLambdas4() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		for (int i = 0; i < 5; i++) {
			s.addTask(createRandomTask());
		}
		for (int i = 0; i < 2; i++) {
			s.addTask(createRandomTask(-200));
		}
		List<T> tasks = s.getAllTasksSatisfyingCondition((T t) -> 
				(t.getActivity() instanceof WhileStatement));
		Assert.assertEquals(0, tasks.size());
	}
	
	@Test
	public void assertAssignTaskToUnit() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		Faction f = new Faction(w, s);
		int[] pos = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, true);
		f.addUnit(unit);
		Assert.assertTrue(f.getUnits().contains(unit));
	}
	
	@Test
	public void assertAssignTaskToUnit2() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		Faction f = new Faction(w, s);
		int[] pos = getPassableCube(w);
		for (int i = 0; i < 2; i++) {
			s.addTask(createRandomTask());
		}
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, true);
		f.addUnit(unit);
		unit.pickTask();
		Assert.assertTrue(unit.hasTask());
	}
	
	@Test 
	public void assertMoveToStatement() {
		MoveToStatement moveTo = (MoveToStatement) createRandomStatement();
		World w = createRandomWorld();
		Unit unit = w.spawnUnit();
		T t = new T("Name", 50, moveTo, getPassableCube(w));
		Faction f = unit.getFaction();
		Scheduler s = f.getScheduler();
		s.addTask(t);
		unit.pickTask();
		unit.executeTask();
		boolean b = (((int) moveTo.getUnit().getFinalTarget().getX()) == 
					moveTo.getPosition().evaluate()[0] && 
					((int) moveTo.getUnit().getFinalTarget().getY()) == 
					moveTo.getPosition().evaluate()[1] &&
					((int) moveTo.getUnit().getFinalTarget().getY()) == 
					moveTo.getPosition().evaluate()[1]);
		Assert.assertTrue(b);
	}
	
	int[] pos1 = {1,2,3};
	int[] pos2 = {4,4,5};
	int[] pos3 = {10,10,10};
	double[] pos4 = {3.54,4.66,5.999};
	int[] posInt4 = {3,4,5};
	
	Unit unit1 = new Unit(Aron, pos1, 10, 10,10,10,false);
	Unit unit2 = new Unit(Steven, pos2, 20,20,20,20,false);
	Unit unit3 = new Unit(Eric, pos3, 30,30,30,30,false);
	Unit unit4 = new Unit(Piet, pos3, 30,30,30,30,false);
	
	
	Raw raw1 = new Raw(world,pos1);
	Raw raw2 = new Raw(world,pos2);
	Raw raw3 = new Raw(world,pos3);
	Raw raw4 = new Raw(world,pos4);
	int[][][] terrainTypess = 	{{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,2,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,0,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,0,2}}};
	World world1 = new World(terrainTypess, null);
	
	@Test
	public void TestGetSetRaw(Raw raw1, Raw raw2, Raw raw3) {
		unit4.terminate();
		Assert.assertEquals(raw1.getWorld,world1); 				//getWorld
		Assert.assertTrue(raw1.isValidPosition(pos1));			//isValidPosition
		Assert.assertTrue(raw2.isValidPosition(pos2));			//
		Assert.assertTrue(raw1.isValidWorld(world1));			//isValidWorld
		Assert.assertFalse(raw1.isValidWorld(null));			//
		Assert.assertTrue(world1.isPassable(pos1));				//isPassable
		Assert.assertFalse(world1.isPassable(pos2));			//
		Assert.assertTrue(world1.isPassable(raw1));				//
		Assert.assertTrue(world1.isSolidBelow(raw1));			//isSolidBelow
		Assert.assertFalse(world1.isSolidBelow(raw2));			//
		Assert.assertFalse(raw1.isActive(raw1));				//isActive
		raw1.setOwner(unit1);									//setOwner
		Assert.assertTrue(raw1.isActive());						//isActiveAfterDeletingOwner
		Assert.assertTrue(raw1.shouldFall());					//shouldFall
		Assert.assertEquals(raw4.getCubeint(),posInt4);			//getCubeInt
		raw4.setPosition(pos1);									//
		Assert.assertEquals(raw4.getPosition(),pos1);			//getPosition
		raw1.removeOwner();										//removeOwner
		Assert.assertFalse(raw4.getOwner() == null);													//hasOwner
		AssertFalse(!(!unit1.isCarryingBoulder()) && !unit1.isCarryingLog());							//removeOwnerIsCarryingRaw
		raw2.terminate();																				//terminate
		Assert.assertTrue(raw2.isTerminated() && raw2.getWorld() == null && raw2.getOwner() == null);	//
		
}
	
	@Test (expected = IllegalStateException.class)
	public void assertSetDeadOwner() {
		raw4.setOwner(unit4);
	}
	/**
	 * Simple method to check if two lists of doubles are almost equal, not 100% accurate for very long lists
	 */
	public listDoubleEquals(double[] list1, double[] list2) {
		assert(len(list1)==len(list2));
		double error = 0;
		for (int i=0 ; i < len(list1) ; i++) {
			error += (list1[i] - list2[i]);
		}
		
		return (error < 0.00001 && error > -0.00001);
	}
	
	@Test
	public void assertChangePositionFallingRaw() {
		double[] pos1fall = {1,2,0}; 
		raw1.changePosition(1);
		Assert.assertTrue(listDoubleEquals(raw1.getPosition,pos1fall));
	}
	
	
	int[][][] terrainTypes = 	{{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,2,1,1},{1,1,1,1,1,1},{1,1,1,1,1,3}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1}},
			{{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,1},{1,1,1,1,1,2}}};
	World world = new World(terrainTypes, null);
	
	@Test
	public void TestGetSetWorld() {
		int[] pos1 = {1,1,1};
		int[] pos2 = {2,2,2};
		int[] pos3 = {10,10,10};
		double[] pos4 = {3.54,4.66,5.999};
		int[] posInt4 = {3,4,5};
		int[] posWorkshop = {0,4,5};
		int[] posBoulder = {3,3,0};
		unit1 = new Unit(Aron, pos1, 10, 10,10,10,false);
		unit2 = new Unit(Steven, pos2, 20,20,20,20,false);
		unit3 = new Unit(Eric, pos3, 30,30,30,30,false);
		unit4 = new Unit(Piet, posInt4, 30,30,30,30,false);
		unit5 = new Unit(Hein, pos5, 20,20,20,20,false);
		TerrainType workshop = workshop;

		TerrainType WORKSHOP = workshop;
		world.cave(pos1);																					//cave
		world.getTerrainType();																				//getTerrainType
		Assert.assertEquals(world.getTerrainType(pos1),TerrainType.AIR);									//
		world.caveAndThrow(pos2);																			//caveAndThrow
		Assert.assertEquals(world.getTerrainType(pos2),TerrainType.AIR);									//
		Assert.assertTrue(world.boulderAvailable(pos2));													//
		world.throwRawMaterial(pos4);																		//throwRawaMaterial
		Assert.assertTrue(world.boulderAvailable(posInt4));													//
		world.setTerrainType(workshop,posInt4);																//setTerrainType
		Assert.assertTrue(getTypeTerrain(pos4[0],pos4[1],pos4[2]) == 3);									//getTerrainType
		int[] pos5 = world.getNextPosition(pos2);															//getNextPosition
		Assert.assertTrue(world.isValidCube(pos5) && areAdjacentCubes(pos5,pos2));							//areAdjacentCubes & isValidCube
		Assert.assertTrue(world.isPassable(pos1));															//isPassable(pos1 is caved in lijn 932)
		Assert.assertFalse(world.isPositionInWorld(pos3));													//isPositionInWorld
		Assert.assertTrue(world.isValidUnit(unit1));														//isValidUnit
		Assert.assertTrue(world.getNbX() == 5 && world.getNbY() == 5 && world.getNbZ() == 6);				//getNbX,Y,Z
		Unit closestUnit = world.getClosestUnit(predicate, unit1);											//getClosestUnit
		Assert.assertEquals(closestUnit,unit2);
		Assert.assertTrue(world.getClosestTerrainType(workshop, unit1) == posWorkshop);						//getClosestTerrainType
		Assert.assertEquals(world.getDistanceBetweenPosAndUnit(unit1, pos2),Math.pow(3,0.5),0.00000001);	//getDistanceBetweenPosAndUnit
		Boulder b1 = new Boulder(world, posBoulder);														//
		Boulder b2 = getBoulder(posBoulder);																//getBoulder
		Assert.assertEquals(b1,b2);																			//
		Assert.assertTrue(world.getNbFaction() ==0);														//getNbFaction
		world.addUnit(unit5);																				//addUnit
		Assert.assertTrue(world.unitsInWorld.contains(unit5));												//containsUnit
		
	}
}
	
