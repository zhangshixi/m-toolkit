package com.mtoolkit.serializer;

import java.io.IOException;
import java.io.NotSerializableException;

/**
 * Object serializer.
 */
public interface Serializer {
    
    /**
     * Serializes a <code>object</code> value to a <code>byte array</code>.
     * 
     * @param   target a object type value.
     * 
     * @return  a byte array that converted from the specified object value.
     * 
     * @throws  IOExceptoin if an I/O error occurs while serializing.
     */
    public <T> byte[] serialize(T target) throws IOException;
    
    /**
     * Deserializes a <code>byte array</code> to a </code>object</code> value.
     * 
     * @param   bytes a byte array.
     * 
     * @return  a object value that converted from the specified byte array.
     * 
     * @throws  IOException 
     *          if an I/O error occurs while converting object to byte array.
     * @throws  ClassNotFoundException 
     *          if not found the target object class.
     * @throws  NotSerializableException 
     *          if the target object is not implemented {@link java.io.Serializable} interface.
     * @throws  NullPointerException if <code>bytes</code> is null.
     */
    public <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException;
    
}
