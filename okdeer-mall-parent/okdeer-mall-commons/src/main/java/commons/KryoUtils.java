package commons;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Input;

public class KryoUtils {

    public static final int DEFAULT_BUFFER_SIZE = 4096;

    private static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = new ThreadLocal<Kryo>() {
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            return kryo;
        }
    };

    public static Kryo get() {
        return KRYO_THREAD_LOCAL.get();
    }

    public static byte[] serializer(Object object) {
       return serializer(object, DEFAULT_BUFFER_SIZE, false);
    }

    public static byte[] serializer(Object object, boolean compressed) {
        return serializer(object, DEFAULT_BUFFER_SIZE, compressed);

    }

    public static byte[] serializer(Object object, int bufferSize, boolean compressed) {
        try (ByteBufferOutput output = new ByteBufferOutput(bufferSize, -1)) {
            KryoUtils.get().writeObject(output, object);
            byte[] bytes = output.toBytes();
            return compressed? CompressUtils.compress(bytes) : bytes;
        }
    }

    public static byte[] serializerWithClass(Object object) {
        return serializerWithClass(object, DEFAULT_BUFFER_SIZE, false);
    }

    public static byte[] serializerWithClass(Object object, boolean compressed) {
        return serializerWithClass(object, DEFAULT_BUFFER_SIZE, compressed);

    }

    public static byte[] serializerWithClass(Object object, int bufferSize, boolean compressed) {
        try (ByteBufferOutput output = new ByteBufferOutput(bufferSize, -1)) {
            KryoUtils.get().writeClassAndObject(output, object);
            byte[] bytes = output.toBytes();
            return compressed? CompressUtils.compress(bytes) : bytes;
        }
    }

    public static <T> T deserializer(byte[] data, Class<T> objectClass) {
        return deserializer(data, objectClass, false);
    }

    public static <T> T deserializer(byte[] data, Class<T> objectClass, boolean compressed) {
        byte[] bytes = compressed ? CompressUtils.decompress(data) : data;

        try (Input input = new ByteBufferInput(bytes)) {
            return KryoUtils.get().readObject(input, objectClass);
        }
    }

    public static <T> T deserializerWithClass(byte[] data) {
        return deserializerWithClass(data, false);
    }

    public static <T> T deserializerWithClass(byte[] data, boolean compressed) {
        byte[] bytes = compressed ? CompressUtils.decompress(data) : data;

        try (Input input = new ByteBufferInput(bytes)) {
            return (T)KryoUtils.get().readClassAndObject(input);
        }
    }
}
