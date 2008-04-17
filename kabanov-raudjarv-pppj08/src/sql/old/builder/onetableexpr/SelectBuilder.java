package sql.old.builder.onetableexpr;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sql.api.QueryHandler;
import sql.api.ResultReader;
import sql.api.StatementCallback;
import sql.dict.Table;
import sql.expr.Expression;

public class SelectBuilder extends FromBuilder {

	protected List<Expression<?>> columns = new ArrayList<Expression<?>>();
	
	public SelectBuilder(QueryHandler handler, Table table, Expression<Boolean> where, Expression<?>... columns) {
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
		for (Iterator<Expression<?>> it = columns.iterator(); it.hasNext();) {
			sb.append(it.next().getSqlString());
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
