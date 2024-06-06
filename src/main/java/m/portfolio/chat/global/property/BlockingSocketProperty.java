package m.portfolio.chat.global.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "blocking-socket")
@Data
public class BlockingSocketProperty {
    int serverPort;
    String serverHostname;

    int clientPort;
    String clientHostname;
}
