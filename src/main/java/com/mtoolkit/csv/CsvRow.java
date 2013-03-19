package com.mtoolkit.csv;

import java.util.LinkedList;
import java.util.List;

/**
 * CSV row.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 07/17/2012
 * @since 	JDK1.5
 */
public class CsvRow {
	
	/** escaped row value */
	private String _value;
	/** row cells */
	private CsvCell[] _cells;
	
	/** comma character */
	private static final char COMMA = ',';
	/** double quote character */
	private static final char DOUBLE_QUOTE = '"';
	
	// ---- constructors ----------------------------------
	/**
	 * Creates a CsvRow instance with given escaped row value.
	 *  
	 * @param value escaped row value.
	 */
	public CsvRow(String value) {
		_value = value;
		_cells = doParseRow(value);
	}
	
	/**
	 * Creates a CsvRow instance with given CsvCell array.
	 * 
	 * @param cells CsvCell array.
	 */
	public CsvRow(CsvCell[] cells) {
		_cells = cells;
		_value = doParseRow(cells);
	}
	
	// ---- public methods --------------------------------
	/**
	 * Returns escaped row value.
	 * 
	 * @return escaped row value.
	 */
	public String getRowValue() {
		return _value;
	}
	
	/**
	 * Returns CSV cells of this row.
	 * 
	 * @return CSV cells of this row.
	 */
	public CsvCell[] getCsvCells() {
		return _cells;
	}
	
	/**
	 * Returns CSV cell number of this row.
	 * 
	 * @return CSV cell number of this row.
	 */
	public int getCellNumber() {
		return _cells.length;
	}
	
	/**
	 * Returns specified CSV cell of this row.
	 *  
	 * @param  index cell index, start from 0.
	 * 
	 * @return specified CSV cell of this row.
	 */
	public CsvCell getCsvCell(int index) {
		return _cells[index];
	}
	
	// ---- private methods -------------------------------
	/**
	 * Parses the escaped row value to CSV cell array.
	 * 
	 * @param  value escaped row value.
	 *  
	 * @return CSV cell array.
	 */
	private CsvCell[] doParseRow(String value) {
		String cell = null;
		List<CsvCell> cellList = new LinkedList<CsvCell>();
		
		int from = 0;
		int index = 0;
		boolean ended = false;

		while (!ended) {
			index = value.indexOf(COMMA, index);
			if (index > -1) {
				String temp = value.substring(from, index);
				index += 1;
				if (isCellValue(temp)) {
					cell = temp;
					from = index;
				} else {
					continue;
				}
			} else {
				cell = value.substring(from);
				ended = true;
			}
			
			cellList.add(new CsvCell(unEscape(cell)));
		}
		
		return cellList.toArray(new CsvCell[cellList.size()]);
	}

	/**
	 * Checks the specified string value is CsvCell value or not.
	 *  
	 * @param  value check value.
	 * 
	 * @return {@code true} if and only if value is a valid 
	 * 		   CsvCell value; {@code false} otherwise. 
	 */
	public static boolean isCellValue(String value) {
		if (value == null || value.isEmpty()) {
			return true;
		}
		if (!value.startsWith(String.valueOf(DOUBLE_QUOTE))) {
			return true;
		}
		if (value.endsWith(String.valueOf(DOUBLE_QUOTE))) {
			int dqNumber = 0;
			for (int i = 1; i < value.length() - 1; i++) {
				if (value.charAt(i) == DOUBLE_QUOTE) {
					dqNumber += 1;
				}
			}
			if (dqNumber % 2 == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Parses the CSV cell array to escaped row value.
	 * 
	 * @param  cells CSV cell array.
	 *  
	 * @return escaped row value.
	 */
	private String doParseRow(CsvCell[] cells) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i< cells.length; i++) {
			builder.append(escape(cells[i].getStringValue()));
			builder.append(',');
		}
		builder.deleteCharAt(builder.length() - 1);
		
		return builder.toString();
	}

	/**
	 * Escapes the given string value. Examples:
	 * <pre>
	 * 		escape("value") = value;
	 * 		escape("value\"") = value";
	 * 		escape("\"value") = """value";
	 * 		escape("\"value\"") = """value""";
	 * 		escape("contains\"value") = "contains""value";
	 * 		escape("contains,value") = "contains,value";
	 * 		escape("contains\",\"value") = "contains"",""value";
	 * </pre>
	 * 
	 * @param value source value.
	 * 
	 * @return escaped value.
	 */
	private String escape(String value) {
		if (value == null || value.isEmpty()) {
			return value;
		}
		
		boolean hasComma = value.indexOf(COMMA) > -1;
		int iDQuote = value.indexOf(DOUBLE_QUOTE);
		boolean hasDQuote = iDQuote > -1 && iDQuote < value.length() - 1;
		
		if (!hasComma && !hasDQuote) {
			return value;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(DOUBLE_QUOTE);
		for (int i = 0; i < value.length(); i++) {
			char point = value.charAt(i);
			if (point == DOUBLE_QUOTE) {
				builder.append(point);
			}
			builder.append(point);
		}
		builder.append(DOUBLE_QUOTE);
		
		return builder.toString();
	}
	
	/**
	 * UNEscapes the given string value. Examples:
	 * <pre>
	 * 		unEscape("value") = value;
	 * 		unEscape("value\"") = value";
	 * 		unEscape("\"\"value") = "value;
	 * 		unEscape("\"\"\"value\"\"\"") = "value";
	 * 		unEscape("contains\"\"value") = contains"value;
	 * 		unEscape("contains,value") = contains,value;
	 * 		unEscape("contains\"\",\"\"value") = contains","value;
	 * </pre>
	 * 
	 * @param value source value.
	 * 
	 * @return escaped value.
	 */
	private String unEscape(String value) {
		if (value == null || value.isEmpty()) {
			return value;
		}
		
		boolean hasComma = value.indexOf(COMMA) > -1;
		int iDQuote = value.indexOf(DOUBLE_QUOTE);
		boolean hasDQuote = iDQuote > -1 && iDQuote < value.length() - 1;
				
		if (!hasComma && !hasDQuote) {
			return value;
		}
		
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i < value.length() - 1; i++) {
			char point = value.charAt(i);
			if (point == DOUBLE_QUOTE) {
				builder.append(DOUBLE_QUOTE);
				++i;
			} else {
				builder.append(point);
			}
		}
		
		return builder.toString();
	}
	
}
