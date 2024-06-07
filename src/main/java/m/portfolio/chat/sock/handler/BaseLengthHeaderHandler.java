package m.portfolio.chat.sock.handler;

import m.portfolio.chat.sock.codec.decoder.LengthHeaderDecodable;
import m.portfolio.chat.sock.codec.decoder.LengthHeaderDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.ByteBuffer;

import static m.portfolio.chat.sock.sock.Constants.headerLength;

public abstract class BaseLengthHeaderHandler implements Handleable {
    private final LengthHeaderDecodable decodable
            = new LengthHeaderDecoder();


    @Override
    public void handle(InputStream is) throws IOException {
        byte[] bytes = decodable.decode_(is);

        this.handle(bytes);
    }

    public abstract void handle(byte[] body);
}
