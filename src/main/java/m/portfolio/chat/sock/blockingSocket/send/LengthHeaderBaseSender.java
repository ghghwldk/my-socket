package m.portfolio.chat.sock.blockingSocket.send;

import m.portfolio.chat.sock.blockingSocket.codec.endcoder.Encodable;
import m.portfolio.chat.sock.blockingSocket.codec.endcoder.LengthHeaderEncoder;

import java.io.IOException;
import java.io.OutputStream;


public abstract class LengthHeaderBaseSender extends BaseSender{
    private final Encodable encodable;

    public LengthHeaderBaseSender(OutputStream os, Encodable encodable) {
        super(os);
        this.encodable = encodable;
    }

    public void send(String message) throws IOException {
        byte[] bytes = encodable.encode(message);

        super.send(bytes);
    }
}
