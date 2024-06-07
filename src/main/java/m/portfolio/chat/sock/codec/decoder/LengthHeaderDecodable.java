package m.portfolio.chat.sock.codec.decoder;


import java.io.IOException;
import java.io.InputStream;

public interface LengthHeaderDecodable extends Decodable {
    byte[] decode_(InputStream is) throws IOException;
}
