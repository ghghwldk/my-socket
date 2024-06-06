package m.portfolio.chat.sock.blockingSocket.config;

import jakarta.annotation.PreDestroy;
import m.portfolio.chat.global.property.BlockingSocketProperty;
import m.portfolio.chat.sock.blockingSocket.principal.BaseManager;
import m.portfolio.chat.sock.blockingSocket.principal.client.ClientManager;
import m.portfolio.chat.sock.blockingSocket.principal.server.ServerManager;
import m.portfolio.chat.sock.blockingSocket.principal.util.ConsoleUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
@ConditionalOnProperty()
public class SocketConfig {
    private final BlockingSocketProperty blockingSocketProperty;

    private final List<BaseManager> managerList = new LinkedList<>();
    private final ServerManager serverManager;
    private final ClientManager clientManager;
    private final ConsoleUtil consoleUtil;

    public SocketConfig(BlockingSocketProperty blockingSocketProperty, ConsoleUtil consoleUtil) {
        this.blockingSocketProperty = blockingSocketProperty;
        this.consoleUtil = consoleUtil;

        this.serverManager = new ServerManager(blockingSocketProperty.getServerPort(),
                blockingSocketProperty.getServerHostname()
        );

        this.clientManager = new ClientManager(blockingSocketProperty.getClientPort(),
                blockingSocketProperty.getClientHostname(),
                consoleUtil
        );

        managerList.add(serverManager);
        managerList.add(clientManager);
    }

    @PreDestroy
    public void clear(){
        managerList.forEach(e->{
            e.clear();
        });
    }
}
