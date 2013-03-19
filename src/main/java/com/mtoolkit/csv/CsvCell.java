package com.mtoolkit.csv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * CSV cell.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 07/17/2012
 * @since 	JDK1.5
 */
public class CsvCell {
	
	/** cell value */
	public String _value;
	
	/** default date value pattern */
	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	// ---- constructors ---------------------------------------
	/**
	 * Creates a CsvCell instance with specified value.
	 * 
	 * @param value cell value.
	 */
	public CsvCell(String value) {
		_value = value;
	}
	
	// ---- public methods -------------------------------------
	/**
	 * Gets the cell string value.
	 * 
	 * @return cell string value.
	 */
	public String getStringValue() {
		return _value;
	}
	
	/**
	 * Parses the cell value as a boolean. 
	 * 
	 * @return the boolean cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the string does not contain a parsable boolean.
	 */
	public boolean getBooleanValue() {
		return Boolean.parseBoolean(_value);
	}
	
	/**
	 * Parses the cell value as a signed decimal byte. 
	 * 
	 * @return the byte cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the string does not contain a parsable byte.
	 */
	public byte getByteValue() {
		return Byte.parseByte(_value);
	}
	
	/**
	 * Parses the cell value as a signed decimal short. 
	 * 
	 * @return the short cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the string does not contain a parsable short.
	 */
	public short getShortValue() {
		return Short.parseShort(_value);
	}
	
	/**
	 * Parses the cell value as a signed decimal integer. 
	 * 
	 * @return the integer cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the string does not contain a parsable integer.
	 */
	public int getIntValue() {
		return Integer.parseInt(_value);
	}

	/**
	 * Parses the cell value as a signed decimal float. 
	 * 
	 * @return the float cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the string does not contain a parsable float.
	 */
	public float getFloatValue() {
		return Float.parseFloat(_value);
	}
	
	/**
	 * Parses the cell value as a signed decimal long. 
	 * 
	 * @return the long cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the string does not contain a parsable long.
	 */
	public long getLongValue() {
		return Long.parseLong(_value);
	}
	
	/**
	 * Parses the cell value as a signed decimal double. 
	 * 
	 * @return the double cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the string does not contain a parsable double.
	 */
	public double getDoubleValue() {
		return Double.parseDouble(_value);
	}
	
	/**
	 * Parses the cell value as a signed decimal big integer. 
	 * 
	 * @return the big integer cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the value is not a valid representation of a {@code BigInteger}.
	 */
	public BigInteger getBigIntegerValue() {
		return new BigInteger(_value);
	}
	
	/**
	 * Parses the cell value as a signed decimal big decimal. 
	 * 
	 * @return the big decimal cell value.
	 * 
     * @throws NumberFormatException 
     * 		   if the value is not a valid representation of a {@code BigDecimal}.
	 */
	public BigDecimal getBigDecimalValue() {
		return new BigDecimal(_value);
	}
	
	/**
	 * Parses the cell value as a date with {@linkplain #DEFAULT_DATE_PATTERN}. 
	 * 
	 * @return the date cell value.
	 * 
     * @throws ParseException 
     * 		   if the beginning of the specified string cannot be parsed.
	 */
	public Date getDateValue() throws ParseException {
		return getDateValue(DEFAULT_DATE_PATTERN);
	}
	
	/**
	 * Parses the cell value as a date with specified format pattern. 
	 * 
	 * @return the date cell value.
	 * 
     * @throws ParseException 
     * 		   if the beginning of the specified string cannot be parsed.
	 */
	public Date getDateValue(String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(_value);
	}
	
}
