package com.mtoolkit.mail.support;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import com.mlogger.Loggers;

public class VirtualMailSender extends AbstractMailSender {

    /** logger utility */
    protected final Loggers logger = Loggers.getLoggers(VirtualMailSender.class);
    
	@Override
	public boolean sendMessage(String[] to, String msg, Map<String, Object> params, MimeType type, File... attachs) {
		logger.info("==>> Virtual mail sender sended an email succeed!");
		logger.debug("mail to  : {0}", Arrays.toString(to));
		logger.debug("message  : {0}", msg);
		logger.debug("parameter: {0}", params);
		logger.debug("mime type: {0}", type.name());
		logger.debug("attachs  : {0}", Arrays.toString(attachs));
		
		return true;
	}

}
