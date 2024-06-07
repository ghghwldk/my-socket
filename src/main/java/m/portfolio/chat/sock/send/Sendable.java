package m.portfolio.chat.sock.send;

import java.io.IOException;

public interface Sendable {
    void send(byte[] bytes) throws IOException;
}
