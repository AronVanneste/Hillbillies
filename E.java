package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class E<K> {
	
	public E(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public abstract K evaluate();
	
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public boolean wasReadVariableExpression() {
		return this.read;
	}
	
	public void enableReadVariableExpression() {
		this.read = true;
	}
	
	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		if (this.wasReadVariableExpression())
			this.variableName = variableName;
	}

	private final SourceLocation sourceLocation;
	private boolean read;
	private String variableName;
	


}
