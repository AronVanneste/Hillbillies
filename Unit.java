package hillbillies.model;


import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


import hillbillies.model.Vector;

/**
 * 
 * @author Steven, Aron
 *
 */

public class Unit {
	
	/**
	 * 
	 * @param initialPosition
	 * 		A list containing the (x, y, z) coordinates of the initial position of the unit
	 * @param name
	 * 		The name of the unit
	 * @param strength
	 * 		The strength of the unit
	 * @param agility
	 * 		The agility of the unit
	 * @param toughness
	 * 		The toughness of the unit
	 * @param weight
	 * 		The weight of the unit
	 * @param enableDefaultBehavior
	 * 		Indicates whether or not the unit should start defaultBehavior
 	 * @post The name of the unit equals the given name
	 * 		| new.getName() == name
	 * @post The values in the array list correspond with the values in the given list initialPosition. 
	 * 		 This array list is the unit's starting position 
	 * 		| new.getPosition().get(0) == initialPosition[0]
	 * 		| new.getPosition().get(1) == initialPosition[1]
	 * 		| new.getPosition().get(2) == initialPosition[2]
	 * @post The toughness of the unit equals the absolute value of the given toughness minus 25 modulo 75 plus 25
	 * 		| new.getToughness() == (Math.abs(toughness) - 25) % 75 + 25
	 * @post The agility of the unit equals the absolute value of the given agility minus 25 modulo 75 plus 25
	 * 		| new.getAgility() == (Math.abs(agility) - 25) % 75 + 25
	 * @post The strength of the unit equals the absolute value of the given strength minus 25 modulo 75 plus 25
	 * 		| new.getStrenght() == (Math.abs(strength) - 25) % 75 + 25
	 * @post The weight of the unit equals the absolute value of the given weight minus MIN modulo 75 plus MIN
	 * 		 in which MIN equals the strength of the unit plus the agility of the unit divided by two.
	 * 		| new.getWeight() == (Math.abs(weight) - (this.getStrength() + this.getAgility()/2) % 75 
	 * 			+ (this.getStrength() + this.getAgility())/2
	 * @post The stamina of the unit equals the weight of the unit multiplied by the toughness of the unit
	 * 		 divided by 50 and rounded up to the next integer
	 * 		| new.getStamina() == Math.ceil(2 * this.weight * this.toughness / 100.0);
	 * @post The hitpoints of the unit equal the weight of the unit multiplied by the toughness of the unit
	 * 		 divided by 50 and rounded up to the next integer
	 * 		| new.getHitpoints() == Math.ceil(2 * this.weight * this.toughness / 100.0);
	 * @post The unit's speed equals zero
	 * 		| new.getSpeed() = 0
	 * @post The defaultBehavior status of the unit equals the given boolean enableDefaultBehavior
	 * 		| new.getDefaultBehavior() = enableDefaultBehavior
	 * @post The faction of the unit is null
	 * 		|new.getFaction = null
	 * @post The target of the unit is it's initial position
	 * 		|new.getTarget = position
	 * @post The finaltarget of the unit is it's initial position
	 * 		|new.getFinalTarget = position
	 * @post The experiencepoints of the unit will be zero
	 * 		|new.getExperiencepoints = 0
	 * @throws IllegalNameException
	 * 		If the name does not start with an upper case letter, doesn't have a length of at least two characters
	 * 		or has a character other than letters or quotes
	 * 		| ((!Character.isUpperCase(name.charAt(0))) | (name.length() < 2) | (!name.matches("[a-zA-Z\\s\'\"]+")))
	 * @throws IllegalPositionException
	 * 		 If one of given X, Y or Z is negative or larger than 50.
	 * 		| !isValidPosition(X, Y, Z)
	 */

	public Unit(String name, int[] initialPosition, int weight,
			int agility,int strength,  int toughness, boolean enableDefaultBehaviour) 
					throws IllegalNameException, IllegalPositionException {
		
		int X = initialPosition[0];
		int Y = initialPosition[1];
		int Z = initialPosition[2];

		try {
			this.setPosition(X, Y, Z);
		} catch (IllegalPositionException invalidPosition) {
			throw invalidPosition;
		}
		
		try {
			this.setName(name);
		} catch (IllegalNameException invalidName) {
			throw invalidName;
		}
		
		this.setFaction(null);
		
		this.setStrength((Math.abs(strength) - 25) % 75 + 25);
		this.setAgility((Math.abs(agility) - 25) % 75 + 25);
		this.setToughness((Math.abs(toughness) - 25) % 75 + 25);
		
		int MIN = (this.getStrength() + this.getAgility()) / 2;
		this.setWeight((Math.abs(weight) - MIN) % (100 - MIN) + MIN);
		
		this.setStamina((int) Math.ceil(2 * this.getWeight() * this.getToughness() / 100.0));
		this.setHitpoints((int) Math.ceil(2 * this.getWeight() * this.getToughness() / 100.0));
		this.setOrientation((float) Math.PI/2);
				
		this.setSpeed(0);
		this.setDefaultBehavior(enableDefaultBehaviour);
		this.setTarget(this.getPosition().getX(), 
				this.getPosition().getY(), this.getPosition().getZ());
		this.setFinalTarget(this.getPosition().getX(), 
				this.getPosition().getY(), this.getPosition().getZ());
		this.setExperiencePoints(0);

		
	}
	
	
	
	
	
	/**
	 * #####################################################################################
	 * ##### SETTERS, GETTERS AND OTHER FUNCTIONS TO CHANGE THE UNIT'S CHARACTERISTICS #####
	 * #####################################################################################
	 */
	
	

	/**
	 * 
	 * @return Returns the world the unit is in
	 */
	public World getWorld() {
		try {
			return this.getFaction().getWorld();
		} catch (NullPointerException e) {
			return null;
		}
	}

	
	/**
	 * 
	 * @param faction
	 * 		The faction of the unit
	 * @post The faction of the unit is the given faction
	 * 		|new.getFaction = faction
	 */
	protected void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	/**
	 * 
	 * @return Returns the faction of the unit
	 */
	public Faction getFaction() {
		return this.faction;
	}

	
	/**
	 * 
	 * @param name
	 * 			The name of the unit
	 * @post The name of the unit equals the given name
	 * 		| new.getName() == name
	 * @throws IllegalNameException
	 * 		If the name does not start with an upper case letter, doesn't have a length of at least two characters
	 * 		or has a character other than letters or quotes
	 * 		| ((!Character.isUpperCase(name.charAt(0))) | (name.length() < 2) | (!name.matches("[a-zA-Z\\s\'\"]+")))
	 */
	public void setName(String name) throws IllegalNameException {
		
		if (!this.isValidName(name))
			throw new IllegalNameException();
		else
			this.name = name;
	}
	
	/**
	 * 
	 * @return Returns the name of the unit
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @param X
	 * 		The x-axis position of the center of the unit
	 * @param Y
	 * 		The y-axis position of the center of the unit
	 * @param Z
	 * 		The z-axis position of the center of the unit
	 * @post The double at index 0 of the list POS equals the given X and is the x-axis position 
	 * 		 of the center of the unit
	 * 		 The double at index 1 of the list POS equals the given Y and is the y-axis position 
	 * 		 of the center of the unit
	 * 		 The double at index 2 of the list POS equals the given Z and is the z-axis position
	 * 		 of the center of the unit
	 * 		| new.getPosition().getX == X
	 * 		| new.getPosition().getY == Y
	 * 		| new.getPosition().getZ == Z 
	 * @throws IllegalPositionException
	 * 		 If one of given X, Y or Z is negative or larger than 50.
	 * 		| (!isValidPosition(X,Y,Z))
	 * 
	 */
	protected void setPosition(double X, double Y, double Z) throws IllegalPositionException {
		
		if (!isValidPosition(X,Y,Z))
			throw new IllegalPositionException();
		else {
			this.POS.setX(X);
			this.POS.setY(Y);
			this.POS.setZ(Z);
		}	

	}
	
	
	/**
	 * 
	 * @return Returns a list containing the x-axis, y-axis and z-axis position of the center of the unit
	 */
	public Vector getPosition() {
		return this.POS;
	}
	
	/**
	 * @return Returns a list of doubles containing the x-axis, y-axis and z-axis position 
	 * of the center of the unit
	 */
	public double[] getPositionList() {
		double X = this.getPosition().getX();
		double Y = this.getPosition().getY();
		double Z = this.getPosition().getZ();
		double[] pos = {X, Y, Z};
		return pos;

	}
	
	/**
	 * 
	 * @return Returns a Vector containing the x-axis, y-axis and z-axis position of the cube in which the unit is located. 
	 * 		   These are the coordinates of the top left corner of this cube.
	 */
	public Vector getCube() {
		return this.getCube(this.getPosition());
	}
	
	/**
	 * 
	 * @param position
	 * 		The position of which the cube coordinates are returned
	 *  @return Returns a Vector containing the x-axis, y-axis and z-axis position of the cube of 
	 *  		the given position
	 */
	private Vector getCube(Vector position) {
		
		double XCube = Math.floor(position.getX());
		double YCube = Math.floor(position.getY());
		double ZCube = Math.floor(position.getZ());
		Vector CUBE = new Vector(XCube, YCube, ZCube);

		return CUBE;

	}
	
	/**
	 * 
	 * @return Returns a list of ints containing the x-axis, y-axis and z-axis position of the cube in 
	 * 		   which the unit is located. 
	 * 		   These are the coordinates of the top left corner of this cube.
	 */
	public int[] getCubeInt() {
		int XCube = (int) Math.floor(this.getPosition().getX());
		int YCube = (int) Math.floor(this.getPosition().getY());
		int ZCube = (int) Math.floor(this.getPosition().getZ());
		int[] cube = {XCube, YCube, ZCube};
		return cube;
	}
	
	
	/**
	 * 
	 * @param toughness
	 * 			The toughness of the unit
	 * @post The toughness of the unit equals the absolute value of the given toughness minus 25 modulo 75 plus 25
	 * 		| new.getToughness() == (Math.abs(toughness) - 25) % 75 + 25
	 */
	public void setToughness(int toughness) {
		this.toughness = (Math.abs(toughness) - 25) % 75 + 25;
	}
	
	
	/**
	 * 
	 * @return Return the toughness of the unit
	 */
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * 
	 * @param agility
	 * 			The agility of the unit
	 * @post The agility of the unit equals the absolute value of the given agility minus 25 modulo 75 plus 25
	 * 		| new.getAgility() == (Math.abs(agility) - 25) % 75 + 25
	 */
	public void setAgility(int agility) {
		this.agility = (Math.abs(agility) - 25) % 75 + 25;
		this.setWeight(this.getWeight());
	}
	
	
	/**
	 * 
	 * @return Return the agility of the unit
	 */
	public int getAgility() {
		return this.agility;
	}
	
	
	/**
	 * 
	 * @param strength
	 * 			The strength of the unit
	 * @post The strength of the unit equals the absolute value of the given strength minus 25 modulo 75 plus 25
	 * 		| new.getStrenght() == (Math.abs(strength) - 25) % 75 + 25
	 */
	public void setStrength(int strength) {
		this.strength = (Math.abs(strength) - 25) % 75 + 25;
		this.setWeight(this.getWeight());
	}
	
	
	/**
	 * 
	 * @return Return the strength of the unit
	 */
	public int getStrength() {
		return this.strength;
	}
	
	
	/**
	 * 
	 * @param weight
	 * 			The weight of the unit
	 * @post The weight of the unit equals the absolute value of the given weight minus the weight of a carried bolder or log 
	 * 		 minus MIN modulo 75 plus MIN plus the weight of the boulder or log
	 * 		 in which MIN equals the strength of the unit plus the agility of the unit divided by two.
	 * 		| new.getWeight() == (Math.abs(weight) - (this.getStrength() + this.getAgility()/2) % 75 
	 * 			+ (this.getStrength() + this.getAgility())/2
	 */
	public void setWeight(int weight) {
		
		int unitWeight = weight;
		if (this.isCarryingBoulder())
			unitWeight -= this.getCarryingBoulder().getWeight();
		else if (this.isCarryingLog())
			unitWeight -= this.getCarryingLog().getWeight();
		int restWeight = weight - unitWeight;
		
		int MIN = (this.getStrength() + this.getAgility()) / 2;
		unitWeight = (Math.abs(unitWeight) - MIN) % (100 - MIN) + MIN;
		this.weight = unitWeight + restWeight;
	}
	
	
	/**
	 * 
	 * @return Return the weight of the unit
	 */
	public int getWeight() {
		return this.weight;
	}
	
	
	/**
	 * 
	 * @param stamina
	 * 			The stamina of the unit
	 * @pre The stamina of the unit is not negative and is smaller than the weight of the unit multiplied by the toughness of the 
	 * 		unit divided by 50 rounded up to an integer
	 * 		| (isValidStamina(stamina) 
	 * @post The stamina of the unit equals the given stamina
	 * 		| new.getStamina() = stamina
	 */
	public void setStamina(int stamina) {
		assert (this.isValidStamina(stamina));
		this.stamina = stamina;

	}
	
	
	/**
	 * 
	 * @return Return the stamina of the unit
	 */
	public int getStamina() {
		return this.stamina;
	}
	
	/**
	 * 
	 * @return Returns the maximal stamina points the unit can have
	 */
	public int getMaxStamina() {
		return (int) Math.ceil(2 * this.getWeight() * this.getToughness() / 100.0);
	}
	
	
	/**
	 * 
	 * @param hitpoints
	 * 			The hitpoints of the unit
	 * @pre The hitpoints of the unit is not negative and is smaller than the weight of the unit multiplied by the toughness of the 
	 * 		unit divided by 50 rounded up to an integer
	 * 		| (isValidHitpoints(hitpoints) 
	 * @post The hitpoints of the unit equals the given hitpoints
	 * 		| new.getHitpoints() = hitpoints
	 */
	public void setHitpoints(int hitpoints) {
		assert (isValidHitpoints(hitpoints));
		this.hitpoints = Math.max(0, hitpoints);
		this.checkTerminate();

	}
	
	/**
	 * 
	 * @return Return the hitpoints of the unit
	 */
	public int getHitpoints() {
		return this.hitpoints;
	}
	
	/**
	 * 
	 * @return Returns the maximal hitpoints the unit can have
	 */
	public int getMaxHitpoints() {
		return (int) Math.ceil(2 * this.getWeight() * this.getToughness() / 100.0);
	}
	

	/**
	 * 
	 * @param points
	 * 		The experiencepoints of the unit
	 * @post
	 * 		The experiencepoints equal the maximum of the given experiencepoints an zero
	 * 		| new.getExperiencePoints = Math.max(experiencepoints, 0)
	 */
	private void setExperiencePoints(int points) {
		this.experiencePoints = Math.max(points, 0);
		if (this.getExperiencePoints() % 10 >= 0 && this.getExperiencePoints() != 0)
			this.updateExperiencePoints();
	}
	
	/**
	 * 
	 * @return Returns the experiencepoints of the unit
	 */
	public int getExperiencePoints() {
		return this.experiencePoints;
	}
	
	/**
	 *@post The strength, agility or toughness will increase by one points if the unit has more than 
	 *		10 experience points and its experience points will decrease with 10. If strength, agility
	 *		and toughness all are at their maximum, then the unit's experience points will remain unchanged.
	 *		|if (new.getExperiencePoints() % 10 >= 0
	 *		|	new.getExperiencePoints == this.getExperiencePoints() - 10
	 * 		|	if (this.getStrength() < 100)
	 *		|		then new.getStrength == this.getStrength() + 1
	 *		|	else if (this.getAgility() < 100)
	 *		|		then new.getAgility == this.getAgility() + 1
	 *		|	else if (this.getToughness() < 100)
	 *		|		then new.getToughness == this.getToughness() + 1
	 *		|	else
	 *		|		then new.getExperiencePoints() == this.getExperiencePoints() + 10
	 * 
	 */
	public void updateExperiencePoints() {
		
		if (this.getExperiencePoints() % 10 >= 0) {  
			this.setExperiencePoints(this.getExperiencePoints() - 10);
			if (this.getStrength() < 100)
				this.setStrength(this.getStrength() + 1);
			else if (this.getAgility() < 100)
				this.setAgility(this.getAgility() + 1);
			else if (this.getToughness() < 100)
				this.setToughness(this.getToughness() + 1);
			else
				this.setExperiencePoints(this.getExperiencePoints() + 10);
		}
		
	}

	
	
	/**
	 * @post The orientation of the unit equals the absolute value of the given orientation modulo two 
	 * 		 multiplied by Math.PI if the orientation is a non-negative value
	 * 		| if (orientation >= 0) 
	 * 		| 	then new.getOrientation() = Math.abs(orientation) % (2 * Math.PI)
	 * @post The orientation of the unit equals Math.PI multiplied by two minus the absolute value 
	 * 		 of the given orientation modulo two multiplied by Math.PI if the orientation is a negative value
	 * 		| if (orientation < 0)
	 * 		|	then new.getOrientation() = 2 * Math.PI - Math.abs(orientation) % (2 * Math.PI)
	 */
	private void setOrientation(double orientation) {
		
		if (orientation >= 0) 
			this.orientation = Math.abs(orientation) % (double) (2 * Math.PI);
		else
			this.orientation = (double)(2 * Math.PI) - Math.abs(orientation) % (double)(2 * Math.PI);
	}
	
	
	
	/**
	 * 
	 * @return Return the orientation of the unit
	 */
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * @post The orientation of the unit equals the atan2(y-component of the unit's speed, x-component of the unit's speed)
	 * 		 | new.getOrientation() == Math.atan2(this.getSpeedVector.get(1), this.getSpeedVector().get(2))
	 */
	private void updateOrientation() {
		this.setOrientation(Math.atan2(this.getSpeedVector().getY(), 
				this.getSpeedVector().getX()));
	}

	
	
	/**
	 * 
	 * @param XTarget
	 * 			The x-axis position of the target cube
	 * @param YTarget
	 * 			The y-axis position of the target cube
	 * @param ZTarget
	 * 			The z-axis position of the target cube
	 * @post The double at index 0 of the list TARGET equals the given XTarget and is the x-axis position of the center of the unit
	 * 		 The double at index 1 of the list TARGET equals the given YTarget and is the y-axis position of the center of the unit
	 * 		 The double at index 2 of the list TARGET equals the given ZTarget and is the z-axis position of the center of the unit
	 * 		| new.getTarget().getX() == XTarget
	 * 		| new.getTarget().getY() == YTarget
	 * 		| new.getTarget().getZ() == ZTarget
	 * @throws IllegalPositionException
	 * 		If the given target (XTarget, YTarget, ZTarget) is not a valid position
	 * 		| (!isValidPosition(XTarget, YTarget, ZTarget)
	 */
	private void setTarget(double XTarget, double YTarget, double ZTarget) throws IllegalPositionException {

		if (!isValidPosition(XTarget, YTarget, ZTarget))
			throw new IllegalPositionException();
		
		else {
			this.TARGET.setX(XTarget);
			this.TARGET.setY(YTarget);
			this.TARGET.setZ(ZTarget);
		}
	}
	
	/**
	 * 
	 * @return Returns a list containing the x-, y- and z-axis position of the unit's target
	 */
	private Vector getTarget() {
		return this.TARGET;
	}
	
	/**
	 * 
	 * @param X
	 * 		The X coordinate of the final target
	 * @param Y
	 * 		The Y coordinate of the final target
	 * @param Z
	 * 		The Z coordinate of the final target
	 * @post The final target of the unit will equal the vector {X,Y,Z}
	 * 		new.getFinalTarget().equals(Vector(X,Y,Z))
	 * @throws IllegalPositionException
	 * 		Throws IllegalPositionException is the position is not valid
	 * 		|!(isValidPosition(X, Y, Z))
	 * 
	 */
	private void setFinalTarget(double X, double Y, double Z) throws IllegalPositionException {
		
		if (!this.isValidPosition(X, Y, Z))
			throw new IllegalPositionException();
		else {
			this.finalTARGET.setX(X);
			this.finalTARGET.setY(Y);
			this.finalTARGET.setZ(Z);
		}
	}
	
	/**
	 * 
	 * @return Returns the finalTarget of the unit
	 */
	private Vector getFinalTarget() {
		return this.finalTARGET;
	}

	
	
	
	/**
	 * 
	 * @param speed
	 * 			The speed of the unit
	 * @post The unit's speed equals the given speed
	 * 		 | new.getSpeed() == speed
	 */
	private void setSpeed(double speed) {
		this.speed = speed;
	}

	
	/**
	 * 
	 * @return Returns the current speed of the unit
	 */
	public double getSpeed() {
		if (this.isFalling())
			return ZSpeed;
		return this.speed;
	}
	
	/**
	 * 
	 * @return Returns the unit's allowed speed. The standard velocity equals the strength of the unit plus 
	 * 		   the agility of the unit multiplied by 1.5 and divided by the weight of the unit 
	 * 		   which is first multiplied by two.
	 * 		   If the unit wants to move upwards, his current speed is his standard velocity divided by two
	 * 		   If the unit wants to move downwards, his current speed is his standard velocity multiplied by 1.2
	 * 		   In every other case, his speed equals his standard velocity
	 * 		   | velocity = 1.5 * (this.getStrength() + this.getAgility()) / (2 * this.getWeight())
	 * 		   | if (ZNow - ZTarget == -1)
	 * 		   | 	then this.getSpeed() = 0.5 * velocity;
	 * 		   | if (ZNow - ZTarget == 1)
	 * 		   |	then this.getSpeed() = 1.2 * velocity;
	 * 		   | else
	 * 		   |	then this.getSpeed() = velocity;
	 */
	private double determineSpeed() {
		
		
		double ZTarget = Math.floor(this.getTarget().getZ());
		double velocity = 1.5 * (this.getStrength() + this.getAgility()) / (2 * this.getWeight());
		double ZNow = this.getCube().getZ();
		
		if (this.isSprinting())
			velocity *= 2;
		if (ZNow - ZTarget == -1)
			return 0.5 * velocity;
		else if (ZNow - ZTarget == 1)
			return 1.2 * velocity;
		else
			return velocity;
		
	}


	
	/**
	 * 
	 * @return Returns a list containing the x-, y- and z- components, respectively, of the unit's velocity
	 */
	private Vector getSpeedVector() {
		double distance = Math.sqrt(Math.pow(this.getTarget().getX() - this.getPosition().getX(),2) + 
				Math.pow(this.getTarget().getY() - this.getPosition().getY(), 2) + 
				Math.pow(this.getTarget().getZ() - this.getPosition().getZ(), 2));
		
		double XSpeed = this.getSpeed() * (this.getTarget().getX() - this.getPosition().getX()) / distance;
		double YSpeed = this.getSpeed() * (this.getTarget().getY() - this.getPosition().getY()) / distance;
		double ZSpeed = this.getSpeed() * (this.getTarget().getZ() - this.getPosition().getZ()) / distance;
		
		Vector speedVector = new Vector(XSpeed, YSpeed, ZSpeed);
		
		return speedVector;

	}
	
	/**
	 * 
	 * @param behavior
	 * 			A boolean indicating whether the unit is allowed 
	 * 			to behave in a random way or not
	 * 
	 * @post The enableDefaultBehavior status of the unit equals the given boolean behavior
	 * 		| new.getDefaultBehavior() = behavior

	 */
	public void setDefaultBehavior(boolean behavior) {		
		this.enableDefaultBehaviour = behavior;
	}
	
	
	/**
	 * 
	 * @return Returns the enableDefaultBehavior status of the unit
	 */
	public boolean getDefaultBehavior() {
		
		return this.enableDefaultBehaviour;
	}

	
	/**
	 * #######################################################################
	 * ##### CHECKERS TO CHECK IF THE GIVEN VALUE MEETS THE REQUIREMENTS #####
	 * #######################################################################
	 */
	
	
	/**
	 * 
	 * @param X
	 * 			The given x-axis position
	 * @param Y
	 * 			The given y-axis position
	 * @param Z
	 * 			The given z-axis position
	 * @return	Returns true if X, Y and Z form a valid position. This is when X, Y and Z
	 * 			are a non-negative value and do not exceed the borders of the units world
	 * 			| if ((X < 0) | (X > new.getWorld.getNbX) | (Y < 0) |(Y > new.getWorld.getNbY) | (Z < 0) | (Z > new.getWorld.getNbZ))
	 * 			| 	then false
	 * 			| else
	 * 			| 	then true
	 */
	private boolean isValidPosition(double X, double Y, double Z) {
		double[] pos = {X, Y, Z};
		return isValidPosition(pos);
	}
	
	private boolean isValidPosition(int X, int Y, int Z) {
		double[] pos = {(double) X, (double) Y, (double) Z};
		return isValidPosition(pos);
	}

	
	private boolean isValidTarget(int X, int Y, int Z) {
		return (isValidPosition(X, Y, Z) && isNeighbouringSolidTerrain(X, Y, Z));
	}
	
	
	/**
	 * 
	 * @param position
	 * 		The position to be checked
	 * @return	Returns true if position is a valid position. This is when X, Y and Z
	 * 			are a non-negative value and do not exceed the borders of the units world
	 * 			| if ((X < 0) | (X > new.getWorld.getNbX) | (Y < 0) |(Y > new.getWorld.getNbY) | (Z < 0) | (Z > new.getWorld.getNbZ))
	 * 			| 	then false
	 * 			| else
	 * 			| 	then true	
	 */
	private boolean isValidPosition(double[] position) {
		try {
			return this.getWorld().isValidPosition(position);
		} catch (NullPointerException e) {
			return true;
		}
	}	
	
	/**
	 * 
	 * @param position
	 * 		The position to be checked
	 * @return Returns whether or not the position is passable
	 * 		
	 */
	private boolean isPassable(int[] position) {
		try {
			return this.getWorld().isPassable(position);
		} catch (NullPointerException e) {
			return true;
		}
	}
	
	/**
	 * 
	 * @param name
	 * 			The name of the unit
	 * @return Return true if the given name is a valid name, else false. A name is a valid name 
	 * 		   if it starts with an upper case letter, has a minimal length of two characters and 
	 * 		   contains letters, ' or " only.
	 * 			
	 */
	private boolean isValidName(String name) {
		return !((!Character.isUpperCase(name.charAt(0))) | 
				(name.length() < 2) | (!name.matches("[a-zA-Z\\s\'\"]+")));

	}
	
	/**
	 * 
	 * @param hitpoints
	 * 			The hitpoints of the unit
	 * @return Returns true if the given hitpoints are valid, else false. The hitpoints are valid
	 * 			if they're nonnegative and smaller than MaxHitpoints
	 */
	private boolean isValidHitpoints(double hitpoints) {
		return (hitpoints <= this.getMaxHitpoints());
	}
	
	/**
	 * 
	 * @param stamina
	 * 			The stamina of the unit
	 * @return Returns true if the given stamina is valid, else false. The stamina is valid
	 * 			if it's nonnegative and smaller than MaxStamina
	 */
	private boolean isValidStamina(double stamina) {
		return (stamina >= 0 ) && (stamina <= this.getMaxStamina());
	}
	

	/**
	 * 
	 * @param defender
	 * 			The defending unit
	 * @return Returns true if the cube the unit's in and the cube the defending unit's in are 
	 * 		   neighboring cubes, they're not falling and their factions differ, else false
	 * 
	 */
	
	private boolean isValidAttack(Unit attacker, Unit defender) {
		
		return ((Math.abs(attacker.getCube().getX()- defender.getCube().getX()) <= 1) &&
				(Math.abs(attacker.getCube().getY()- defender.getCube().getY()) <= 1) &&
				(Math.abs(attacker.getCube().getZ()- defender.getCube().getZ()) <= 1) &&
				(attacker.getFaction() != defender.getFaction()) && 
				(!attacker.isFalling() && !defender.isFalling()));
	}
	
	
	private boolean canRest() {
		return ((this.getNotRestTime() >= 180 | this.getSpeed() == 0)
				&& (!this.isAttacked() && !this.isAttacking()));
	}
	
	private boolean canWork() {
		return (this.canOverruleResting() && this.getSpeed() == 0 && !this.isAttacked() && !this.isAttacking());
	}
	
	private boolean canMove() {
		return (!this.isFalling() && !this.isAttacked() && !this.isAttacking()
				&& this.getNotRestTime() < 180 && this.canOverruleResting());
	}
 	
	/**
	 * @return Returns whether or not a boulder is valid
	 * 		A boulder is valid if it's active and when it's not null
	 */
	private boolean isValidBoulder(Boulder boulder) {
		return (boulder != null && boulder.isActive());
	}
	
	/**
	 * @return Returns whether or not a log is valid
	 * 		A log is valid if it's active and when it's not null
	 */
	private boolean isValidLog(Log log) {
		return (log != null && log.isActive());
	}
	
	/**
	 * @param X
	 * 		The distance on the x-axis
	 * @param Y
	 * 		The distance on the y-axis
	 * @param Z
	 * 		The distance on the z-axis
	 * @return Returns whether or not X, Y and Z point on adjacent cubes
	 */
	private boolean isAdjacent(int X, int Y, int Z) {
		return !(((Math.abs(X) != 1) && (X != 0)) | ((Math.abs(Y) != 1) && (Y != 0)) 
				| ((Math.abs(Z) != 1) && (Z != 0)));
	}
	
	/**
	 * @param position
	 * 		The position to be examined
	 * @return Returns whether or not the position is in the borders of the world of the unit
	 */
	private boolean isPositionInWorld(int[] position) {
		try {
			return this.getWorld().isPositionInWorld(position);
		} catch (NullPointerException e) {
			return true;
		}
	}
	
	private boolean isNeighbouringSolidTerrain(int X, int Y, int Z) {
		int[] pos = {X, Y, Z};
		return isNeighbouringSolidTerrain(pos);	
	}
	
	
	/**
	 * @param cube
	 * 		The cube to be examined
	 * @return Returns true if the cube is at the bottom of the terrain or when one of it's adjacent cubes if solid
	 */
	private boolean isNeighbouringSolidTerrain(int[] position) {
		
		if (position[2] == 0)
			return true;

		ArrayList<int[]> adjacentCubes = getAdjacentCubes(position);
		
		for (int[] adjacentCube: adjacentCubes) {
			try{
				if (!isPassable(adjacentCube))
					return true;
			} catch (IndexOutOfBoundsException e) {};
		}
		return false;
		
	}
	
	/**
	 * @return Returns whether or not the units cube is a workshop
	 */
	private boolean isWorkshop() {
		return isWorkshop(this.getCubeInt());
	}
	
	/**
	 * @param cube
	 * 		The cube to be examined
	 * @return Returns whether or not the position is a workshop
	 */
	private boolean isWorkshop(int[] position) {
		try {
			return this.getWorld().isWorkshop(position);
		} catch (NullPointerException e) {
			return true;
		}
	}
		
	/**
	 * @return Returns whether or not the units cube is wood
	 */
	private boolean isWood() {
		return isWood(this.getCubeInt());
	}
	
	/**
	 * @param cube
	 * 		The cube to be examined
	 * @return Returns whether or not the position is wood
	 */
	private boolean isWood(int[] position) {
		try {
			return this.getWorld().isWood(position);
		} catch (NullPointerException e) {
			return true;
		}
	}

	/**
	 * @return Returns whether or not the units cube is rock
	 */
	private boolean isRock() {
		return isRock(this.getCubeInt());
	}
	
	/**
	 * @param cube
	 * 		The cube to be examined
	 * @return Returns whether or not the position is rock
	 */
	private boolean isRock(int[] position) {
		try {
			return this.getWorld().isRock(position);
		} catch (NullPointerException e) {
			return true;
		}
	}
	

	
	/**
	 *@param Queue
	 *		The queue that's checked
	 *@position
	 *		The position of which
	 */
	private boolean isNotInQueue(ArrayList<Object[]> Queue, int[] position, int n0) {
		
		if (Queue.size() == 0) 
			return true;
		for (int i = 0; i < Queue.size(); i++) {
			Object[] something = ((Object[]) Queue.get(i));
			if ((something[0] == position) && (((int) something[1]) <= n0))
				return false;
		}
		return true;
	}
	
	
	/**
	 * @param Queue
	 * 		The queue that's checked
	 * @param position
	 * 		The position of which we want to know if it's in the Queue
	 * @return Returns true if the Queue contains a list with the position, else false
	 */
	private boolean isInQueue(ArrayList<Object[]> Queue, int[] position) {
		for (int i = 0; i < Queue.size(); i++) {
			int[] comparable = (int[])(Queue.get(i)[0]);
			if (comparable[0] == position[0] && comparable[1] == position[1] 
					&& comparable[2] == position[2])
				return true;
		}
		return false;
	}


	
	/**
	 * ###################################################################
	 * ##### FUNCTIONS TO INDICATE THE UNIT SHOULD START AN ACTIVITY #####
	 * ###################################################################
	 */
	
	
	/**
	 * @param sprinting
	 * 		The boolean status of sprinting of the unit
	 * @post The status of the boolean sprint of the unit equals sprinting
	 * 		| new.isSprinting() == sprinting
	 * @post The time the unit has been sprinting equals zero if sprinting is false
	 * 		|if (!sprinting)
	 * 		| 	this.getSprintingTime == 0
	 */
	public void setSprinting(boolean sprinting) {
		if (!sprinting) {
			this.setSprintingTime(0);
			this.sprint = sprinting;
		}
		else {
			assert(this.getStamina() >= 1);
			this.sprint = true;
		}
	}
	
	
	/**
	 * @param target
	 * 		The cube where the unit should move to
	 * @post the finaltarget of the unit will be target
	 * 		|new.getFinalTarget == target
	 * @throws IllegalPositionException
	 * 		Throws IllegalPositionException if the position if invalid
	 * 		|if (!isValidPosition(target))
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the unit is terminated
	 * 		|if(new.isTerminated())
	 */
	public void moveTo(int[] target) throws IllegalPositionException, IllegalStateException, 
		IllegalArgumentException, IllegalTargetException {
		
		try {
			this.moveTo(target[0], target[1], target[2]);
		} catch (IllegalPositionException invalidPosition) {
			throw invalidPosition;
		} catch (IllegalArgumentException otherPriority) {
			throw otherPriority;
		} catch (IllegalStateException unitTerminated) {
			throw unitTerminated;
		} catch (IllegalTargetException unreachableTarget) {
			throw unreachableTarget;
		}
	}
	
	/**
	 * 
	 * @param X
	 * 		The X-coordinate of the cube where the unit should move to
	 * @param Y
	 * 		The Y-coordinate of the cube where the unit should move to
	 * @param Z
 	 * 		The Z-coordinate of the cube where the unit should move to
	 * @post the finaltarget of the unit will be {X,Y,Z}
	 * 		|new.getFinalTarget == {X,Y,Z}
	 * @throws IllegalPositionException
	 * 		Throws IllegalPositionException if the position {X,Y,Z}if invalid
	 * 		|if (!isValidPosition(target))
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the unit is terminated
	 * 		|if(new.isTerminated())
	 */
	public void moveTo(int X, int Y, int Z) throws IllegalPositionException , IllegalStateException, 
		IllegalArgumentException, IllegalTargetException {
		
		if (!this.canMove())
			throw new IllegalArgumentException("Unit is doing another activity with higher priority"); 
		if (this.isTerminated())
			throw new IllegalStateException("Unit is terminated");
		if (!isValidTarget(X, Y, Z))
			throw new IllegalTargetException("Target is unreachable");
		try {
			this.setFinalTarget(((double) X), ((double) Y), ((double) Z));
			this.setWorking(false);
			this.setResting(false);
		} catch (IllegalPositionException invalidPosition) {
			throw invalidPosition;
		}
	}

	
	
	/**
	 * 
	 * @param X
	 * 			The distance the unit has to travel in the direction of the x-axis
	 * @param Y
	 * 			The distance the unit has to travel in the direction of the y-axis
	 * @param Z
	 * 			The distance the unit has to travel in the direction of the z-axis
	 * 
	 * @post The new position of the unit equals his previous position plus (X, Y, Z)
	 * 		 if the unit was not interrupted during his activity
	 * 		| if (!this.isStopped())
	 * 		|	then new.getPosition() == this.getPosition() + [X, Y, Z]
	 * @post If the unit was interrupted, the unit's status of notReachedTarget changes to true
	 * 		 to indicate the unit's moveToAdjacent activity was aborted
	 * 		| if (this.isStopped())
	 * 		|	then new.getNotReachedTarget() == true
	 * @throws IllegalPositionException
	 * 		Throws IllegalPositionException if the unit has to travel to a non-adjacent cube
	 * 		|if (!isAdjacent(X, Y, Z))
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the unit is terminated
	 * 		|if(new.isTerminated())
	 */
	public void moveToAdjacent(int X, int Y, int Z) throws IllegalPositionException, IllegalStateException {
		
		if (!isAdjacent(X, Y, Z))
			throw new IllegalPositionException("Not an adjacent cube");
		if (this.isTerminated())
			throw new IllegalStateException("Unit is terminated");
		else {
			this.setTarget(this.getPosition().getX() + ((double) (X)), 
					this.getPosition().getY() + ((double) (Y)), 
					this.getPosition().getZ() + ((double) (Z)));
		}
		
	}
	

	
	/**
	 * 
	 * @throws IllegalPositionException
	 */
	public void pathFindingAlgorithm() throws IllegalPositionException {
		
		int X = (int) Math.round(this.getFinalTarget().getX());
		int Y = (int) Math.round(this.getFinalTarget().getY());
		int Z = (int) Math.round(this.getFinalTarget().getZ());
		int[] destination = {X, Y, Z};
		
		ArrayList<Object[]> Queue = new ArrayList<>();
		if (this.getCubeInt() != destination) {
			Object[] somethingNew = {destination, 0};
			Queue.add(somethingNew);
			int i = 0;
			while ((!isInQueue(Queue, this.getCubeInt())) && (i < Queue.size())) {
				Object[] something = ((Object[]) Queue.get(i));
				search(Queue, something);
				i += 1;
			}
			if (isInQueue(Queue, this.getCubeInt())) {
				int[] next = takeNext(Queue);
				moveToAdjacent(this.getCubeInt()[0] - next[0], 
						this.getCubeInt()[1] - next[1], 
						this.getCubeInt()[2] - next[2]);
			} else	{
				throw new IllegalPositionException("Destination is unreachable");
			}
		}
		
				
	}

	

	
	/**
	 * @param Queue 
	 */
	private void search(ArrayList<Object[]> Queue, Object[] something) {
		int[] position = ((int[]) something[0]);
		int n0 = ((int) something[1]);
		search(Queue, position, n0);
	}
	
	/**
	 * @param Queue 
	 */
	private void search(ArrayList<Object[]> Queue, int[] position, int n0) {
		
		ArrayList<int[]> adjacentCubes = this.getAdjacentCubes(position);
		for (int[] cube: adjacentCubes) {
			if ((isPassable(cube)) && (isNeighbouringSolidTerrain(cube))
					&& isNotInQueue(Queue, cube, n0)) {
				Object[] somethingNew = {cube, n0+1};
				Queue.add(somethingNew);
			}
		}
	}

	
	
	
	private int[] takeNext(ArrayList<Object[]> Queue) {
		
		int maximalN = Integer.MAX_VALUE;
		int[] next = null;
		
		for (Object[] something: Queue) {
			if (((int) something[1]) <= maximalN) {
			maximalN = ((int) something[1]);
			next = ((int[]) something[0]);
			}
		}
		
		return next;
			
	}
	
		
	
	
	/**
	 * @post The unit will be at its final target
	 * 		new.getPosition() == this.getFinalTarget
	 */
	private void moveToTarget() {
		
		double X = this.getFinalTarget().getX();
		double Y = this.getFinalTarget().getY();
		double Z = this.getFinalTarget().getZ();
		
		int x, y, z;
		if (this.getPosition().getX() == X)
			x = 0;
		else if (this.getPosition().getX() < X)
			x = 1;
		else 
			x = -1;
		if (this.getPosition().getY() == Y)
			y = 0;
		else if (this.getPosition().getY() < Y)
			y = 1;
		else 
			y = -1;
		if (this.getPosition().getZ() == Z)
			z = 0;
		else if (this.getPosition().getZ() < Z)
			z = 1;
		else 
			z = -1;
			this.moveToAdjacent(x, y, z);
		
	}
	
	/**
	 * @post The unit starts working, it stops moving and stops resting
	 * 		new.getWorking == true
	 * 		new.getSpeed == 0
	 * 		new.getResting == false
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the unit is terminated
	 * 		|if(new.isTerminated())
	 */
	public void work() throws IllegalStateException, IllegalArgumentException {
		if (this.isTerminated())
			throw new IllegalStateException("Unit is terminated");
		if (!this.canWork())
			throw new IllegalArgumentException("Unit is doing another activity with higher priority");
		this.setWorking(true);
		this.workAfterMoving(false);
	}
	
	
	/**
	 * @param x
	 * 		The x coordinate of the cube where the unit should start moving
	 * @param y
	 * 		The y coordinate of the cube where the unit should start moving
	 * @param z
	 * 		The z coordinate of the cube where the unit should start moving
	 * 
	 * @post
	 * 		The unit starts working, stops resting and starts moving towards position {x,y,z}
	 * 		new.getWorking == true
	 * 		new.getResting == false
	 * 		new.getFinalTarget == {x,y,z}
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the unit is terminated
	 * 		|if(new.isTerminated())
	 */
	public void workAt(int x, int y, int z) throws IllegalStateException, IllegalArgumentException {
		if (this.isTerminated())
			throw new IllegalStateException("Unit is terminated");
		if (!this.canWork())
			throw new IllegalArgumentException("Unit is doing another activity with higher priority");
		this.workAfterMoving(true);
		this.moveTo(x, y, z);
	}
	
	/**
	 * @post
	 * 		if (new.isCarryingBoulder() | new.isCarryingLog())
	 *			new.isCarryingLog == false
	 *			new.isCarryingBoulder == false
	 *		else if (isWorkshop() && logAndBoulderAreAvailable())
	 *			new.isCarryingLog == false
	 *			new.isCarryingBoulder == false
	 *			new.getWeight == this.getWeight + 5
	 *			new.getToughness == this.getToughness + 5				
	 *		else if (boulderAvailable())
	 *			new.isCarryingBoulder == true
	 *		else if (logAvailable())
	 *			new.isCarryingLog == true
	 */
	protected void finishWorking() {
		
		this.setExperiencePoints(this.getExperiencePoints() + 10);
		
		if (this.isCarryingBoulder() | this.isCarryingLog())
			this.dropEverything();
		else if (isWorkshop() && logAndBoulderAreAvailable())
			this.improveEquipment();
		else if (boulderAvailable())
			this.carryBoulder();
		else if (logAvailable())
			this.carryLog();
		else if (isWood())
			this.collapseAndCreate();
		else if (isRock())
			this.collapseAndCreate();
			
	}
	
	/**
	 * @post
	 * 		| new.isCarryingLog == false
	 *		| new.isCarryingBoulder == false
	 *		| new.getWeight == this.getWeight + 5
	 *		| new.getToughness == this.getToughness + 5	
	 */
	private void improveEquipment() {
		
		this.setWeight(this.getWeight() + 5);
		this.setToughness(this.getToughness() + 5);
		
		try {
			this.getBoulder().terminate();
		} catch (NullPointerException e) {};
		
		try {
			this.getLog().terminate();
		} catch (NullPointerException e) {};
	}
	
	private void collapseAndCreate() {
		try {
			this.getWorld().caveAndThrow(this.getCubeInt());
		} catch (NullPointerException e) {};
	}
	
	
	/**
	 * 
	 * @param attacker
	 * 			The attacking unit
	 * @param defender
	 * 			The defending unit (attacked by the attacker)
	 * @pre The attacking unit and defending unit must be located in neighboring cubes
	 * 		| attacker.isValidAttack(defender)
	 * @post The attacker's status of getAttacking() equals true
	 * 		| attacker.getAttacking() == true
	 * @post The defender's status of isAttacked() equals true
	 * 		| defender.isAttacked() == true
	 * @post The attackers opponent is defender
	 * 		| attacker.getOpponent == defender
	 * @post The defenders opponent is attacker
	 * 		| defender.getOpponent == attacker
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the unit is terminated
	 * 		|if(new.isTerminated())
	 */
	public void fight(Unit attacker, Unit defender) throws IllegalStateException {
		assert isValidAttack(attacker, defender);
		
		if (attacker.isTerminated() | defender.isTerminated())
			throw new IllegalStateException("One of the units is terminated");
		
		
		attacker.setOpponent(defender);
		defender.setOpponent(attacker);
		attacker.attack();
		defender.attacked();
		
	}
	
	/**
	 * 
	 * @post The unit starts resting
	 * 		|new.getResting == true
	 * @post The units attackedstate, attackingstate, sprintingstate, defendingstate and workingstate are all false
	 * 		|new.getAttacked == false;
	 * 		|new.getAttacking == false;
	 * 		|new.getSprinting == false;
	 * 		|new.getDefending == false;
	 * 		|new.getWorking == false;
	 * @post The units speed is set 0
	 * 		|new.getSpeed == 0
	 * 
	 * @throws IllegalStateException
	 * 		Throws IllegalStateException if the unit is terminated
	 * 		|if(new.isTerminated())
	 *  	
	 */
	public void resting() throws IllegalStateException, IllegalArgumentException, IllegalStateException {
		
		if (this.isTerminated())
			throw new IllegalStateException("The unit is terminated");
		if (!this.canRest())
			throw new IllegalArgumentException("Unit is doing another activity with higher priority");
		
		this.setResting(true);
		this.setWorking(false);
		this.workAfterMoving(false);
		
		
	}
	
	
	/**
	 * ##################################################################################################
	 * ##### FUNCTIONS TO INDICATE THAT THE UNIT'S STATUS HAS CHANGED OR TO CHECK THE UNIT'S STATUS #####
	 * ##################################################################################################
	 */
	
	
	/**
	 * 
	 * @param working
	 * 			The working status of the unit
	 * @post The unit's working status equals the given boolean
	 * 		| new.isWorking() == working
	 * @post workingTime is zero if the unit stops working
	 * 		|if (working == false)
			|	new.getWorkingTime == 0
	 * 		
	 */
	private void setWorking(boolean working) {
		this.working = working;
		if (working == false)
			this.setWorkingTime(0);
	}
	
	/**
	 * 
	 * @return Returns the status of the boolean working
	 */
	public boolean isWorking() {
		return this.working;
	}
	
	/**
	 * 
	 * @return Returns the value (true or false) of the boolean sprint of the unit
	 */
	public boolean isSprinting() {
		return (this.sprint && this.getSpeed() != 0 && !this.isFalling());
	}

	/**
	 * @param attacked
	 * 			The attacked status of the unit
	 * 
	 * @post The unit's attacked status equals the given boolean attacked
	 * 		 | new.getAttacked() == true
	 */
	
	private void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}
	
	/**
	 * Indicates whether or not the unit is being attacked
	 * @return Returns the status of the attacked boolean of the unit
	 */
	public boolean isAttacked() {
		return this.attacked;
	}
	
	
	/**
	 * @post The unit's resting status equals the given boolean
	 * 		 | new.isResting() == restingStatus
	 */
	private void setResting(boolean restingStatus) {
	
		if (restingStatus && !this.isResting())
			this.setOverruleResting(false);
		
		this.resting = restingStatus;
		
		if (this.isResting())
			this.setNotRestTime(0);
		else
			this.setRestingTime(0);
			
			
	}
	
	/**
	 * 
	 * @return Returns the status of the resting boolean of the unit
	 */
	public boolean isResting() {
		return this.resting;
	}
	

	/**
	 * 
	 * @param attack
	 * 			A boolean indicating whether or not the unit should attack
	 * 
	 * @post The isAttacking status of the unit equals the given boolean attack
	 * 		| new.isAttacking() == attack
	 * 
	 */
	private void setAttacking(boolean attack) {
		this.attacking = attack;
	}

	/**
	 * 
	 * @return Returns the isAttacking status of the unit
	 * 
	 */
	public boolean isAttacking() {
		return this.attacking;
	}
	

	
	/**
	 * 
	 * @return Returns whether or not the unit is falling
	 */
	public boolean isFalling() {
		return this.falling;
	}
	
	/**
	 * 
	 * @param trueorfalse
	 */
	private void setOverruleResting(boolean trueorfalse) {
		this.firstRest = trueorfalse;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean canOverruleResting() {
		return this.firstRest;
	}
	

	
	/**
	 * ##########################################################
	 * ##### FUNCTIONS THAT CHANGE THE UNIT'S CURRENT STATE #####
	 * ##########################################################
	 */

	
	public void advanceTime(double seconds) throws IllegalArgumentException, IllegalStateException {
		
		if ((seconds <= 0) | (seconds > 0.2))
			throw new IllegalArgumentException("Given seconds should be a double value between 0 and 0.2");
		if (this.isTerminated())
			throw new IllegalStateException("The unit is terminated");
		
		// Falling:
		if (this.shouldFall()) {
			this.fall(seconds);
			return;
		}
		
		// Fight:
		if (this.isAttacking()) {
			if (this.getFightTime() < 1) {
				this.setFightTime(this.getFightTime() + seconds);
				this.fightUnitOrientation();
			} else {
				this.setAttacking(false);
				this.setFightTime(0);
			}
			return;
		} else if (this.isAttacked()) {
			if (this.getFightTime() < 1) {
				this.setFightTime(this.getFightTime() + seconds);
				this.fightUnitOrientation();
			} else {
				this.setAttacked(false);
				this.setFightTime(0);
				this.defend();
			}
			return;
		}
		
		// checkResting:
		if (!this.isResting()) {
			this.setNotRestTime(this.getNotRestTime() + seconds);
			this.checkResting();
		}
		
		if (this.isResting()) {
			this.setRestingTime(this.getRestingTime() + seconds);
			this.changeResting();
			return;
		}
		
		if (!this.getPosition().equals(this.getFinalTarget())) {
			if (!this.getPosition().equals(this.getTarget())) {
				this.setSpeed(this.determineSpeed());
				this.changePosition(seconds);
			} else {
				try {
					this.pathFindingAlgorithm();
					this.setSpeed(this.determineSpeed());
					this.changePosition(seconds);
				} catch (IllegalPositionException e) {
					this.setFinalTarget(this.getPosition().getX(), this.getPosition().getY(),
							this.getPosition().getZ());;
				}
			}
			if (this.isSprinting()) {
				this.setSprintingTime(this.getSprintingTime() + seconds);
				this.controlSprinting();
			} 
			return;
		} else {
			if (!this.isFalling())
				this.setSpeed(0);
			if (this.shouldWorkAfterMoving()) {
				this.work();
			}
		}
		
		
		if (this.isWorking()) {
			if (this.getWorkingTime() >= this.getTimeToWork()) {
				this.setWorking(false);
				this.finishWorking();
			}
			else
				this.setWorkingTime(this.getWorkingTime() + seconds);
			return;
		}
		
		// DefaultBehavior:
		if (this.getDefaultBehavior())
			this.startDefaultBehavior();
		else
			this.stopDefaultBehavior();	
	}
	
	/**
	 * 
	 * @param defender
	 * 			The defending unit
	 * @post The unit's attacking status equals true
	 * 		| new.getAttacking() == true
	 * @post The unit's working status equals false
	 * 		| new.getWorking() == false
	 * @post The unit's resting status equals false
	 * 		| new.getResting() == false
	 * @post The unit's speed equals zero
	 * 		| new.getSpeed() == 0
	 */
	private void attack() {
		
		this.setAttacking(true);
		this.setWorking(false);
		this.setResting(false);
		this.setAttacked(false);
		this.workAfterMoving(false);
		
	}
	
	/**
	 * 
	 */
	private void attacked() {
		
		this.setAttacking(false);
		this.setWorking(false);
		this.setResting(false);
		this.setAttacked(true);
		this.workAfterMoving(false);
	}
	
	/**
	 * 
	 */
	private void controlSprinting() {
		
		assert (this.isSprinting());
		while (this.getSprintingTime() - 0.1 >= 0 && this.getStamina() >= 1) {
			this.setStamina(this.getStamina() - 1);
			this.setSprintingTime(this.getSprintingTime() - 0.1);
		}
		if (this.getStamina() < 1)
			this.setSprinting(false);
	}

	/**
	 * NEW
	 * De maximale afstand die een unit kan afleggen in een keer advanceTime() is 0.72.
	 * Dit is omdat zijn maximale snelheid v = 1.5 * (strength + agillity) / (2 * weight) * 2 * 1.2
	 * deze is dus maximaal als weight minimaal is (d.i. gelijk aan (strength + agility) / 2). Invullen 
	 * geeft 3.6 m/s, onafhankelijk van strength of agility. Het maximaal aantal seconden is 0.2, de maximale
	 * afstand is dus 0.72. Binnen één changePosition() blijft de unit dus in dezelfde cube of gaat één cube verder.
	 * 
	 * @param time
	 */
	private void changePosition(double time) {
		
		double X = this.getPosition().getX();
		double Y = this.getPosition().getY();
		double Z = this.getPosition().getZ();
		Vector previousCube = this.getCube();
		
		this.setPosition(this.getPosition().getX() + this.getSpeedVector().getX() * time,
				this.getPosition().getY() + this.getSpeedVector().getY() * time,
				this.getPosition().getZ() + this.getSpeedVector().getZ() * time);
		this.updateOrientation();
		
		if (Math.abs(this.getPosition().getX() - X) > Math.abs(X - this.getTarget().getX()))
			this.setPosition(this.getTarget().getX(), this.getPosition().getY(), this.getPosition().getZ());
		if (Math.abs(this.getPosition().getY() - Y) > Math.abs(Y - this.getTarget().getY()))
			this.setPosition(this.getPosition().getX(), this.getTarget().getY(), this.getPosition().getZ());
		if (Math.abs(this.getPosition().getZ() - Z) > Math.abs(Z - this.getTarget().getZ()))
			this.setPosition(this.getPosition().getX(), this.getPosition().getY(), this.getTarget().getZ());
		
		if (previousCube != this.getCube())
			this.setExperiencePoints(this.getExperiencePoints() + 1);
	
	}
	
	/**
	 * 
	 * @param time
	 */
	private void fall(double time) {
		
		int previousZLevel = this.getCubeInt()[2];
		this.setPosition(this.getPosition().getX(), this.getPosition().getY(),
				this.getPosition().getZ() + ZSpeed * time);
		int ZLevelsFallen = this.getCubeInt()[2] - previousZLevel;
		this.setHitpoints(this.getHitpoints() - 10 * ZLevelsFallen);

	}
	
	/**
	 * 
	 * @param defender 
	 * 		The defending unit
	 * @param attacker 
	 * 		The attacking unit
	 * 
	 * @post Both unit's orientation changes depending on their current position, by using the function Math.atan2(x,y) 
	 * 		| defender.getOrientation() == Math.atan2((attacker.getPosition().get(1) - defender.getPosition().get(1)),
	 * 		| 	attacker.getPosition().get(0) - defender.getPosition().get(0))
	 * 		| attacker.getOrientation() == Math.atan2((defender.getPosition().get(1) - attacker.getPosition().get(1)),
	 * 		|	defender.getPosition().get(0) - attacker.getPosition().get(0))
	 */
	
	private void fightUnitOrientation() {
		this.setOrientation(Math.atan2((this.getOpponent().getPosition().getY() - this.getPosition().getY()),
				this.getOpponent().getPosition().getX() - this.getPosition().getX()));
		
	}
	
	/**
	 * 
	 * @param attacker
	 * 			The attacking unit
	 * @post The resting status of the unit equals false
	 * 		 | new.getResting() == false
	 * @post The speed status of the unit equals false
	 * 		 | new.getSpeed() == 0
	 * @post The working status of the unit equals false
	 * 		 | new.getWorking() == false
	 * @post The 'attacked' status of the unit equals true
	 * 		 | new.getAttacked() == true 
	 * @post If the unit succeeds in dodging the attack, it moves to a randomly chosen adjacent position
	 * 		 | PDodge = 0.20 * (this.getAgility() / attacker.getAgility())
	 * 		 | if (Math.random() <= PDodge)
	 * 		 |	then new.getPosition() == [this.getPosition().get(0) + (Math.random() - 0.5) * 2,
	 * 		 |									this.getPosition().get(1) + (Math.random() - 0.5) * 2,
	 * 		 |									this.getPosition().get(2) + (Math.random() - 0.5) * 2]
	 * @post If the unit fails in dodging the attack, but succeeds in blocking the attack, it's not harmed
	 * 		 | PBlock =  0.25 * (this.getStrength() + this.getAgility()) / (attacker.getStrength() + attacker.getAgility())
	 * 		 | if ((Math.random() > PDodge) && (Math.random() <= PBlock))
	 * 		 |	then new.getHitpoints() == this.getHitpoints()
	 * @post If the unit fails in both dodging and blocking the attack, its hitpoints diminish by the strength of the attacker 
	 * 		 divided by ten with a minimum of zero
	 * 		 | if ((Math.random() > PDodge) && (Math.random() > PBlock))
	 * 		 |	then new.getHitpoints() == Math.max(this.getHitpoints() - attacker.getStrength() / 10, 0)
	 * @post If the unit's move to a target was interrupted because of this attack, it should resume its previous activity
	 * 		 | if (this.getNotReachedTarget())
	 *  	 |	then new.getSpeed() >= 0
	 * 		 
	 */
	private void defend() {
		
		this.setResting(false);
		this.setSpeed(0);
		this.setWorking(false);
		this.setAttacked(false);
		
		double PDodge = 0.20 * (this.getAgility() / this.getOpponent().getAgility());
		double PBlock = 0.25 * (this.getStrength() + this.getAgility()) / 
				(this.getOpponent().getStrength() + this.getOpponent().getAgility());
		
		if (Math.random() <= PDodge) {
			this.moveToRandomAdjacentPosition();
			this.setExperiencePoints(this.getExperiencePoints() + 20);
		}
		else if (Math.random() > PBlock) {
			this.setHitpoints(Math.max(this.getHitpoints() - this.getOpponent().getStrength() / 10, 0));
			this.getOpponent().setExperiencePoints(this.getOpponent().getExperiencePoints() + 20);
		}
		else
			this.setExperiencePoints(this.getExperiencePoints() + 20);
		
		
	}
	
	private void fightPotentialEnemies() {
		try {
			Unit enemy = this.getWorld().getEnemy(this);
			if (enemy != null)
				fight(this, enemy);
		} catch (NullPointerException e) {
			this.startDefaultBehavior();
		}
	}
	
	
	/**
	 * The unit moves to a random adjacent position when it succeeds in dodging the attack by another unit
	 * @post The unit's new position is a randomly chosen position in an adjacent cube
	 * 		 | new.getPosition() = [this.getPosition().get(0) + (Math.random() - 0.5) * 2,
	 * 		 |							this.getPosition.get(1) + (Math.random() - 0.5) * 2,
	 * 		 |							this.getPosition.get(2) + (Math.random() - 0.5) * 2]
	 */
	private void moveToRandomAdjacentPosition() {
		
		try {
			this.setPosition(this.getPosition().getX() + (Math.random() - 0.5) * 2, 
					this.getPosition().getY() + (Math.random() - 0.5) * 2,
					this.getPosition().getZ() + (Math.random() - 0.5) * 2);
		} catch (IllegalPositionException e) {
			this.moveToRandomAdjacentPosition();
		}
	}
	
	/**
	 * 
	 */
	private void changeResting() {
		
		double ricoHitpoints = this.getToughness() / 200;
		double ricoStamina = this.getToughness() / 100;
		
		while (this.getHitpoints() < this.getMaxHitpoints() 
				&& this.getRestingTime() - ricoHitpoints >= 0) {
				this.setHitpoints(this.getHitpoints() + 1);
				this.setRestingTime(this.getRestingTime() - ricoHitpoints);
				if (!this.canOverruleResting()) {
					this.setOverruleResting(true);;
			}
			
		} 
		this.setOverruleResting(true);
		
		while (this.getStamina() < this.getMaxStamina() 
				&& this.getRestingTime() - ricoStamina >= 0) {
				this.setStamina(this.getStamina() + 1);
				this.setRestingTime(this.getRestingTime() - ricoStamina);
		}
	}
	
	
	/**
	 * @post The unit starts to behave in a random way,  it can move to a random position,
	 * 		 start working or start resting.
	 * 		| new.getDefaultBehavior() = true
	 * 		| P = Math.random()
	 * 		| if (P <= 1/3)
	 * 		|	then new.getTarget() ==  [Math.random() * 50, Math.random() * 50, Math.random() * 50]
	 * 		| else if (P <= 2/3)
	 * 		|	then new.isWorking() == true
	 * 		| else
	 * 		|	then new.isResting() == true
	 */
	private void startDefaultBehavior() {
		
		//assert (this.getDefaultBehavior());
		double P = Math.random();
		if (P <= 0.25)
			this.moveToRandomPosition(); 
		else if (P <= 0.50)
			this.work();
		else if (P <= 0.75)
			this.resting();
		else
			this.fightPotentialEnemies();
		
		
				
	}
	
	/**
	 * @post The unit starts moving to a random position
	 * 		| new.getTarget() ==  [Math.random() * 50, Math.random() * 50, Math.random() * 50]
	 * 		
	 */
	private void moveToRandomPosition() {
		
		int X = (int) (Math.round(Math.random() * 50));
		int Y = (int) (Math.round(Math.random() * 50));
		int Z = (int) (Math.round(Math.random() * 50));
		
		try {
			this.moveTo(X, Y, Z);
		} catch (IllegalPositionException e) {
			this.moveToRandomPosition();
		}
		
	}
	
	/**
	 * @post The moving stops with everything it's doing
	 * 		| new.isworking() == false &&
	 * 		| new.isResting() == false &&
	 * 		| new.getSpeed() == false &&
	 * 		| new.getTarget() == this.getPosition()
	 */
	private void stopDefaultBehavior() {
		
		this.setWorking(false);
		this.setResting(false);
		this.setSpeed(0);
		this.setTarget(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());

	}
	
	
	
	/**
	 * ####################################################################
	 * ##### FUNCTIONS TO CHECK WHETHER TO UNIT'S STATE SHOULD CHANGE #####
	 * ####################################################################
	 */

	/**
	 * Checks whether the unit should start resting,
	 * this is if it hasn't been resting for at least 180 seconds
	 * @post If the time the unit hasn't been resting is greater than or equal to 180,
	 * 		 this unit starts resting
	 * 		 | if (this.getRestingTime() >= 180)
	 * 		 |	then new.isResting() == true
	 */
	private void checkResting() {
		if (this.getNotRestTime() >= 180)
			this.resting();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public boolean shouldFall() {
		
		if (this.getCubeInt()[2] == 0) {
			falling = false;
			return false;
		}
			
		
		Set<int[]> directlyAdjacentPositionList = this.getDirectlyAdjacentCubes();
		
		for (int[] adjacentPosition: directlyAdjacentPositionList) {
			try{
				if (!isPassable(adjacentPosition)) {
					falling = false;
					return false;
				}
			} catch (IndexOutOfBoundsException e) {};
		}
		falling = true;
		
		this.setResting(false);
		this.setWorking(false);
		this.setAttacked(false);
		this.setAttacking(false);
		this.workAfterMoving(false);
		
		return true;
		
	}
	



	/**
	 * #####################################################
	 * ##### FUNCTIONS ABOUT CARRYING BOULDERS OR LOGS #####
	 * #####################################################
	 */
	
	/**
	 * 
	 * @param log
	 * @throws IllegalLogException
	 */
	public void carryLog(Log log) throws IllegalLogException {
		if (isValidLog(log))
			throw new IllegalLogException("Log is null");
		
		this.carriedLog = log;
		log.setOwner(this);
		this.setWeight(this.getWeight() + log.getWeight());
	}
	
	/**
	 * 
	 * @return
	 */
	public Log getCarryingLog() {
		return this.carriedLog;
	}
	
	public void carryBoulder() {
		try {
			carryBoulder(this.getWorld().getBoulder(this.getCubeInt()));
		} catch (NullPointerException e) {};
		
	}
	
	public void carryLog() {
		try {
			carryLog(this.getWorld().getLog(this.getCubeInt()));
		} catch (NullPointerException e) {};
		
	}
	
	/**
	 * 
	 * @param boulder
	 * @throws IllegalBoulderException
	 */
	public void carryBoulder(Boulder boulder) throws IllegalBoulderException {
		if (isValidBoulder(boulder))
			throw new IllegalBoulderException("Boulder is null");
			
		this.carriedBoulder = boulder;
		boulder.setOwner(this);
		this.setWeight(this.getWeight() + boulder.getWeight());;
			
	}
	
	/**
	 * 
	 * @return
	 */
	public Boulder getCarryingBoulder() {
		return this.carriedBoulder;
	}

 	/**
 	 * 
 	 */
	public void dropEverything() {
		this.dropLog();
		this.dropBoulder();
	}
	
	/**
	 * 
	 */
	public void dropLog() {
		
		try {
			this.getCarryingLog().setPosition(this.getPositionList());
			this.getCarryingLog().setOwner(null);
			this.removeLog();
		} catch (NullPointerException e) {}
	}
	
	/**
	 * 
	 */
	public void dropBoulder() {
		
		try {
			this.getCarryingBoulder().setPosition(this.getPositionList());
			this.getCarryingBoulder().setOwner(null);	
			this.removeBoulder();
		} catch (NullPointerException e) {}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCarryingLog() {
		return this.getCarryingLog() != null;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCarryingBoulder() {
		return this.getCarryingLog() != null;
	}
	
	/**
	 * 
	 */
	protected void removeBoulder() {
		this.setWeight(this.getWeight() - this.getCarryingBoulder().getWeight());
		carriedBoulder = null;
	}
	
	/**
	 * 
	 */
	protected void removeLog() {
		this.setWeight(this.getWeight() - this.getCarryingLog().getWeight());
		carriedLog = null;
	}
	
	
	private boolean logAvailable() {
		return logAvailable(this.getCubeInt());
	}
	
	
	private boolean logAvailable(int[] position) {
		try {
			return this.getWorld().logAvailable(position);
		} catch (NullPointerException e) {
			return true;
		}
	}
	
	private boolean boulderAvailable() {
		return boulderAvailable(this.getCubeInt());
	}
	
	
	private boolean boulderAvailable(int[] position) {
		try {
			return this.getWorld().boulderAvailable(position);
		} catch (NullPointerException e) {
			return true;
		}
	}
	
	private boolean logAndBoulderAreAvailable() {
		return logAndBoulderAreAvailable(this.getCubeInt());
	}
	
	private boolean logAndBoulderAreAvailable(int[] position) {
		return (boulderAvailable(position) && logAvailable(position));
	}
	
	private Boulder getBoulder() {
		try {
			return this.getWorld().getBoulder(this.getCubeInt());
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	private Log getLog() {
		try {
			return this.getWorld().getLog(this.getCubeInt());
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * ################################################
	 * ##### FUNCTIONS ABOUT TERMINATING THE UNIT #####
	 * ################################################
	 */
	
	/**
	 * 
	 */
	public void terminate() {
		if (!this.isTerminated()) {
			this.dropEverything();
			try {
				this.getWorld().removeUnit(this);
			} catch (NullPointerException e) {};
			this.getFaction().removeUnit(this);
			this.setFaction(null);
			this.isTerminated = true;
		}
	}
	
	/**
	 * 
	 */
	private void checkTerminate() {
		if (this.getHitpoints() <= 0)
			this.terminate();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}


	
	/**
	 * #########################
	 * ##### HELPFUNCTIONS #####
	 * #########################
	 */
	
	/**
	 * The unit starts working and stops after 500 divided by its strength seconds
	 * unless its working activity is interrupted by an attack or because its need to rest
	 * @post The working status of the unit equals false after the work is done
	 * 		 | new.getWorking() == false
	 * @post The unit's speed is equal to zero
	 * 		 | new.getSpeed() == 0
	 * @post The unit's resting status is false
	 * 		 | new.isResting() == false
	 */
	private double getTimeToWork() {
		return (double) (500 / this.getStrength());
	}
	
	/**
	 * @return Returns the time in seconds that has passed since the last time the unit was resting
	 */
	private double getRestingTime() {
		return this.restingTime;
	}
	
	/**
	 * 
	 * @param restingTime
	 * 			Time in seconds
	 * @pre	 The given restingTime is a non-negative double and does not equal zero
	 * 		 | restingTime > 0
	 * @post The time in seconds that has passed since the last time the unit was resting equals 
	 * 		 the given time
	 * 		 | new.getRestingTime() == restingTime
	 */
	private void setRestingTime(double restingTime) {
		this.restingTime = Math.max(restingTime, 0);
		this.checkResting();
	}
	
	
	
	
	/**
	 * 
	 * @return Returns the time in seconds a unit has been sprinting
	 */
	private double getSprintingTime() {
		return this.sprintingTime;
	}
	
	
	/**
	 * @pre	 The given activityTime is greater than zero 
	 * 		 | activityTime > 0
	 * @param activityTime
	 * 			The time in seconds 
	 * @post The time in seconds a unit has been sprinting equals the given time
	 * 		 | new.getSprintingTime() == activityTime
	 */
	private void setSprintingTime(double activityTime) {
		if (this.isSprinting())
			this.sprintingTime = Math.max(activityTime, 0);
	}
	
	/**
	 * 
	 * @param time
	 */
	private void setFightTime(double time) {
		this.fightTime = Math.max(time, 0);
	}
	
	/**
	 * 
	 * @return
	 */
	private double getFightTime() {
		return this.fightTime;
	}
	
	/**
	 * 
	 * @param time
	 */
	private void setWorkingTime(double time) {
		this.workingTime = Math.max(time, 0);
	}
	
	/**
	 * 
	 * @return
	 */
	private double getWorkingTime() {
		return this.workingTime;
	}
	
	/**
	 * 
	 * @param time
	 */
	private void setNotRestTime(double time) {
		this.notRestTime = Math.max(time, 0);
	}
	
	/**
	 * 
	 * @return
	 */
	private double getNotRestTime() {
		return this.notRestTime;
	}
	
	private void workAfterMoving(boolean value) {
		this.workAfterMoving = value;
	}
	
	private boolean shouldWorkAfterMoving() {
		return this.workAfterMoving;
	}
	
	/**
	 * 
	 * @param opponent
	 */
	private void setOpponent(Unit opponent) {
		this.opponent = opponent;
	}
	
	/**
	 * 
	 * @return
	 */
	private Unit getOpponent() {
		return this.opponent;
	}
	

	public Set<int[]> getDirectlyAdjacentCubes() {
		
		int X = this.getCubeInt()[0];
		int Y = this.getCubeInt()[1];
		int Z = this.getCubeInt()[2];
		
		int[] pos1 = {X+1,Y,Z};
		int[] pos2 = {X-1,Y,Z};
		int[] pos3 = {X,Y+1,Z};
		int[] pos4 = {X,Y-1,Z};
		int[] pos5 = {X,Y,Z+1};
		int[] pos6 = {X,Y,Z-1};
		
		Set<int[]> cubes = new HashSet<int[]>(Arrays.asList(pos1, pos2, pos3, pos4, pos5, pos6));
		
		return cubes;
	}
	
	public ArrayList<int[]> getAdjacentCubes() {
		return this.getAdjacentCubes(this.getCubeInt());
	}
	
	public ArrayList<int[]> getAdjacentCubes(int[] position) {
		ArrayList<int[]> adjacentCubes = new ArrayList<>();
		
		int X = position[0];
		int Y = position[1];
		int Z = position[2];
		
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				for (int z = -1; z <= 1; z++) {
					if (!(x == 0 && y == 0 && z == 0)) {
						int[] adjacentPosition = {X + x, Y + y, Z + z};
						if (isPositionInWorld(adjacentPosition))
							adjacentCubes.add(adjacentPosition);

					}
				}
			}
		}
		
		return adjacentCubes;
	}


	
	
	
	private String name;
	private int strength, agility, toughness, weight;
	private int stamina, hitpoints;
	private double orientation;
	private double speed;
	private boolean sprint;
	private boolean working;
	private double restingTime, sprintingTime, fightTime, workingTime, notRestTime;
	private boolean resting;
	private boolean attacked;
	private boolean enableDefaultBehaviour;
	private boolean attacking;
	private boolean firstRest = true;
	private Unit opponent;
	private final Vector TARGET = new Vector(0,0,0);
	private final Vector POS = new Vector(0,0,0);
	private final Vector finalTARGET = new Vector(0,0,0);
	private int experiencePoints;
	private Faction faction;
	private boolean  isTerminated;
	private Boulder carriedBoulder;
	private Log carriedLog;
	private final double ZSpeed = -3.0;
	private boolean falling;
	private boolean workAfterMoving;




}
	




