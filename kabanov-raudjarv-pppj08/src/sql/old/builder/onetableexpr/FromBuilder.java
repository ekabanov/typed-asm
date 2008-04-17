package sql.old.builder.onetableexpr;

import sql.api.QueryHandler;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetableexpr.select.SelectBuilder1;
import sql.old.builder.onetableexpr.select.SelectBuilder2;
import sql.old.builder.onetableexpr.select.SelectBuilder3;

public class FromBuilder extends QueryBuilder {

	protected Table table;
	protected Expression<Boolean> where;
	
	public FromBuilder(QueryHandler handler, Table table) {
		super(handler);
		this.table = table;
	}
	
	protected FromBuilder(QueryHandler handler, Table table, Expression<Boolean> where) {
		this(handler, table);
		this.where = where;
	}
	
	// Where

	public FromBuilder where(Expression<Boolean> where) {
		this.where = where;
		return this;
	}
	
	// Select
	
	public <C1> SelectBuilder1<C1> select(Expression<C1> c1) { 
		return new SelectBuilder1<C1>(handler, table, where, c1);
	}
	
	public <C1, C2> SelectBuilder2<C1, C2> select(Expression<C1> c1, Expression<C2> c2) {
		return new SelectBuilder2<C1, C2>(handler, table, where, c1, c2);
	}
	
	public <C1, C2, C3> SelectBuilder3<C1, C2, C3> select(Expression<C1> c1, Expression<C2> c2, Expression<C3> c3) { 
		return new SelectBuilder3<C1, C2, C3>(handler, table, where, c1, c2, c3);
	}
	
}
