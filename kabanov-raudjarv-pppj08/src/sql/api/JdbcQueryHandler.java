package sql.api;

import java.util.List;
import javax.sql.DataSource;

public class JdbcQueryHandler implements QueryHandler {

	public JdbcQueryHandler(DataSource dataSource) {
	}
	
	public <T> List<T> find(String sql, StatementCallback sc, ResultReader<T> rr) {
		// TODO Auto-generated method stub
		return null;
	}

}
