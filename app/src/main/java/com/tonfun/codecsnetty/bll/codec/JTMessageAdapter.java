package com.tonfun.codecsnetty.bll.codec;


import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageDecoder;
import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageEncoder;

import io.github.yezhihao.netmc.codec.MessageDecoder;
import io.github.yezhihao.netmc.codec.MessageEncoder;
import io.github.yezhihao.netmc.session.Session;
import io.netty.buffer.ByteBuf;


/**
 * JT消息编解码适配器
 */
public class JTMessageAdapter implements MessageEncoder<JTMessage>, MessageDecoder<JTMessage> {

    private JTMessageEncoder messageEncoder;

    private JTMessageDecoder messageDecoder;

    public JTMessageAdapter(JTMessageEncoder messageEncoder, JTMessageDecoder messageDecoder) {
        this.messageEncoder = messageEncoder;
        this.messageDecoder = messageDecoder;
    }

    @Override
    public ByteBuf encode(JTMessage message) {
        return messageEncoder.encode(message);
    }

    @Override
    public JTMessage decode(ByteBuf buf) {
        return messageDecoder.decode(buf);
    }

    @Override
    public JTMessage decode(ByteBuf buf, Session session) {
        return messageDecoder.decode(buf);
    }
}
