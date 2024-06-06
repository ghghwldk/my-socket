package m.portfolio.nettychat.ordinary.principal;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
// 서버 클래스
public class Server {
  private final static int BUFFER_SIZE = 1024;
  private final static int PORT = 9999;

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket()) {
      InetSocketAddress ipep = new InetSocketAddress(PORT);
      server.bind(ipep);

      ExecutorService receiver = Executors.newCachedThreadPool();
      List<Socket> list = new ArrayList<>();
      while (true) {
        try {
          Socket client = server.accept();
          list.add(client);
          // 접속 정보 콘솔 출력
          receiver.execute(() -> {
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
                sb.append(msg.replace("\0", ""));
                if (sb.length() > 2 && sb.charAt(sb.length() - 2) == '\r' && sb.charAt(sb.length() - 1) == '\n') {
                  msg = sb.toString();
                  sb.setLength(0);
                  System.out.println(msg);
                  if ("exit\r\n".equals(msg)) {
                    break;
                  }
                  msg = "echo : " + msg + ">";
                  b = msg.getBytes();
                  send.write(b);
                }
              }
            } catch (Throwable e) {
              e.printStackTrace();
            } finally {
              System.out.println("Client disconnected IP address =" + client.getRemoteSocketAddress().toString());
            }
          });
        } catch (Throwable e) {
          e.printStackTrace();
        }
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
}
