package sql.expr;

public interface Alias<E> extends ColumnExpression<E> {
	Expression<E> getExpression();	// TODO
	String getName();
}
