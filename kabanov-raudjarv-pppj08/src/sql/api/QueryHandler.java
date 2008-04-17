package sql.api;

import java.util.List;

public interface QueryHandler {

	<T> List<T> find(String sql, StatementCallback sc, ResultReader<T> rr);
	
}
