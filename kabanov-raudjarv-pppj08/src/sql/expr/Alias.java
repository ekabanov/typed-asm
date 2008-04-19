package sql.expr;

public interface Alias<E> extends NamedExpression<E> {
	Expression<E> getExpression();	// TODO
	String getName();
}
