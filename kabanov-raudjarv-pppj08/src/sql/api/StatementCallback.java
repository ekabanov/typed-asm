package sql.api;

import java.sql.PreparedStatement;

public interface StatementCallback {

	void doInStatement(PreparedStatement stmt);
	
}
