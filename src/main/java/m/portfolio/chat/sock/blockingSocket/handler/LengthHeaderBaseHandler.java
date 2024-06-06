package m.portfolio.chat.sock.blockingSocket.handler;

import m.portfolio.chat.sock.blockingSocket.codec.endcoder.LengthHeaderEncoder;

public abstract class LengthHeaderBaseHandler implements Handleable {
    @Override
    public void onListen(byte[] bytes) {
        String body
                = LengthHeaderEncoder.convert(bytes);

        this.onListen(body);
    }

    public abstract void onListen(String body);
}
