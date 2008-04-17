package sql.old.builder.onetable;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sql.api.QueryHandler;
import sql.api.ResultReader;
import sql.api.StatementCallback;
import sql.dict.Column1;
import sql.dict.Table;
import sql.expr.Expression;

public class SelectBuilderTable<T extends Table> extends FromBuilder1<T> {

	protected List<Column1<T, ?>> columns = new ArrayList<Column1<T, ?>>();
	
	public SelectBuilderTable(QueryHandler handler, T table, Expression<Boolean> where, Column1<T, ?>... columns) {
		super(handler, table, where);
	}
	
	@SuppressWarnings("unchecked")
	public List uncheckedFind() {
		return handler.find(getSql(), getStatementCallback(), getResultReader());
	}
	
	@SuppressWarnings("unchecked")
	public Object uncheckedGet() {
		List list = uncheckedFind();
		if (list.size() != 1) throw new IllegalStateException();
		return list.iterator().next();
	}
	
	protected String getSql() {
		StringBuilder sb = new StringBuilder("SELECT ");
		for (Iterator<Column1<T, ?>> it = columns.iterator(); it.hasNext();) {
			sb.append(it.next().getName());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append(" FROM ");
		sb.append(table.getName());
		if (where != null) {
			sb.append(" WHERE ");
			sb.append(where.getSqlString());
		}
		return sb.toString();
	}
	
	protected StatementCallback getStatementCallback() {
		return new StatementCallback() {
			public void doInStatement(PreparedStatement stmt) {
				// do nothing
			}
		};
	}
	
	protected ResultReader<?> getResultReader() {
		return null;	// TODO
	}
	
}
