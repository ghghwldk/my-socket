package m.portfolio.chat.sock.blockingSocket.principal.util;

import java.io.IOException;
import java.io.OutputStream;

public interface BaseSender {
    void send(String message) throws IOException;
}
