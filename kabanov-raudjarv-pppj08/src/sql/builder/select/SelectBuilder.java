package sql.builder.select;

import java.util.List;
import sql.builder.Builder;
import sql.expr.FromExpression;

public abstract class SelectBuilder extends Builder implements FromExpression {

	public SelectBuilder(Builder builder) {
		super(builder);
	}
	
	public String getSqlString() {
		return super.getSqlString();
	}

	public List<Object> getSqlArguments() {
		return null;
	}
	
	public Class<Object> getType() {
		return null;
	}

}
