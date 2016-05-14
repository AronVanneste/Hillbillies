package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class AnyExpression extends UnitExpression {
	/**
	 * Initialisation of AnyExpression
	 * 
	 * @param source
	 * 		The column and line of the AnyExpression in its Task
	 */
	public AnyExpression(SourceLocation source) {
		super(source);
	}
	/**
	 * Evaluates the AnyExpression
	 * 
	 * @return Returns the closest unit to the unit assigned to AnyExpression
	 * 
	 * @throws Throws IllegalUnitExpression if the unit is not assigned to a world
	 * 
	 * @throws Throws IllegalArgumentExpression if the AnyExpression has no unit
	 */ 
	@Override
	public Unit evaluate() throws IllegalUnitException, IllegalArgumentException {
		if (this.isAssigned()) {
			try {
				return this.getUnit().getWorld()
						.getClosestUnit((Unit unit) -> (true), this.getUnit());
			} catch (NullPointerException e){
				throw new IllegalUnitException("Unit does not belong to a world");
			}
		}
		throw new IllegalArgumentException("Expression not assigned to Unit");
		
	}
	
}