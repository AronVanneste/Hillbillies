package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class ReadVariableExpression extends E<E<?>> {

	public ReadVariableExpression(String name,  SourceLocation source) {
		super(source);
		this.name = name;
	}
	
	
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
	
	public String getName() {
		return this.name;
	}
	
	public boolean isAssignedToTask() {
		return getTask() != null;
	}
	public T getTask() {
		return task;
	}


	public void setTask(T task) {
		this.task = task;
	}

	private String name;
	private T task;
	
	
	
	

}
