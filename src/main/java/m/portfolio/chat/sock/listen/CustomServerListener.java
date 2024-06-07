package m.portfolio.chat.sock.listen;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.handler.Handleable;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
public class CustomServerListener implements Runnable{
    private final Socket client;
    private final Handleable handleable;

    @SneakyThrows
    @Override
    public void run() {
        try (OutputStream os = client.getOutputStream();
             InputStream is = client.getInputStream()) {
//            os.write(encodable.encode(SUCCESSFUL_CONNECTION_MSG));
        } catch (Throwable e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            log.info("Client disconnected IP address =" + client.getRemoteSocketAddress().toString());
        }
    }
}
