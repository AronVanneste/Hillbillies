package hillbillies.model;

import java.util.Iterator;

public class T implements PerformInterface {
	
	public T(String name, int priority, S activity, int[] cube) throws IllegalStatementException {
		if (!isValidStatement(activity))
			throw new IllegalStatementException("Not a valid statement");
		this.setName(name);
		this.setPriority(priority);
		this.setActivity(activity);
		this.setCubes(cube);
	}
	
	
	public void terminate() {
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return this.name;
	}
	
	protected void setPriority(int priority) {
		this.priority = priority;
	}
	
	protected int getPriority() {
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
			this.getUnit().stopTasks();
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
	
	private Unit performer;
	private String name;
	private int priority;
	private S activity;
	private int[] cube;
	
	
}
