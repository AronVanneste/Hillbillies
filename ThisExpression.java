package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class ThisExpression extends UnitExpression {
	
	public ThisExpression(SourceLocation source) throws IllegalSourceException {
		super(source);
	}

	@Override
	public Unit evaluate() throws IllegalArgumentException {
		if (this.isAssigned()) {
			return this.getUnit();
		}
		throw new IllegalArgumentException("Expression not assigned to Unit");
		
	}

}
