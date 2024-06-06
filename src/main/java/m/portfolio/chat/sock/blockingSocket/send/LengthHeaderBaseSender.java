package m.portfolio.chat.sock.blockingSocket.send;

import m.portfolio.chat.sock.blockingSocket.codec.endcoder.LengthHeaderEncoder;

import java.io.IOException;
import java.io.OutputStream;

public class LengthHeaderBaseSender extends BaseSender{
    public LengthHeaderBaseSender(OutputStream os) {
        super(os);
    }

    public void send(String message) throws IOException {
        byte[] b = LengthHeaderEncoder.convert(message);
        super.send(b);
    }
}
