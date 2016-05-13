package hillbillies.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Scheduler {
	



	public Scheduler() {
		
	}
	/**
	 * Adds a task to the scheduler
	 * 
	 * @post The task is added to the scheduler and the scheduler is added to the task
	 */
	public void addTask(T task) {
		this.taskList.add(task);
		task.addScheduler(this);
	}
	
	public List<T> getTasks() {
				
		return this.taskList;
	}
	
	/**
	 * Removes a given task form the scheduler and terminates it
	 */
	public void removeTask(T task) {
		this.getTasks().remove(task);
		task.terminate();
	}
	/**
	 *Replaces an old task by a new task
	 */
	public void replaceTask(T newTask, T oldTask) {
		removeTask(oldTask);
		addTask(newTask);
	}
	
	/**
	 * @return Returns whether or not all tasks in a collection are part of the scheduler
	 */
	public boolean areAllPartOfScheduler(Collection<T> tasks) {
		
		for (T task: tasks) {
			if (!this.getTasks().contains(task))
				return false;
		}
		return true;
	}
	
	/**
	 *@return Returns the task in the scheduler that has the highest priority and is not yet assigned
	 */
	public T getHighestPriorityTaskNotYetAssigned() {
		
		Iterator<T> iter = this.getAllTasksIterator();
		while (iter.hasNext()) {
			T task = iter.next(); 
			if (!task.isAssigned())
				return task;
		}
		
		throw new NoSuchElementException();
	}
	
	/**
	 * Assigns the task with the highest priotity and that's not yet assigned to the given unit
	 */
	public void assignTaskToUnit(Unit unit) throws NoSuchElementException {
		
		try {
			T task = getHighestPriorityTaskNotYetAssigned();
			assignTaskToUnit(unit, task);
		} catch (NoSuchElementException exc) {
			throw exc;
		}
	}
	
	
	/**
	 * Assigns the task and that's not yet assigned to the given unit
	 */
	public void assignTaskToUnit(Unit unit, T task) throws IllegalArgumentException, 
		IllegalUnitException {
		
		if (!this.getTasks().contains(task))
			throw new IllegalArgumentException("Task is not part of this scheduler");
		if (task.isAssigned())
			throw new IllegalUnitException("Task is already assigned to a unit");
		task.setUnit(unit);
		unit.setTask(task);
	}
	
	
	/**
	 * 
	 * @return Returns an iterator with all tasks
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
			private List<T> passedT = new ArrayList<>();

		};
	}

	/**
	 * Algorithms to return all tasks satisfying some condition
	 */
	
	public List<T> getAllTaskPriorityLargerThan(int K) {
		return getAllTasksSatisfyingCondition((T t) -> t.getPriority() > K);
	}
	
	public List<T> getAllTaskPrioritySmallerThan(int K) {
		return getAllTasksSatisfyingCondition((T t) -> t.getPriority() < K);
	}
	
	public List<T> getAllTaskCurrentlyNotExecuting() {
		return getAllTasksSatisfyingCondition((T t) -> !t.isAssigned());
	}
	
	
	public List<T> getAllTasksSatisfyingCondition(Predicate<T> condition) {
		
		List<T> taskList = new ArrayList<>();
		this.getTasks().stream().filter(condition).forEach(taskList::add);
		return taskList;
			
	}
	
	
	
	private List<T> taskList = new ArrayList<>();
	
		
}
