package com.tonfun.codecsnetty.bll.codec;

import android.util.Log;

import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageDecoder;
import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageEncoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class EncoderHandler extends MessageToByteEncoder<JTMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, JTMessage msg, ByteBuf out) throws Exception {
        JTMessageAdapter messageAdapter = new JTMessageAdapter(
                new JTMessageEncoder("com.tonfun.codecsnetty.bll.protocol.t808"),
                new JTMessageDecoder("com.tonfun.codecsnetty.bll.protocol.t808")
        );
        ByteBuf buf = messageAdapter.encode(msg);
        Log.i("Encoder", "<<<<<原始报文hex=" + ByteBufUtil.hexDump(buf));
        out.writeBytes(buf);
    }
}
