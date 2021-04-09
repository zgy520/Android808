package com.tonfun.codecsnetty.bll.handler;

import com.tonfun.codecsnetty.bll.protocol.commons.JT808;
import com.tonfun.codecsnetty.client.JT808Beans;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBetaHandler extends SignalingHandler{
    private static final String TAG = "HeartBetaHandler";

    public HeartBetaHandler() {
        super(JT808.终端心跳);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event =(IdleStateEvent)evt;
            switch (event.state()){
                case ALL_IDLE:
                    ctx.channel().writeAndFlush(JT808Beans.H2019(JT808Beans.T0002()));
                    break;
                default:
                    break;
            }
        }else {
            super.userEventTriggered(ctx, evt);
        }

    }
}
