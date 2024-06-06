package m.portfolio.chat.sock.netty.converter;

import java.nio.ByteBuffer;

public class ByteConverter {
    public static byte[] convert(String msg) {
        byte[] lengthBytes = ByteBuffer.allocate(4).putInt(msg.length()).array();

        // Convert the body to bytes
        byte[] bodyBytes = msg.getBytes();

        // Create the result byte array
        byte[] resultByteArray = new byte[lengthBytes.length + bodyBytes.length];

        // Copy lengthBytes and bodyBytes into resultByteArray
        System.arraycopy(lengthBytes, 0, resultByteArray, 0, lengthBytes.length);
        System.arraycopy(bodyBytes, 0, resultByteArray, lengthBytes.length, bodyBytes.length);

        return resultByteArray;
    }
}
