package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class CarriesItemExpression extends StatusExpression {

	public CarriesItemExpression(UnitExpression unit, SourceLocation source) {
		super(unit, source);
	}

	@Override
	public Boolean evaluate() {
		return (this.getPassiveUnit().evaluate().isCarryingBoulder() | 
				this.getPassiveUnit().evaluate().isCarryingLog());
	}

}
