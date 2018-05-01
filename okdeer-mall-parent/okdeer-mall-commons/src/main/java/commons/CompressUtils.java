package commons;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;

import java.util.Arrays;

public class CompressUtils {

    private static LZ4Factory factory = LZ4Factory.fastestInstance();

    private static void putInteger(byte[] bytes, int i) {
        bytes[3] = (byte) (i & 0xFF);
        bytes[2] = (byte) (i >> 8 & 0xFF);
        bytes[1] = (byte) (i >> 16 & 0xFF);
        bytes[0] = (byte) (i >> 24 & 0xFF);
    }

    private static int getInteger(byte[] bytes) {
        int b0 = bytes[0] & 0xFF;
        int b1 = bytes[1] & 0xFF;
        int b2 = bytes[2] & 0xFF;
        int b3 = bytes[3] & 0xFF;
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    public static byte[] compress(byte[] data) {
        if (data == null || data.length == 0) {
            return Constants.EMPTY_ARRAY;
        }

        final int length = data.length;

        LZ4Compressor compressor = factory.fastCompressor();
        int maxCompressedLength = compressor.maxCompressedLength(length);

        byte[] compressed = new byte[maxCompressedLength + 4];
        putInteger(compressed, length);

        int compressedLength = compressor.compress(data, 0, length, compressed, 4, maxCompressedLength);
        return Arrays.copyOf(compressed, compressedLength + 4);
    }

    public static byte[] decompress(byte[] data) {
        if (data == null || data.length == 0) {
            return Constants.EMPTY_ARRAY;
        }

        int length = getInteger(data);
        return factory.fastDecompressor().decompress(data, 4, length);
    }

    public static byte[] compressString(String data) {
        return compress(data.getBytes(Constants.UTF8));
    }

    public static String decompressAsString(byte[] data) {
        return new String(decompress(data), Constants.UTF8);
    }
}
