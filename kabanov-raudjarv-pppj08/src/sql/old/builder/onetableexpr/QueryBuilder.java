package sql.old.builder.onetableexpr;

import javax.sql.DataSource;
import sql.api.JdbcQueryHandler;
import sql.api.QueryHandler;
import sql.dict.Table;


public class QueryBuilder {

	protected QueryHandler handler;

	public QueryBuilder(QueryHandler handler) {
		this.handler = handler;
	}
	
	public QueryBuilder(DataSource dataSource) {
		this(new JdbcQueryHandler(dataSource));
	}
	
	public FromBuilder from(Table table) {
		return new FromBuilder(handler, table);
	}
	
}
