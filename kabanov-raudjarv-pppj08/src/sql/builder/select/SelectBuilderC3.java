package sql.builder.select;

import java.util.List;
import sql.builder.Builder;
import sql.builder.Closure;
import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.Expression;
import sql.tuple.Tuple3;

public class SelectBuilderC3<C1,C2,C3> extends SelectBuilder {

	public SelectBuilderC3(Builder builder) {
		super(builder);
	}
	
	// From
	
	public SelectBuilderC3<C1,C2,C3> addTables(Table... tables) {
		return addTables(tables);
	}

	public SelectBuilderC3<C1,C2,C3> removeTables(Table... tables) {
		return removeTables(tables);
	}
	
	// Where
	
	public SelectBuilderC3<C1,C2,C3> addConditions(BooleanExpression... conditions) {
		return addConditions(conditions);
	}
	
	public SelectBuilderC3<C1,C2,C3> removeConditions(BooleanExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Select
	
	@SuppressWarnings("unchecked")
	public Expression<C1> getColumn1() {
		return (Expression<C1>) _getColumnFromEnd(2);
	}
	
	@SuppressWarnings("unchecked")
	public Expression<C2> getColumn2() {
		return (Expression<C2>) _getColumnFromEnd(1);
	}
	
	@SuppressWarnings("unchecked")
	public Expression<C3> getColumn3() {
		return (Expression<C3>) _getColumnFromEnd(0);
	}
	
	public SelectBuilderC2<C2,C3> removeColumn1() {
		_removeColumnFromEnd(2);
		return new SelectBuilderC2<C2,C3>(this);
	}
	
	public SelectBuilderC2<C1,C3> removeColumn2() {
		_removeColumnFromEnd(1);
		return new SelectBuilderC2<C1,C3>(this);
	}
	
	public SelectBuilderC2<C1,C2> removeColumn3() {
		_removeColumnFromEnd(0);
		return new SelectBuilderC2<C1,C2>(this);
	}
	
	public SelectBuilderC2<C1,C2> removeColumn() {
		return removeColumn3();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Tuple3<C1,C2,C3>> find() {
		return uncheckedFind();
	}
	
	@SuppressWarnings("unchecked")
	public Tuple3<C1,C2,C3> get() {
		return (Tuple3<C1,C2,C3>) uncheckedGet();
	}
	
	// Closure
	
	public SelectBuilderC3<C1,C2,C3> closure(Closure closure) {
		super.closure(closure);
		return this;
	}
	
	public void closure(ClosureC3<C1,C2,C3> closure) {
		closure.apply(this);
	}
	
	public static interface ClosureC3<C1,C2,C3> {
		void apply(SelectBuilderC3<C1,C2,C3> builder);
	}	

}
