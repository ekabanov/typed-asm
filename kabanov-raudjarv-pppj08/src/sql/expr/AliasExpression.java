package sql.expr;

public interface AliasExpression<E> extends Expression<E> {
	Expression<E> getExpression();
	String getAlias();
}
