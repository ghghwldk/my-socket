package m.portfolio.chat.sock.codec.decoder;

import java.io.IOException;
import java.io.InputStream;

public interface Decodable {
    Object decode(InputStream is) throws IOException;
}
