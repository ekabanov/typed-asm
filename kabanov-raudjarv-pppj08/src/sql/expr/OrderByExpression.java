package sql.expr;

public interface OrderByExpression extends Expression<Object> {
	enum Direction { ASC, DESC };
	String getName();	
	Direction getDirection();
}
