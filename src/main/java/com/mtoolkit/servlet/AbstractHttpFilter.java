package com.mtoolkit.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A abstract http filter class that convert the request/response to http format.
 * Child class need implements 
 * {@link #doFilter(HttpServletRequest, HttpServletResponse, FilterChain)} 
 * method to extends own logic.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 26/11/2011
 */
public abstract class AbstractHttpFilter implements Filter {

	/* filter configuration */
    private FilterConfig _config;

    /**
     * {@inheritDoc}
     * 
     * @param config filter configuration.
     * 
     * @throws ServletException if a servlet exception occurs.
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        _config = config;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
    }
    
    /**
     * {@inheritDoc}
     * 
     * @param  request	servlet request.
     * @param  response servlet response.
     * @param  chain	filter chain.
     * 
     * @throws IOException		if an I/O exception occurs.
     * @throws ServletException if an servlet exception occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    /**
     * Gets the current filter configuration.
     * 
     * @return  current filter configuration.
     */
    protected FilterConfig getFilterConfig() {
    	return _config;
    }
    
    /**
     * Actually do filter method over http protocol.
     * 
     * @param  request	http servlet request.
     * @param  response http servlet response.
     * @param  chain	filter chain.
     * 
     * @throws IOException		if an I/O exception occurs.
     * @throws ServletException if an http servlet exception occurs.
     */
    protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    	   throws IOException, ServletException;
    
}
