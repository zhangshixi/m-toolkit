package com.mtoolkit.mail.message;

import java.util.Map;

import com.mtoolkit.mail.MessageGenerator;
import com.mtoolkit.util.StringUtil;

public class NamedMessageGenerator implements MessageGenerator {

	@Override
	public String generate(String message, Map<String, Object> params) {
		return StringUtil.replaceNamedArgs(message, params);
	}
    
}
