package sql.builder.transform;

import sql.builder.select.SelectBuilderC0;
import sql.builder.select.SelectBuilderC2;

public interface ColumnAppenderC2<C1,C2> {
	SelectBuilderC2<C1,C2> append(SelectBuilderC0 builder);
}
