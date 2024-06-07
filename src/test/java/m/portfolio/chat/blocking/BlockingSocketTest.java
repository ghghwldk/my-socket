package m.portfolio.chat.blocking;

import m.portfolio.chat.global.property.BlockingSocketProperty;
import m.portfolio.chat.sock.blockingSocket.handler.CustomLengthHeaderHandler;
import m.portfolio.chat.sock.blockingSocket.sock.ClientSock;
import m.portfolio.chat.sock.blockingSocket.sock.ClientSockExtended;
import m.portfolio.chat.sock.blockingSocket.sock.ServerSock;
import m.portfolio.chat.sock.blockingSocket.sock.ServerSockExtended;
import m.portfolio.chat.sock.blockingSocket.send.BaseLengthHeaderSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestClass;

import java.io.IOException;

public class BlockingSocketTest {
    private static final BlockingSocketProperty blockingSocketProperty
            = new BlockingSocketProperty(9091, "127.0.0.1");
    private MockingController mockingController;
    private ServerSock serverSock;
    private ClientSock clientSock;

    private static final BaseLengthHeaderSender standardSender =
            new BaseLengthHeaderSender();
    private static final CustomLengthHeaderHandler customLengthHeaderHandler =
            new CustomLengthHeaderHandler();

    @Test
    public void Test(){

    }

    @DisplayName("--초기화--")
    @BeforeEach
    public void init(){
        int port = blockingSocketProperty.getPort();

        this.serverSock = new ServerSockExtended(
                port,
                new BaseLengthHeaderSender()
        );

        this.clientSock = new ClientSockExtended(
                port,
                blockingSocketProperty.getHostname()
        );

        this.mockingController = new MockingController(
                new ServerSockExtended(port, standardSender)
        );

        this.serverSock.start();
        this.clientSock.start();
    }

    @DisplayName("--clear 되는지 확인--")
    @AfterEach
    public void clear() throws IOException {
        this.clientSock.clear();
        this.serverSock.clear();
    }
}
