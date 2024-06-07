package m.portfolio.chat.global.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "blocking-socket")
@Data
@AllArgsConstructor
public class BlockingSocketProperty {
    int port;
    String hostname;
}
