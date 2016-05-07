package hillbillies.model;

public class ReadVariableExpression extends E {

	public ReadVariableExpression(String name) {
		
	}
	
	
	@Override
	public Object evaluate() {
		return name;
	}

}
