package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class E<K> implements ISourceCheck {
	/**
	 * Initialization of a new expression
	 * 
	 * @param sourceLocation
	 *    		The column and line of the expression in its Task
	 *@throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public E(SourceLocation sourceLocation) throws IllegalSourceException {
		if (!isValidSourceLocation(sourceLocation))
			throw new IllegalSourceException();
		this.sourceLocation = sourceLocation;
	}
	
	/**
	 * Evaluates the Expression
	 * 
	 * @return Returns an object of class K, this can be of any type.
	 */
	public abstract K evaluate();
	
	/**
	 * Returns the sourcelocation of the expression
	 * @return Returns the sourceLocation of the expression
	 */
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	/**
	 * Returns whether or not the expression was read by a ReadVariableExpression
	 * @return Returns whether or not the expression was read by a ReadVariableExpression
	 */
	public boolean wasReadVariableExpression() {
		return this.read;
	}
	
	/**
	 * Sets the read boolean to true, indicates that it is read by a ReadVariableExpression
	 */
	public void enableReadVariableExpression() {
		this.read = true;
	}
	
	/**
	 * @return Returns the variableName
	 */
	public String getVariableName() {
		return variableName;
	}
	/**
	 * Sets the variableName
	 * 
	 * @param variableName
	 * 		The name assigned to the Expression
	 * @post
	 * 		The name of the expression is the given name
	 */
	public void setVariableName(String variableName) {
		if (this.wasReadVariableExpression())
			this.variableName = variableName;
	}

	private final SourceLocation sourceLocation;
	private boolean read;
	private String variableName;
	


}
