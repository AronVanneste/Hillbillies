package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WorkStatement extends MoveStatement {

	public WorkStatement(PositionExpression position, SourceLocation source) throws
			IllegalExpressionException, IllegalSourceException {
		super(position, source);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().workAt((int[]) this.getPosition().evaluate());
		else
			return;
	}
	
	

}
