package sql.api;

import java.sql.ResultSet;

public interface ResultReader<T> {

	T read(ResultSet rs);
	
}
