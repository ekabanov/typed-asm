package sql.builder.select;

import java.util.List;
import sql.builder.Builder;
import sql.builder.Closure;
import sql.builder.transform.ColumnAppenderC1;
import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.Expression;
import sql.tuple.Tuple2;

public class SelectBuilderC2<C1,C2> extends SelectBuilder {

	public SelectBuilderC2(Builder builder) {
		super(builder);
	}
	
	// From
	
	public SelectBuilderC2<C1,C2> addTables(Table... tables) {
		return addTables(tables);
	}

	public SelectBuilderC2<C1,C2> removeTables(Table... tables) {
		return removeTables(tables);
	}
	
	// Where
	
	public SelectBuilderC2<C1,C2> addConditions(BooleanExpression... conditions) {
		return addConditions(conditions);
	}
	
	public SelectBuilderC2<C1,C2> removeConditions(BooleanExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Select
	
	@SuppressWarnings("unchecked")
	public Expression<C1> getColumn1() {
		return (Expression<C1>) _getColumnFromEnd(1);
	}
	
	@SuppressWarnings("unchecked")
	public Expression<C2> getColumn2() {
		return (Expression<C2>) _getColumnFromEnd(0);
	}
	
	public <C3> SelectBuilderC3<C1,C2,C3> addColumn(Expression<C3> column) {
		addColumn(column);
		return new SelectBuilderC3<C1, C2, C3>(this);
	}
	
	public SelectBuilderC1<C2> removeColumn1() {
		_removeColumnFromEnd(1);
		return new SelectBuilderC1<C2>(this);
	}
	
	public SelectBuilderC1<C1> removeColumn2() {
		_removeColumnFromEnd(0);
		return new SelectBuilderC1<C1>(this);
	}
	
	public SelectBuilderC1<C1> removeColumn() {
		return removeColumn2();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tuple2<C1,C2>> list() {
		return uncheckedFind();
	}
	
	@SuppressWarnings("unchecked")
	public Tuple2<C1,C2> get() {
		return (Tuple2<C1,C2>) uncheckedGet();
	}
	
	// Closure
	
	public SelectBuilderC2<C1,C2> closure(Closure closure) {
		super.closure(closure);
		return this;
	}
	
	public void closure(ClosureC2<C1,C2> closure) {
		closure.apply(this);
	}
	
	public static interface ClosureC2<C1,C2> {
		void apply(SelectBuilderC2<C1,C2> builder);
	}
	
	// Appender
	
	public <C3> SelectBuilderC3<C1,C2,C3> appender(ColumnAppenderC1<C3> appender) {
		return new SelectBuilderC3<C1,C2,C3>(appender.append(new SelectBuilderC0(this)));
	}

}
