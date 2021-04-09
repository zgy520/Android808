package com.tonfun.codecsnetty.bll.handler;

import com.tonfun.codecsnetty.bll.codec.MessageHandler;
import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;

import io.netty.channel.ChannelHandlerContext;

public abstract class SignalingHandler extends MessageHandler {

    private int type = 0;

    public SignalingHandler(int type) {
        this.type = type;
    }

    @Override
    protected boolean handle(ChannelHandlerContext ctx, JTMessage msg) {
        boolean result = false;
        if (msg != null && msg.getHeader() != null && msg.getHeader().getMessageId() == type) {
            result = true;
            messageHandle(ctx, msg);
        }
        return result;
    }

    protected void messageHandle(ChannelHandlerContext ctx, JTMessage msg) {

    }
}
