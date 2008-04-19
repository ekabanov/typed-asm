package sql.builder;

import sql.dict.Table;
import sql.expr.BooleanExpression;

public class GroupByBuilder extends HavingBuilder {

	public GroupByBuilder(Builder builder) {
		super(builder);
	}
	
	// From
	
	public GroupByBuilder addTables(Table... tables) {
		return (GroupByBuilder) super.addTables(tables);
	}

	public GroupByBuilder removeTables(Table... tables) {
		return (GroupByBuilder) super.removeTables(tables);
	}
	
	// Where
	
	public GroupByBuilder addConditions(BooleanExpression... conditions) {
		return (GroupByBuilder) super.addConditions(conditions);
	}
	
	public GroupByBuilder removeConditions(BooleanExpression... conditions) {
		return (GroupByBuilder) super.removeConditions(conditions);
	}
	
	// Having
	
	public GroupByBuilder addHavingConditions(BooleanExpression... conditions) {
		return (GroupByBuilder) super.addHavingConditions(conditions);
	}
	
	public GroupByBuilder removeHavingConditions(BooleanExpression... conditions) {
		return (GroupByBuilder) super.removeHavingConditions(conditions);
	}
	
	public HavingBuilder having(BooleanExpression... conditions) {
		addHavingConditions(conditions);
		return new HavingBuilder(this);
	}
	
	// Closure
	
	public HavingBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
