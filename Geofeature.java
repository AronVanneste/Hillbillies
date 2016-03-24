package hillbillies.model;

public enum Geofeature {
	
	AIR (true),
	WORKSHOP (true),
	ROCK (false),
	WOOD (false);
	
	
	Geofeature(boolean passable) {
		this.passable = passable;
		
	}
	
	private boolean passable() {
		return passable;
	}
	
	private boolean passable;

}
