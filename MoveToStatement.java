package hillbillies.model;

public class MoveToStatement extends MoveStatement {
	
	public MoveToStatement(E<?> position) {
		super(position);
	}

	@Override
	public void execute() {
		if (this.isAssigned())
			this.getUnit().moveTo((int[]) this.getPosition().evaluate());
		else
			return;
	}

}
