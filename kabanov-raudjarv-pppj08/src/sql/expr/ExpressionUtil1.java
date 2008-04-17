package sql.expr;


public class ExpressionUtil1 {

	public static <E> Expression<E> constant(E value) { return null; }
	public static <E> Expression<Boolean> eq(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> Expression<Boolean> gt(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> Expression<Boolean> lt(Expression<E> e1, Expression<E> e2) { return null; }
	public static <E> Expression<Boolean> like(Expression<E> e, Expression<String> pattern) { return null; }
	public static Expression<Boolean> not(Expression<Boolean> e) { return null; }
	public static Expression<Boolean> and(Expression<Boolean>... e) { return null; }
	public static Expression<Boolean> or(Expression<Boolean>... e) { return null; }
	
}
