package hillbillies.model;

public class WorkStatement extends MoveStatement {

	public WorkStatement(E<?> position) {
		super(position);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().workAt((int[]) this.getPosition().evaluate());
		else
			return;
	}
	
	

}
