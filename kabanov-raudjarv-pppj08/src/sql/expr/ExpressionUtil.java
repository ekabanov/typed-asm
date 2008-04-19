package sql.expr;


/**
 * @author Rein Raudjärv
 */
public class ExpressionUtil {
	
	/*
	 * #####################################################
	 *                      F R O M
	 * #####################################################
	 */
	
	// Unchecked
	public static FromExpression uncheckedFrom(String sql, Object... args) { return null; }
	public static UncheckedFromBuilder uncheckedFrom() { return null; }
	public static interface UncheckedFromBuilder {
		UncheckedFromBuilder append(String sql, Object... args);
		FromExpression end();
	}
	
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
	public static <E> WhereExpression eq(SelectExpression<E> e, E c) { return null; }
	public static <E> WhereExpression eq(E c, SelectExpression<E> e) { return null; }
	
	public static <E> WhereExpression gt(SelectExpression<E> e1, SelectExpression<E> e2) { return null; }
	public static <E> WhereExpression gt(SelectExpression<E> e, E c) { return null; }
	public static <E> WhereExpression gt(E c, SelectExpression<E> e) { return null; }
	
	public static <E> WhereExpression lt(SelectExpression<E> e1, SelectExpression<E> e2) { return null; }
	public static <E> WhereExpression lt(SelectExpression<E> e, E c) { return null; }
	public static <E> WhereExpression lt(E c, SelectExpression<E> e) { return null; }
	
	// Is Null
	public static WhereExpression isNull(SelectExpression<?> e) { return null; }
	
	// Like
	public static WhereExpression like(SelectExpression<?> e, SelectExpression<String> pattern) { return null; }
	public static WhereExpression like(SelectExpression<?> e, String pattern) { return null; }
	public static WhereExpression like(Object c, SelectExpression<String> e) { return null; }
	
	// In
	public static <E> InBuilder<E> in(SelectExpression<E> e) { return null; }
	public static interface InBuilder<E> {
		InBuilder<E> value(SelectExpression<E> e);
		InBuilder<E> value(E e);
		WhereExpression end();
	}
	
	// Exists
	public static WhereExpression exists(FromExpression e) { return null; }

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
	public static ConcatBuilder concat() { return null; }
	public static interface ConcatBuilder {
		ConcatBuilder value(SelectExpression<?> e);
		ConcatBuilder value(Object e);
		SelectExpression<String> end();
	}
	
	// Aggregations
	public static SelectExpression<Integer> count(SelectExpression<?> e) { return null; }
	public static <E> SelectExpression<E> max(SelectExpression<E> e) { return null; }
	public static <E> SelectExpression<E> min(SelectExpression<E> e) { return null; }
	public static <E> SelectExpression<E> sum(SelectExpression<E> e) { return null; }
	public static <E> SelectExpression<E> average(SelectExpression<E> e) { return null; }
	
	// Unchecked
	public static <E> SelectExpression<E> unchecked(Class<E> type, String sql, Object... args) { return null; }
	public static <E> UncheckedBuilder<E> unchecked(Class<E> type) { return null; }
	public static interface UncheckedBuilder<E> {
		UncheckedBuilder<E> append(String sql, Object... args);
		SelectExpression<E> end();
	}
	
	/*
	 * #####################################################
	 *                  O R D E R   B Y
	 * #####################################################
	 */
	
	// Ordering direction
	public static OrderByExpression asc(ColumnExpression<?> e) { return null; }
	public static OrderByExpression desc(ColumnExpression<?> e) { return null; }

}
