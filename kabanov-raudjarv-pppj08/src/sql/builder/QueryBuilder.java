package sql.builder;

import javax.sql.DataSource;
import sql.api.JdbcQueryHandler;
import sql.api.QueryHandler;
import sql.dict.Table;
import sql.expr.BooleanExpression;


public class QueryBuilder extends BaseBuilder {

	public QueryBuilder(BaseBuilder builder) {
		super(builder);
	}
	
	public QueryBuilder(QueryHandler handler) {
		_setHandler(handler);
	}
	
	public QueryBuilder(DataSource dataSource) {
		this(new JdbcQueryHandler(dataSource));
	}
	
	// From
	
	public QueryBuilder addTables(Table... tables) {
		return addTables(tables);
	}

	public QueryBuilder removeTables(Table... tables) {
		return removeTables(tables);
	}
	
	public FromBuilder from(Table... tables) {
		addTables(tables);
		return new FromBuilder(this);
	}
	
	// Where
	
	public QueryBuilder addConditions(BooleanExpression... conditions) {
		return addConditions(conditions);
	}
	
	public QueryBuilder removeConditions(BooleanExpression... conditions) {
		return removeConditions(conditions);
	}
	
	// Closure
	
	public QueryBuilder closure(Closure closure) {
		closure.apply(this);
		return this;
	}	
	
}
