package m.portfolio.chat.sock.send;

import lombok.NonNull;
import m.portfolio.chat.sock.codec.encoder.LengthHeaderEncodable;
import m.portfolio.chat.sock.codec.encoder.LengthHeaderEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;


public class BaseLengthHeaderSender extends BaseSender{
    private static final LengthHeaderEncodable encodable
            = new LengthHeaderEncoder();

    public BaseLengthHeaderSender(){
        super(new LinkedList<>());
    }

    public BaseLengthHeaderSender(@NonNull LinkedList<OutputStream> outputStreams) {
        super(outputStreams);
    }

    public void send(String message) throws IOException {
        byte[] bytes = encodable.encode(message);

        super.send(bytes);
    }
}
