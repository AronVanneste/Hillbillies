package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class S {
	
	public S(SourceLocation source) {
		this.source = source;
	}
	
	public abstract void execute();
	
	public SourceLocation getSource() {
		return this.source;
	}
	
	private SourceLocation source;

}
