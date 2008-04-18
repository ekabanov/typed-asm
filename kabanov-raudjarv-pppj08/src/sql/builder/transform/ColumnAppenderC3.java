package sql.builder.transform;

import sql.builder.select.SelectBuilderC0;
import sql.builder.select.SelectBuilderC3;

public interface ColumnAppenderC3<C1,C2,C3> {

	SelectBuilderC3<C1,C2,C3> append(SelectBuilderC0 builder);
	
}
