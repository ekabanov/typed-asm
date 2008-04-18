package sql.builder.transform;

import sql.builder.select.SelectBuilderC0;
import sql.builder.select.SelectBuilderC1;

public interface ColumnAppenderC1<C1> {
	SelectBuilderC1<C1> append(SelectBuilderC0 builder);
}
