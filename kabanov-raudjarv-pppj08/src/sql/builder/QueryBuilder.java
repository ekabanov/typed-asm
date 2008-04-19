package sql.builder;

import javax.sql.DataSource;
import sql.api.JdbcQueryHandler;
import sql.api.QueryHandler;
import sql.expr.FromExpression;
import sql.expr.WhereExpression;


public class QueryBuilder extends Builder {

	public QueryBuilder(Builder builder) {
		super(builder);
	}
	
	public QueryBuilder(QueryHandler handler) {
		_setHandler(handler);
	}
	
	public QueryBuilder(DataSource dataSource) {
		this(new JdbcQueryHandler(dataSource));
	}
	
	// From
	
	public QueryBuilder addTables(FromExpression... tables) {
		return (QueryBuilder) super.addTables(tables);
	}

	public QueryBuilder removeTables(FromExpression... tables) {
		return (QueryBuilder) super.removeTables(tables);
	}
	
	public FromBuilder from(FromExpression... tables) {
		addTables(tables);
		return new FromBuilder(this);
	}
	
	// Where
	
	public QueryBuilder addConditions(WhereExpression... conditions) {
		return addConditions(conditions);
	}
	
	public QueryBuilder removeConditions(WhereExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Closure
	
	public QueryBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}	
	
}
