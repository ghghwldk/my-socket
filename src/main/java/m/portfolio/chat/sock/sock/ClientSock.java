package m.portfolio.chat.sock.sock;

import java.io.IOException;

public interface ClientSock {
    void start();
    void clear() throws IOException;
}
