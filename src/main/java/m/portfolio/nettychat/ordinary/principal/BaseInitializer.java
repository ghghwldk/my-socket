package m.portfolio.nettychat.ordinary.principal;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public abstract class BaseInitializer {
    protected final int port;
    protected ExecutorService executorService = Executors.newCachedThreadPool();
}
