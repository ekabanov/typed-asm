package sql.old.builder.onetable;

import javax.sql.DataSource;
import sql.api.JdbcQueryHandler;
import sql.api.QueryHandler;
import sql.dict.Table;


public class QueryBuilder1 {

	protected QueryHandler handler;

	public QueryBuilder1(QueryHandler handler) {
		this.handler = handler;
	}
	
	public QueryBuilder1(DataSource dataSource) {
		this(new JdbcQueryHandler(dataSource));
	}
	
	public <T extends Table> FromBuilder1<T> from(T table) {
		return new FromBuilder1<T>(handler, table);
	}
	
}
