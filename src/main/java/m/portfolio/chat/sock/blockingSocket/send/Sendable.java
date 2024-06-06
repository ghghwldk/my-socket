package m.portfolio.chat.sock.blockingSocket.send;

import java.io.IOException;

public interface Sendable {
    void send(byte[] bytes) throws IOException;
}
