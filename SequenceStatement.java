package hillbillies.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.part3.programs.SourceLocation;

public class SequenceStatement extends S {
	
	public SequenceStatement(List<S> statements, SourceLocation source) {
		super(source);
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
	
	public Iterator<S> iterator() {
		return new Iterator<S>() {


			@Override
			public boolean hasNext() {
				for (S s: getStatements()) {
					if (!passedS.contains(s)) {
						return true;
					}
				}
				return false;
					
			}

			@Override
			public S next() throws NoSuchElementException {
				if (!hasNext())
					throw new NoSuchElementException();
				for (S s: getStatements()) {
					if (s.getSource().getLine() < currentPos.getLine() | 
							(s.getSource().getLine() == currentPos.getLine() &&
							s.getSource().getColumn() < currentPos.getColumn())) {
						currentS = s;
						currentPos = s.getSource();
					}
						
					
				}
				
				passedS.add(currentS);
				return currentS;
			}
			private List<S> passedS = new ArrayList<>();
			
			private S currentS;
			private SourceLocation currentPos = new SourceLocation(Integer.MAX_VALUE, 
					Integer.MAX_VALUE);
			
		};
	}
	
	public List<S> getStatements() {
		return this.statements;
	}
	

	
	private final List<S> statements = new ArrayList<>();

}
