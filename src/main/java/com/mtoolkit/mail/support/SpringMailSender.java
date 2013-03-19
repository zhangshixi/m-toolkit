package com.mtoolkit.mail.support;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.mtoolkit.mail.MessageGenerator;
import com.mtoolkit.mail.SendMailException;

public class SpringMailSender extends AbstractMailSender {

    private String   _from;
    private String   _personal;
    private String   _subject;
    private String   _charset;
    private String[] _cc;
    private String[] _bcc;
    
    private JavaMailSender   _mailSender;
    private MessageGenerator _messageGenerator;
    
    public SpringMailSender() {
    }
    
    @Override
    public boolean sendMessage(String[] to, String msg, Map<String, Object> params, MimeType type, File... attachs) {
        final String message = _messageGenerator.generate(msg, params);
        
        switch (type) {
        case TEXT:
            final SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setFrom(_from);
            simpleMessage.setTo(to);
            simpleMessage.setCc(_cc);
            simpleMessage.setBcc(_bcc);
            simpleMessage.setSubject(_subject);
            simpleMessage.setText(message);
            
            _mailSender.send(simpleMessage);
            break;
            
        case HTML:
            MimeMessage mimeMessage = _mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;
            try {
                mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, _charset);
                mimeMessageHelper.setFrom(new InternetAddress(_from, _personal, _charset));
                mimeMessageHelper.setTo(to);
                mimeMessageHelper.setBcc(_bcc);
                mimeMessageHelper.setSubject(_subject);
                mimeMessageHelper.setText(message, true);
            } catch (UnsupportedEncodingException e) {
                throw new SendMailException("Unsupported mime charset:" + _charset, e);
            } catch (MessagingException e) {
                throw new SendMailException("Build mime message error.", e);
            }
            
            _mailSender.send(mimeMessage);
            break;
            
        default:
//          LOGGER.warn("Mail message mime type is not set!");
            break;
        }
        
        return true;
    }
    
    // ---- getter/setter methods ---------------------------------------------------------
    public String getFrom() {
        return _from;
    }
    
    public void setFrom(String from) {
        _from = from;
    }
    
    public String getPersonal() {
        return _personal;
    }
    
    public void setPersonal(String personal) {
        _personal = personal;
    }
    
    public String getSubject() {
        return _subject;
    }
    
    public void setSubject(String subject) {
        _subject = subject;
    }
    
    public String getCharset() {
        return _charset;
    }
    
    public void setCharset(String charset) {
        _charset = charset;
    }
    
    public String[] getCc() {
        return _cc;
    }
    
    public void setCc(String[] cc) {
        _cc = cc;
    }
    
    public String[] getBcc() {
        return _bcc;
    }
    
    public void setBcc(String[] bcc) {
        _bcc = bcc;
    }
    
    public JavaMailSender getMailSender() {
        return _mailSender;
    }
    
    public void setMailSender(JavaMailSender mailSender) {
        _mailSender = mailSender;
    }
    
    public MessageGenerator getMessageGenerator() {
        return _messageGenerator;
    }
    
    public void setMessageGenerator(MessageGenerator messageGenerator) {
        _messageGenerator = messageGenerator;
    }
	
}
