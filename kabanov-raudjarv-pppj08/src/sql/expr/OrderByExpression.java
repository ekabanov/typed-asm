package sql.expr;

public interface OrderByExpression extends NamedExpression<Object> {
	enum Direction { ASC, DESC };
	Direction getDirection();
}
