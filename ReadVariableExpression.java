package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class ReadVariableExpression extends E<E<?>> {
	/**
	 * Initializes a ReadVariableExpression
	 * 
	 * @param name
	 * 		The name of the Expression that has to be read
	 * @param source
	 *   		The column and line of the ReadVariableExpression in its Task
	 */
	public ReadVariableExpression(String name,  SourceLocation source) {
		super(source);
		this.name = name;
	}
	
	/**
	 *Evaluates the ReadVariableExpression
	 *
	 * @return Returns the expression with the name assigned to the ReadVariableExpression
	 * 
	 * @post The expression is read
	 */
	@Override
	public E<?> evaluate() throws IllegalArgumentException {
		if (isAssignedToTask()) {
			E<?> expression =  this.getTask().getVariables().get(this.getName()).getExpression();
			expression.enableReadVariableExpression();
			expression.setVariableName(this.getName());
			return expression;
		}
		
		throw new IllegalArgumentException("Is not assigned to task") ;
	}
	/**
	 * 
	 * @return Returns the name of the ReadvariableExpression
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * 
	 * @return Returns whether or not the expression has a task assigned
	 */
	public boolean isAssignedToTask() {
		return getTask() != null;
	}
	
	/**
	 * 
	 * @return Returns the task of the expression
	 */
	public T getTask() {
		return task;
	}

	/**
	 * 
	 * @param task
	 * 		The task of the expression
	 * @post 
	 * 		The Expressions task is the given task
	 */
	public void setTask(T task) {
		this.task = task;
	}

	private String name;
	private T task;
	
	
	
	

}