package m.portfolio.chat.blocking;

import m.portfolio.chat.sock.sock.ServerSock;

import java.io.IOException;

public class MockingController {
    private final ServerSock serverSock;

    public MockingController(ServerSock serverSock) {
        this.serverSock = serverSock;
    }

    public void onMessage(String message) throws IOException {
        serverSock.broadcast(message);
    }
}
