package com.mtoolkit.mail.message;

import java.io.IOException;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.mtoolkit.mail.MessageGenerator;
import com.mtoolkit.mail.SendMailException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerMessageGenerator implements MessageGenerator {

	private Configuration _configuration;
	
	public FreemarkerMessageGenerator(Configuration configuration) {
		_configuration = configuration;
	}
	
	@Override
	public String generate(String message, Map<String, Object> params) {
		Template template = null;
		String templateMessage = null;
		try {
			template = _configuration.getTemplate(message);
			templateMessage = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
		} catch (IOException e) {
			throw new SendMailException("Load freemarker template message error.", e);
		} catch (TemplateException e) {
			throw new SendMailException("Freemarker template message content error.", e);
		}
		
		return templateMessage;
	}

}
