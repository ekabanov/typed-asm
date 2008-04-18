package sql.builder.select;

import java.util.List;
import sql.builder.BaseBuilder;
import sql.builder.Closure;
import sql.builder.QueryBuilder;
import sql.builder.transform.ColumnAppenderC1;
import sql.builder.transform.ColumnAppenderC2;
import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.Expression;

public class SelectBuilderC1<C1> extends SelectBuilder {

	public SelectBuilderC1(BaseBuilder builder) {
		super(builder);
	}
	
	// From
	
	public SelectBuilderC1<C1> addTables(Table... tables) {
		return addTables(tables);
	}

	public SelectBuilderC1<C1> removeTables(Table... tables) {
		return removeTables(tables);
	}
	
	// Where
	
	public SelectBuilderC1<C1> addConditions(BooleanExpression... conditions) {
		return addConditions(conditions);
	}
	
	public SelectBuilderC1<C1> removeConditions(BooleanExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Select
	
	@SuppressWarnings("unchecked")
	public Expression<C1> getColumn1() {
		return (Expression<C1>) _getColumnFromEnd(0);
	}
	
	public <C2> SelectBuilderC2<C1,C2> addColumn(Expression<C2> column) {
		addColumn(column);
		return new SelectBuilderC2<C1, C2>(this);
	}
	
	public QueryBuilder removeColumn1() {
		_removeColumnFromEnd(0);
		return new QueryBuilder(this);
	}
	
	public QueryBuilder removeColumn() {
		return removeColumn1();
	}
	
	@SuppressWarnings("unchecked")
	public List<C1> find() {
		return uncheckedFind();
	}
	
	@SuppressWarnings("unchecked")
	public C1 get() {
		return (C1) uncheckedGet();
	}
	
	// Closure
	
	public SelectBuilderC1<C1> closure(Closure closure) {
		super.closure(closure);
		return this;
	}	
	
	public void closure(ClosureC1<C1> closure) {
		closure.apply(this);
	}
	
	public static interface ClosureC1<C1> {
		void apply(SelectBuilderC1<C1> builder);
	}
	
	// Appender
	
	public <C2> SelectBuilderC2<C1,C2> appender(ColumnAppenderC1<C2> appender) {
		return new SelectBuilderC2<C1,C2>(appender.append(new SelectBuilderC0(this)));
	}
	
	public <C2,C3> SelectBuilderC3<C1,C2,C3> appender(ColumnAppenderC2<C2,C3> appender) {
		return new SelectBuilderC3<C1,C2,C3>(appender.append(new SelectBuilderC0(this)));
	}
	
}
