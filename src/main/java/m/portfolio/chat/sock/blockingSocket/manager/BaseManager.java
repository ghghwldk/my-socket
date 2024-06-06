package m.portfolio.chat.sock.blockingSocket.manager;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public abstract class BaseManager {
    protected final int port;
    protected final String hostname;
    protected final static int BUFFER_SIZE = 1024;

    protected ExecutorService executorService = Executors.newCachedThreadPool();
}
