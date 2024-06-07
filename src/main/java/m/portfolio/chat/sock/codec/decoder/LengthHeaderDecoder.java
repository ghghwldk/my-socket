package m.portfolio.chat.sock.codec.decoder;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.ByteBuffer;

import static m.portfolio.chat.sock.sock.Constants.headerLength;

@RequiredArgsConstructor
public class LengthHeaderDecoder implements LengthHeaderDecodable{
    @Override
    public Object decode(InputStream is) throws IOException {
        return (Object) this.decode_(is);
    }

    @Override
    public byte[] decode_(InputStream is) throws IOException {
        byte[] lengthBytes = this.readLengthBytes(is);
        int length = ByteBuffer.wrap(lengthBytes).getInt();

        return receive(is, length);
    }

    private byte[] receive(InputStream is, int length) throws SocketException, IOException {
        byte[] bytes = new byte[length];

        is.read(bytes);

        return bytes;
    }

    private byte[] readLengthBytes(InputStream is) throws IOException {
        return this.receive(is, headerLength);
    }
}
