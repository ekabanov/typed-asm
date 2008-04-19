package sql.expr;

public interface ColumnExpression<E> extends SelectExpression<E> {
	String getName();
}
