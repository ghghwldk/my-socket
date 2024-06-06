package m.portfolio.chat.sock.blockingSocket.principal.client;

import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.blockingSocket.principal.BaseManager;
import m.portfolio.chat.sock.blockingSocket.principal.util.BaseSender;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ClientManager extends BaseManager {

    public ClientManager(int port, String hostname) {
        super(port, hostname, Executors.newSingleThreadExecutor());
    }

    @Override
    public void start() {
        try (Socket client = new Socket()) {
            InetSocketAddress ipep
                    = new InetSocketAddress(this.hostname, this.port);

            client.connect(ipep);

            try (OutputStream send = client.getOutputStream();
                 InputStream is = client.getInputStream();) {

                log.info("Client connected IP address =" + client.getRemoteSocketAddress().toString());

                this.executorService.execute(new ClientRunnable(is));


            }
        } catch (Throwable e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void clear() {
    }
}
