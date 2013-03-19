package com.mtoolkit.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility class that for get the specified page result data.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 18/10/2011
 * @since 	JDK1.5
 */
public class Page<TYPE> implements Serializable {

	/** serial version id */
	private static final long serialVersionUID = 2903651488316306069L;
	
	private int    	   _pageIndex;
	private int    	   _pageSize;
	private int	   	   _totalData;
	private int    	   _totalPage;
	private int    	   _remainPage;
	private int    	   _fromIndex;
	private int        _toIndex;
	
	private boolean	   _autoCount;
	private String 	   _orderBy;
	private OrderType  _orderType;
	private List<TYPE> _resultList;
	
	private volatile boolean _initialized;
	
	/** default page index */
	public static final int 	DEFAULT_PAGE_INDEX = 1;
	/** default page size */
	public static final int 	DEFAULT_PAGE_SIZE  = 10;
	/** default auto count */
	public static final boolean DEFAULT_AUTO_COUNT = true;
	
	/**
	 * Order type.
	 */
	public static enum OrderType {
		ASC, DESC
	}
	
	/**
	 * Creates a default page instance with 
	 * {@link #DEFAULT_PAGE_INDEX} and {@link #DEFAULT_PAGE_SIZE}.
	 */
	public Page() {
		this(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * Creates a page instance with the 
	 * specified {@code pageIndex} and {@code pageSize}.
	 * 
	 * @param  pageIndex page index.
	 * @param  pageSize  page size.
	 * 
	 * @throws IllegalArgumentException 
	 * 		   if {@code pageIndex} or {@code pageSize} is non-positive.
	 */
	public Page(int pageIndex, int pageSize) {
		checkPageIndex(pageIndex);
		checkPageSize(pageSize);
		
		_pageIndex  = pageIndex;
		_pageSize   = pageSize;
		
		_autoCount  = DEFAULT_AUTO_COUNT;
		_orderBy    = null;
		_orderType  = null;
		_resultList = new ArrayList<TYPE>();
	}
	
	/**
	 * Creates a first page with the specified {@code pageSize}.
	 * 
	 * @param  pageSize page size.
	 * 
	 * @return first page instance.
	 * 
	 * @throws IllegalArgumentException if {@code pageSize} is non-positive.
	 */
	public static <TYPE> Page<TYPE> newFirstPage(int pageSize) {
		return new Page<TYPE>(1, pageSize);
	}
	
	/**
	 * Sets total data number to current page.
	 * 
	 * @param  totalData total data number.
	 * 
	 * @throws IllegalArgumentException if {@code totalData} is negative.
	 */
	public synchronized void setTotalData(int totalData) {
		if (totalData < 0) {
			throw new IllegalArgumentException("totalData: " + totalData);
		}
		
		_totalData = totalData;
		
		_totalPage = (totalData / _pageSize) + (totalData % _pageSize == 0 ? 0 : 1); 
		
		_remainPage = _totalPage > _pageIndex ? _totalPage - _pageIndex : 0;
		
		if (_pageIndex > _totalPage) {
			_fromIndex =_toIndex = Integer.MAX_VALUE;
		} else {
			_fromIndex = (_pageIndex - 1) * _pageSize;
			_toIndex = _totalData > _fromIndex + _pageSize 
					   ? _fromIndex + _pageSize - 1 
					   : _totalData - 1;
		}
		
		_initialized = true;
	}
	
	/**
	 * Tests whether current page is ready.
	 * @return {@code true} if and only if after invoke 
	 * 		   {@link #setTotalData(int)} method succeed;
	 * 		   {@code false} otherwise.
	 */
	public boolean isReady() {
		return _initialized;
	}

	/**
	 * Gets the total data number.
	 * 
	 * @return total data number.
	 * 
	 * @throws IllegalStateException 
	 * 		   if not invoke {@link #setTotalData(int)} method to initialize.
	 */
	public int getTotalData() {
		checkPageState();
		return _totalData;
	}
	
	/**
	 * Gets the total page number.
	 * 
	 * @return total page number.
	 * 
	 * @throws IllegalStateException 
	 * 		   if not invoke {@link #setTotalData(int)} method to initialize.
	 */
	public int getTotalPage() {
		checkPageState();
		return _totalPage;
	}
	
	/**
	 * Gets the remain page number.
	 * 
	 * @return remain page number.
	 * 
	 * @throws IllegalStateException 
	 * 		   if not invoke {@link #setTotalData(int)} method to initialize.
	 */
	public int getRemainPage() {
		checkPageState();
		return _remainPage;
	}
	
	/**
	 * Gets from index of current page.
	 * 
	 * @return from index; if {@code pageIndex} is larger than {@code totalPage}, 
	 * 		   will return {@link Integer#MAX_VALUE}.
	 * 
	 * @throws IllegalStateException 
	 * 		   if not invoke {@link #setTotalData(int)} method to initialize.
	 */
	public int getFromIndex() {
		checkPageState();
		return _fromIndex;
	}
	
	/**
	 * Gets to index of current page.
	 * 
	 * @return to index; if {@code pageIndex} is larger than {@code totalPage}, 
	 * 		   will return {@link Integer#MAX_VALUE}.
	 * 
	 * @throws IllegalStateException 
	 * 		   if not invoke {@link #setTotalData(int)} method to initialize.
	 */
	public int getToIndex() {
		checkPageState();
		return _toIndex;
	}
	
	/**
	 * Gets current page index.
	 * 
	 * @return page index.
	 */
	public int getPageIndex() {
		return _pageIndex;
	}
	
	/**
	 * Sets current page index.
	 * 
	 * @param  pageIndex page index, start from {@code 1}.
	 * 
	 * @throws IllegalArgumentException if {@code pageIndex} is non-positive.
	 */
	public void setPageIndex(int pageIndex) {
		checkPageIndex(pageIndex);
		_pageIndex = pageIndex;
	}
	
	/**
	 * Gets current page size.
	 * 
	 * @return page size.
	 */
	public int getPageSize() {
		return _pageSize;
	}
	
	/**
	 * Sets current page size.
	 * 
	 * @param  pageSize page size.
	 * 
	 * @throws IllegalArgumentException if {@code pageSize} is non-positive.
	 */
	public void setPageSize(int pageSize) {
		checkPageSize(pageSize);
		_pageSize = pageSize;
	}
	
	/**
	 * Returns auto count total data or not.
	 * 
	 * @return {@code true} auto count total data;
	 * 		   {@code false} not count total data.
	 */
	public boolean isAutoCount() {
		return _autoCount;
	}
	
	/**
	 * Sets auto count total data or not.
	 * 
	 * @param autoCount auto count total data or not.
	 */
	public void setAutoCount(boolean autoCount) {
		_autoCount = autoCount;
	}
	
	/**
	 * Gets database order by column for page select.
	 * 
	 * @return database order by column.
	 */
	public String getOrderBy() {
		return _orderBy;
	}
	
	/**
	 * Set database order by column for page select.
	 * 
	 * @param orderBy database order by column.
	 */
	public void setOrderBy(String orderBy) {
		this._orderBy = orderBy;
	}

	/**
	 * Gets database column order type for page select.
	 * 
	 * @return database column order type.
	 */
	public OrderType getOrderType() {
		return _orderType;
	}
	
	/**
	 * Sets database column order type for page select.
	 * 
	 * @param orderType database column order type.
	 */
	public void setOrderType(OrderType orderType) {
		this._orderType = orderType;
	}
	
	/**
	 * Gets page result list.
	 * 
	 * @return result list.
	 */
	public List<TYPE> getResultList() {
		return _resultList;
	}
	
	/**
	 * Sets page result list.
	 * 
	 * @param _resultList result list.
	 */
	public void setResultList(List<TYPE> _resultList) {
		this._resultList = _resultList;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @param obj the another page instance witch to compare.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Page)) {
            return false;
        }
		
		@SuppressWarnings("unchecked")
		final Page<TYPE> pageResult = (Page<TYPE>) obj;
        return _initialized == pageResult._initialized &&
        	   _pageIndex 	== pageResult._pageIndex   &&
               _pageSize  	== pageResult._pageSize    &&
               _totalData 	== pageResult._totalData   &&
               _totalPage 	== pageResult._totalPage   &&
               _fromIndex 	== pageResult._fromIndex   &&
               _toIndex 	== pageResult._toIndex	   &&
               _autoCount	== pageResult._autoCount   &&
               _orderBy.equals(pageResult._orderBy)	   &&
               _orderType.equals(pageResult._orderType)&&
               _resultList.equals(pageResult.getResultList());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = 17;
        hash = 37 * hash + (_pageIndex ^ (_pageIndex >>> 32));
        hash = 37 * hash + (_pageSize  ^ (_pageSize  >>> 32));
        
        if (_initialized) {
        	hash = 37 * hash + (_totalData ^ (_totalData >>> 32));
            hash = 37 * hash + (_totalPage ^ (_totalPage >>> 32));
            hash = 37 * hash + (_fromIndex ^ (_fromIndex >>> 32));
            hash = 37 * hash + (_toIndex   ^ (_toIndex   >>> 32));
        }
        
        return hash;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder pageInfo = new StringBuilder();
        pageInfo.append(Page.class.getName())
        		.append("[initialized=").append(_initialized)
        		.append(",pageIndex=").append(_pageIndex)
        		.append(",pageSize=").append(_pageSize);
        
        if (_initialized) {
        pageInfo.append(",totalData=").append(_totalData)
        		.append(",totalPage=").append(_totalPage)
        		.append(",fromIndex=").append(_fromIndex)
        		.append(",toIndex=").append(_toIndex);
        }
        pageInfo.append("]");

        return pageInfo.toString();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws CloneNotSupportedException {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Page<TYPE> clone() throws CloneNotSupportedException {
		return (Page<TYPE>) super.clone();
	}
	
	// ---- private methods --------------------------------------------------------
	private void checkPageIndex(int pageIndex) {
		if (pageIndex <= 0) {
			throw new IllegalArgumentException("pageIndex: " + pageIndex);
		}
	}
	
	private void checkPageSize(int pageSize) {
		if (pageSize <= 0) {
			throw new IllegalArgumentException("pageSize: " + pageSize);
		}
	}
	
	private void checkPageState() {
		if (!_initialized) {
			throw new IllegalStateException(
				  "Not invoke setTotalData(int totalData) to initialize!");
		}
	}
	
}
