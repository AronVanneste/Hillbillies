package hillbillies.model;

public abstract class UnitStatement extends ActionStatement implements RelationInterface {
	
	public UnitStatement(Unit unit) {
		this.passiveUnit = unit;
	}
	
	public Unit getPassiveUnit() {
		return this.passiveUnit;
	}
	
	private final Unit passiveUnit;

}
