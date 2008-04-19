package sql.builder;

import sql.expr.WhereExpression;
import sql.expr.ColumnExpression;
import sql.expr.Table;

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
	
	public WhereBuilder addConditions(WhereExpression... conditions) {
		return addConditions(conditions);
	}
	
	public WhereBuilder removeConditions(WhereExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Group by
	
	@SuppressWarnings("unchecked")
	public GroupByBuilder groupBy(ColumnExpression columns) {
		addGroups(columns);
		return new GroupByBuilder(this);
	}
	
	// Closure
	
	public WhereBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
