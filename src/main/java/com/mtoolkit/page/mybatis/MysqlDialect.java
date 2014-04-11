package com.mtoolkit.page.mybatis;

import com.mtoolkit.page.Page;
import com.mtoolkit.page.Page.OrderType;
import com.mtoolkit.util.EmptyUtil;

public class MysqlDialect extends AbstractDialect {
	
	public MysqlDialect() {
	}
	
	@Override
	public String getCountSql(String targetSql) {
		String countSql = sqlTrim(targetSql);
		
		String upperSql = countSql.toUpperCase();
		StringBuilder builder = new StringBuilder(countSql);
		
		int index = upperSql.indexOf(ORDER_BY);
		if (index > 0) {
			builder.delete(index, builder.length());
		}

		builder.delete(0, upperSql.indexOf(FROM));
		
		index = upperSql.indexOf(GROUP_BY);
		if (index > 0) {
			builder.insert(0, "SELECT COUNT(1) FROM (SELECT 1 ");
			builder.append(") AS temp");
		} else {
			builder.insert(0, "SELECT COUNT(1) ");
		}

		return builder.toString();
	}

	@Override
	public String getLimitSql(String targetSql, Page<?> page) {
		String limitSql = sqlTrim(targetSql);
		StringBuilder builder = new StringBuilder(limitSql);
		
		// order by
		String orderBy = page.getOrderBy();
		String order = null;
		if (EmptyUtil.isNotNullEmpty(orderBy)) {
			OrderType orderType = page.getOrderType();
			if (orderType != null) {
				order = orderBy + BLANK + orderType.name();
			} else {
				order = orderBy + BLANK + OrderType.ASC.name();
			}
		}
		int index = limitSql.toUpperCase().indexOf(ORDER_BY);
		if (order != null) {
			if (index > 0) {
				index += ORDER_BY.length();
				builder.insert(index, order + DOT);
			} else {
				builder.append(BLANK).append(ORDER_BY).append(BLANK).append(order);
			}
		}
		
		// limit
		builder.append(BLANK).append(LIMIT).append(BLANK);
		builder.append(Math.max(0, (page.getPageIndex() - 1) * page.getPageSize()));
		builder.append(DOT);
		builder.append(page.getPageSize());
		
		return builder.toString();
	}
	
	private String sqlTrim(String targetSql) {
		String trimedSql = targetSql.trim();
		if (trimedSql.endsWith(SQL_EOF)) {
			trimedSql = trimedSql.substring(0, trimedSql.length() - 1 - SQL_EOF.length());
		}
		
		return trimedSql;
	}
	
}
