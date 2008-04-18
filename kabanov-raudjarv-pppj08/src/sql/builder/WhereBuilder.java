package sql.builder;

import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.OrderByExpression;

public class WhereBuilder extends OrderByBuilder {

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
	
	// Order By
	
	public OrderByBuilder orderBy(OrderByExpression... orders) {
		// TODO
		return new OrderByBuilder(this);
	}
	
	// Closure
	
	public WhereBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
