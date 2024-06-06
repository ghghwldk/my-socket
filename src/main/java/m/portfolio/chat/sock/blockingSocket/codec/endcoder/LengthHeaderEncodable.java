package m.portfolio.chat.sock.blockingSocket.codec.endcoder;

public interface LengthHeaderEncodable extends Encodable {
    byte[] encode(String message);
}
