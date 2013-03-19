package com.mtoolkit.mail;

import java.util.Map;

public interface MessageGenerator {
	
	public String generate(String message, Map<String, Object> params);
	
}
