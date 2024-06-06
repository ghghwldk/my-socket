package m.portfolio.chat.sock.blockingSocket.handler;

public interface Handleable {
    void onListen(byte[] bytes);
}
