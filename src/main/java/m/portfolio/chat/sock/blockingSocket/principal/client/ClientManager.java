package m.portfolio.chat.sock.blockingSocket.principal.client;

import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.blockingSocket.principal.BaseManager;
import m.portfolio.chat.sock.blockingSocket.principal.util.ConsoleUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
public class ClientManager extends BaseManager {
    private final ConsoleUtil consoleUtil;

    public ClientManager(int port, String hostname, ConsoleUtil consoleUtil) {
        super(port, hostname);
        this.consoleUtil = consoleUtil;
    }

    @Override
    public void init() {
        try (Socket client = new Socket()) {
            InetSocketAddress ipep
                    = new InetSocketAddress(this.hostname, this.port);

            client.connect(ipep);

            try (OutputStream send = client.getOutputStream();
                 InputStream is = client.getInputStream();) {

                log.info("Client connected IP address =" + client.getRemoteSocketAddress().toString());

                this.executorService.execute(new ClientRunnable(is));

                consoleUtil.waitAndSend(send);
            }
        } catch (Throwable e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void clear() {

    }
}
