package m.portfolio.chat.sock.blockingSocket.principal;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public abstract class BaseManager {
    protected final int port;
    protected final String hostname;
    protected final static int BUFFER_SIZE = 1024;

    protected ExecutorService executorService = Executors.newCachedThreadPool();

    protected abstract void init();

    public abstract void clear();
}
