package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public interface ISourceCheck {
	
	public default boolean isValidSourceLocation(SourceLocation souce) {
		return (souce != null);
	}

}
