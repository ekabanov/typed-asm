package sql.expr;

import java.util.List;

public abstract class Table implements FromExpression {
	
	private String alias;
	
	public Table() {
	}
	
	public Table(String alias) {
	}
	
	public String getAlias() { return alias; }
	public abstract String getName();
	
	public String getSqlString() {
		return null;
	}
	public List<Object> getSqlArguments() {
		return null;
	}
	public Class<Object> getType() {
		return null;
	}
}
