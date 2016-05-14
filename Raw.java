package hillbillies.model;

public abstract class Raw implements ITerminate {
 
		
		/**
		 * The initialization of a raw
		 * 
		 * @param world
		 * 		The world in which the raw is created
		 * @param position
		 * 		An array of doubles, the position where the raw is created
		 * 
		 * @post The position of the raw will equal the given position
		 * 
		 * @post The world of the raw will equal the given world
		 * 
		 * @post The weight of the raw will be a random number between 10 and 40
		 * 
		 * @post The world will contain the raw in its raw list
		 * 
		 * @throws IllegalPositionException
		 * 		Throws an IllegalPositionException if the given position is not valid
		 * 
		 * @throws IllegalWorldException
		 * 		Throws an IllegalWorldException if the given world is null
		 * 		
		 */
		public Raw(World world, double[] position) throws IllegalPositionException, IllegalWorldException {

			
			try { 
				this.setWorld(world);
			} catch (IllegalWorldException invalidWorld) {
				throw invalidWorld;
			}
			
			try {
				this.setPosition(position);
			} catch (IllegalPositionException invalidPosition) {
				throw invalidPosition;
			}
		
		
			
			this.weight = ((int) Math.round(Math.random() * 40 + 10));

		}
		
		/**
		 * The initialization of a raw
		 * 
		 * @param world
		 * 		The world in which the raw is created
		 * @param position
		 * 		An array of integers, the position where the raw is created
		 * 
		 * @post The position of the raw will equal the given position
		 * 
		 * @post The world of the raw will equal the given world
		 * 
		 * @post The weight of the raw will be a random number between 10 and 40
		 * 
		 * @post The world will contain the raw in its raw list
		 * 
		 * @throws IllegalArgumentException
		 * 		Throws an IllegalArgumentException if the given position is not valid
		 * 
		 * @throws IllegalWorldException
		 * 		Throws an IllegalWorldException if the given world is null
		 * 		
		 */
		public Raw(World world, int[] position) throws IllegalPositionException, IllegalWorldException {

			
			try { 
				this.setWorld(world);
			} catch (IllegalWorldException invalidWorld) {
				throw invalidWorld;
			}

			try {
				this.setPosition(position);
			} catch (IllegalPositionException invalidPosition) {
				throw invalidPosition;
			}

			this.weight = ((int) Math.round(Math.random() * 40 + 10));
		}

		
		/**
		 * @param world
		 * 		The world in which the raw is created
		 * @post
		 * 		The world of the raw will be the given world
		 * @post
		 * 		The raw will be in the raw list of the world
		 *@throws IllegalWorldException
		 *		Throws an IllegalWorldException if the world is not valid
		 */
		private void setWorld(World world) throws IllegalWorldException {
			if (!isValidWorld(world))
				throw new IllegalWorldException("This world is not valid");
			this.world = world;
			world.addRaw(this);
		}
		
		/**
		 * @return Returns the world of the raw
		 */
		public World getWorld() {
			return this.world;
		}
		
		
		
		
		/**
		 * @return Returns true if the given position is within the range of the world of the raw else false
		 */
		private boolean isValidPosition(double[] position) {
			return this.getWorld().isValidPosition(position);
		}
		
		/**
		 * @return Returns true if the given position is within the range of the world of the raw else false
		 */
		private boolean isValidPosition(int[] position) {
			return this.getWorld().isValidPosition(position);
		}
		
		/**
		 * @return Returns whether or not the world is valid (i.e. not null)
		 */
		private boolean isValidWorld(World world) {
			return (world != null);
		}
		
		/**
		 * @return Returns whether or not the current position of the raw is passable
		 */
		private boolean isPassable() {
			return (this.getWorld().isPassable(this.getCube()));
		}
		
		/**
		 * @return Returns whether or not the raw is supported by a solid cube
		 */
		private boolean isSolidBelow() {
			return (this.getWorld().isSupported(this.getCube()));
		}
		
		/**
		 * @return Returns whether or not the raw is active (i.e. is without owner and not terminated)
		 */
		public boolean isActive() {
			return (this.getOwner() == null && !this.isTerminated());
		}
		
		/**
		 * @return Returns true if the raw is not supported
		 */
		private boolean shouldFall() {
			return !(this.isPassable() && this.isSolidBelow());
		}
		

		/**
		 * @return Returns the coordinates of the cube of the raw
		 */
		public int[] getCube() {
			
			int X = (int) Math.floor(this.getPosition()[0]);
			int Y = (int) Math.floor(this.getPosition()[1]);
			int Z = (int) Math.floor(this.getPosition()[2]);
			
			int[] cube = {X, Y, Z};
			return cube;
			
		}
		
		/**
		 * @param position
		 * 		The new position of the raw
		 * @post
		 * 		The position of the raw will be the given position
		 * @throws IllegalArgumentException
		 * 		Throws an IllegalArgumentException if the given position is invalid
		 * 		
		 */
		protected void setPosition(double[] position) throws IllegalPositionException {
			
			if (!isValidPosition(position))
				throw new IllegalPositionException("This is not a valid position");
			else
				this.position = position;
		}
		
		/**
		 * @param position
		 * 		The new position of the raw
		 * @post
		 * 		The position of the raw will be the given position
		 * @throws IllegalArgumentException
		 * 		Throws an IllegalArgumentException if the given position is invalid
		 */
		protected void setPosition(int[] position) throws IllegalPositionException {
			
			if (!isValidPosition(position))
				throw new IllegalPositionException("This is not a valid position");
			else {
				double X = ((double) position[0]);
				double Y = ((double) position[1]);
				double Z = ((double) position[2]);
				double[] pos = {X, Y, Z};
				this.position = pos; 
			}
		}
		
		/**
		 * @return Returns the position of the raw as a list of doubles
		 */
		public double[] getPosition() {
			return this.position;
		}
		

		// GET WEIGHT
		/**
		 * @return Returns the weight of the raw
		 */
		public int getWeight() {
			return this.weight;
		}
		
		/**
		 * @param owner
		 * 		The new owner of the raw
		 * @post
		 * 		The owner of the raw will be the given owner
		 */
		protected void setOwner(Unit owner) throws IllegalStateException {
			if (this.isTerminated())
				throw new IllegalStateException("Terminated");
			this.owner = owner;
			this.changeInWorld();
		}
		
		/**
		 * @return Returns the owner of the raw
		 */
		public Unit getOwner() {
			return this.owner;
		}
		
		
		
		
		public void advanceTime(double time) throws IllegalArgumentException, IllegalStateException {
			
			if (this.isTerminated())
				throw new IllegalStateException("Terminated");
			if ((time <= 0) | (time > 0.2))
				throw new IllegalArgumentException("Given time is not valid");
			else {
				if (this.isActive()) {
					if (this.shouldFall())
						this.changePosition(time);
				}	
			}
			
		}
		
		/**
		 *@param time
		 *		The given time (seconds)
		 *@post
		 *		The new position of the raw will be (time * ZSpeed) lower than the previous one
		 */
		private void changePosition(double time) {
			
			double[] position = {this.getPosition()[0], this.getPosition()[1],
					this.getPosition()[2] + ZSpeed * time};
			this.setPosition(position);
		}
		
		/**
		 * The owner of the raw will be removed and vice versa
		 * 
		 * @post
		 * 		The raw will be without owner
		 * @post 		
		 * 		The owner (unit) will not longer carry this raw
		 */
		protected void removeOwner() {
			try {
				this.getOwner().removeRaw();
				this.setOwner(null);
			} catch (NullPointerException e) {};
		}
		
		/**
		 * Applies changes in the world or in the raws status to the boulder
		 * @post
		 * 		If the world of the raw is terminated, the raw will terminate as well
		 * 		If the raw is active, the raw will be added to the raw list of the world
		 * 		If the raw is inactive, the raw will be removed from the raw list of the world
		 */
		private void changeInWorld() {
			if (this.getWorld().isTerminated())
				this.terminate();
			else {
				if (this.isActive())
					this.getWorld().addRaw(this);
				else
					this.getWorld().removeRaw(this);
			}
		}
		
		/**
		 * Terminates the raw, cuts the links attached to it's owner and world
		 * 
		 * @post
		 * 		The raw is terminated. It has no owner and is no longer part of a world
		 */
		public void terminate() {
			if (!this.isTerminated()) {
				this.removeOwner();
				this.removeWorld();
				this.isTerminated = true;
			}
		}
		
		/**
		 * 
		 * @return Returns whether or not the raw is terminated
		 */
		public boolean isTerminated() {
			return this.isTerminated;
		}
		
		/**
		 * @return Returns whether or not the raws owner is null
		 */
		public boolean isCarriedByUnit() {
			return this.getOwner() != null;
		}
		
		/**
		 * The raw will be removed from the world
		 * 
		 * @post
		 * 		The raw is removed from the raw list of its world
		 */
		private void removeWorld() {
			try {
				this.getWorld().removeRaw(this);
				this.setWorld(null);
			} catch (NullPointerException e) {};
		}
		
		
		

		private double[] position;
		private final int weight;
		private final double ZSpeed = -3.0;
		private World world;
		private Unit owner = null;
		private boolean isTerminated;

	}


