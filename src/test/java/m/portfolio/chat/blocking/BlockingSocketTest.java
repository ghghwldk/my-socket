package m.portfolio.chat.blocking;

import m.portfolio.chat.global.property.BlockingSocketProperty;
import m.portfolio.chat.sock.handler.CustomLengthHeaderHandler;
import m.portfolio.chat.sock.sock.ClientSock;
import m.portfolio.chat.sock.sock.ClientSockExtended;
import m.portfolio.chat.sock.sock.ServerSock;
import m.portfolio.chat.sock.sock.ServerSockExtended;
import m.portfolio.chat.sock.send.BaseLengthHeaderSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingSocketTest {
    private static final BlockingSocketProperty blockingSocketProperty
            = new BlockingSocketProperty(9091, "127.0.0.1");
    private MockingController mockingController;
    private ServerSock serverSock;
    private ClientSock clientSock;

    private static final BaseLengthHeaderSender standardSender =
            new BaseLengthHeaderSender();
//    private static final CustomLengthHeaderHandler customLengthHeaderHandler =
//            new CustomLengthHeaderHandler();

    @Test
    public void Test() throws IOException, InterruptedException {
        ExecutorService es
                = Executors.newFixedThreadPool(2);

        es.execute(()->{
            this.serverSock.start();
        });
        es.execute(()->{
            this.clientSock.start();
        });


        Thread.sleep(10000);
        mockingController.onMessage("this is for the test...");
        Thread.sleep(10000);
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
                blockingSocketProperty.getHostname(),
                new CustomLengthHeaderHandler()
        );

        this.mockingController = new MockingController(
                this.serverSock
        );
    }

    @DisplayName("--clear 되는지 확인--")
    @AfterEach
    public void clear() throws IOException {
        this.clientSock.clear();
        this.serverSock.clear();
    }
}
