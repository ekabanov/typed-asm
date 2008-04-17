package sql.builder;

import sql.builder.select.SelectBuilderC1;
import sql.builder.select.SelectBuilderC2;
import sql.builder.select.SelectBuilderC3;
import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.Expression;

public class WhereBuilder extends BaseBuilder {

	public WhereBuilder(BaseBuilder builder) {
		super(builder);
	}
	
	// From
	
	public WhereBuilder addTables(Table... tables) {
		return addTables(tables);
	}

	public WhereBuilder removeTables(Table... tables) {
		return removeTables(tables);
	}
	
	// Where
	
	public WhereBuilder addConditions(BooleanExpression... conditions) {
		return addConditions(conditions);
	}
	
	public WhereBuilder removeConditions(BooleanExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Select

	public <C1> SelectBuilderC1<C1> select(Expression<C1> c1) {
		addColumn(c1);
		return new SelectBuilderC1<C1>(this);
	}

	public <C1, C2> SelectBuilderC2<C1, C2> select(Expression<C1> c1, Expression<C2> c2) {
		addColumn(c1);
		addColumn(c2);
		return new SelectBuilderC2<C1, C2>(this);
	}

	public <C1, C2, C3> SelectBuilderC3<C1, C2, C3> select(Expression<C1> c1, Expression<C2> c2, Expression<C3> c3) {
		addColumn(c1);
		addColumn(c2);
		addColumn(c3);
		return new SelectBuilderC3<C1, C2, C3>(this);
	}
	
	// Closure
	
	public WhereBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
