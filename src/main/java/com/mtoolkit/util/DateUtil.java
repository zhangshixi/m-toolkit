package com.mtoolkit.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Calendar.*;

/**
 * A utility class that provides date operations.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 3/8/2011
 * @since 	JDK1.5
 */
public class DateUtil {
	
	/** date format pattern */
	private static final String DATE_FORMAT_PATTERN 	= "yyyy-MM-dd";
	private static final String DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/** Date format factory cache */
	private static Map<String, DateFormat> formatCache  = new ConcurrentHashMap<String, DateFormat>();
	
	/**
	 * Private constructor, not permit to construct the instance.
	 */
	private DateUtil() {
	}
	
	/**
	 * Returns current time in milliseconds.
	 * 
	 * @return current time in milliseconds.
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * Returns current time in nanoseconds.
	 * 
	 * @return current time in nanoseconds.
	 */
	public static long getCurrentTimeNanos() {
	    return System.nanoTime();
	}
	/**
	 * Returns current date time.
	 * 
	 * @return current date time.
	 */
	public static Date getCurrentTime() {
		return getInstance().getTime();
	}
	
	/**
	 * Transfers a date object to calendar object.
	 * 
	 * @param  date date object.
	 * 
	 * @return calendar object.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Calendar date2Calendar(Date date) {
		Calendar calendar = getInstance();
		calendar.setTime(date);
		
		return calendar;
	}
	
	/**
	 * Transfers a calendar object to date object.
	 * 
	 * @param  calendar calendar object.
	 * 
	 * @return date object.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date calendar2Date(Calendar calendar) {
		return calendar.getTime();
	}
	
	/**
	 * Returns first time of current day.
	 * 
	 * @return first time of current day.
	 */
	public static Date getFirstTimeOfCurrentDay() {
		return getFirstTimeOfDay(getCurrentTime());
	}
	
	/**
	 * Returns first time of specified day.
	 * 
	 * @param  date specified day.
	 * 
	 * @return first time of specified day.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getFirstTimeOfDay(Date date) {
		Calendar calendar = date2Calendar(date);
		
		setFirstHourMinuteSeconds(calendar);
		
		return calendar.getTime();
	}
	
	/**
	 * Returns last time of current day.
	 * 
	 * @return last time of current day.
	 */
	public static Date getLastTimeOfCurrentDay() {
		return getLastTimeOfDay(getCurrentTime());
	}
	
	/**
	 * Returns last time of specified day.
	 * 
	 * @param  date specified day.
	 * 
	 * @return last time of specified day.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getLastTimeOfDay(Date date) {
		Calendar calendar = date2Calendar(date);
		
		setLastHourMinuteSeconds(calendar);
		
		return calendar.getTime();
	}

	/**
	 * Returns first day of current week.
	 * 
	 * @return first day of current week.
	 */
	public static Date getFirstDayOfCurrentWeek() {
		return getFirstDayOfWeek(getCurrentTime());
	}
	
	/**
	 * Returns first day of week with the specified date.
	 * 
	 * @return first day of week with the specified date.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar calendar = date2Calendar(date);
		
		int value = calendar.getActualMinimum(DAY_OF_WEEK);
		calendar.set(DAY_OF_WEEK, value);
		
		return calendar.getTime();
	}
	
	/**
	 * Returns last day of current week.
	 * 
	 * @return last day of current week.
	 */
	public static Date getLastDayOfCurrentWeek() {
		return getLastDayOfWeek(getCurrentTime());
	}

	/**
	 * Returns last day of week with the specified date.
	 * 
	 * @return last day of week with the specified date.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar calendar = date2Calendar(date);
		
		int value = calendar.getActualMaximum(DAY_OF_WEEK);
		calendar.set(DAY_OF_WEEK, value);
		
		return calendar.getTime();
	}
	
	/**
	 * Returns first day of current month.
	 * 
	 * @return first day of current month.
	 */
	public static Date getFirstDayOfCurrentMonth() {
		return getFirstDayOfMonth(getCurrentTime());
	}

	/**
	 * Returns first day of month with the specified date.
	 * 
	 * @return first day of month with the specified date.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = date2Calendar(date);	
		
		int value = calendar.getActualMinimum(DAY_OF_MONTH);
		calendar.set(DAY_OF_MONTH, value);
		
		return calendar.getTime();
	}
	
	/**
	 * Returns last day of current month.
	 * 
	 * @return last day of current month.
	 */
	public static Date getLastDayOfCurrentMonth() {
		return getLastDayOfMonth(getCurrentTime());
	}

	/**
	 * Returns last day of month with the specified date.
	 * 
	 * @return last day of month with the specified date.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = date2Calendar(date);
		
		int value = calendar.getActualMaximum(DAY_OF_MONTH);
		calendar.set(DAY_OF_MONTH, value);
		
		return calendar.getTime();
	}
	
	/**
	 * Returns first day of current year.
	 * 
	 * @return first day of current year.
	 */
	public static Date getFirstDayOfCurrentYear() {
		return getFirstDayOfYear(getCurrentTime());
	}
	
	/**
	 * Returns first day of year with the specified date.
	 * 
	 * @return first day of year with the specified date.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getFirstDayOfYear(Date date) {
		Calendar calendar = date2Calendar(date);
		
		int value = calendar.getActualMinimum(DAY_OF_YEAR);
		calendar.set(DAY_OF_YEAR, value);
		
		return calendar.getTime();
	}
	
	/**
	 * Returns last day of current year.
	 * 
	 * @return last day of current year.
	 */
	public static Date getLastDayOfCurrentYear() {
		return getLastDayOfYear(getCurrentTime());
	}
	
	/**
	 * Returns last day of year with the specified date.
	 * 
	 * @return last day of year with the specified date.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date getLastDayOfYear(Date date) {
		Calendar calendar = date2Calendar(date);
		
		int value = calendar.getActualMaximum(DAY_OF_YEAR);
		calendar.set(DAY_OF_YEAR, value);
		
		return calendar.getTime();
	}
	
	/**
	 * Checks if two date objects are on the same day ignoring time.
	 * 
	 * @param  first the first day.
	 * @param  second the second day.
	 * 
	 * @return true if they represent the same day; false otherwise.
	 * 
	 * @throws NullPointerException if {@code first} or {@code second} is null.
	 */
	public static boolean isSameDay(Date first, Date second) {
		return isSameDay(date2Calendar(first), date2Calendar(second));
	}
	
	/**
	 * Checks if two calendar objects are on the same day ignoring time.
	 * 
	 * @param  first the first day.
	 * @param  second the second day.
	 * 
	 * @return true if they represent the same day; false otherwise.
	 * 
	 * @throws NullPointerException if {@code first} or {@code second} is null.
	 */
	public static boolean isSameDay(Calendar first, Calendar second) {
		return first.get(Calendar.ERA) == second.get(Calendar.ERA) && 
			   first.get(Calendar.YEAR) == second.get(Calendar.YEAR) &&
			   first.get(Calendar.DAY_OF_YEAR) == second.get(Calendar.DAY_OF_YEAR);
	}
	
	/**
	 * Formats a date value to string with specified pattern.
	 * 
	 * @param  date date time.
	 * @param  pattern format pattern.
	 * 
	 * @return string value.
	 * 
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static String format(Date date, String pattern) {
		return getDateFormat(pattern).format(date);
	}
	
	/**
	 * Parses a string value to date with specified pattern.
	 * 
	 * @param  date string value.
	 * @param  pattern format pattern.
	 * 
	 * @return date time.
	 * 
	 * @throws ParseException if the specified string cannot be parsed.
	 * @throws NullPointerException if {@code date} is null.
	 */
	public static Date parse(String date, String pattern) throws ParseException {
		return getDateFormat(pattern).parse(date);
	}
	
	/**
	 * Returns date format with date pattern {@link #DATE_FORMAT_PATTERN}.
	 * 
	 * @return date format with date pattern {@link #DATE_FORMAT_PATTERN}.
	 */
	public static DateFormat getDateFormat() {
		return getDateFormat(DATE_FORMAT_PATTERN);
	}
	
	/**
	 * Returns date format with date pattern {@link #DATETIME_FORMAT_PATTERN}.
	 * 
	 * @return date format with date pattern {@link #DATETIME_FORMAT_PATTERN}.
	 */
	public static DateFormat getDatetimeFormat() {
		return getDateFormat(DATETIME_FORMAT_PATTERN);
	}
	
	/**
	 * Returns cached date format factory with specified pattern.
	 * 
	 * @param  pattern format pattern.
	 * 
	 * @return cached date format factory.
	 */
	public static DateFormat getDateFormat(String pattern) {
		DateFormat dateFormat = formatCache.get(pattern);
		if (dateFormat == null) {
			if (pattern == null) {
				dateFormat = new SimpleDateFormat();
			} else {
				dateFormat = new SimpleDateFormat(pattern);
			}
			formatCache.put(pattern, dateFormat);
		}
		return dateFormat;
	}
	
	/**
	 * Sets the hour, minute and seconds field to first time in given calendar.
	 *   
	 * @param  calendar specified calendar.
	 * 
	 * @throws NullPointerException if {@code calendar} is null. 
	 */
	public static void setFirstHourMinuteSeconds(Calendar calendar) {
		calendar.set(HOUR_OF_DAY, 0);
		calendar.set(MINUTE, 	  0);
		calendar.set(SECOND, 	  0);
		calendar.set(MILLISECOND, 0);
	}

	/**
	 * Sets the hour, minute and seconds field to last time in given calendar.
	 *   
	 * @param  calendar specified calendar.
	 * 
	 * @throws NullPointerException if {@code calendar} is null. 
	 */
	public static void setLastHourMinuteSeconds(Calendar calendar) {
		calendar.set(HOUR_OF_DAY, 23 );
		calendar.set(MINUTE, 	  59 );
		calendar.set(SECOND, 	  59 );
		calendar.set(MILLISECOND, 999);
	}
	
}
