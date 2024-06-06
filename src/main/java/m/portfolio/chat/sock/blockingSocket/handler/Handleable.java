package m.portfolio.chat.sock.blockingSocket.handler;

public interface Handleable {
    void onTriggered(byte[] bytes);
}
