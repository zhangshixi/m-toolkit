package com.mtoolkit.spring;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.UrlPathHelper;

import com.mlogger.Loggers;
import com.mtoolkit.util.EmptyUtil;

/**
 * 
 * @author michael
 * @version $Id: SpringDispatcherServlet.java, v 0.1 2012-8-16 下午3:16:57 michael Exp $
 */
public class SpringMvcDispatcherServlet extends DispatcherServlet {

    /** serial version UID */
    private static final long serialVersionUID = -7824832266835284293L;
    
    /** spring url path helper */
    private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();
    /** logger utility */
    private static final Loggers LOGGER = Loggers.getLoggers(SpringMvcDispatcherServlet.class);
    
    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestUri = URL_PATH_HELPER.getRequestUri(request);
        LOGGER.debug("==> SpringMvc process starting: {0}", requestUri);
        
        logBeforeRequest(request);
        
        long startTime = System.currentTimeMillis();
        super.doService(request, response);
        long endTime = System.currentTimeMillis();
        
        logAfterRequest(response);

        LOGGER.debug("<== Request execute time: [{0}] ms", (endTime - startTime));
        
        LOGGER.debug("SpringMvc process ended.");
    }

    /**
     * Records logger before request.
     * 
     * @param request http request.
     */
    @SuppressWarnings("unchecked")
    private void logBeforeRequest(HttpServletRequest request) {
    	String contentType = request.getContentType();
    	if (EmptyUtil.isNotNullEmpty(contentType)) {
    		LOGGER.debug("==> Request contentType: {0}", request.getContentType());
    	}
    	
        Map<String, String[]> paramMap = request.getParameterMap();
        if (EmptyUtil.isNotNullEmpty(paramMap)) {
        	LOGGER.debug("==> Request parameters :");
        }
        for (Entry<String, String[]> entry : paramMap.entrySet()) {
            LOGGER.debug("    {0} = {1}", 
                entry.getKey(), Arrays.toString(entry.getValue()));
        }
    }
    
    /**
     * Records logger after request.
     * 
     * @param request http request.
     */
    private void logAfterRequest(HttpServletResponse response) {
    	String contentType = response.getContentType();
    	if (EmptyUtil.isNotNullEmpty(contentType)) {
    		LOGGER.debug("<== Response contentType: {0}", response.getContentType());
    	}
    }
    
}
