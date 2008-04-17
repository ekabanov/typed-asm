package sql.old.builder.onetable.select;

import java.util.List;
import sql.api.QueryHandler;
import sql.dict.Column1;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetable.SelectBuilderTable;
import sql.tuple.Tuple2;

public class SelectBuilder2Table<T extends Table,C1,C2> extends SelectBuilderTable<T> {

	public SelectBuilder2Table(QueryHandler handler, T table, Expression<Boolean> where, Column1<T, C1> column1, Column1<T, C2> column2) {
		super(handler, table, where);
		columns.add(column1);
		columns.add(column2);
	}

	@SuppressWarnings("unchecked")
	public List<Tuple2<C1,C2>> find() {
		return uncheckedFind();
	}
	
	@SuppressWarnings("unchecked")
	public Tuple2<C1,C2> get() {
		return (Tuple2<C1,C2>) uncheckedGet();
	}
	
}
