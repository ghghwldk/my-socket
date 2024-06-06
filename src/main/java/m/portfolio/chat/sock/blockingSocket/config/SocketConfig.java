package m.portfolio.chat.sock.blockingSocket.config;

import m.portfolio.chat.global.property.BlockingSocketProperty;
import m.portfolio.chat.sock.blockingSocket.manager.client.ClientManager;
import m.portfolio.chat.sock.blockingSocket.manager.server.ServerManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty()
public class SocketConfig {
    private final BlockingSocketProperty blockingSocketProperty;
    private final ServerManager serverManager;
    private final ClientManager clientManager;

    public SocketConfig(BlockingSocketProperty blockingSocketProperty) {
        this.blockingSocketProperty = blockingSocketProperty;

        this.serverManager
                = new ServerManager(blockingSocketProperty.getServerPort(), blockingSocketProperty.getServerHostname());
        this.clientManager
                = new ClientManager(blockingSocketProperty.getClientPort(), blockingSocketProperty.getClientHostname());
    }
}
