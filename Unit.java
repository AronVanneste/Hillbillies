package hillbillies.model;


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
	 * @throws IllegalArgumentException
	 * 		If the name does not start with an upper case letter, doesn't have a length of at least two characters
	 * 		or has a character other than letters or quotes
	 * 		| ((!Character.isUpperCase(name.charAt(0))) | (name.length() < 2) | (!name.matches("[a-zA-Z\\s\'\"]+")))
	 * @throws IllegalArgumentException
	 * 		 If one of given X, Y or Z is negative or larger than 50.
	 * 		| !isValidPosition(X, Y, Z)
	 */

	public Unit(String name, int[] initialPosition, int weight,
			int agility,int strength,  int toughness, boolean enableDefaultBehaviour) throws IllegalArgumentException {
		
		int X = initialPosition[0];
		int Y = initialPosition[1];
		int Z = initialPosition[2];

		
		if (!this.isValidPosition(X,Y,Z))
			throw new IllegalArgumentException();
		else 
			this.setPosition(X, Y, Z);
		
		if (!this.isValidName(name))
				throw new IllegalArgumentException();
		else
			this.setName(name);
		
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

		
	}

	
	/**
	 * 
	 * @param name
	 * 			The name of the unit
	 * @post The name of the unit equals the given name
	 * 		| new.getName() == name
	 * @throws IllegalArgumentException
	 * 		If the name does not start with an upper case letter, doesn't have a length of at least two characters
	 * 		or has a character other than letters or quotes
	 * 		| ((!Character.isUpperCase(name.charAt(0))) | (name.length() < 2) | (!name.matches("[a-zA-Z\\s\'\"]+")))
	 */
	public void setName(String name) throws IllegalArgumentException {
		
		if (!this.isValidName(name))
			throw new IllegalArgumentException();
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
	 * @post The double at index 0 of the list POS equals the given X and is the x-axis position of the center of the unit
	 * 		 The double at index 1 of the list POS equals the given Y and is the y-axis position of the center of the unit
	 * 		 The double at index 2 of the list POS equals the given Z and is the z-axis position of the center of the unit
	 * 		| new.POS.get(0) == X
	 * 		| new.POS.get(1) == Y
	 * 		| new.POS.get(2) == Z 
	 * @throws IllegalArgumentException
	 * 		 If one of given X, Y or Z is negative or larger than 50.
	 * 		| (!isValidPosition(X,Y,Z))
	 * 
	 */
	
	public void setPosition(double X, double Y, double Z) throws IllegalArgumentException {
		
		if (!isValidPosition(X,Y,Z))
			throw new IllegalArgumentException();
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
	 * 
	 * @return Return a list containing the x-axis, y-axis and z-axis position of the cube in which the unit is located. 
	 * 		   These are the coordinates of the top left corner of this cube.
	 */
	public Vector getCube() {
		
		double XCube = Math.floor(this.getPosition().getX());
		double YCube = Math.floor(this.getPosition().getY());
		double ZCube = Math.floor(this.getPosition().getZ());
		Vector CUBE = new Vector(XCube, YCube, ZCube);

		return CUBE;
	
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
	 * @post The weight of the unit equals the absolute value of the given weight minus MIN modulo 75 plus MIN
	 * 		 in which MIN equals the strength of the unit plus the agility of the unit divided by two.
	 * 		| new.getWeight() == (Math.abs(weight) - (this.getStrength() + this.getAgility()/2) % 75 
	 * 			+ (this.getStrength() + this.getAgility())/2
	 */
	public void setWeight(int weight) {
		
		int MIN = (this.getStrength() + this.getAgility()) / 2;
		this.weight = (Math.abs(weight) - MIN) % (100 - MIN) + MIN;

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
	 * 		| (stamina >=) 0 && (stamina <= Math.ceil(2 * this.getWeight() * getToughness() / 100.0))) 
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
	 * @param hitpoints
	 * 			The hitpoints of the unit
	 * @pre The hitpoints of the unit is not negative and is smaller than the weight of the unit multiplied by the toughness of the 
	 * 		unit divided by 50 rounded up to an integer
	 * 		| (hitpoints >=) 0 && (hitpoints <= Math.ceil(2 * this.getWeight() * getToughness() / 100.0))) 
	 * @post The hitpoints of the unit equals the given hitpoints
	 * 		| new.getHitpoints() = hitpoints
	 */
	public void setHitpoints(int hitpoints) {
		assert (this.isValidHitpoints(hitpoints));
		this.hitpoints = hitpoints;

	}
	
	/**
	 * 
	 * @return Return the hitpoints of the unit
	 */
	public int getHitpoints() {
		return this.hitpoints;
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
	public void setOrientation(double orientation) {
		
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
	 * 		| new.TARGET.get(0) == XTarget
	 * 		| new.TARGET.get(1) == YTarget
	 * 		| new.TARGET.get(2) == ZTarget
	 * @throws IllegalArgumentException
	 * 		If the given target (XTarget, YTarget, ZTarget) is not a valid position
	 * 		| (!isValidPosition(XTarget, YTarget, ZTarget)
	 */
	public void setTarget(double XTarget, double YTarget, double ZTarget) throws IllegalArgumentException {

		if (!isValidPosition(XTarget, YTarget, ZTarget))
			throw new IllegalArgumentException();
		
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
	public Vector getTarget() {
		return this.TARGET;
	}
	
	
	/**
	 * 
	 * @return Returns the current speed of the unit
	 */
	public double getSpeed() {
		return this.speed;
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
	 * @param working
	 * 			The working status of the unit
	 * @post The unit's working status equals the given boolean
	 * 		| new.isWorking() == working
	 */
	private void setWorking(boolean working) {
		this.working = working;
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
	 * @return Returns the maximal hitpoints the unit can have
	 */
	public int getMaxHitpoints() {
		return (int) Math.ceil(2 * this.getWeight() * this.getToughness() / 100.0);
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
	 * @param X
	 * 			The given x-axis position
	 * @param Y
	 * 			The given y-axis position
	 * @param Z
	 * 			The given z-axis position
	 * @return	Returns true if X, Y and Z form a valid position. This is when X, Y and Z
	 * 			are a non-negative value and not larger than 50
	 * 			| if ((X < 0) | (X > 50) | (Y < 0) |(Y > 50) | (Z < 0) | (Z > 50))
	 * 			| 	then false
	 * 			| else
	 * 			| 	then true

	 */

	private boolean isValidPosition(double X, double Y, double Z) {
		if ((X >= 0.0) && (X <= 50.0) && (Y >= 0.0) && (Y <= 50.0) && (Z >= 0.0) && (Z <= 50.0))
			return true;
		else
			return false;

		
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
		
		if ((!Character.isUpperCase(name.charAt(0))) | (name.length() < 2) | (!name.matches("[a-zA-Z\\s\'\"]+")))
			return false;
		else
			return true;

	}
	
	/**
	 * 
	 * @param weight
	 * 			The weight of the unit
	 * @return Returns true if the given weight is valid, else false. The weight of a unit is valid
	 * 		   if it's bigger than or equal to the units strength + the units agility divided by two.
	 */
	private boolean isValidWeight(double weight) {
		return weight >= (this.getAgility()+this.getStrength())/2;
	}
	/**
	 * 
	 * @param hitpoints
	 * 			The hitpoints of the unit
	 * @return Returns true if the given hitpoints are valid, else false. The hitpoints are valid
	 * 			if they're nonnegative and smaller than MaxHitpoints
	 */
	private boolean isValidHitpoints(double hitpoints) {
		return (hitpoints >= 0 ) && (hitpoints <= this.getMaxHitpoints());
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
	 * @post The status of the boolean sprint of the unit equals false
	 * 		| new.isSprinting() == false
	 * @post The time the unit has been sprinting equals zero
	 * 		| this.getSprintingTime == 0
	 */
	// CHANGED
	public void stopSprinting() {
		this.sprint = false;
		this.setSprintingTime(0);
	}
	
	
	/**
	 * @post The unit's speed equals his previous speed multiplied by two
	 * 		 | new.getSpeed() == 2 * this.getSpeed()
	 */
	// CHANGED
	public void startSprinting() {
		assert (this.getStamina() >= 1);
		this.sprint = true;
	}
	
	/**
	 * 
	 * @return Returns the value (true or false) of the boolean sprint of the unit
	 */
	public boolean isSprinting() {
		return this.sprint;
	}
	

	
	/**
	 * 
	 * @param X
	 * 			The distance the unit has to travel in the direction of the x-axis
	 * @param Y
	 * 			The distance the unit has to travel in the direction of the y-axis
	 * @param Z
	 * 			The distance the unit has to travel in the direction of the z-axis
	 * @post The new position of the unit equals his previous position plus (X, Y, Z)
	 * 		 if the unit was not interrupted during his activity
	 * 		| if (!this.isStopped())
	 * 		|	then new.getPosition() == this.getPosition() + [X, Y, Z]
	 * @post If the unit was interrupted, the unit's status of notReachedTarget changes to true
	 * 		 to indicate the unit's moveToAdjacent activity was aborted
	 * 		| if (this.isStopped())
	 * 		|	then new.getNotReachedTarget() == true
	 * @throws IllegalArgumentException
	 * 			If the X, Y or Z value is not -1, 0 or 1
	 * 		| (((Math.abs(X) != 1) && (X != 0)) | ((Math.abs(Y) != 1) && (Y != 0)) 
	 * 		|	| ((Math.abs(Z) != 1) && (Z != 0)))
	 */
	public void moveToAdjacent(int X, int Y, int Z) throws IllegalArgumentException {
		
		if (((Math.abs(X) != 1) && (X != 0)) | ((Math.abs(Y) != 1) && (Y != 0)) 
				| ((Math.abs(Z) != 1) && (Z != 0)))
			throw new IllegalArgumentException();
		
		else {
			this.setTarget(this.getPosition().getX() + ((double) (X)), 
					this.getPosition().getY() + ((double) (Y)), 
					this.getPosition().getZ() + ((double) (Z)));
		}
		
	}
	
	/**
	 * Indicates whether or not another priority activity occurs
	 * @return Returns true if the unit's status of isAttacked() or isResting() equals true, else false
	 * 
	 */
	private boolean isStopped() {
		
		if (this.isAttacked() | this.isResting())
			return true;
		return false;
		
	}
	
	/**
	 * 
	 * @param seconds
	 * 			The seconds 
	 * @throws IllegalArgumentException
	 * 		| (seconds <= 0) | (seconds >= 0.2)
	 * @post 
	 * 		| new.setPosition(this.getPosition().get(0) + this.getSpeedVector().get(0) * seconds,
	 * 		|	this.getPosition().get(1) + this.getSpeedVector().get(1) * seconds,
	 * 		|	this.getPosition().get(2) + this.getSpeedVector().get(2) * seconds);

	 */
	
	// CHANGED
	public void advanceTime(double seconds) throws IllegalArgumentException {
		
		
		if ((seconds <= 0) | (seconds > 0.2))
			throw new IllegalArgumentException();
		else {
			
			if (this.isAttacking()) {
				if (this.getAttackTime() < 1) {
					this.setAttackTime(this.getAttackTime() + seconds);
					this.fightUnitOrientation();
				} else {
					this.setAttacking(false);
					this.setAttackTime(0);
				}
			} else if (this.isAttacked()) {
				if (this.getAttackedTime() < 1) {
					this.setAttackedTime(this.getAttackedTime() + seconds);
					this.fightUnitOrientation();
				} else {
					this.setAttacked(false);
					this.setAttackedTime(0);
					this.defend();
				}
			} else { 
				} if (this.getDefaultBehavior()) {
					if (!this.getStartedDefaultBehavior())
						this.startDefaultBehavior();
				} else if (!this.getDefaultBehavior()) {
					if (this.getChangedDefaultBehavior())
						this.stopDefaultBehavior();
				} 
				
				if (this.isResting()) {
					this.setRestingTime(this.getRestingTime() + seconds);
					this.changeResting();
				} else if (!this.isResting()) {
						this.setNotRestTime(this.getNotRestTime() + seconds);
						this.checkResting();
				
				
					if (this.isWorking()) {
						if (this.getWorkingTime() >= this.getTimeToWork())
							this.setWorking(false);
						else
							this.setWorkingTime(this.getWorkingTime() + seconds);
					} else {
						if (!this.getPosition().equals(this.getFinalTarget())) {
							if (!this.getPosition().equals(this.getTarget())) {
								this.setSpeed(this.determineSpeed());
								this.changePosition(seconds);
							} else {
								this.moveToTarget();
								this.setSpeed(this.determineSpeed());
								this.changePosition(seconds);
							}
							if (this.isSprinting()) {
								this.setSprintingTime(this.getSprintingTime() + seconds);
								this.controlSprinting();
							}
						} else
							this.setSpeed(0);
					}	
				}
			}
		
	}
	
	// NEW
	private void controlSprinting() {
		
		assert (this.isSprinting());
		if (this.getSprintingTime() % 0.1 == 0)
			this.setStamina(this.getStamina() - 1);
		if (this.getStamina() < 1)
			this.stopSprinting();
	}
	
	// NEW
	private void changePosition(double time) {
		
		double X = this.getPosition().getX();
		double Y = this.getPosition().getY();
		double Z = this.getPosition().getZ();
		
		this.setPosition(this.getPosition().getX() + this.getSpeedVector().getX() * time,
				this.getPosition().getY() + this.getSpeedVector().getY() * time,
				this.getPosition().getZ() + this.getSpeedVector().getZ() * time);
		this.updateOrientation();
		
		if (this.getPosition().getX() - X > X - this.getTarget().getX())
			this.setPosition(this.getTarget().getX(), this.getPosition().getY(), this.getPosition().getZ());
		if (this.getPosition().getY() - Y > Y - this.getTarget().getY())
			this.setPosition(this.getPosition().getX(), this.getTarget().getY(), this.getPosition().getZ());
		if (this.getPosition().getZ() - Z > Z - this.getTarget().getZ())
			this.setPosition(this.getPosition().getX(), this.getPosition().getY(), this.getTarget().getZ());



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
	 * @param X
	 * 			The x-axis position of the target cube
	 * @param Y
	 * 			The y-axis position of the target cube
	 * @param Z
	 * 			The z-axis position of the target cube
	 * @post If the unit was not interrupted, his new position equals the given target (X, Y, Z)µ
	 * 		 | if (!this.isStopped())
	 * 		 | 	new.getPosition() == [X, Y, Z]
	 * @post If the unit was interrupted, his new position equals the position it was when the 
	 * 		 interruption occurred
	 */
	public void moveTo2(int X, int Y, int Z) {
		
		
		Vector Target = new Vector(((double) X), ((double) Y), ((double) Z));
		
		int x, y, z;
		while (!this.getPosition().equals(Target)) {
			//if (this.getPosition().equals(this.getTarget())) {
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
			//}
		}
		
		
	}
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
	
	public void moveTo(int X, int Y, int Z) {
		
		this.setFinalTarget(((double) X), ((double) Y), ((double) Z));
		
	}
	
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
	
	public void work() {

		this.setWorking(true);
		this.setSpeed(0);		
		this.setResting(false);
		
		

	}	
	
	private double getTimeToWork() {
		return (double) (500 / this.getStrength());
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
		this.setSpeed(0);
		this.setAttacked(false);
		this.setDefaultBehavior(false);
		this.setDefending(false);
		
	}
	
	private void attacked() {
		
		this.setAttacking(false);
		this.setWorking(false);
		this.setResting(false);
		this.setSpeed(0);
		this.setAttacked(true);
		this.setDefaultBehavior(false);
		this.setDefending(false);

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
	public void defend() {
		
		this.setResting(false);
		this.setSpeed(0);
		this.setWorking(false);
		this.setAttacked(false);
		
		double PDodge = 0.20 * (this.getAgility() / this.getOpponent().getAgility());
		double PBlock = 0.25 * (this.getStrength() + this.getAgility()) / 
				(this.getOpponent().getStrength() + this.getOpponent().getAgility());
		
		if (Math.random() <= PDodge)
			this.moveToRandomAdjacentPosition();		
		else if (Math.random() > PBlock)
			this.setHitpoints(Math.max(this.getHitpoints() - this.getOpponent().getStrength() / 10, 0));
		
		
		
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
	 */
	public void fight(Unit attacker, Unit defender) {
		
		assert attacker.isValidAttack(defender);
		attacker.setOpponent(defender);
		defender.setOpponent(attacker);
		attacker.attack();
		defender.attacked();
		
	}
	
	/**
	 * The unit moves to a random adjacent position when it succeeds in dodging the attack by another unit
	 * @post The unit's new position is a randomly chosen position in an adjacent cube
	 * 		 | new.getPosition() = [this.getPosition().get(0) + (Math.random() - 0.5) * 2,
	 * 		 |							this.getPosition.get(1) + (Math.random() - 0.5) * 2,
	 * 		 |							this.getPosition.get(2) + (Math.random() - 0.5) * 2]
	 */
	private void moveToRandomAdjacentPosition() {
		
		boolean newPos = false; 
		boolean catched = false;
		
		while (!newPos) {
			try {
				this.setPosition(this.getPosition().getX() + (Math.random() - 0.5) * 2, 
						this.getPosition().getY() + (Math.random() - 0.5) * 2,
						this.getPosition().getZ() + (Math.random() - 0.5) * 2);
			} catch (IllegalArgumentException e) {
				catched = true;
			} 
			if (!catched) 
				newPos = true;
			catched = false;
			
		}

	}
	
	

	/**
	 * 
	 * @param defender
	 * 			The defending unit
	 * @return Returns true if the cube the unit's in and the cube the defending unit's in are 
	 * 		   neighboring cubes, else false
	 * 
	 */
	
	private boolean isValidAttack(Unit defender) {
		
		if ((Math.abs(this.getCube().getX()- defender.getCube().getX()) <= 1) &&
				(Math.abs(this.getCube().getY()- defender.getCube().getY()) <= 1) &&
				(Math.abs(this.getCube().getZ()- defender.getCube().getZ()) <= 1))
				return true;
		else
			return false;
		
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
		assert (restingTime > 0);
		this.restingTime = restingTime;
		this.checkResting();
	}
	
	
	/**
	 * Checks whether the unit should start resting,
	 * this is if it hasn't been resting for at least 180 seconds
	 * @post If the time the unit hasn't been resting is greater than or equal to 180,
	 * 		 this unit starts resting
	 * 		 | if (this.getRestingTime() >= 180)
	 * 		 |	then new.isResting() == true
	 */
	private void checkResting() {
		if (this.getRestingTime() >= 180)
			this.resting();
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
		assert (activityTime > 0);
		if (this.isSprinting())
			this.sprintingTime = activityTime;
	}
	/**
	 * 
	 * @pre The hitpoints of the unit as well as the stamina points of the unit must not have reached its maximum
	 *     |(this.getHitpoints() != this.getMaxHitPoints()) | (this.getStamina() != this.getMaxStamina())
	 * @post After the unit was resting, its restingTime is set to zero
	 *	   | new.getRestingTime() == 0
	 * @post If a previous activity was interrupted because the unit needed to rest, it resumes this activity afterwards
	 * 	   | if (this.getNotReachedTarget())
	 * 	   |	then new.getSpeed() > 0
	 * @post If no previous activity was interrupted by resting, the unit's speed equals zero
	 * 	   | if (!this.getNotReachedTarget())
	 * 	   |	then new.getSpeed() == 0  
	 *
	 *  	
	 */
	public void resting() {
		
		this.setResting(true);
		this.setAttacked(false);
		this.setAttacking(false);
		this.stopSprinting();
		this.setDefending(false);
		this.setWorking(false);
		this.setSpeed(0);

		
//		assert ((this.getHitpoints() != this.getMaxHitpoints()) | (this.getStamina() != this.getMaxStamina()));
//
//		this.setResting(true);
//		
//		double ricoHitpoints = this.getToughness() / 200;
//		double ricoStamina = this.getToughness() / 100;
//		
//		boolean firstHitpoint = false;
//		boolean satisfied = false;
//		
//		this.setSpeed(0);
//		
//		double minTimeH = 0.2 / ricoHitpoints;
//		double minTimeS = 0.2 / ricoStamina;
//		
//		
//		long timeNow = System.currentTimeMillis();
//		
//		while ((!this.isAttacked()) && (!firstHitpoint) && (this.getHitpoints() < this.getMaxHitpoints()))
//			if (System.currentTimeMillis() == timeNow + (long) (minTimeH * 1000))
//				this.setHitpoints(this.getHitpoints() + 1);
//				firstHitpoint = true;
//		
//		timeNow = System.currentTimeMillis();
//		while ((!this.isResting()) && (!satisfied)) {
//			
//			if (this.getHitpoints() < this.getMaxHitpoints())
//				if ((System.currentTimeMillis() - timeNow) % (long) (minTimeH * 1000) == 0)
//					this.setHitpoints(this.getHitpoints() + 1);
//			else if (this.getStamina() < this.getMaxStamina())
//				if ((System.currentTimeMillis() - timeNow) % (long) (minTimeS * 1000) == 0)
//					this.setStamina(this.getStamina() + 1);
//			else
//				satisfied = true;
//			
//		if (this.getNotReachedTarget())
//			this.moveTo(this.getTarget().get(0), this.getTarget().get(1), this.getTarget().get(2));
//	
//				
//
//		}
//				
//		
//		this.setRestingTime(0);
		
	}
	
	private void changeResting() {
		
		double ricoHitpoints = this.getToughness() / 200;
		double ricoStamina = this.getToughness() / 100;
		
		if (this.getHitpoints() < this.getMaxHitpoints()) {
			if (this.getRestingTime() % ricoHitpoints == 0) {
				this.setHitpoints(this.getHitpoints() + 1);
				if (!this.getFirstRest())
					this.setFirstRest(true);
			}
		} else if (this.getStamina() < this.getMaxStamina()) {
			if (this.getRestingTime() % ricoStamina == 0)
				this.setStamina(this.getStamina() + 1);
		}
	}
	
	private boolean usefulResting() {
		return ((this.getHitpoints() != this.getMaxHitpoints()) | (this.getStamina() != this.getMaxStamina()));

	}
	
	/**
	 * @post The unit's resting status equals the given boolean
	 * 		 | new.isResting() == restingStatus
	 */
	private void setResting(boolean restingStatus) {
		
		if (!restingStatus && (!this.isAttacked() | !this.isAttacking()) && 
				!this.getFirstRest())
			this.resting = true;
		
		else
			if (!restingStatus) {
				this.setFirstRest(false);
				this.setRestingTime(0);
			} else
				this.setNotRestTime(0);
		
			this.resting = restingStatus;
		
	}
	
	/**
	 * 
	 * @return Returns the status of the resting boolean of the unit
	 */
	public boolean isResting() {
		return this.resting;
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
		if (P <= 0.3333333333333)
			this.moveToRandomPosition(); 
		else if (P <= 0.666666666666)
			this.work();
		else
			this.resting();
		
		this.setStartedDefaultBehavior(true);
		
				
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
		
		this.moveTo(X, Y, Z);
		
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
		this.setStartedDefaultBehavior(false);

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
	 * @param behavior
	 * 			A boolean indicating whether the unit is allowed 
	 * 			to behave in a random way or not
	 * 
	 * @post The enableDefaultBehavior status of the unit equals the given boolean behavior
	 * 		| new.getDefaultBehavior() = behavior

	 */
	public void setDefaultBehavior(boolean behavior) {
		
		if (this.getDefaultBehavior() != behavior)
			this.setChangedDefaultBehavior(true);
		else
			this.setChangedDefaultBehavior(false);
		
		this.enableDefaultBehaviour = behavior;
		this.setStartedDefaultBehavior(false);
		
		
		
	
	}
	/**
	 * 
	 * @return Returns the enableDefaultBehavior status of the unit
	 */
	public boolean getDefaultBehavior() {
		
		return this.enableDefaultBehaviour;
	}
	
	private void setAttackTime(double time) {
		this.attackTime = time;
	}
	
	private double getAttackTime() {
		return this.attackTime;
	}
	
	private void setDefendTime(double time) {
		this.defendingTime = time;
	}
	
	private double getDefendTime() {
		return this.defendingTime;
	}
	private void setWorkingTime(double time) {
		this.workingTime = time;
	}
	
	private double getWorkingTime() {
		return this.workingTime;
	}
	
	public boolean isDefending() {
		return this.defending;
	}
	
	private void setDefending(boolean defending) {
		this.defending = defending;
	}
	
	private void setAttackerAgility(int agility) {
		this.attAgility = agility;
	}
	
	private void setAttackerStrength(int strength) {
		this.attStrength = strength;
	}
	
	private int getAttackerAgility() {
		return this.attAgility;
	}
	
	private int getAttackerStrength() {
		return this.attStrength;
	}

	private void setFirstRest(boolean trueorfalse) {
		this.firstRest = trueorfalse;
	}
	
	private boolean getFirstRest() {
		return this.firstRest;
	}
	
	private void setNotRestTime(double time) {
		this.notRestTime = time;
	}
	
	private double getNotRestTime() {
		return this.notRestTime;
	}
	
	private void setOpponent(Unit opponent) {
		this.opponent = opponent;
	}
	
	private Unit getOpponent() {
		return this.opponent;
	}
	
	private double getAttackedTime() {
		return this.attackedTime;
	}
	
	private void setAttackedTime(double time) {
		this.attackedTime = time;
	}
	
	private boolean getStartedDefaultBehavior() {
		return this.startedDefaultBehavior;
	}
	
	private void setStartedDefaultBehavior(boolean bool) {
		this.startedDefaultBehavior = bool;
	}
	
	private boolean getChangedDefaultBehavior() {
		return this.changedBehavior;
	}
	
	private void setChangedDefaultBehavior(boolean bool) {
		this.changedBehavior = bool;
	}
	
	private void setFinalTarget(double X, double Y, double Z) {
		
		if (!this.isValidPosition(X, Y, Z))
			throw new IllegalArgumentException();
		else {
			this.finalTARGET.setX(X);
			this.finalTARGET.setY(Y);
			this.finalTARGET.setZ(Z);
		}
	}
	
	private Vector getFinalTarget() {
		return this.finalTARGET;
	}

	
	
	
	
	
	
	
	private String name;
	private int strength, agility, toughness, weight;
	private int stamina, hitpoints;
	private double orientation;
	private double speed;
	private boolean sprint;
	private boolean working;
	private double restingTime = 0;
	private double sprintingTime = 0;
	private boolean resting;
	private boolean attacked;
	private boolean enableDefaultBehaviour;
	private boolean attacking;
	private double attackTime;
	private double defendingTime;
	private double workingTime;
	private boolean defending;
	private int attAgility;
	private int attStrength;
	private boolean firstRest;
	private double notRestTime = 0;
	private Unit opponent;
	private final Vector TARGET = new Vector(0,0,0);
	private final Vector POS = new Vector(0,0,0);
	private double attackedTime;
	private boolean startedDefaultBehavior;
	private boolean changedBehavior;
	private final Vector finalTARGET = new Vector(0,0,0);



}
	





