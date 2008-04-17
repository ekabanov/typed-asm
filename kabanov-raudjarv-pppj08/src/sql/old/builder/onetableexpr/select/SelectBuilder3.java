package sql.old.builder.onetableexpr.select;

import java.util.List;
import sql.api.QueryHandler;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetableexpr.SelectBuilder;
import sql.tuple.Tuple3;

public class SelectBuilder3<C1,C2,C3> extends SelectBuilder {

	public SelectBuilder3(QueryHandler handler, Table table, Expression<Boolean> where, Expression<C1> column1, Expression<C2> column2, Expression<C3> column3) {
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
