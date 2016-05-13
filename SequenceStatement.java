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
	/**
	 * Executes the SequenceStatement
	 * 
	 * @post All the statements in the sequence are executed
	 */
	@Override
	public void execute() {
		for (S s: this.getStatements())
			s.execute();
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
				currentS = null;
				currentPos = new SourceLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
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
