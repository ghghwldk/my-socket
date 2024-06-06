package m.portfolio.chat.sock.blockingSocket.principal.handler;

import org.springframework.stereotype.Component;

public interface StandardMessageHandler {
    void handle(String message);
}
