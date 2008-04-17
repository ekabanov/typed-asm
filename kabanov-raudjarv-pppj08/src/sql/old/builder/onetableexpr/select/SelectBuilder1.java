package sql.old.builder.onetableexpr.select;

import java.util.List;
import sql.api.QueryHandler;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetableexpr.SelectBuilder;

public class SelectBuilder1<C1> extends SelectBuilder {

	public SelectBuilder1(QueryHandler handler, Table table, Expression<Boolean> where, Expression<C1> column) {
		super(handler, table, where);
		columns.add(column);
	}

	@SuppressWarnings("unchecked")
	public List<C1> find() {
		return uncheckedFind();
	}
	
	@SuppressWarnings("unchecked")
	public C1 get() {
		return (C1) uncheckedGet();
	}
	
}
