package com.mtoolkit.page;

public abstract class AbstractDialect implements Dialect {
	
	protected static final String DOT 	= ",";
	protected static final String BLANK = " ";
	
	/** from keyword */
	protected static final String FROM     = " FROM ";
	/** limit keyword */
	protected static final String LIMIT    = " LIMIT ";
	/** order by keyword */
	protected static final String ORDER_BY = " ORDER BY ";
	/** group by keyword */
	protected static final String GROUP_BY = " GROUP BY ";
	/** sql ended character */
	protected static final String SQL_ENDED_CHARACTER = ";";
	
}
