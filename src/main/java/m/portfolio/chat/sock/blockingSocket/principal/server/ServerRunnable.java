package m.portfolio.chat.sock.blockingSocket.principal.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.blockingSocket.converter.LengthHeaderConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class ServerRunnable implements Runnable{
    private final Socket client;

    @Override
    public void run() {
        try (OutputStream os = client.getOutputStream();
             InputStream is = client.getInputStream()) {
            String msg = "Welcome server!\r\n>";
            byte[] b = msg.getBytes();

            os.write(b);

            waitAndEcho(is, os);
        } catch (Throwable e) {
            log.info(e.getMessage());
        } finally {
            log.info("Client disconnected IP address =" + client.getRemoteSocketAddress().toString());
        }
    }

    private void waitAndEcho(InputStream is, OutputStream os)
            throws IOException {
        while (true) {
            byte[] b = is.readAllBytes();
            String msg
                    = LengthHeaderConverter.convert(b);

            os.write(
                    LengthHeaderConverter.convert("echo : " + msg + ">")
            );
        }
    }
}
