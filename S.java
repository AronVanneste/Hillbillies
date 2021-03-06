package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class S implements IPerform, ISourceCheck {
	/**
	 * Initialization of a Statement
	 * 
	 * @param source
	 *   	The column and line of the Statement in its Task
	 * @post
	 * 		|new.getSource() = source 
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 */
	public S(SourceLocation source) throws IllegalSourceException {
		if (!isValidSourceLocation(source))
			throw new IllegalSourceException();
		this.source = source;
	}
	
	/**
	 * Executes the statement
	 */
	public abstract void execute();
	
	/**
	 * 
	 * @return Returns the SourceLocation of the Statement
	 */
	public SourceLocation getSource() {
		return this.source;
	}
	
	/**
	 * @param unit
	 * 		The unit assigned to the statement
	 * @post
	 * 		|new.getUnit() = unit
	 */
	@Override
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	/**
	 * @return Returns the unit of the Statement
	 */
	@Override
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * @return Returns whether or not the statement has a unit assigned
	 */
	@Override
	public boolean isAssigned() {
		return this.getUnit() != null;
	}
	
	private final SourceLocation source;
	private Unit unit;

}