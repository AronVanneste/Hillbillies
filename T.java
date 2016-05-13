package hillbillies.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import hillbillies.part3.programs.SourceLocation;

public class T implements IPerform, ITerminate {
	
	public T(String name, int priority, S activity, int[] cube) throws IllegalStatementException {
		if (!isWellFormed(activity))
			throw new IllegalStatementException("Not a valid statement");
		this.setName(name);
		this.setPriority(priority);
		this.setActivity(activity);
		this.setCubes(cube);
	}
	
	
	public void terminate() {
		this.getUnit().setTask(null);
		this.setUnit(null);
		for (Scheduler s: this.getSchedulers()) {
			s.removeTask(this);
			this.removeScheduler(s);
		}
	}
	
	public Map<String, AssignStatement> getVariables() {
		return this.variables;
	}
	
	public void executeTask() {
		if (!isAssigned())
			throw new IllegalArgumentException("Task not assigned");
		if (!(this.getActivity() instanceof SequenceStatement)) {
			this.getActivity().setUnit(this.getUnit());
			this.getActivity().execute();
		}
	}
	
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
	
	
	
	
	protected void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	protected void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	protected void setCubes(int[] cube) {
		this.cube = cube;
	}
	
	protected int[] getCube() {
		return this.cube;
	}
	
	protected void setActivity(S activity) {
		this.activity = activity;
		this.setIterator();
	}
	
	protected S getActivity() {
		return this.activity;
	}

	
	@Override
	public void setUnit(Unit unit) {
		if (this.isAssigned())
			throw new IllegalArgumentException("Task already assigned");
		this.performer = unit;
		
	}

	@Override
	public Unit getUnit() {
		return this.performer;
	}


	@Override
	public boolean isAssigned() {
		return this.getUnit() != null;
	}
	
	public void removeUnit() {
		if (this.isAssigned()) {
			this.performer = null;
		}
	}
	
	public void performTask(S statement) throws IllegalArgumentException {
		if (!this.isAssigned())
			throw new IllegalArgumentException("Not assigned to a unit");
		
	}
	
	protected boolean isValidStatement(S s) {
		if (!(s instanceof SequenceStatement) && !(s instanceof ActionStatement))
			return false;
		Iterator<S> iterS = ((SequenceStatement)s).iterator();
		while (iterS.hasNext()) {
			S next = iterS.next();
			if (!isValidSubStatement(next))
				return false;
		}
		return true;
		
			
	}
	
	protected boolean isValidSubStatement(S statement) {
		if (statement instanceof SequenceStatement)
			return isValidStatement(statement);
		if (statement instanceof BreakStatement)
			return false;
		return true;
		
		
	}
	
	public void addAssignStatements() {
		addAssignStatements(this.getActivity());
	}
 	
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
	
	public boolean isWellFormed() {
		return isWellFormed(this.getActivity());
	}
	
	public boolean isWellFormed(S s) {
		return this.hasValidBreaks(s) && this.hasValidBreaks(s);
	}
	
	public Map<String,SourceLocation> getReadVariables() {
		return this.readVariables;
	}
	
	public boolean hasValidVariables() {
		for(AssignStatement variable:this.getVariables().values()) {
			SourceLocation source2 = this.getReadVariables().get(variable.getName());
			if (!variable.getSource().precedes(source2))
				return false;
		}
		return true;
	}
	
	public boolean hasValidBreaks() {
		return this.hasValidBreaks(this.getActivity());
	}
	
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
	
	@Override
	public boolean isTerminated() {
		return false;
	}
	
	public Iterator<S> getIterator() {
		return this.iter;
	}
	
	public void setIterator() throws IllegalArgumentException {
		if (!(this.getActivity() instanceof SequenceStatement))
			throw new IllegalArgumentException();
		this.iter = ((SequenceStatement) this.getActivity()).iterator();
	}
	
	private void setTaskRead(boolean bool) {
		this.taskRead = bool;
	}
	
	public boolean getTaskRead() {
		return this.taskRead;
	}
	
	public Set<Scheduler> getSchedulers() {
		return this.schedulers;
	}
	
	public void addScheduler(Scheduler sch) {
		this.schedulers.add(sch);
	}
	
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
