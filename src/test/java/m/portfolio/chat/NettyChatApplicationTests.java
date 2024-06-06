package m.portfolio.chat;

import m.portfolio.chat.sock.netty.converter.ByteConverter;
import m.portfolio.chat.sock.netty.initializer.ClientInitializer;
import m.portfolio.chat.sock.netty.initializer.ServerInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@SpringBootTest
class NettyChatApplicationTests {
	@Autowired
	private ServerInitializer serverManager;
	@Autowired
	private ClientInitializer clientManager;

	private ExecutorService executorService = Executors.newFixedThreadPool(2);

	@Test
	void contextLoads() throws InterruptedException {
		executorService.submit(() -> {
			try {
				serverManager.initialize();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});

		clientManager.initialize();

		executorService.shutdown();

		serverManager.shutdown();
		clientManager.shutdown();
	}

	@Test
	void test(){
		String a = "here!! this is a message";

		ByteConverter byteConverter = new ByteConverter();
		byte[] bytes = ByteConverter.convert(a);

		System.out.println(ByteConverter.convertBack(bytes));
	}
}
