package m.portfolio.chat.sock.blockingSocket.codec.decoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class LengthHeaderDecoder implements LengthHeaderDecodable{
    private static final int headerCapacity = 4;

    private static String convert(byte[] byteArray) {
        byte[] lengthBytes = new byte[headerCapacity];
        System.arraycopy(byteArray, 0, lengthBytes, 0, headerCapacity);
        int length = ByteBuffer.wrap(lengthBytes).getInt();

        byte[] bodyBytes = new byte[length];
        System.arraycopy(byteArray, headerCapacity, bodyBytes, 0, length);

        return new String(bodyBytes, StandardCharsets.UTF_8);
    }

    @Override
    public String decode_(byte[] bytes) {
        return this.convert(bytes);
    }

    @Override
    public Object decode(byte[] before) {
        return (Object) this.decode(before);
    }
}
