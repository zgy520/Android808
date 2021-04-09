package com.tonfun.codecsnetty.bll.codec;

import android.util.Log;

import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public abstract class MessageHandler extends SimpleChannelInboundHandler<JTMessage> {
    private static final String TAG = "MessageHandler";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JTMessage msg) throws Exception {
        Log.i(TAG, "channelRead0: 执行了");

        if (!handle(ctx, msg)) {
            // 如果当前通道不处理该消息，则转到下一个通道处理
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        super.channelInactive(ctx);
        Log.e(TAG, "channelInactive: netty connect close");
        ctx.channel().close();
    }

    /**
     * 处理读取到的消息数据
     * 如果不是该处理器的消息，则不予处理，进行下一个处理器
     *
     * @param ctx 通道处理的上下文对象
     * @param msg 读取到的消息数据
     * @return 如果读取到了消息数据，则返回true，否则返回false
     */
    protected abstract boolean handle(ChannelHandlerContext ctx, JTMessage msg);


}
