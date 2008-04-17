package sql.old.builder.onetableexpr.select;

import java.util.List;
import sql.api.QueryHandler;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetableexpr.SelectBuilder;
import sql.tuple.Tuple2;

public class SelectBuilder2<C1,C2> extends SelectBuilder {

	public SelectBuilder2(QueryHandler handler, Table table, Expression<Boolean> where, Expression<C1> column1, Expression<C2> column2) {
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
