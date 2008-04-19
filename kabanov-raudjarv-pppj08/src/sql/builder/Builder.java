package sql.builder;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import sql.api.QueryHandler;
import sql.api.ResultReader;
import sql.api.StatementCallback;
import sql.expr.WhereExpression;
import sql.expr.Expression;
import sql.expr.ColumnExpression;
import sql.expr.OrderByExpression;
import sql.expr.Table;

/**
 * SQL Select builder implementation.
 * <p>
 * Provides all the functionality protected. 
 * Subclasses have to publish the functionality accordingly.
 * </p><p>
 * Methods starting with underscore are for internal use.
 * </p><p>
 * The order of syntax:
 * <ol>
 *   <li>from</li>
 *   <li>where</li>
 *   <li>select</li>
 * </ol>
 * 
 * @author Rein Raudjärv
 */
public class Builder {

	private QueryHandler 				handler;
	
	// Select
	private List<Expression<?>> 		columns;
	// From
	private List<Table> 				tables;
	// Where
	private List<WhereExpression> 		conditions;
	
	// Group by
	private List<ColumnExpression<?>>	groups;
	// Having
	private List<WhereExpression> 		havingConditions;
	
	// Order by
	private List<OrderByExpression> 	directions;	
	

	public Builder() {
		this.columns	= new ArrayList<Expression<?>>();
		this.tables		= new ArrayList<Table>();
		this.conditions	= new ArrayList<WhereExpression>();
	}
	
	protected Builder(Builder builder) {
		this.handler	= builder.handler;
		this.columns	= builder.columns;
		this.tables		= builder.tables;
		this.conditions	= builder.conditions;
	}
	
	
	// Handler
	
	protected QueryHandler getHandler() {
		return handler;
	}
	
	protected void _setHandler(QueryHandler handler) {
		this.handler = handler;
	}

	// Select
	
	public List<Expression<?>> getColumns() {
		return new ArrayList<Expression<?>>(columns);
	}
	
	protected List<Expression<?>> _getColumns() {
		return columns;
	}
	
	protected Expression<?> _getColumnFromEnd(int indexFromEnd) {
		return columns.get(_getColumnIndexFromEnd(indexFromEnd));
	}
	
	protected Expression<?> _removeColumnFromEnd(int indexFromEnd) {
		return columns.remove(_getColumnIndexFromEnd(indexFromEnd));
	}
	
	protected int _getColumnIndexFromEnd(int indexFromEnd) {
		return columns.size() - 1 - indexFromEnd;
	}
	
	protected void _setColumns(List<Expression<?>> columns) {
		this.columns = columns;
	}

	public Builder addColumn(Expression<?> column) {
		columns.add(column);
		return this;
	}
	
	public Builder removeColumn(Expression<?> column) {
		columns.remove(column);
		return this;
	}
	
	// From
	
	public List<Table> getTables() {
		return new ArrayList<Table>(tables);
	}
	
	protected List<Table> _getTables() {
		return tables;
	}

	protected void _setTables(List<Table> tables) {
		this.tables = tables;
	}
	
	public Builder addTables(Table... tables) {
		this.tables.addAll(Arrays.asList(tables));
		return this;
	}
	
	public Builder removeTables(Table... tables) {
		this.tables.removeAll(Arrays.asList(tables));
		return this;
	}
	
	// Where
	
	public List<WhereExpression> getConditions() {
		return new ArrayList<WhereExpression>(conditions);
	}

	protected List<WhereExpression> _getConditions() {
		return conditions;
	}
	
	protected void _setConditions(List<WhereExpression> conditions) {
		this.conditions = conditions;
	}
	
	public Builder addConditions(WhereExpression... conditions) {
		this.conditions.addAll(Arrays.asList(conditions));
		return this;
	}
	
	public Builder removeConditions(WhereExpression... conditions) {
		this.conditions.removeAll(Arrays.asList(conditions));
		return this;
	}
	
	// Group by
	
	public List<ColumnExpression<?>> getGroups() {
		return new ArrayList<ColumnExpression<?>>(groups);
	}
	
	protected List<ColumnExpression<?>> _getGroups() {
		return groups;
	}
	
	protected void _setGroups(List<ColumnExpression<?>> groups) {
		this.groups = groups;
	}
	
	@SuppressWarnings("unchecked")
	public Builder addGroups(ColumnExpression... groups) {
		this.groups.addAll((Collection<? extends ColumnExpression<?>>) Arrays.asList(groups));
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public Builder removeGroups(ColumnExpression... groups) {
		this.groups.removeAll(Arrays.asList(groups));
		return this;
	}
	
	// Having
	
	public List<WhereExpression> getHavingConditions() {
		return new ArrayList<WhereExpression>(havingConditions);
	}
	
	protected List<WhereExpression> _getHavingConditions() {
		return havingConditions;
	}
	
	protected void _setHavingConditions(List<WhereExpression> havingConditions) {
		this.havingConditions = havingConditions;
	}
	
	public Builder addHavingConditions(WhereExpression... havingConditions) {
		this.havingConditions.addAll(Arrays.asList(havingConditions));
		return this;
	}
	
	public Builder removeHavingConditions(WhereExpression... havingConditions) {
		this.havingConditions.removeAll(Arrays.asList(havingConditions));
		return this;
	}
	
	// Order by
	
	public List<OrderByExpression> getDirections() {
		return new ArrayList<OrderByExpression>(directions);
	}
	
	protected List<OrderByExpression> _getDirections() {
		return directions;
	}
	
	protected void _setDirections(List<OrderByExpression> directions) {
		this.directions = directions;
	}
	
	public Builder addDirections(OrderByExpression... directions) {
		this.directions.addAll(Arrays.asList(directions));
		return this;
	}
	
	public Builder removeDirections(OrderByExpression... directions) {
		this.directions.removeAll(Arrays.asList(directions));
		return this;
	}
	
	// Closure
	
	public Builder closure(Closure closure) {
		closure.apply(this);
		return this;
	}
	
	/* Find / Get */
	
	@SuppressWarnings("unchecked")
	protected List uncheckedFind() {
		return handler.find(getSql(), getStatementCallback(), getResultReader());
	}
	
	@SuppressWarnings("unchecked")
	protected Object uncheckedGet() {
		List list = uncheckedFind();
		if (list.size() != 1) throw new IllegalStateException();
		return list.iterator().next();
	}
	
	protected String getSql() {
		StringBuilder sb = new StringBuilder("SELECT ");
		for (Iterator<Expression<?>> it = columns.iterator(); it.hasNext();) {
			sb.append(it.next().getSqlString());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append(" FROM ");
		for (Iterator<Table> it = tables.iterator(); it.hasNext();) {
			sb.append(it.next().getName());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		if (!conditions.isEmpty()) {
			sb.append(" WHERE ");
			for (Iterator<WhereExpression> it = conditions.iterator(); it.hasNext();) {
				sb.append(it.next().getSqlString());
				if (it.hasNext()) {
					sb.append(", ");
				}
			}			
		}
		return sb.toString();
	}
	
	protected StatementCallback getStatementCallback() {
		return new StatementCallback() {
			public void doInStatement(PreparedStatement stmt) {
				// TODO
			}
		};
	}
	
	protected ResultReader<?> getResultReader() {
		// TODO
		return null;
	}
	
}
