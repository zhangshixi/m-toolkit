package com.mtoolkit.util;

import static com.mtoolkit.util.CharsetUtil.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Servlet utility.
 *
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.00, 5/8/2012
 */
public class ServletUtil {
	
	/**
	 * Gets client ip address from specified request.
	 * 
	 * @param  request http request.
	 * 
	 * @return client ip address.
	 */
	public static String getIpAddress(HttpServletRequest request) {
        String ips = request.getHeader("x-forwarded-for");
        if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
            ips = request.getHeader("Proxy-Client-IP");
        }
        if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
            ips = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ips == null || ips.length() == 0 || "unknown".equalsIgnoreCase(ips)) {
            ips = request.getRemoteAddr();
        }

        String[] ipArray = ips.split(",");
        String clientIp = null;
        for (String ip : ipArray) {
            if (!("unknown".equalsIgnoreCase(ip))) {
                clientIp = ip;
                break;
            }
        }
        return clientIp;
    }
	
	/**
	 * Transfer a ISO-8859-1 string to UTF-8 value.
	 * 
	 * @param  value ISO-8859-1 value.
	 * 
	 * @return UTF-8 value.
	 * 
	 * @throws NullPointerException if {@code value} is null.
	 */
	public static String iso88591ToUtf8(String value) {
		return new String(value.getBytes(ISO_8859_1), UTF_8);
	}
	
}
