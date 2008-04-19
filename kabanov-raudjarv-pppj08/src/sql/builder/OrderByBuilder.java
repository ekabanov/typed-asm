package sql.builder;

import sql.builder.select.SelectBuilderC1;
import sql.builder.select.SelectBuilderC2;
import sql.builder.select.SelectBuilderC3;
import sql.expr.WhereExpression;
import sql.expr.Expression;
import sql.expr.OrderByExpression;
import sql.expr.Table;

public class OrderByBuilder extends Builder {

	public OrderByBuilder(Builder builder) {
		super(builder);
	}
	
	// From
	
	public OrderByBuilder addTables(Table... tables) {
		return addTables(tables);
	}

	public OrderByBuilder removeTables(Table... tables) {
		return removeTables(tables);
	}
	
	// Where
	
	public OrderByBuilder addConditions(WhereExpression... conditions) {
		return addConditions(conditions);
	}
	
	public OrderByBuilder removeConditions(WhereExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Having
	
	public OrderByBuilder addHavingConditions(WhereExpression... conditions) {
		return (OrderByBuilder) super.addHavingConditions(conditions);
	}
	
	public OrderByBuilder removeHavingConditions(WhereExpression... conditions) {
		return (OrderByBuilder) super.removeHavingConditions(conditions);
	}	
	
	// Order by
	
	public OrderByBuilder addDirections(OrderByExpression... directions) {
		return (OrderByBuilder) super.addDirections(directions);
	}
	
	public OrderByBuilder removeDirections(OrderByExpression... directions) {
		return (OrderByBuilder) super.removeDirections(directions);
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
	
	public OrderByBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
