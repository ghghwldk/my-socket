package m.portfolio.chat.sock.codec.encoder;

public interface LengthHeaderEncodable extends Encodable {
    byte[] encode(String message);
}
