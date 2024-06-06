package m.portfolio.chat.sock.blockingSocket.codec.endcoder;

import m.portfolio.chat.sock.blockingSocket.codec.decoder.LengthHeaderDecodable;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class LengthHeaderEncoder
        implements LengthHeaderEncodable{
    private static final int headerCapacity = 4;

    private static byte[] convert(String msg) {
        byte[] lengthBytes = ByteBuffer.allocate(headerCapacity).putInt(msg.length()).array();
        byte[] bodyBytes = msg.getBytes();
        byte[] resultByteArray = new byte[lengthBytes.length + bodyBytes.length];

        System.arraycopy(lengthBytes, 0, resultByteArray, 0, lengthBytes.length);
        System.arraycopy(bodyBytes, 0, resultByteArray, lengthBytes.length, bodyBytes.length);

        return resultByteArray;
    }

    @Override
    public byte[] encode(Object before) {
        return this.encode((String) before);
    }

    @Override
    public byte[] encode(String message) {
        return this.convert(message);
    }


}
