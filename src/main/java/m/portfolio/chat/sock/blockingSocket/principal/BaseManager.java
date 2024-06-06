package m.portfolio.chat.sock.blockingSocket.principal;

import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public abstract class BaseManager {
    protected final int port;
    protected final String hostname;
    protected final ExecutorService executorService;

    protected abstract void start();


    public abstract void clear();

}
