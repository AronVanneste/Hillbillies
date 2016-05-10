package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class WorkStatement extends MoveStatement {

	public WorkStatement(E<?> position, SourceLocation source) {
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
