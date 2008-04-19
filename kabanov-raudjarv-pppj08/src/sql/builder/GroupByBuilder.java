package sql.builder;

import sql.expr.WhereExpression;
import sql.expr.Table;

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
	
	public GroupByBuilder addConditions(WhereExpression... conditions) {
		return (GroupByBuilder) super.addConditions(conditions);
	}
	
	public GroupByBuilder removeConditions(WhereExpression... conditions) {
		return (GroupByBuilder) super.removeConditions(conditions);
	}
	
	// Having
	
	public GroupByBuilder addHavingConditions(WhereExpression... conditions) {
		return (GroupByBuilder) super.addHavingConditions(conditions);
	}
	
	public GroupByBuilder removeHavingConditions(WhereExpression... conditions) {
		return (GroupByBuilder) super.removeHavingConditions(conditions);
	}
	
	public HavingBuilder having(WhereExpression... conditions) {
		addHavingConditions(conditions);
		return new HavingBuilder(this);
	}
	
	// Closure
	
	public HavingBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
