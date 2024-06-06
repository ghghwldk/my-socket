package m.portfolio.chat.sock.blockingSocket.listen;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.blockingSocket.codec.endcoder.Encodable;
import m.portfolio.chat.sock.blockingSocket.handler.Handleable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class CustomServerListener implements Runnable{
    private final Socket client;
    private final Handleable handleable;
    private static final String SUCCESSFUL_CONNECTION_MSG
            = "Successful connection!";

    @SneakyThrows
    @Override
    public void run() {
        try (OutputStream os = client.getOutputStream();
             InputStream is = client.getInputStream()) {
//            os.write(encodable.encode(SUCCESSFUL_CONNECTION_MSG));
            wait(is);
        } catch (Throwable e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            log.info("Client disconnected IP address =" + client.getRemoteSocketAddress().toString());
        }
    }

    private void wait(InputStream is)
            throws IOException {
        while (true) {
            byte[] bytes = is.readAllBytes();

            handleable.onTriggered(bytes);
        }
    }
}
