package sql.builder.select;

import sql.builder.Builder;
import sql.builder.Closure;
import sql.builder.transform.ColumnAppenderC1;
import sql.builder.transform.ColumnAppenderC2;
import sql.builder.transform.ColumnAppenderC3;
import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.Expression;

public class SelectBuilderC0 extends SelectBuilder {

	public SelectBuilderC0(Builder builder) {
		super(builder);
	}
	
	// From
	
	public SelectBuilderC0 addTables(Table... tables) {
		return addTables(tables);
	}

	public SelectBuilderC0 removeTables(Table... tables) {
		return removeTables(tables);
	}
	
	// Where
	
	public SelectBuilderC0 addConditions(BooleanExpression... conditions) {
		return addConditions(conditions);
	}
	
	public SelectBuilderC0 removeConditions(BooleanExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Select
	
	public <C1> SelectBuilderC1<C1> addColumn(Expression<C1> column) {
		addColumn(column);
		return new SelectBuilderC1<C1>(this);
	}
	
	// Closure
	
	public SelectBuilderC0 closure(Closure closure) {
		super.closure(closure);
		return this;
	}
	
	public void closure(ClosureC0 closure) {
		closure.apply(this);
	}
	
	public static interface ClosureC0 {
		void apply(SelectBuilderC0 builder);
	}
	
	// Appender
	
	public <C1> SelectBuilderC1<C1> appender(ColumnAppenderC1<C1> appender) {
		return appender.append(this);
	}
	
	public <C1,C2> SelectBuilderC2<C1,C2> appender(ColumnAppenderC2<C1,C2> appender) {
		return appender.append(this);
	}
	
	public <C1,C2,C3> SelectBuilderC3<C1,C2,C3> appender(ColumnAppenderC3<C1,C2,C3> appender) {
		return appender.append(this);
	}
	
}
