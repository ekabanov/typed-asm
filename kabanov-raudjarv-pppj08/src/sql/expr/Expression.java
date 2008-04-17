package sql.expr;

import java.util.List;

public interface Expression<E> {

	public String getSqlString();
	public List<Object> getSqlArguments();
	public Class<E> getType();
	
}
