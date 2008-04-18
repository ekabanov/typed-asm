package sql.dict;

import java.util.List;
import sql.expr.NamedExpression;

public class Column<E> implements NamedExpression<E> {

	private Table table;
	private String name;
	private Class<E> type;
	
	public Column(Table table, String name, Class<E> type) {
		this.table = table;
		this.name = name;
		this.type = type;
	}
	
	public Table getTable() { return table; }
	public String getName() { return name; }
	public Class<E> getType() { return type; }

	public String getSqlString() { return name; }
	public List<Object> getSqlArguments() { return null; }
	
}
