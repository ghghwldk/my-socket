package m.portfolio.chat.sock.sock;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public abstract class BaseSock {
    protected final int port;
    protected final ExecutorService executorService;

    public abstract void start();
    public abstract void clear() throws IOException;
}
