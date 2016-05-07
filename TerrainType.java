package hillbillies.model;

public enum TerrainType {
	
	WORKSHOP {

	},
	
	TREE {
	
 	},
	
	ROCK {
	
	},
	
	AIR {
		
	};
	
	 private int number;

	    static {
	        AIR.number = 0;
	        ROCK.number = 1;
	        TREE.number = 2;
	        WORKSHOP.number = 3;
	    }

	    public int getInt() {
	        return number;
	    }


}
