package sql.expr;


public class ExpressionUtil {

	public static <E> Alias<E> alias(Expression<E> e, String alias) { return null; }
	public static <E> Alias<E> alias(Expression<E> e) { return null; }
	
	public static OrderByExpression asc(NamedExpression<?> e) { return null; }
	public static OrderByExpression desc(NamedExpression<?> e) { return null; }
	
	public static <E> Expression<E> constant(E value) { return null; }
	
	public static <E> Expression<E> unchecked(Class<E> type, String sql, Object... args) { return null; }
	
	public static Expression<String> concat(Expression<?> e1, Expression<?> e2) { return null; }
	
	public static BooleanExpression isNull(Expression<?> e) { return null; }
	public static <E> BooleanExpression eq(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> BooleanExpression gt(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> BooleanExpression lt(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> BooleanExpression like(Expression<E> e, Expression<String> pattern) { return null; }
	
	public static BooleanExpression not(BooleanExpression e) { return null; }
	public static BooleanExpression and(BooleanExpression... e) { return null; }
	public static BooleanExpression or(BooleanExpression... e) { return null; }
	
	public static <E> Expression<Integer> count(Expression<E> e) { return null; }
	public static <E> Expression<E> max(Expression<E> e) { return null; }
	public static <E> Expression<E> min(Expression<E> e) { return null; }
	public static <E> Expression<E> sum(Expression<E> e) { return null; }

}
