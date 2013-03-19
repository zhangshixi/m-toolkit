package com.mtoolkit.serializer.support;

import java.io.IOException;

import com.mtoolkit.serializer.Serializer;
import com.mtoolkit.util.ConversionUtil;

/**
 * JDK serializer implementation.
 */
public class JdkSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T target) throws IOException {
        return ConversionUtil.object2Bytes(target);
    }

    @Override
    public <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        return ConversionUtil.bytes2Object(bytes);
    }

}
