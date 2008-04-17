package sql.dict;

import java.util.List;
import sql.expr.Expression;

public class Column2<T extends Table1, C> implements Expression<C> {

	private T table;
	private String name;
	private Class<C> type;
	
	public Column2(T table, String name, Class<C> type) {
		this.table = table;
		this.name = name;
		this.type = type;
	}
	
	public T getTable() { return table; }
	public String getName() { return name; }
	public Class<C> getType() { return type; }

	public String getSqlString() { return name; }
	public List<Object> getSqlArguments() { return null; }
	
}
