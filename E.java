package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public abstract class E<K> {
	
	public E(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public abstract K evaluate();
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private String name;
	private final SourceLocation sourceLocation;
	


}
