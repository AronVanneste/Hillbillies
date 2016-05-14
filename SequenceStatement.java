package hillbillies.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import hillbillies.part3.programs.SourceLocation;

public class SequenceStatement extends S implements IStatementCheck {
	/**
	 * Initializes a SequenceStatement
	 * 
	 * @param statements
	 * 		The list of statement in the SequenceStatement
	 * @param source
	 * 		The column and row of the SequenceStatement in its Task
	 * @throws IllegalSourceException
	 * 		Throws IllegalSourceException if the sourceLocation is not valid
	 * @throws IllegalExpressionException
	 * 		Throws IllegalSourceException if the Expression is not valid
	 */
	public SequenceStatement(List<S> statements, SourceLocation source) throws
			IllegalSourceException, IllegalStatementException {
		super(source);
		for (S s: statements) {
			if (!isValidStatement(s))
				throw new IllegalStatementException();
		}
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
	
	/**
	 * 
	 * @return Returns an iterator
	 */
	public Iterator<S> iterator() {
		return new Iterator<S>() {

			/**
			 * 
			 * @return Returns true is the iterator has a next Statement
			 */
			@Override
			public boolean hasNext() {
				for (S s: getStatements()) {
					if (!passedS.contains(s)) {
						return true;
					}
				}
				return false;
					
			}
			/**
			 * 
			 * @return Returns the next Statement in the iterator
			 * 
			 * @throws NoSuchElementException
			 * 		Throws NoSuchElementException if the iterator has no next element
			 */
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
	
	/**
	 * 
	 * @return Returns a list with all the Statements in the SequenceStatement
	 */
	public List<S> getStatements() {
		return this.statements;
	}
	

	
	private final List<S> statements = new ArrayList<>();

}
