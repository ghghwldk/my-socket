package m.portfolio.chat.sock.codec.encoder;

public interface Encodable {
    byte[] encode(Object before);
}
