package hillbillies.model;

import java.util.Iterator;

public class Expermint {

	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		for (int i = 0; i < 6; i++) {
			int P = (int) Math.round(Math.random() * 1000 - 500);
			int[] pos = {5, 4, 3};
			String name = "Name" + Integer.toString(Math.abs(P));
			T task = new T(name, P, null, null);
			s.addTask(task);
		}
		Iterator<T> iter = s.getAllTasksIterator();
		while (iter.hasNext()) {
			T t = iter.next();
			System.out.println(t.getName());
			System.out.println(t.getPriority());
			
		}
		
		
		
	}

}
