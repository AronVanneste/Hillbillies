package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class PositionExpression extends E<int[]> implements IPerform {

	public PositionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}


}
