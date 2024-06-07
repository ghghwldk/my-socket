package m.portfolio.chat.sock.handler;

import java.io.IOException;
import java.io.InputStream;

public interface Handleable {
    void handle(InputStream is) throws IOException;
}
