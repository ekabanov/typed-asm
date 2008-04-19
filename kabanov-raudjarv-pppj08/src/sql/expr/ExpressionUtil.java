package sql.expr;


public class ExpressionUtil {
	
	// Constant
	public static <E> Expression<E> constant(E value) { return null; }
	
	// Unchecked
	public static <E> Expression<E> unchecked(Class<E> type, String sql, Object... args) { return null; }

	// Alias
	public static <E> Alias<E> alias(Expression<E> e, String alias) { return null; }
	public static <E> Alias<E> alias(Expression<E> e) { return null; }
	
	// Ordering direction
	public static OrderByExpression asc(NamedExpression<?> e) { return null; }
	public static OrderByExpression desc(NamedExpression<?> e) { return null; }
	
	// Arithmetic
	public static <E> BooleanExpression eq(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> BooleanExpression eq(Expression<E> e, Object c) { return null; }
	public static <E> BooleanExpression eq(Object c, Expression<E> e) { return null; }
	
	public static <E> BooleanExpression gt(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> BooleanExpression gt(Expression<E> e, Object c) { return null; }
	public static <E> BooleanExpression gt(Object c, Expression<E> e) { return null; }
	
	public static <E> BooleanExpression lt(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> BooleanExpression lt(Expression<E> e, Object c) { return null; }
	public static <E> BooleanExpression lt(Object c, Expression<E> e) { return null; }
	
	// Is Null
	public static BooleanExpression isNull(Expression<?> e) { return null; }
	
	// Like
	public static BooleanExpression like(Expression<?> e, Expression<String> pattern) { return null; }
	public static BooleanExpression like(Expression<?> e, String pattern) { return null; }
	public static BooleanExpression like(Object c, Expression<String> e) { return null; }
	
	// Concatenation
	@SuppressWarnings("unchecked")
	public static Expression<String> concat(Expression... e) { return null; }
	
	// Logic
	public static BooleanExpression not(BooleanExpression e) { return null; }
	public static BooleanExpression and(BooleanExpression... e) { return null; }
	public static BooleanExpression or(BooleanExpression... e) { return null; }
	
	// Aggregations
	public static Expression<Integer> count(Expression<?> e) { return null; }
	public static <E> Expression<E> max(Expression<E> e) { return null; }
	public static <E> Expression<E> min(Expression<E> e) { return null; }
	public static <E> Expression<E> sum(Expression<E> e) { return null; }

}
