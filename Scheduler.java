package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Scheduler {
	


	/**
	 * Initialization of the scheduler
	 */
	public Scheduler() {
		
	}
	
	/**
	 * Initialization of the scheduler
	 * @param faction
	 * 			The faction this scheduler is part of
	 * @post The scheduler's faction equals the given faction
	 * @throws IllegalArgumentException
	 * 			Throws exception if the faction is invalid
	 */
	public Scheduler(Faction faction) throws IllegalArgumentException {
		try {
			this.setFaction(faction);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
	/**
	 * Adds a task to the scheduler
	 * 
	 * @post The task is added to the scheduler and the scheduler is added to the task
	 * 		
	 * @throws IllegalArgumentException
	 * 			throws exception if the given task is not valid
	 * @throws IllegalPositionException
	 * 			throws exception if the given task is to be performed at a cube which is not 
	 * 			part of the world this scheduler's faction belong to.
	 */
	public void addTask(T task) throws IllegalArgumentException, IllegalPositionException {
		if (!isValidArgument(task))
			throw new IllegalArgumentException("Is not a valid task");
		if (!isValidPosition(task.getCube()))
			throw new IllegalPositionException("Not a valid position");
		this.taskList.add(task);
		task.addScheduler(this);
	}
	/**
	 * 
	 * @return Returns a list with all tasks in the scheduler
	 */
	public List<T> getTasks() {
		return this.taskList;
	}
	
	/**
	 * Removes a given task form the scheduler
	 * 
	 * @param task
	 * 		The task that has to be removed
	 * 
	 */
	public void removeTask(T task) {
		this.getTasks().remove(task);
	}
	/**
	 *Replaces an old task by a new task
	 *
	 * @param newTask
	 * 		The new Task
	 * @param oldTask
	 * 		The task that's removed
	 */
	public void replaceTask(T newTask, T oldTask) {
		if (this.getTasks().contains(oldTask)) {
			try {
				this.removeTask(oldTask);
				addTask(newTask);
			} catch (IllegalArgumentException e) {};
		}
	}
	
	/**
	 * 
	 * @param t
	 * 			The object to be checked
	 * @return
	 * 		Returns true if the given object is valid (not null), else false
	 */
	public boolean isValidArgument(Object t) {
		return t != null;
	}
	
	/**
	 * Checks if the given cube is a part of this scheduler's world
	 * @param cube
	 * 			The given cube to be checked
	 * @return
	 * 		Return true if the given cube is in this scheduler's world or if the 
	 * 		scheduler is not part of a world, else false
	 */
	public boolean isValidPosition(int[] cube) {
		
		try {
			if (this.getFaction().getWorld().isPositionInWorld(cube))
				return true;
		} catch (NullPointerException n) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * @return Returns whether or not all tasks in a collection are part of the scheduler
	 */
	public boolean areAllPartOfScheduler(Collection<T> tasks) {
		
		if (tasks.size() == 0 | !isValidArgument(tasks))
			return true;
		
		for (T task: tasks) {
			if (!this.getTasks().contains(task))
				return false;
		}
		return true;
	}
	
	/**
	 *@return Returns the task in the scheduler that has the highest priority and is not yet assigned
	 *
	 *@throws	NoSuchElementException
	 *			throws NoSuchElementException if there is no highest priority task which is not
	 *			yet assigned
	 */
	public T getHighestPriorityTaskNotYetAssigned() throws NoSuchElementException {
		
		Iterator<T> iter = this.getAllTasksIterator();
		while (iter.hasNext()) {
			T task = iter.next(); 
			if (!task.isAssigned())
				return task;
		}
		
		throw new NoSuchElementException("No highest priority task not yet asssigned found");
	}
	
	/**
	 * Assigns the task with the highest priority and that's not yet assigned to the given unit
	 * 
	 * @param unit
	 * 		The unit to which a task should be assigned
	 * @throws NoSuchElementException
	 * 		Throws NoSuchElementException if there's no available task in the scheduler left
	 */
	protected void assignTaskToUnit(Unit unit) throws NoSuchElementException {
		
		try {
			T task = getHighestPriorityTaskNotYetAssigned();
			assignTaskToUnit(unit, task);
		} catch (NoSuchElementException exc) {
			throw exc;
		}
	}
	
	
	/**
	 * Assigns the task that's not yet assigned to the given unit
	 * 
	 * @param unit
	 * 		The unit to which a task is assigned
	 * @param task
	 * 		The task that has to be assigned
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the task is not part of the Scheduler
	 * @throws IllegalTaskException
	 * 		Throws IllegalTaskException  if the task is already assigned to a unit
	 * @throws IllegalUnitException
	 * 		Throws IllegalUnitException if the unit has a task assigned
	 * @throws IllegalFactionException
	 * 		Throws IllegalFactionException if the unit and scheduler are part of a different faction
	 */
	protected void assignTaskToUnit(Unit unit, T task) throws IllegalArgumentException, 
		IllegalUnitException, IllegalTaskException, IllegalFactionException {
		
		if (!this.getTasks().contains(task))
			throw new IllegalArgumentException("Task is not part of this scheduler");
		if (task.isAssigned())
			throw new IllegalTaskException("Task is already assigned to a unit");
		if (unit.hasTask())
			throw new IllegalUnitException("Unit has already a task assigned");
		if (unit.getFaction() != this.getFaction())
			throw new IllegalFactionException("Unit and scheduler not part of the same faction");
		task.setUnit(unit);
		unit.setTask(task);
	}
	
	
	/**
	 * 
	 * @return Returns an iterator with all tasks ordered by highest priority
	 */
	public Iterator<T> getAllTasksIterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				for (T t: getTasks()) {
					if (!passedT.contains(t)) {
						return true;
					}
				}
				return false;
					
			}

			@Override
			public T next() throws NoSuchElementException {
				currentElement = null;
				largestPriority = Integer.MIN_VALUE;
				if (!hasNext())
					throw new NoSuchElementException();
				for (T t: getTasks()) {
					if ((t.getPriority() > largestPriority) && !passedT.contains(t)) {
						currentElement = t;
						largestPriority = t.getPriority();
					}
				}
				passedT.add(currentElement);
				return currentElement;
			}
			private T currentElement = null;
			private int largestPriority = Integer.MIN_VALUE;
			private final List<T> passedT = new ArrayList<>();

		};
	}

	
	/**
	 * Returns all tasks having a priority higher than the given value
	 * 
	 * @param K
	 * 		 The minimum priority a task must have
	 * @return
	 * 		Returns all tasks with a higher priority than the given value
	 */
	public List<T> getAllTaskPriorityLargerThan(int K) {
		return getAllTasksSatisfyingCondition((T t) -> t.getPriority() > K);
	}
	
	/**
	 * Returns all tasks having a priority lower than the given value
	 * 
	 * @param K
	 * 		 The maximum priority a task may have
	 * @return
	 * 		Returns all tasks with a lower priority than the given value
	 */
	public List<T> getAllTaskPrioritySmallerThan(int K) {
		return getAllTasksSatisfyingCondition((T t) -> t.getPriority() < K);
	}
	
	
	/**
	 * Returns all tasks currently not executing
	 * 
	 * @return
	 * 		Returns all tasks currently not executing
	 */
	public List<T> getAllTaskCurrentlyNotExecuting() {
		return getAllTasksSatisfyingCondition((T t) -> !t.isAssigned());
	}
	
	/**
	 * Returns all tasks satisfying the given condition
	 * 
	 * @param condition
	 * 			The condition the tasks must satisfy
	 * @return
	 * 		Returns all tasks satisfying the given condition
	 */
	public List<T> getAllTasksSatisfyingCondition(Predicate<T> condition) {
		
		List<T> taskList = new ArrayList<>();
		this.getTasks().stream().filter(condition).forEach(taskList::add);
		return taskList;
			
	}
	
	/**
	 * Sets the given faction as the faction of this world
	 * 
	 * @param faction
	 * 			The given faction
	 * 
	 * @post The given faction equals the faction of this world
	 * 
	 * @post All tasks part of this world have a valid position
	 * 
	 * @throws IllegalArgumentException
	 * 		throw exception if this scheduler is already part of another faction or the 
	 * 		given faction has another faction
	 */
	protected void setFaction(Faction faction) throws IllegalArgumentException {
		if (this.getFaction() != null && this.getFaction() != faction)
			throw new IllegalArgumentException("Scheduler already part of another faction");
		if (faction.getScheduler() != null && faction.getScheduler() != this)
			throw new IllegalArgumentException("Faction has another scheduler");
		this.faction = faction;
		this.updateTasks();
	}
	
	/**
	 * Returns the faction of this scheduler
	 * 
	 * @return Returns the faction of this scheduler
	 */
	public Faction getFaction() {
		return this.faction;
	}
	
	/**
	 * Removes all tasks with a position outside of the faction's world
	 * 
	 * @post All tasks part of this world have a valid position
	 */
	protected void updateTasks() {
		
		int i = 0;
		while (i < this.getTasks().size()) {
			if (!isValidPosition(this.getTasks().get(i).getCube()))
				this.removeTask(this.getTasks().get(i));
		}
	}
	
	
	
	private final List<T> taskList = new ArrayList<>();
	private Faction faction;
	
		
}