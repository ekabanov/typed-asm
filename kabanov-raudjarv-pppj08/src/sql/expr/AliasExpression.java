package sql.expr;

public interface AliasExpression<E> extends NamedExpression<E> {
	Expression<E> getExpression();
	String getName();
}
