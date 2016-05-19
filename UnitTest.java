 package hillbillies.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import hillbillies.model.Unit;
import ogp.framework.util.Util;

import org.junit.Test;
import hillbillies.model.Vector;

//public class UnitTest {
//
//	@Test
//	public void assertPosition() {
//		int[] pos = {31, 31, 31};
//		double[] sol = {31, 31, 31};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		double[] position = {unit1.getPosition().getX(), unit1.getPosition().getY(), unit1.getPosition().getZ()};
//		Assert.assertArrayEquals(sol , position, Util.DEFAULT_EPSILON);
//
//	}
//	
//	@Test
//	public void assertName() {
//		int[] pos = {31, 31, 31};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		Assert.assertEquals("Unit", unit1.getName());
//
//	}
//	
//	@Test
//	public void assertWeight() {
//		int[] pos = {31, 31, 31};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		Assert.assertEquals(50, unit1.getWeight());
//
//	}
//	@Test
//	public void assertWeight2() {
//		int[] pos = {31, 31, 31};
//		Unit unit1 = new Unit("Unit", pos, 1025, 50, 50, 50, false);
//		Assert.assertEquals(75, unit1.getWeight());
//
//	}
//	@Test
//	public void assertWeight3() {
//		int[] pos = {31, 31, 31};
//		Unit unit1 = new Unit("Unit", pos, -50, 50, 50, 50, false);
//		Assert.assertEquals(50, unit1.getWeight());
//
//	}
//	@Test
//	public void assertWeight4() {
//		int[] pos = {31, 31, 31};
//		Unit unit1 = new Unit("Unit", pos, 0, 50, 50, 50, false);
//		Assert.assertEquals(50, unit1.getWeight());
//
//	}
//	@Test
//	public void assertStamina() {
//		int[] pos = {31, 31, 31};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		Assert.assertEquals(50, unit1.getWeight());
//
//	}
//	
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIllegalPosition() {
//		int[] pos = {59, 59, 59};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.getPosition();
//	}
//	
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIllegalPosition2() {
//		int[] pos = {-1, 34, 34};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.getPosition();
//	}
//	
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIllegalPosition3() {
//		int[] pos = {51, 0, 0};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.getPosition();
//	}
//	
//	@Test 
//	public void assertSetPosition() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setPosition(34, 35, 36);
//		Vector POS = new Vector(34,35,36);
//		Assert.assertTrue(unit1.getPosition().equals(POS));
//	}
//	
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIllegalSetPosition() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setPosition(-34, 35, 36);
//		List<Double> POS = new ArrayList<>();
//		POS.add((double) 34); 
//		POS.add((double) 35); 
//		POS.add((double) 36);
//		Assert.assertEquals(POS, unit1.getPosition());
//	}
//
//	@Test 
//	public void assertSetToughness() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setToughness(51);
//		Assert.assertEquals(51, unit1.getToughness());
//	}
//	
//	@Test 
//	public void assertSetToughness2() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setToughness(105);
//		Assert.assertEquals(30, unit1.getToughness());
//	}
//	
//	@Test 
//	public void assertSetWeight() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setWeight(51);
//		Assert.assertEquals(51, unit1.getWeight());
//	}
//	
//	@Test 
//	public void assertSetWeight2() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setWeight(105);
//		Assert.assertEquals(55, unit1.getWeight());
//	}
//	
//	@Test 
//	public void assertSetStamina() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setStamina(51);
//		Assert.assertEquals(51, unit1.getStamina());
//	}
//	
//	@Test (expected = AssertionError.class)
//	public void assertSetIllegalStamina() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setStamina(-5);
//		Assert.assertEquals(50, unit1.getStamina());
//	}
//
//	
//	@Test 
//	public void assertSetHitpoints() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setHitpoints(51);
//		Assert.assertEquals(51, unit1.getHitpoints());
//	}
//	
//	@Test (expected = AssertionError.class)
//	public void assertSetIllegalHitpoints() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setHitpoints(-5);
//		Assert.assertEquals(50, unit1.getHitpoints());
//	}
//
//
//	@Test 
//	public void assertSetOrientation() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setOrientation((double) Math.PI / 4);
//		Assert.assertEquals((double) (Math.PI / 4), unit1.getOrientation(), Util.DEFAULT_EPSILON);
//	}
//	
//	@Test 
//	public void assertSetOrientation2() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setOrientation((double) 7 * Math.PI);
//		Assert.assertEquals((double) (Math.PI), unit1.getOrientation(), Util.DEFAULT_EPSILON);
//	}
//	
//	@Test 
//	public void assertSetSpeed() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setOrientation((double) Math.PI / 4);
//		Assert.assertEquals((double) (Math.PI / 4), unit1.getOrientation(), Util.DEFAULT_EPSILON);
//	}
//	
//	@Test 
//	public void assertSetSpeed2() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setOrientation((double) 7 * Math.PI);
//		Assert.assertEquals((double) (Math.PI), unit1.getOrientation(), Util.DEFAULT_EPSILON);
//	}
//	
//	@Test 
//	public void assertIsStopped() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setAttacked(true);
//		Assert.assertTrue(unit1.isStopped());
//	}
//	
//	@Test 
//	public void assertDetermineSpeed() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setTarget(5, 4, 4);
//		double velocity = 0.5 * 1.5 * (unit1.getStrength() + unit1.getAgility()) / (2 * unit1.getWeight());
//		Assert.assertEquals(velocity, unit1.determineSpeed(), Util.DEFAULT_EPSILON);
//		
//	}
//	
//	@Test
//	public void assertDetermineSpeedSprinting() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setTarget(5, 4, 2);
//		unit1.startSprinting();
//		double velocity = 2 * 1.2 * 1.5 * (unit1.getStrength() + unit1.getAgility()) / (2 * unit1.getWeight());
//		Assert.assertEquals(velocity, unit1.determineSpeed(), Util.DEFAULT_EPSILON);
//		
//	}
//	
//	@Test
//	public void assertDetermineSpeed2() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setTarget(5, 4, 2);
//		double velocity = 1.2 * 1.5 * (unit1.getStrength() + unit1.getAgility()) / (2 * unit1.getWeight());
//		Assert.assertEquals(velocity, unit1.determineSpeed(), Util.DEFAULT_EPSILON);
//		
//	}
//	
//	@Test
//	public void assertSpeedVector() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setTarget(5, 4, 4);
//		unit1.setSpeed(unit1.determineSpeed());
//		Vector speedVector = new Vector(0.0, 0.0, 0.75);
//		Assert.assertTrue(unit1.getSpeedVector().equals(speedVector));
//		
//	}
//	
//	@Test
//	public void assertMoveToRandomAdjacentPosition() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.moveToRandomAdjacentPosition();
//		Assert.assertTrue(unit1.isValidPosition(unit1.getPosition().getX(), 
//				unit1.getPosition().getY(), unit1.getPosition().getZ()));
//		
//	}
//	
//	@Test
//	public void assertCheckResting() {
//		int[] pos = {5, 4, 3};
//		Unit unit1 = new Unit("Unit", pos, 50, 50, 50, 50, false);
//		unit1.setRestingTime(181);
//		unit1.checkResting();
//		Assert.assertTrue(unit1.isResting());
//	}
//	
//	
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIllegalNameCapital() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("aron", pos, 50, 50, 50, 50, false);
//	}
//
//
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIllegalNameNumber() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron69", pos, 50, 50, 50, 50, false);
//	}
//
//
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIllegalNameNotEnough() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("A", pos, 50, 50, 50, 50, false);
//
//	}
//
//
//	@Test
//	public void assertGetCube() {
//		
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.setPosition(1, 2, 3);
//		Vector cube = new Vector(1, 2, 3);
//		Assert.assertTrue(unit.getCube().equals(cube));
//		}
//
//
//	@Test(expected = IllegalArgumentException.class)
//	public void assertSetIllegalTarget() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.setTarget(40, 43, 51);
//
//	}
//
//
//	@Test
//	public void assertGetMaxHitpoints() {
//
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		int maxHitpoints = (int) Math.ceil(2 * 50 * 50 / 100.0);
//		Assert.assertEquals(maxHitpoints,unit.getMaxHitpoints());
//	}
//
//
//	@Test
//	public void assertUpdateOrientation() {
//		
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.setSpeed(10);
//		unit.setTarget(4,5,6);
//		unit.updateOrientation();
//		double distance = Math.sqrt(9+9+9);
//		double orientation = Math.atan2(10*3/distance,10*3/distance);
//		Assert.assertEquals((double) unit.getOrientation(), orientation, Util.DEFAULT_EPSILON);
//
//	}
//
//
//	@Test
//	public void assertIsValidAttack() {
//		
//		int[] pos1 = {1,2,3};
//		Unit unit1 = new Unit("Aron", pos1, 50, 50, 50, 50, false);
//		int[] pos2 = {2,2,3};
//		Unit unit2 = new Unit("Aron", pos2, 50, 50, 50, 50, false);
//		int[] pos3 = {3,2,3};
//		Unit unit3 = new Unit("Aron", pos3, 50, 50, 50, 50, false);
//		Assert.assertEquals(true, unit1.isValidAttack(unit2));
//		Assert.assertEquals(true, unit3.isValidAttack(unit2));
//		Assert.assertEquals(false, unit1.isValidAttack(unit3));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.setTarget(0, 0, 0);
//		Vector TARGET = new Vector(0,0,0);
//		Assert.assertTrue(unit.getTarget().equals(TARGET));
//
//	}
//	
//	@Test
//	public void assertIsValidPositionInt() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.setTarget(0, 0, 0);
//		Assert.assertTrue(unit.isValidPosition(0, 0, 0));;;
//
//	}
//	
//	@Test 
//	public void assertVectorClassEquals() {
//		Vector vector1 = new Vector(1,1,1);
//		Vector vector2 = new Vector(1,1,1);
//		Assert.assertEquals(true, vector1.equals(vector2));
//	
//	}
//	
//	@Test
//	public void assertIsValidPosition1() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition2() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition3() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition4() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition5() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition6() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition7() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition8() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition9() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition10() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition11() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition12() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition13() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition14() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test
//	public void assertIsValidPosition15() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		Assert.assertTrue(unit.isValidPosition(Math.random()*50, Math.random()*50, Math.random()*50));
//
//	}
//	
//	@Test (expected = IllegalArgumentException.class)
//	public void assertIsValidPosition16() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.setPosition(-1, 50, 56);
//
//	}
//	
//	@Test 
//	public void assertIsValidPosition17() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		int X = 1;
//		int Y = 49;
//		int Z = 49;
//		Assert.assertTrue(unit.isValidPosition(X, Y, Z));
//
//	}
//	
//	@Test 
//	public void assertIsValidPosition18() {
//		int[] pos = {1,2,3};
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		float X = 1;
//		float Y = 49;
//		float Z = 49;
//		Assert.assertTrue(unit.isValidPosition(X, Y, Z));
//
//	}
//	
//	@Test
//	public void assertMoveToAdjacent1() {
//		int[] pos = {1,2,3};
//		int x = 1;
//		int y = 0;
//		int z = 1;
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.moveToAdjacent(x, y, z);
//		Vector test = new Vector(2, 2, 4);
//		Assert.assertTrue(test.equals(unit.getTarget()));
//
//	}
//	
//	@Test
//	public void assertMoveToAdjacent2() {
//		int[] pos = {1,2,3};
//		int x = -1;
//		int y = 1;
//		int z = 1;
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.moveToAdjacent(x, y, z);
//		Vector test = new Vector(0, 3, 4);
//		Assert.assertTrue(test.equals(unit.getTarget()));
//
//	}
//	@Test
//	public void assertMoveToAdjacent3() {
//		int[] pos = {1,2,3};
//		int x = 0;
//		int y = 1;
//		int z = -1;
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.moveToAdjacent(x, y, z);
//		Vector test = new Vector(1, 3, 2);
//		Assert.assertTrue(test.equals(unit.getTarget()));
//
//	}
//	
//	@Test
//	public void assertMoveTo() {
//		int[] pos = {1,2,3};
//		int x = 0;
//		int y = 0;
//		int z = 0;
//		Unit unit = new Unit("Aron", pos, 50, 50, 50, 50, false);
//		unit.moveTo(x, y, z);
//		Vector test = new Vector(0,0,0);
//		while (!unit.getPosition().equals(unit.getTarget()))
//			unit.advanceTime(0.2);
//		Assert.assertTrue(test.equals(unit.getTarget()));
//
//	}
//
//	
//	
//
//
//
//
//	
//}
