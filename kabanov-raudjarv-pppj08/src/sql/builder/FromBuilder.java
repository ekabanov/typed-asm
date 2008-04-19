package sql.builder;

import sql.dict.Table;
import sql.expr.BooleanExpression;

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
	
	public FromBuilder addConditions(BooleanExpression... conditions) {
		return (FromBuilder) super.addConditions(conditions);
	}
	
	public FromBuilder removeConditions(BooleanExpression... conditions) {
		return (FromBuilder) super.removeConditions(conditions);
	}
	
	public WhereBuilder where(BooleanExpression conditions) {
		addConditions(conditions);
		return new WhereBuilder(this);
	}

	// Closure
	
	public FromBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}

}
