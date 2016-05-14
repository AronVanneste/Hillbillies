package hillbillies.factory;

import java.util.ArrayList;
import java.util.List;

import hillbillies.model.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<E<?>, S, T> {
	
 
	@Override
	public List<T> createTasks(String name, int priority, S activity, List<int[]> selectedCubes) {
		List<T> tasks = new ArrayList<>();
		if(!(activity instanceof SequenceStatement)){
			tasks.add(new T(name, priority, activity, selectedCubes.get(0)));
			return tasks;
		}
		else  {
			int i = 0;
			for (S s: ((SequenceStatement) activity).getStatements()) {
				tasks.add(new T(name, priority, activity, selectedCubes.get(i)));
				i+=1;

			}
		}
		return tasks;
	
	}

	@Override
	public S createAssignment(String variableName, E<?> value, SourceLocation sourceLocation) {
		return new AssignStatement(variableName, value, sourceLocation);
	}

	@Override
	public S createWhile(E<?> condition, S body, SourceLocation sourceLocation) {
		return new WhileStatement((BooleanExpression) condition, body, sourceLocation);
	}

	@Override
	public S createIf(E<?> condition, S ifBody, S elseBody, SourceLocation sourceLocation) {
		return new IfStatement((BooleanExpression) condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public S createBreak(SourceLocation sourceLocation) {
		return new BreakStatement(sourceLocation);
	}

	@Override
	public S createPrint(E<?> value, SourceLocation sourceLocation) {
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public S createSequence(List<S> statements, SourceLocation sourceLocation) {
		return new SequenceStatement(statements, sourceLocation);
	}

	@Override
	public S createMoveTo(E<?> position, SourceLocation sourceLocation) {
		return new MoveToStatement((PositionExpression) position, sourceLocation);
	}

	@Override
	public S createWork(E<?> position, SourceLocation sourceLocation) {
		return new WorkStatement(position, sourceLocation);
	}

	@Override
	public S createFollow(E<?> unit, SourceLocation sourceLocation) {
		return new FollowStatement((UnitExpression) unit, sourceLocation);
	}

	@Override
	public S createAttack(E<?> unit, SourceLocation sourceLocation) {
		return new AttackStatement((UnitExpression) unit,sourceLocation);
	}

	@Override
	public E<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new ReadVariableExpression(variableName, sourceLocation);
	}

	@Override
	public E<?> createIsSolid(E<?> position, SourceLocation sourceLocation) {
		return new CheckSolidExpression((PositionExpression) position, sourceLocation);
	}

	@Override
	public E<?> createIsPassable(E<?> position, SourceLocation sourceLocation) {
		return new CheckPassableExpression((PositionExpression) position,sourceLocation);
	}

	@Override
	public E<?> createIsFriend(E<?> unit, SourceLocation sourceLocation) {
		return new CheckFriendExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public E<?> createIsEnemy(E<?> unit, SourceLocation sourceLocation) {
		return new CheckEnemyExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public E<?> createIsAlive(E<?> unit, SourceLocation sourceLocation) {
		return new CheckAliveExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public E<?> createCarriesItem(E<?> unit, SourceLocation sourceLocation) {
		return new CarriesItemExpression((UnitExpression) unit, sourceLocation);
	}

	@Override
	public E<?> createNot(E<?> expression, SourceLocation sourceLocation) {
		return new NotExpression((BooleanExpression) expression, sourceLocation);
		
	}

	@Override
	public E<?> createAnd(E<?> left, E<?> right, SourceLocation sourceLocation) {
		return new AndExpression((BooleanExpression) left, (BooleanExpression) right, sourceLocation);
	}

	@Override
	public E<?> createOr(E<?> left, E<?> right, SourceLocation sourceLocation) {
		return new OrExpression((BooleanExpression) left, (BooleanExpression) right, sourceLocation);
	}

	@Override
	public E<?> createHerePosition(SourceLocation sourceLocation) {
		return new HereExpression(sourceLocation);
	}

	@Override
	public E<?> createLogPosition(SourceLocation sourceLocation) {
		return new LogExpression(sourceLocation);
	}

	@Override
	public E<?> createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderExpression(sourceLocation);
	}

	@Override
	public E<?> createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopExpression(sourceLocation);
	}

	@Override
	public E<?> createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedExpression(sourceLocation);
	}

	@Override
	public E<?> createNextToPosition(E<?> position, SourceLocation sourceLocation) {
		return new NextToExpression((PositionExpression) position, sourceLocation);
	}

	@Override
	public E<?> createPositionOf(E<?> unit, SourceLocation sourceLocation) {
		return new UnitPositionExpression((UnitExpression) unit, sourceLocation);
		
	}

	@Override
	public E<?> createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new LiteralExpression(x,y,z, sourceLocation);
	}

	@Override
	public E<?> createThis(SourceLocation sourceLocation) {
		return new ThisExpression(sourceLocation);
	}

	@Override
	public E<?> createFriend(SourceLocation sourceLocation) {
		return new FriendExpression(sourceLocation);
	}

	@Override
	public E<?> createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression(sourceLocation);
	}

	@Override
	public E<?> createAny(SourceLocation sourceLocation) {
		return new AnyExpression(sourceLocation);
	}

	@Override
	public E<?> createTrue(SourceLocation sourceLocation) {
		return new TrueExpression(sourceLocation);
	}

	@Override
	public E<?> createFalse(SourceLocation sourceLocation) {
		return new FalseExpression(sourceLocation);
	}

}
