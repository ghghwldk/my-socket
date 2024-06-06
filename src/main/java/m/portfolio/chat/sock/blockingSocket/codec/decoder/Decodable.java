package m.portfolio.chat.sock.blockingSocket.codec.decoder;

public interface Decodable {
    Object decode(byte[] before);
}
