package m.portfolio.chat.sock.blockingSocket.sock;

import java.io.IOException;

public interface ServerSock{
    void start();
    void clear() throws IOException;
    void broadcast(String message) throws IOException;
}
