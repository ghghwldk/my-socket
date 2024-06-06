package m.portfolio.chat.sock.blockingSocket.manager.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@RequiredArgsConstructor
@Slf4j
public class ServerRunnable implements Runnable {
    private final Socket client;
    private final int bufferSize;

    @Override
    public void run() {
        try (OutputStream os = client.getOutputStream();
             InputStream is = client.getInputStream()) {
            String msg = "Welcome server!\r\n>";
            byte[] b = msg.getBytes();

            os.write(b);

            waitAndEcho(is, os, msg);
        } catch (Throwable e) {
            log.info(e.getMessage());
        } finally {
            log.info("Client disconnected IP address =" + client.getRemoteSocketAddress().toString());
        }
    }

    private void waitAndEcho(InputStream is, OutputStream os, String msg)
            throws IOException {
        while (true) {
            byte[] b = new byte[bufferSize];
            is.read(b, 0, b.length);

            msg = new String(b);
            msg = "echo : " + msg + ">";

            b = msg.getBytes();
            os.write(b);
        }
    }
}
