package m.portfolio.chat.sock.blockingSocket.codec.decoder;

import m.portfolio.chat.sock.blockingSocket.codec.decoder.Decodable;

public interface LengthHeaderDecodable extends Decodable {
    String decode_(byte[] before);
}
