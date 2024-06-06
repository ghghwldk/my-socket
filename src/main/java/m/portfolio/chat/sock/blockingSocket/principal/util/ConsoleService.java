package m.portfolio.chat.sock.blockingSocket.principal.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

@Component
public class ConsoleService implements ConsoleUtil{
    @Override
    public void waitAndSend(OutputStream send){
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String msg = sc.next() + "\r\n";
                byte[] b = msg.getBytes();
                send.write(b);
                if ("exit\r\n".equals(msg)) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
