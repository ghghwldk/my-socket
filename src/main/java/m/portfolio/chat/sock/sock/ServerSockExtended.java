package m.portfolio.chat.sock.sock;

import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.send.BaseLengthHeaderSender;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
public class ServerSockExtended extends BaseSock
        implements ServerSock {
    private static final List<Socket> clientList = new ArrayList<>();
    private final BaseLengthHeaderSender baseLengthHeaderSender;

    public ServerSockExtended(int port, BaseLengthHeaderSender sender) {
        super(port, Executors.newCachedThreadPool());
        this.baseLengthHeaderSender = sender;
    }

    @Override
    public void start(){
        try (java.net.ServerSocket server = new java.net.ServerSocket()) {
            InetSocketAddress ipep = new InetSocketAddress(this.port);
            server.bind(ipep);

            log.info("--after bound--");
            while (true) {
                try {
                    Socket client = server.accept();
                    clientList.add(client);
                    this.baseLengthHeaderSender
                            .add(client.getOutputStream());
                } catch (Throwable e) {
                    log.info(e.getMessage());
                }
            }
        } catch (Throwable e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void clear() throws IOException {
        for (Socket socket : clientList) {
            socket.close();
        }

        clientList.clear();
    }

    @Override
    public void broadcast(String message) throws IOException {
        this.baseLengthHeaderSender.send(message);
    }
}
