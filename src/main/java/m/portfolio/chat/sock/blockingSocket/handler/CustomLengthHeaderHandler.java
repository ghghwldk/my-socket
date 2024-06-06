package m.portfolio.chat.sock.blockingSocket.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLengthHeaderHandler extends BaseLengthHeaderHandler{
    @Override
    public void handle(String body) {
        log.info(body);
    }
}
