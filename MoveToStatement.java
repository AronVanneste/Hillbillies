package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class MoveToStatement extends MoveStatement {
	
	public MoveToStatement(PositionExpression position, SourceLocation source) throws 
			IllegalSourceException, IllegalExpressionException {
		super(position, source);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().moveTo((int[]) this.getPosition().evaluate());
		else
			return;
	}

}
