package m.portfolio.chat.sock.blockingSocket.principal.util;

import lombok.RequiredArgsConstructor;
import m.portfolio.chat.sock.blockingSocket.converter.LengthHeaderConverter;
import m.portfolio.chat.sock.blockingSocket.principal.client.ClientManager;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

@RequiredArgsConstructor
public class ClientBaseSender implements BaseSender {
    private final OutputStream os;

    @Override
    public void send(String message) throws IOException {
        byte[] b = LengthHeaderConverter.convert(message);

        this.os.write(b);
    }
}
