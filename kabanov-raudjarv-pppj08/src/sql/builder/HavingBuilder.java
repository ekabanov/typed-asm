package sql.builder;

import sql.expr.WhereExpression;
import sql.expr.OrderByExpression;
import sql.expr.Table;

public class HavingBuilder extends OrderByBuilder {

	public HavingBuilder(Builder builder) {
		super(builder);
	}
	
	// From
	
	public HavingBuilder addTables(Table... tables) {
		return (HavingBuilder) super.addTables(tables);
	}

	public HavingBuilder removeTables(Table... tables) {
		return (HavingBuilder) super.removeTables(tables);
	}
	
	// Where
	
	public HavingBuilder addConditions(WhereExpression... conditions) {
		return (HavingBuilder) super.addConditions(conditions);
	}
	
	public HavingBuilder removeConditions(WhereExpression... conditions) {
		return (HavingBuilder) super.removeConditions(conditions);
	}
	
	// Having
	
	public HavingBuilder addHavingConditions(WhereExpression... conditions) {
		return (HavingBuilder) super.addHavingConditions(conditions);
	}
	
	public HavingBuilder removeHavingConditions(WhereExpression... conditions) {
		return (HavingBuilder) super.removeHavingConditions(conditions);
	}
	
	// Order By
	
	public OrderByBuilder orderBy(OrderByExpression... orders) {
		// TODO
		return new OrderByBuilder(this);
	}
	
	// Closure
	
	public HavingBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
