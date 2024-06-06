package m.portfolio.chat.sock.blockingSocket.handler;

import lombok.RequiredArgsConstructor;
import m.portfolio.chat.sock.blockingSocket.codec.decoder.Decodable;
import m.portfolio.chat.sock.blockingSocket.codec.decoder.LengthHeaderDecodable;
import m.portfolio.chat.sock.blockingSocket.codec.decoder.LengthHeaderDecoder;

@RequiredArgsConstructor
public abstract class BaseLengthHeaderHandler implements Handleable {
    private final LengthHeaderDecodable decodable
            = new LengthHeaderDecoder();

    @Override
    public void onTriggered(byte[] bytes) {
        String body
                = decodable.decode_(bytes);

        this.handle(body);
    }

    public abstract void handle(String body);
}
