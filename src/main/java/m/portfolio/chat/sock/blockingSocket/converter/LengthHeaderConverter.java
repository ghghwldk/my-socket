package m.portfolio.chat.sock.blockingSocket.converter;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class LengthHeaderConverter {
    private static final int headerCapacity = 4;

    public static byte[] convert(String msg) {
        byte[] lengthBytes = ByteBuffer.allocate(headerCapacity).putInt(msg.length()).array();
        byte[] bodyBytes = msg.getBytes();
        byte[] resultByteArray = new byte[lengthBytes.length + bodyBytes.length];

        System.arraycopy(lengthBytes, 0, resultByteArray, 0, lengthBytes.length);
        System.arraycopy(bodyBytes, 0, resultByteArray, lengthBytes.length, bodyBytes.length);

        return resultByteArray;
    }

    public static String convert(byte[] byteArray) {
        byte[] lengthBytes = new byte[headerCapacity];
        System.arraycopy(byteArray, 0, lengthBytes, 0, headerCapacity);
        int length = ByteBuffer.wrap(lengthBytes).getInt();

        byte[] bodyBytes = new byte[length];
        System.arraycopy(byteArray, headerCapacity, bodyBytes, 0, length);

        return new String(bodyBytes, StandardCharsets.UTF_8);
    }

}
