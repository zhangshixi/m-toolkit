package com.mtoolkit.serializer.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.mtoolkit.serializer.Serializer;

/**
 * Hessian serializer implementation.
 */
public class HessianSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T target) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Hessian2Output output = null;
        try {
            output = new Hessian2Output(out);
            output.writeObject(target);
            output.flush();
        } finally {
            if (output != null) {
                output.close();
            }
        }
        
        return out.toByteArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public<T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        Hessian2Input input = null;
        try {
            input = new Hessian2Input(new ByteArrayInputStream(bytes));
            return (T) input.readObject();
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

}
