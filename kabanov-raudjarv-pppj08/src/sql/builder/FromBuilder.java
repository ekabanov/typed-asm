package sql.builder;

import sql.expr.WhereExpression;
import sql.expr.Table;

public class FromBuilder extends WhereBuilder {

	public FromBuilder(Builder builder) {
		super(builder);
	}
	
	// From
	
	public FromBuilder addTables(Table... tables) {
		return (FromBuilder) super.addTables(tables);
	}

	public FromBuilder removeTables(Table... tables) {
		return (FromBuilder) super.removeTables(tables);
	}
	
	// Where
	
	public FromBuilder addConditions(WhereExpression... conditions) {
		return (FromBuilder) super.addConditions(conditions);
	}
	
	public FromBuilder removeConditions(WhereExpression... conditions) {
		return (FromBuilder) super.removeConditions(conditions);
	}
	
	public WhereBuilder where(WhereExpression conditions) {
		addConditions(conditions);
		return new WhereBuilder(this);
	}

	// Closure
	
	public FromBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
