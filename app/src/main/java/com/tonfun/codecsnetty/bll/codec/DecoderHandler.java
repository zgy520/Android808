package com.tonfun.codecsnetty.bll.codec;

import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageDecoder;
import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageEncoder;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class DecoderHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        JTMessageAdapter messageAdapter = new JTMessageAdapter(
                new JTMessageEncoder("com.tonfun.codecsnetty.bll.protocol.t808"),
                new JTMessageDecoder("com.tonfun.codecsnetty.bll.protocol.t808")
        );
        Object message = messageAdapter.decode(buf);

        if (message != null)
            out.add(message);
        buf.skipBytes(buf.readableBytes());
    }
}
