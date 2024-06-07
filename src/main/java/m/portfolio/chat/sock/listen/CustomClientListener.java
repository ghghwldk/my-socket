package m.portfolio.chat.sock.listen;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.handler.Handleable;

import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
public class CustomClientListener implements Runnable {
    private final Handleable handleable;
    private final InputStream is;

    @Override
    public void run() {
        log.info("runnable started");
        try {
            while (true) {
                handleable.handle(is);
            }
        } catch (Throwable e) {
            log.info("here, " + e.getMessage());
        }
    }


}
