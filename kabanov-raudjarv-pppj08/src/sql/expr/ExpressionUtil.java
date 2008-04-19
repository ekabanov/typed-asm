package sql.expr;


/**
 * @author Rein Raudjärv
 */
public class ExpressionUtil {
	
	/*
	 * #####################################################
	 *                     W H E R E
	 * #####################################################
	 */
	
	// Logic
	public static WhereExpression not(WhereExpression e) { return null; }
	public static WhereExpression and(WhereExpression... e) { return null; }
	public static WhereExpression or(WhereExpression... e) { return null; }
	
	// Arithmetic
	public static <E> WhereExpression eq(SelectExpression<E> e1, SelectExpression<E> e2) { return null; }
	public static <E> WhereExpression eq(SelectExpression<E> e, Object c) { return null; }
	public static <E> WhereExpression eq(Object c, SelectExpression<E> e) { return null; }
	
	public static <E> WhereExpression gt(SelectExpression<E> e1, SelectExpression<E> e2) { return null; }
	public static <E> WhereExpression gt(SelectExpression<E> e, Object c) { return null; }
	public static <E> WhereExpression gt(Object c, SelectExpression<E> e) { return null; }
	
	public static <E> WhereExpression lt(SelectExpression<E> e1, SelectExpression<E> e2) { return null; }
	public static <E> WhereExpression lt(SelectExpression<E> e, Object c) { return null; }
	public static <E> WhereExpression lt(Object c, SelectExpression<E> e) { return null; }
	
	// Is Null
	public static WhereExpression isNull(SelectExpression<?> e) { return null; }
	
	// Like
	public static WhereExpression like(SelectExpression<?> e, SelectExpression<String> pattern) { return null; }
	public static WhereExpression like(SelectExpression<?> e, String pattern) { return null; }
	public static WhereExpression like(Object c, SelectExpression<String> e) { return null; }

	/*
	 * #####################################################
	 *                    S E L E C T
	 * #####################################################
	 */
	
	// Constant
	public static <E> SelectExpression<E> constant(E value) { return null; }
	
	// Alias
	public static <E> Alias<E> alias(SelectExpression<E> e, String alias) { return null; }
	public static <E> Alias<E> alias(SelectExpression<E> e) { return null; }
	
	// Concatenation
	@SuppressWarnings("unchecked")
	public static SelectExpression<String> concat(SelectExpression... e) { return null; }
	
	// Aggregations
	public static SelectExpression<Integer> count(SelectExpression<?> e) { return null; }
	public static <E> SelectExpression<E> max(SelectExpression<E> e) { return null; }
	public static <E> SelectExpression<E> min(SelectExpression<E> e) { return null; }
	public static <E> SelectExpression<E> sum(SelectExpression<E> e) { return null; }
	
	// Unchecked
	public static <E> SelectExpression<E> unchecked(Class<E> type, String sql, Object... args) { return null; }
	
	/*
	 * #####################################################
	 *                  O R D E R   B Y
	 * #####################################################
	 */
	
	// Ordering direction
	public static OrderByExpression asc(ColumnExpression<?> e) { return null; }
	public static OrderByExpression desc(ColumnExpression<?> e) { return null; }

}
