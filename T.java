package hillbillies.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hillbillies.part3.programs.SourceLocation;

public class T implements IPerform, ITerminate {
	/**
	 * The initialization of a task
	 * 
	 * @param name
	 * 		The name of the task
	 * @param priority
	 * 		The priority of the task
	 * @param activity
	 * 		The (sequence of) activities the task consists of
	 * @param cube
	 * 		The position where the task should be executed
	 * @post
	 * 		The name of the task will be the given name
	 * 		|new.getName() = name
	 * @post
	 * 		The priority of the task will be the given prioritycube
	 * 		|new.getPriority() = priority
	 * @post
	 * 		The activities of the task will be the given activities
	 * 		|new.getActivity() = activity
	 * @post
	 * 		The cube of the task will be the given cube
	 * 		|new.getCube() = cube
	 * @throws IllegalStatementException
	 * 		Throws IllegalStatementException is the activity is not well formed
	 */
	public T(String name, int priority, S activity, int[] cube) throws IllegalStatementException {
		if (!isWellFormed(activity) | !isValidStatement(activity))
			throw new IllegalStatementException("Not a valid statement");
		if (!isValidPosition(cube))
		this.setName(name);
		this.setPriority(priority);
		this.setActivity(activity);
		this.setCubes(cube);
	}
	
	/**
	 * Terminates the task
	 * 
	 * @post The unit's task is null
	 * 	|this.getUnit().getTask() = null
	 * @post The unit is  null
	 * 	|new.getUnit() = null
	 * @post The task is removed from all schedulers and vice versa
	 * 	|this.getScheduler().contains(task) == false
	 * 	|new.getScheduler() = null;
	 */
	public void terminate() {
		try {
		this.getUnit().removeTask();
		} catch (NullPointerException e) {};
		this.setUnit(null);
		for (Scheduler s: this.getSchedulers()) {
			s.removeTask(this);
			this.removeScheduler(s);
		}
	}
	
	/**
	 * Returns all the variables used in the task
	 * 
	 * @return Returns all the variables used in the task
	 */
	public Map<String, AssignStatement> getVariables() {
		return this.variables;
	}
	/**
	 * Executes the task
	 * 
	 * @post The activities of the task are executed and have a unit assigned
	 * 	|new.getActivity().getUnit() = this.getUnit();
	 * 
	 * @throws IllegalArgumentsException if the task is not assigned
	 *
	 */
	public void executeTask() throws IllegalArgumentException{
		if (!isAssigned())
			throw new IllegalArgumentException("Task not assigned");
		if (!(this.getActivity() instanceof SequenceStatement)) {
			this.getActivity().setUnit(this.getUnit());
			this.getActivity().execute();
		}
	}
	/**
	 * Returns the next statement
	 * 
	 * @return Returns the statement that's not yet eecuted
	 * 
	 * @throws NoStatementsLeftException if there's no statements left
	 */
	public S getNextStatement() throws NoStatementsLeftException {
		if (this.getActivity() instanceof SequenceStatement) {
			if (this.getIterator().hasNext())
				return this.getIterator().next();
			else
				throw new NoStatementsLeftException("No statements left");
		}		
		else {
			if (!getTaskRead()) {
				this.setTaskRead(true);
				return this.getActivity();
			}
			else
				throw new NoStatementsLeftException("No statements left");
		}
			
	}
	
	
	
	/**
	 * 
	 * @param name
	 * 		The name of the task
	 * @post
	 * 		|new.getName() = name
	 */
	protected void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return Returns the name of the task
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 
	 * @param priority
	 * 		The priority of the task
	 * @post
	 * 		|new.getPriority() = priority
	 */
	protected void setPriority(int priority) {
		this.priority = priority;
	}
	
	/**
	 * 
	 * @return Returns the priority of the task
	 */
	public int getPriority() {
		return this.priority;
	}
	
	/**
	 * Reduces the priority of the task by 20
	 * @post
	 * 		|new.getPriority() = (this.getPriority-20)
	 */
	public void reducePriority() {
		this.setPriority(this.getPriority() - 20);
	}
	
	/**
	 * 
	 * @param cube	
	 * 		The cube assigned to the Task
	 * @post
	 * 		|new.getCube() = cube
	 */
	protected void setCubes(int[] cube) {
		this.cube = cube;
	}
	/**
	 * 
	 * @return returns the cube assigned to the task
	 */
	protected int[] getCube() {
		return this.cube;
	}
	
	/**
	 * 
	 * @param activity
	 * 		The activity of which the task consists
	 * @post
	 * 		|new.getActivity() = activity
	 */
	protected void setActivity(S activity) {
		this.activity = activity;
		try {
			this.setIterator();
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	/**
	 * 
	 * @return Returns the Activity of which the task consists
	 */
	protected S getActivity() {
		return this.activity;
	}

	/**
	 * 
	 * @param unit
	 * 		The unit to perform the task
	 * @post
	 * 		|new.getUnit() = unit
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the Task is already assigned to a unit
	 */
	@Override
	public void setUnit(Unit unit) throws IllegalArgumentException{
		if (this.isAssigned())
			throw new IllegalArgumentException("Task already assigned");
		this.performer = unit;
		
	}
	
	/**
	 * 
	 * @return Returns the unit that's performing the task
	 */
	@Override
	public Unit getUnit() {
		return this.performer;
	}

	/**
	 * 
	 * @return Returns whether or not the task has a unit assigned
	 */
	@Override
	public boolean isAssigned() {
		return this.getUnit() != null;
	}
	
	/**
	 * Removes the unit from the task
	 * @post
	 * 		The performer of the task is null
	 * 		|new.getUnit() == null
	 * @post
	 * 		The priority of the task is reduced
	 * 		|new.getPriority() = (this.getPriority() - 20)
	 */
	public void removeUnit() {
		if (this.isAssigned()) {
			this.performer = null;
			this.reducePriority();
		}
	}
	
	/**
	 *
	 * @param statement
	 * @throws IllegalArgumentException
	 */
	??
	//TODO ????
	public void performTask(S statement) throws IllegalArgumentException {
		if (!this.isAssigned())
			throw new IllegalArgumentException("Not assigned to a unit");
		
	}
	/**
	 * 
	 * @param s
	 * 		The statement to be checked
	 * @return Returns true if the statement is not true
	 */
	private boolean isValidStatement(S s) {
		return s != null;
	}
	/**
	 * 
	 * @param p
	 * 		The position to be checked
	 * @return Returns true is the position is not null
	 */
	private boolean isValidPosition(int[] p) {
		return p != null;
	}
	
//	protected boolean isValidStatement(S s) {
//		if (!(s instanceof SequenceStatement) && !(s instanceof ActionStatement))
//			return false;
//		Iterator<S> iterS = ((SequenceStatement)s).iterator();
//		while (iterS.hasNext()) {
//			S next = iterS.next();
//			if (!isValidSubStatement(next))
//				return false;
//		}
//		return true;
//		
//			
//	}
//	
//	protected boolean isValidSubStatement(S statement) {
//		if (statement instanceof SequenceStatement)
//			return isValidStatement(statement);
//		if (statement instanceof BreakStatement)
//			return false;
//		return true;
//		
//		
//	}
	
	/**
	 * Adds the activity's name of the Task to its variables
	 * 
	 * @post
	 * 		if this.getActivity() instanceof AssignStatement
	 * 			variables.contains(this.getActivity().getName())
	 */
	public void addAssignStatements() {
		addAssignStatements(this.getActivity());
	}
 	/**
 	 * 
 	 * @param s
 	 * 		The statement that should be added to the variables list if its a AssignStatement
 	 * @post All Statements names that are AssignStatements that are in the given Statement are added to the variables of Task
 	 * 		|if (s instanceof AssignStatement)
 	 * 		|	variables.contains(s.getName())
 	 */
	public void addAssignStatements(S s) {
		if (s instanceof AssignStatement)
			variables.put(((AssignStatement) s).getName(), (AssignStatement) s);
		if (s instanceof SequenceStatement) {
			for (S statement: ((SequenceStatement) s).getStatements())
				addAssignStatements(statement);
		}
		if (s instanceof WhileStatement) 
			addAssignStatements(((WhileStatement) s).getBody());
		if (s instanceof IfStatement) {
			addAssignStatements(((IfStatement) s).getIfBody());
			addAssignStatements(((IfStatement) s).getElseBody());
		}			
		return;
			
	}
	
	/**
	 * 
	 * @param s
	 * 		The statement of which is checked if it has a variable assigned
	 * @post
	 * 		|
	 */
	public void addReadExpressions(S s) {
		if (s instanceof IfStatement) {
			if (((IfStatement) s).getCondition().wasReadVariableExpression())
				readVariables.put(((IfStatement) s).getCondition().getVariableName(), 
						s.getSource());
		}
		if (s instanceof WhileStatement) {
			if (((WhileStatement) s).getCondition().wasReadVariableExpression())
				readVariables.put(((WhileStatement) s).getCondition().getVariableName(), 
						s.getSource());
		}
		
		if (s instanceof UnitStatement) {
			if (((UnitStatement) s).getPassiveUnit().wasReadVariableExpression())
				readVariables.put(((UnitStatement) s).getPassiveUnit().getVariableName(), 
						s.getSource());
		}
		
		if (s instanceof MoveStatement) {
			if (((MoveStatement) s).getPosition().wasReadVariableExpression())
				readVariables.put(((MoveStatement) s).getPosition().getVariableName(), 
						s.getSource());
		}
		
		if (s instanceof SequenceStatement) {
			for (S st: ((SequenceStatement) s).getStatements())
				addReadExpressions(st);
		}
	}
	/**
	 * 
	 * @return Returns whether or not the Statements of the Task are well formed
	 */
	public boolean isWellFormed() {
		return isWellFormed(this.getActivity());
	}
	/**
	 * 
	 * @param s
	 * 		The Statement that's checked
	 * @return Returns true if the statement has ValidVariables an has valid breaks
	 */
	public boolean isWellFormed(S s) {
		return this.hasValidVariables(s) && this.hasValidBreaks(s);
	}
	/**
	 * 
	 * @return Returns all the name of variables in the Task
	 */
	public Map<String,SourceLocation> getReadVariables() {
		return this.readVariables;
	}
	
	/**
	 * 
	 * @param s
	 * 		The statement of which is checked if it has a valid variable
	 * @return Returns whether or not the statement has a valid variable, i.e. it is not used before its declaration
	 */
	public boolean hasValidVariables(S s) {
		addAssignStatements(s);
		addReadExpressions(s);
		for (AssignStatement variable: this.getVariables().values()) {
			SourceLocation source2 = this.getReadVariables().get(variable.getName());
			if (!variable.getSource().precedes(source2))
				return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return Returns true if the Tasks breaks are valid
	 */
	public boolean hasValidBreaks() {
		return this.hasValidBreaks(this.getActivity());
	}
	
	/**
	 * 
	 * @param s
	 * 		The statement of which is checked if it has valid breaks
	 * @return Returns true if the statement is a while statement, false if it's a break statement
	 */
	public boolean hasValidBreaks(S s) {
		
		if (s instanceof SequenceStatement) {
			for (S st: ((SequenceStatement) s).getStatements()) {
				if (!hasValidBreaks(st))
					return false;
			}
		}
		
		if (s instanceof IfStatement) {
			return (hasValidBreaks(((IfStatement) s).getIfBody()) &&
					hasValidBreaks(((IfStatement) s).getElseBody()));
		}
		
		if (s instanceof WhileStatement) {
			return true;
		}
		
		if (s instanceof BreakStatement) {
			return false;
		}
		
		return true;
 		
	}
	
	/**
	 * 
	 * @return Returns whether or not the Task is terminated
	 */
	@Override
	public boolean isTerminated() {
		return false;
	}
	
	/**
	 * 
	 * @return Returns the Iterator of the Task
	 */
	public Iterator<S> getIterator() {
		return this.iter;
	}
	/**
	 * Creates an Iterator with all the activities in the task
	 * 
	 * @throws IllegalArgumentException
	 * 		Throws IllegalArgumentException if the Activity is no instance of SequenceStatement
	 */
	public void setIterator() throws IllegalArgumentException {
		if (!(this.getActivity() instanceof SequenceStatement))
			throw new IllegalArgumentException();
		this.iter = ((SequenceStatement) this.getActivity()).iterator();
	}
	
	/**
	 * 
	 * @param bool
	 * 		The boolean that indicates whether the task is read or not
	 */
	private void setTaskRead(boolean bool) {
		this.taskRead = bool;
	}
	
	/**
	 * 
	 * @return returns whether the task is read or not
	 */
	public boolean getTaskRead() {
		return this.taskRead;
	}
	
	/**
	 * 
	 * @return Returns the Scheduler(s) in which the task is
	 */
	public Set<Scheduler> getSchedulers() {
		return this.schedulers;
	}
	
	/**
	 * 
	 * @param sch
	 * 		One of the schedulers the task is in
	 * @post
	 * 		|new.getScheduler().contains(sch)
	 */
	public void addScheduler(Scheduler sch) {
		this.schedulers.add(sch);
	}
	
	/**
	 * 
	 * @param s
	 * 		The scheduler to be removed
	 * @post
	 * 		|new.getScheduler().contains(s) == false
	 */
	public void removeScheduler(Scheduler s) {
		this.schedulers.remove(s);
	}

	
	
	private Unit performer;
	private String name;
	private int priority;
	private S activity;
	private int[] cube;
	private Map<String, AssignStatement> variables = new HashMap<>();
	private Map<String, SourceLocation> readVariables = new HashMap<>();
	private Iterator<S> iter;
	private boolean taskRead;
	private Set<Scheduler> schedulers = new HashSet<>();;
	
	
}
