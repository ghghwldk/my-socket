package m.portfolio.chat.sock.blockingSocket.manager;

import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.blockingSocket.listen.CustomServerListener;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
public class ServerManager extends BaseManager {
    List<Socket> clientList = new ArrayList<>();

    public ServerManager(int port, String hostname) {
        super(port, hostname, Executors.newCachedThreadPool());
    }

    @Override
    public void start(){
        try (ServerSocket server = new ServerSocket()) {
            InetSocketAddress ipep = new InetSocketAddress(this.port);
            server.bind(ipep);

            while (true) {
                try {
                    Socket client = server.accept();

                    clientList.add(client);
                    executorService.execute(new CustomServerListener(client, ));
                } catch (Throwable e) {
                    log.info(e.getMessage());
                }
            }
        } catch (Throwable e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void clear() {

    }
}
