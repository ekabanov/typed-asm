package sql.expr;

import java.util.List;

public interface TableExpression extends Expression<List<Object>> {
	String getName();
}
