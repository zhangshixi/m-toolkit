package com.mtoolkit.spring.config;

import java.util.Map.Entry;
import java.util.Properties;

import com.mtoolkit.util.EmptyUtil;

public class AbstractPropertyLoader implements PropertyLoader {

	private Properties props;
	public static final String SYSTEM_PREFIX = "system.";
	
	@Override
	public void load(Properties props) {
		this.props = props;
		
		for (Entry<Object, Object> entry : props.entrySet()) {
			if (isSystemProperty((String) entry.getKey())) {
				System.setProperty((String) entry.getKey(), (String) entry.getValue());
			}
		}
	}

	protected String getProperty(String key) {
	    String value = getConfigProperty(key);
		return EmptyUtil.isNotNullEmpty(value) ? value : getSystemProperty(key);
	}
	
	protected String getConfigProperty(String key) {
	    return trim(props.getProperty(key));
	}
	
	protected String getSystemProperty(String key) {
	    return trim(System.getProperty(key));
	}
	
	/* ---- private methods ---- */
	private boolean isSystemProperty(String key) {
		return key != null && key.startsWith(SYSTEM_PREFIX);
	}
	
    private String trim(String source) {
    	return source == null ? null :source.trim();
    }
    
}
