package hillbillies.model;

import java.util.ArrayList;
import java.util.List;

public class SequenceStatement extends S {
	
	public SequenceStatement(List<S> statements) {
		this.statements.addAll(statements);
	}

	@Override
	public void execute() {
		for (S s: this.getStatement())
			s.execute();
	}
	
	public List<S> getStatement() {
		return this.statements;
	}
	

	
	private final List<S> statements = new ArrayList<>();

}
