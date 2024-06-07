package m.portfolio.chat.sock.handler;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomLengthHeaderHandler extends BaseLengthHeaderHandler{

    @Override
    public void handle(byte[] bytes) {

        log.info(new String(bytes, StandardCharsets.UTF_8));

    }
}
