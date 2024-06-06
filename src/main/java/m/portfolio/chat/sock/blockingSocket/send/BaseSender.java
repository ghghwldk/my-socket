package m.portfolio.chat.sock.blockingSocket.send;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;

@RequiredArgsConstructor
public class BaseSender implements Sendable {
    protected final OutputStream os;

    @Override
    public void send(byte[] bytes) throws IOException {
        this.os.write(bytes);
    }
}
