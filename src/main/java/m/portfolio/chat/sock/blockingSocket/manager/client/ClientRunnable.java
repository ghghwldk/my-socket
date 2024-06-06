package m.portfolio.chat.sock.blockingSocket.manager.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@RequiredArgsConstructor
@Slf4j
public class ClientRunnable implements Runnable {
    private final int bufferSize;
    private final InputStream recv;

    @Override
    public void run() {
        try {
            while (true) {
                byte[] b = new byte[bufferSize];
                recv.read(b, 0, b.length);
                System.out.println(new String(b));
            }
        } catch (Throwable e) {
            log.info(e.getMessage());
        }
    }
}
