package m.portfolio.chat.sock.blockingSocket.codec.endcoder;

public interface Encodable {
    byte[] encode(Object before);
}
