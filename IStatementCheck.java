package hillbillies.model;

public interface IStatementCheck {
	
	public default boolean isValidStatement(S s) {
		return s != null;
	}

}
