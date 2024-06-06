package m.portfolio.chat.sock.blockingSocket.manager.client;

import lombok.extern.slf4j.Slf4j;
import m.portfolio.chat.sock.blockingSocket.manager.BaseManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ClientManager extends BaseManager {
    public ClientManager(int port, String hostname) {
        super(port, hostname);
    }

    public void execute() {
        try (Socket client = new Socket()) {
            InetSocketAddress ipep = new InetSocketAddress(this.hostname, this.port);
            client.connect(ipep);

            try (OutputStream send = client.getOutputStream();
                 InputStream recv = client.getInputStream();) {

                log.info("Client connected IP address =" + client.getRemoteSocketAddress().toString());

                ExecutorService receiver = Executors.newSingleThreadExecutor();
                receiver.execute(new ClientRunnable(this.BUFFER_SIZE, recv));
                try (Scanner sc = new Scanner(System.in)) {
                    while (true) {
                        String msg = sc.next() + "\r\n";
                        byte[] b = msg.getBytes();
                        send.write(b);
                        if ("exit\r\n".equals(msg)) {
                            break;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
