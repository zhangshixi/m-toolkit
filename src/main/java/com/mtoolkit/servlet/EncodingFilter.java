package com.mtoolkit.servlet;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Character encoding filter.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 26/11/2011
 */
public class EncodingFilter extends AbstractHttpFilter {
	
	/** character encoding initialize parameter name */
    public static final String INIT_PARAM_ENCODING = "encoding";

    /**
     * Sets the specified character encoding to the request and response.
     * 
     * @param  request	http servlet request.
     * @param  response http servlet response.
     * @param  chain	filter chain.
     * 
     * @throws IOException		if an I/O exception occurs.
     * @throws ServletException if an http servlet exception occurs.
     */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
           throws IOException, ServletException {
    	
        String encoding = getFilterConfig().getInitParameter(INIT_PARAM_ENCODING);

        if (encoding != null && !encoding.isEmpty()) {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);

        if (encoding != null && !encoding.isEmpty()) {
            response.setCharacterEncoding(encoding);
        }
    }
    
}
