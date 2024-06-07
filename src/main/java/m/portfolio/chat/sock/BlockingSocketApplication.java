package m.portfolio.chat.sock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "m.portfolio.chat")
public class BlockingSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockingSocketApplication.class, args);
	}

}
