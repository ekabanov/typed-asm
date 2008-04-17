package sql.dict;


public class Column1<T extends Table, C> {

	private T table;
	private String name;
	private Class<C> type;
	
	public Column1(T table, String name, Class<C> type) {
		this.table = table;
		this.name = name;
		this.type = type;
	}
	
	public T getTable() { return table; }
	public String getName() { return name; }
	public Class<C> getType() { return type; }
	
}
