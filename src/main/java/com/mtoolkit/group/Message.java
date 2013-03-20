package com.mtoolkit.group;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Group message.
 * 
 * @author 	<a href="mailto:xishizhang@gmail.com">ZhangShixi</a>
 * @version 1.0, 12/10/2011
 * @since 	JDK1.5
 */
public class Message implements Serializable {

    /** serial version id */
    private static final long serialVersionUID = 4120582789199432804L;
    
    /** message name */
    private String _name;
    /** message parameters */
    private Map<String, Serializable> _params;
    
    /** default message name */
    public static final String DEFAULT_MESSAGE_NAME = "zhangsx.message";

    /**
     * Creates a message instance with the {@link #DEFAULT_MESSAGE_NAME}.
     */
    public Message() {
        this(DEFAULT_MESSAGE_NAME);
    }

    /**
     * Creates a message instance with the specified name.
     * 
     * @param  name message name.
     * 
     * @throws NullPointerException if {@code name} is null.
     */
    public Message(String name) {
        if (name == null) {
            throw new NullPointerException("name");
        }

        _name = name;
        _params = new HashMap<String, Serializable>();
    }

    /**
     * Gets message name.
     * If not set message name, will return {@link #DEFAULT_MESSAGE_NAME}.
     * 
     * @return message name.
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets the parameter value of the specified parameter name.
     * 
     * @param  name parameter name.
     * 
     * @return parameter value.
     */
    public Serializable getParam(String name) {
        return _params.get(name);
    }

    /**
     * Sets a pair of parameter name and parameter value.
     * 
     * @param  name  parameter name.
     * @param  value parameter value.
     */
    public void setParam(String name, Serializable value) {
        _params.put(name, value);
    }

    /**
     * Removes the parameter value of the specified parameter name.
     * 
     * @param name parameter name.
     */
    public void removeParam(String name) {
        _params.remove(name);
    }

    /**
     * Gets parameter names set.
     * 
     * @return parameter names set.
     */
    public Set<String> paramNameSet() {
        return _params.keySet();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof Message)) {
    		return false;
    	}
    	
    	Message message = (Message) obj;
    	return _name.equals(message._name) && 
    		   _params.equals(message._params);
    }
    
    @Override
    public int hashCode() {
    	int hash = 17;
    	hash = 37 * hash + _name.hashCode();
    	hash = 37 * hash + _params.hashCode();
    		
    	return hash;
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();
        buff.append(Message.class.getName());
        buff.append("[name=").append(_name);
        buff.append(",");
        buff.append("params=").append(_params.toString());
        buff.append("]");

        return buff.toString();
    }
    
}
