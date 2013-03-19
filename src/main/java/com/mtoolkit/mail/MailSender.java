package com.mtoolkit.mail;

import java.io.File;
import java.util.Map;

public interface MailSender {
	
	public enum MimeType {
		TEXT, HTML
	}
	
	public boolean sendMessage(String[] to, String msg, Map<String, Object> params, MimeType type, File... attachs);
	
}
