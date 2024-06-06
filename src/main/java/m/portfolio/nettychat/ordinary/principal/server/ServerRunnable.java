package m.portfolio.nettychat.ordinary.principal.server;

import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@RequiredArgsConstructor
public class ServerRunnable implements Runnable {
    private static final int BUFFER_SIZE = 1024;
    private final Socket client;



    @Override
    public void run() {
        try (Socket thisClient = client;
             OutputStream send = client.getOutputStream();
             InputStream recv = client.getInputStream();) {
            String msg = "Welcome server!\r\n>";
            byte[] b = msg.getBytes();

            send.write(b);
            StringBuffer sb = new StringBuffer();
            while (true) {
                b = new byte[BUFFER_SIZE];
                recv.read(b, 0, b.length);

                msg = new String(b);
                msg = "echo : " + msg + ">";

                b = msg.getBytes();
                send.write(b);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println("Client disconnected IP address =" + client.getRemoteSocketAddress().toString());
        }
    }
}
