package sql.old.builder.onetable;

import sql.api.QueryHandler;
import sql.dict.Column1;
import sql.dict.Table;
import sql.expr.Expression;
import sql.old.builder.onetable.select.SelectBuilder1Table;
import sql.old.builder.onetable.select.SelectBuilder2Table;
import sql.old.builder.onetable.select.SelectBuilder3Table;

public class FromBuilder1<T extends Table> extends QueryBuilder1 {

	protected T table;
	protected Expression<Boolean> where;
	
	public FromBuilder1(QueryHandler handler, T table) {
		super(handler);
		this.table = table;
	}
	
	protected FromBuilder1(QueryHandler handler, T table, Expression<Boolean> where) {
		this(handler, table);
		this.where = where;
	}
	
	// Where

	public FromBuilder1<T> where(Expression<Boolean> where) {
		this.where = where;
		return this;
	}
	
	// Select
	
	public <C1> SelectBuilder1Table<T, C1> select(Column1<T, C1> c1) { 
		return new SelectBuilder1Table<T, C1>(handler, table, where, c1);
	}
	
	public <C1, C2> SelectBuilder2Table<T, C1, C2> select(Column1<T, C1> c1, Column1<T, C2> c2) { 
		return new SelectBuilder2Table<T, C1, C2>(handler, table, where, c1, c2);
	}
	
	public <C1, C2, C3> SelectBuilder3Table<T, C1, C2, C3> select(Column1<T, C1> c1, Column1<T, C2> c2, Column1<T, C3> c3) { 
		return new SelectBuilder3Table<T, C1, C2, C3>(handler, table, where, c1, c2, c3);
	}
	
}
