package com.mtoolkit.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CookieUtil {

    /**
     * Default cookie path.
     */
    public static final String DEFAULT_COOKIE_PATH = "/";
    
    /**
     * Private constructor, not permit to construct the instance.
     */
    private CookieUtil() {
    }
    
    /**
     * Adds cookie to client browser with specified parameters.
     * 
     * @param name     cookie name.
     * @param value    cookie value.
     * @param maxAge   cookie max age.
     * @param request  http request.
     * @param response http response.
     */
    public static void addCookie(
            String name, String value, int maxAge,
            HttpServletRequest request, HttpServletResponse response) {
        addCookie(name, value, maxAge, null, request, response);
    }
    
    /**
     * Adds cookie to client browser with specified parameters.
     * 
     * @param name     cookie name.
     * @param value    cookie value.
     * @param maxAge   cookie max age.
     * @param domain   cookie domain.
     * @param request  http request.
     * @param response http response.
     */
    public static void addCookie(
            String name, String value, int maxAge, String domain,
            HttpServletRequest request, HttpServletResponse response) {
        addCookie(name, value, maxAge, domain, DEFAULT_COOKIE_PATH, request, response);
    }
    
    /**
     * Adds cookie to client browser with specified parameters.
     * 
     * @param name     cookie name.
     * @param value    cookie value.
     * @param maxAge   cookie max age.
     * @param domain   cookie domain.
     * @param path     cookie path.
     * @param request  http request.
     * @param response http response.
     */
    public static void addCookie(
            String name, String value, int maxAge, String domain, String path,
            HttpServletRequest request, HttpServletResponse response) {
        if (name == null || request == null || response == null) {
            throw new NullPointerException();
        }
        
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(request.isSecure());
        cookie.setMaxAge(maxAge);
        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (path != null) {
            cookie.setPath(path);
        }
        
        response.addCookie(cookie);
    }
    
    /**
     * Removes cookie from client browser with specified parameters.
     * 
     * @param name     cookie name.
     * @param request  http request.
     * @param response http response.
     */
    public static void removeCookie(
            String name, HttpServletRequest request, HttpServletResponse response) {
        removeCookie(name, null, request, response);
    }
    
    /**
     * Removes cookie from client browser with specified parameters.
     * 
     * @param name     cookie name.
     * @param domain   cookie domain.
     * @param request  http request.
     * @param response http response.
     */
    public static void removeCookie(
            String name, String domain, 
            HttpServletRequest request, HttpServletResponse response) {
        removeCookie(name, domain, DEFAULT_COOKIE_PATH, request, response);
    }
    
    /**
     * Removes cookie from client browser with specified parameters.
     * 
     * @param name     cookie name.
     * @param domain   cookie domain.
     * @param path     cookie path.
     * @param request  http request.
     * @param response http response.
     */
    public static void removeCookie(
            String name, String domain, String path, 
            HttpServletRequest request, HttpServletResponse response) {
        if (name == null || request == null || response == null) {
            throw new NullPointerException();
        }
        
        addCookie(name, null, 0, domain, path, request, response);
    }
    
    /**
     * Gets cookie from client request with specified cookie name.
     * 
     * @param  name    cookie name.
     * @param  request http request.
     * 
     * @return cookie, return {@code null} if not found the cookie.
     */
    public static Cookie getCookie(String name, HttpServletRequest request) {
        if (name == null) {
            throw new NullPointerException("name");         
        }
        if (request == null) {
            throw new NullPointerException("request");
        }
        
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }
        
        return null;
    }
    
    /**
     * Gets cookie value from client request with specified cookie name.
     * 
     * @param  name    cookie name.
     * @param  request http request.
     * 
     * @return cookie value, return {@code null} if not found the cookie.
     */
    public static String getCookieValue(String name, HttpServletRequest request) {
        Cookie cookie = getCookie(name, request);
        return cookie == null ? null : cookie.getValue();
    }
    
}
