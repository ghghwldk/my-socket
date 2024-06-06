package m.portfolio.chat.sock.blockingSocket.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import m.portfolio.chat.global.property.BlockingSocketProperty;
import m.portfolio.chat.sock.blockingSocket.principal.BaseManager;
import m.portfolio.chat.sock.blockingSocket.principal.client.ClientManager;
import m.portfolio.chat.sock.blockingSocket.principal.server.ServerManager;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class SocketConfig {
    private final BlockingSocketProperty blockingSocketProperty;

    private final List<BaseManager> managerList = new LinkedList<>();
    private final ServerManager serverManager;
    private final ClientManager clientManager;

    public SocketConfig(BlockingSocketProperty blockingSocketProperty) {
        this.blockingSocketProperty = blockingSocketProperty;

        this.serverManager = new ServerManager(blockingSocketProperty.getServerPort(),
                blockingSocketProperty.getServerHostname()
        );

        this.clientManager = new ClientManager(blockingSocketProperty.getClientPort(),
                blockingSocketProperty.getClientHostname()
        );

        managerList.add(serverManager);
        managerList.add(clientManager);
    }

    @PostConstruct
    public void start(){
        this.serverManager.start();
        this.clientManager.start();
    }

    @PreDestroy
    private void clear(){
        managerList.forEach(BaseManager::clear);
    }
}
