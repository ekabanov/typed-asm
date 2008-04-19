package sql.builder;

import sql.dict.Table;
import sql.expr.BooleanExpression;
import sql.expr.NamedExpression;

public class WhereBuilder extends GroupByBuilder {

	public WhereBuilder(Builder builder) {
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
	
	// Group by
	
	@SuppressWarnings("unchecked")
	public GroupByBuilder groupBy(NamedExpression columns) {
		addGroups(columns);
		return new GroupByBuilder(this);
	}
	
	// Closure
	
	public WhereBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
