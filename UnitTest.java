 package hillbillies.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
		int[] pos = {0, 0, 0};
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
		int[] pos = {0, 0, 0};
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
		int[] pos = {0, 0, 0};
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
		int[] pos = {0, 0, 0};
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
		int[] pos = {0, 0, 0};
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
					if (w.isPassable(position) && !passed.contains(position)) {
						passed.add(position);
						return position;
					}
				}
			}
		return null;
	}
	
	private List<int[]> passed = new ArrayList<>();
	
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
	public void assertAddFactionToUnit() {
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
		unit.advanceTime(0.001);
		Assert.assertTrue(unit.hasTask());
	}
	
	@Test
	public void assertAssignTaskToUnit3() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		Faction f = new Faction(w, s);
		int[] pos = getPassableCube(w);
		T t = createRandomTask();
		s.addTask(t);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, true);
		f.addUnit(unit);
		unit.advanceTime(0.001);
		Assert.assertEquals(t, unit.getTask());
	}
	
	@Test
	public void assertAssignTaskToUnit4() {
		Scheduler s = new Scheduler();
		World w = createRandomWorld();
		Faction f = new Faction(w, s);
		int[] pos = getPassableCube(w);
		T t = createRandomTask();
		s.addTask(t);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, true);
		f.addUnit(unit);
		unit.advanceTime(0.001);
		unit.removeTask();
		Assert.assertFalse(unit.hasTask());
	}
	
	@Test
	public void assertDefaultBehavior() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		int[] pos = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		f.addUnit(unit);
		unit.setDefaultBehavior(true);
		Assert.assertTrue(unit.getDefaultBehavior());
	}
	
	@Test
	public void assertGetWorld() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		int[] pos = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		f.addUnit(unit);
		Assert.assertEquals(w, unit.getWorld());
	}
	
	@Test
	public void assertGetWorld2() {
		int[] pos = {5, 6, 0};
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		Assert.assertTrue(unit.getWorld() == null);
	}
	
	@Test
	public void assertDropBoulder() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		int[] pos = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		f.addUnit(unit);
		Boulder b = new Boulder(w, pos);
		unit.carryBoulder(b);
		Assert.assertTrue(unit.isCarryingBoulder());
	}
	
	@Test
	public void assertDropBoulder2() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		int[] pos = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		f.addUnit(unit);
		Boulder b = new Boulder(w, pos);
		unit.carryBoulder(b);
		unit.dropBoulder();
		Assert.assertTrue(!unit.isCarryingBoulder());
	}
	
	@Test
	public void assertDropLog() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		int[] pos = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		f.addUnit(unit);
		Log b = new Log(w, pos);
		unit.carryLog(b);
		unit.dropLog();
		Assert.assertTrue(!unit.isCarryingLog());
	}
	
	@Test
	public void assertIsPassable() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		int[] pos = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		f.addUnit(unit);
		Assert.assertTrue(unit.isPassable(unit.getCubeInt()));
	}
	
	@Test
	public void assertFollow() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		Faction g = new Faction(w);
		int[] pos = getPassableCube(w);
		int[] pos2 = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		Unit follow = new Unit("Namee", pos2, 50, 50, 50, 50, false);
		f.addUnit(unit);
		g.addUnit(follow);
		unit.follow(follow);
		Assert.assertEquals(follow, unit.getUnitToFollow());
	}
	
	@Test
	public void assertFollow2() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		Faction g = new Faction(w);
		int[] pos = getPassableCube(w);
		int[] pos2 = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		Unit follow = new Unit("Namee", pos2, 50, 50, 50, 50, false);
		f.addUnit(unit);
		g.addUnit(follow);
		unit.follow(follow);
		Assert.assertTrue(unit.isFollowing());
	}
	
	@Test
	public void assertAttacking() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		Faction g = new Faction(w);
		int[] pos = getPassableCube(w);
		int[] pos2 = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		Unit follow = new Unit("Namee", pos2, 50, 50, 50, 50, false);
		f.addUnit(unit);
		g.addUnit(follow);
		unit.fight(unit, follow);
		Assert.assertTrue(unit.isAttacking());
	}
	
	@Test
	public void assertAttacked() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		Faction g = new Faction(w);
		int[] pos = getPassableCube(w);
		int[] pos2 = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		Unit follow = new Unit("Namee", pos2, 50, 50, 50, 50, false);
		f.addUnit(unit);
		g.addUnit(follow);
		unit.fight(unit, follow);
		Assert.assertTrue(follow.isAttacked());
	}
	
	@Test
	public void assertWorkAt() {
		World w = createRandomWorld();
		Faction f = new Faction(w);
		Faction g = new Faction(w);
		int[] pos = getPassableCube(w);
		int[] pos2 = getPassableCube(w);
		Unit unit = new Unit("Name", pos, 50, 50, 50, 50, false);
		f.addUnit(unit);
		unit.workAt(pos2);
		Vector v = new Vector(pos2[0], pos2[1], pos2[2]);
		Assert.assertTrue(unit.getFinalTarget().equals(v));
	}
	
	@Test
	public void assertRemoveTask() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		T t = new T("Name", 50, createRandomStatement(), pos);
		s.addTask(t);
		s.removeTask(t);
		Assert.assertEquals(0, s.getTasks().size());
	}
	
	@Test
	public void assertRemoveTask2() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		T t = new T("Name", 50, createRandomStatement(), pos);
		s.removeTask(t);
		Assert.assertEquals(0, s.getTasks().size());
	}
	
	
	@Test
	public void assertReplaceTask() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		T t = new T("Name", 50, createRandomStatement(), pos);
		T m = new T("Mame", 60, createRandomStatement(), pos);
		s.addTask(t);
		s.replaceTask(t, m);
		Assert.assertEquals(1, s.getTasks().size());
	}
	
	@Test
	public void assertReplaceTask2() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		T t = new T("Name", 50, createRandomStatement(), pos);
		T m = new T("Mame", 60, createRandomStatement(), pos);
		s.addTask(t);
		s.replaceTask(m, t);
		List<T> ttt = new ArrayList<>();
		ttt.add(m);
		Assert.assertTrue(s.areAllPartOfScheduler(ttt));
	}
	
	@Test
	public void assertHighestPriorityTask() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		T t = new T("Name", 50, createRandomStatement(), pos);
		T m = new T("Mame", 60, createRandomStatement(), pos);
		s.addTask(t);
		s.addTask(m);
		Assert.assertTrue(s.getHighestPriorityTaskNotYetAssigned() == m);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void assertHighestPriorityTask2() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		T t = new T("Name", 50, createRandomStatement(), pos);
		T m = new T("Mame", 60, createRandomStatement(), pos);
		s.getHighestPriorityTaskNotYetAssigned();
	}
	
	@Test
	public void assertValidPosScheduler() {
		World w = createRandomWorld();
		Scheduler s = new Scheduler();
		Faction f = new Faction(w, s);
		int[] pos = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		Assert.assertTrue(!s.isValidPosition(pos));
	}
	
	@Test
	public void assertValidPosScheduler2() {
		Scheduler s = new Scheduler();
		int[] pos = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		Assert.assertTrue(s.isValidPosition(pos));
	}
	
	@Test
	public void assertIteratorScheduler() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		for (int i = 0; i < 4; i++) {
			s.addTask(new T("Name", (int) Math.round(Math.random() * 50 - 100), 
					createRandomStatement(), pos));
		}
		Iterator<T> iter = s.getAllTasksIterator();
		int k = 0;
		while (iter.hasNext()) {
			iter.next();
			k += 1;
		}
		Assert.assertEquals(4, k);
	}
	
	@Test
	public void assertIteratorScheduler2() {
		Scheduler s = new Scheduler();
		int[] pos = {0,0,0};
		for (int i = 0; i < 8; i++) {
			s.addTask(new T("Name", (int) Math.round(Math.random() * 50 - 100), 
					createRandomStatement(), pos));
		}
		Iterator<T> iter = s.getAllTasksIterator();
		T t = iter.next();
		T d = null;
		while (iter.hasNext()) {
			d = iter.next();
			Assert.assertTrue(t != d);
			Assert.assertTrue(t.getPriority() >= d.getPriority());
			t = d;
			
		}
	}
	
	
	@Test
	public void TestGetSetRaw() {
		World world1 = createRandomWorld();
		int[] pos1 = getPassableCube(world1);
		int[] pos2 = getPassableCube(world1);
		int[] pos3 = getPassableCube(world1);
		int[] pos4 = getPassableCube(world1);
		
		Unit unit1 = new Unit("Aron", pos1, 10, 10,10,10,false);
		Unit unit4 = new Unit("Piet", pos3, 30,30,30,30,false);
		
		Raw raw11 = new Boulder(world1,pos1);
		Raw raw21 = new Boulder(world1,pos2);
		Raw raw4 = new Boulder(world1,pos4);

		unit4.terminate();
		Assert.assertEquals(raw11.getWorld(),world1); 			
		Assert.assertTrue(raw11.isValidPosition(pos1));			
		Assert.assertTrue(raw21.isValidPosition(pos2));			
		Assert.assertTrue(raw11.isValidWorld(world1));			
		Assert.assertFalse(raw11.isValidWorld(null));			
		Assert.assertTrue(world1.isPassable(pos1));				
		Assert.assertTrue(world1.isPassable(pos2));			
		Assert.assertTrue(world1.isPassable(raw11.getCubeInt()));		
		raw11.terminate();
		Assert.assertFalse(raw11.isActive());				
		Assert.assertFalse(!(!unit1.isCarryingBoulder()) && !unit1.isCarryingLog());							//removeOwnerIsCarryingRaw
		raw21.terminate();																				//terminate
		Assert.assertTrue(raw21.isTerminated() && raw21.getWorld() == null && raw21.getOwner() == null);	//
		
}
	
	/**
	 * Simple method to check if two lists of doubles are almost equal, not 100% accurate for very long lists
	 */
	boolean listDoubleEquals(double[] list1, double[] list2) {
		assert(list1.length == list2.length);
		double error = 0;
		for (int i=0 ; i < list1.length ; i++) {
			error += (list1[i] - list2[i]);
		}
		
		return (error < 0.00001 && error > -0.00001);
	}
	
	
	
	@Test
	public void TestGetSetWorld() {
		World world1 = createRandomWorld();

		int[] pos1 = getPassableCube(world1);
		int[] pos2 = getPassableCube(world1);
		int[] pos3 = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		int[] posInt5 = getPassableCube(world1);
		
		Unit unit1 = new Unit("Aron", pos1, 10, 10,10,10,false);
		

		Unit unit5 = new Unit("Hein", posInt5, 20,20,20,20,false);
		Assert.assertTrue(world1.isPassable(pos1));															
		Assert.assertFalse(world1.isPositionInWorld(pos3));													
		Assert.assertTrue(world1.isValidUnit(unit1));														
		Assert.assertTrue(world1.getNbX() == 5 && world1.getNbY() == 5 && world1.getNbZ() == 5);				
		Assert.assertTrue(world1.getNbFaction() ==0);														
		world1.addUnit(unit5);
		Assert.assertTrue(world1.getNbUnits() == 1);
		
	}
	

	
}
