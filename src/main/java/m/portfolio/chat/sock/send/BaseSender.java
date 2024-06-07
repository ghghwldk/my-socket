package m.portfolio.chat.sock.send;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Objects;

@RequiredArgsConstructor
public class BaseSender implements Sendable{
    protected final LinkedList<OutputStream> outputStreams;

    @Override
    public void send(byte[] bytes) throws IOException{
        outputStreams.forEach(e->{
            try {
                e.write(bytes);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void add(OutputStream outputStream){
        Objects.requireNonNull(this.outputStreams);

        outputStreams.add(outputStream);
    }
}
