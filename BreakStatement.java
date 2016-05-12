package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class BreakStatement extends EvaluateStatement {
	

	public BreakStatement(SourceLocation source) {
		super(source);
	}

	public void execute() throws BreakException {
		throw new BreakException();

		
	}


}
