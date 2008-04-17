package sql.old.builder.onetable.select;

import java.util.List;
import sql.api.QueryHandler;
import sql.dict.Column1;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetable.SelectBuilderTable;
import sql.tuple.Tuple3;

public class SelectBuilder3Table<T extends Table,C1,C2,C3> extends SelectBuilderTable<T> {

	public SelectBuilder3Table(QueryHandler handler, T table, Expression<Boolean> where, Column1<T, C1> column1, Column1<T, C2> column2, Column1<T, C3> column3) {
		super(handler, table, where);
		columns.add(column1);
		columns.add(column2);
		columns.add(column3);
	}

	@SuppressWarnings("unchecked")
	public List<Tuple3<C1,C2,C3>> find() {
		return uncheckedFind();
	}
	
	@SuppressWarnings("unchecked")
	public Tuple3<C1,C2,C3> get() {
		return (Tuple3<C1,C2,C3>) uncheckedGet();
	}

}
