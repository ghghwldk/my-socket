package m.portfolio.chat.sock.blockingSocket.manager.server;

import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.blockingSocket.manager.BaseManager;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ServerManager extends BaseManager {
    List<Socket> list = new ArrayList<>();

    public ServerManager(int port, String hostname) {
        super(port, hostname);
    }


    public void init(){
        try (ServerSocket server = new ServerSocket()) {
            InetSocketAddress ipep = new InetSocketAddress(this.port);
            server.bind(ipep);

            while (true) {
                try {
                    Socket client = server.accept();

                    list.add(client);
                    executorService.execute(new ServerRunnable(client, this.BUFFER_SIZE));
                } catch (Throwable e) {
                    log.info(e.getMessage());
                }
            }
        } catch (Throwable e) {
            log.info(e.getMessage());
        }
    }
}
