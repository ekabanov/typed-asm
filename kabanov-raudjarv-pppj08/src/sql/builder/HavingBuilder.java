package sql.builder;

import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.OrderByExpression;

public class HavingBuilder extends OrderByBuilder {

	public HavingBuilder(BaseBuilder builder) {
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
	
	public HavingBuilder addConditions(BooleanExpression... conditions) {
		return (HavingBuilder) super.addConditions(conditions);
	}
	
	public HavingBuilder removeConditions(BooleanExpression... conditions) {
		return (HavingBuilder) super.removeConditions(conditions);
	}
	
	// Having
	
	public HavingBuilder addHavingConditions(BooleanExpression... conditions) {
		return (HavingBuilder) super.addHavingConditions(conditions);
	}
	
	public HavingBuilder removeHavingConditions(BooleanExpression... conditions) {
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
