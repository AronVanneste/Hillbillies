package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

public class T {
	
	public T(String name, int priority, S activity, List<int[]> selectedCubes) {
		this.setName(name);
		this.setPriority(priority);
		this.setActivity(activity);
		this.setCubes(selectedCubes);
	}
	
	
	public void terminate() {
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return this.name;
	}
	
	protected void setPriority(int priority) {
		this.priority = priority;
	}
	
	protected int getPriority() {
		return this.priority;
	}
	
	protected void setCubes(List<int[]> cubes) {
		this.cubes = cubes;
	}
	
	protected List<int[]> getCubes() {
		return this.cubes;
	}
	
	protected void setActivity(S activity) {
		this.activity = activity;
	}
	
	protected S getActivity() {
		return this.activity;
	}

	private String name;
	private int priority;
	private S activity;
	private List<int[]> cubes = new ArrayList<>();
}
