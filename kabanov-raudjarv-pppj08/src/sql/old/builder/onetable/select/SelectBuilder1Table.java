package sql.old.builder.onetable.select;

import java.util.List;
import sql.api.QueryHandler;
import sql.dict.Column1;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetable.SelectBuilderTable;

public class SelectBuilder1Table<T extends Table,C1> extends SelectBuilderTable<T> {

	public SelectBuilder1Table(QueryHandler handler, T table, Expression<Boolean> where, Column1<T, C1> column) {
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
