package sql.expr;

public interface Alias<E> extends ColumnExpression<E> {
	String getName();
	Expression<E> getSelectExpression();
}
