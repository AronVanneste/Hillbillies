package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class PositionExpression extends E<int[]> implements PerformInterface {

	public PositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}


}
