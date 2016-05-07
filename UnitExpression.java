package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class UnitExpression extends E<Unit> implements PerformInterface {

	public UnitExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

}
