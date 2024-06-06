package m.portfolio.chat.sock.blockingSocket.listen;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
@RequiredArgsConstructor
public class CustomClientListener implements Runnable {
    private final InputStream is;

    @Override
    public void run() {
        try {
            while (true) {
                byte[] b = is.readAllBytes();

                log.info(new String(b));
            }
        } catch (Throwable e) {
            log.info(e.getMessage());
        }
    }
}
