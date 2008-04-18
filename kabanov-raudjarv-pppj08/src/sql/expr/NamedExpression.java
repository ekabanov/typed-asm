package sql.expr;

public interface NamedExpression<E> extends Expression<E> {
	String getName();
}
