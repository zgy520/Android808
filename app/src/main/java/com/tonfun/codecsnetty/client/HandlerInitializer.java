package com.tonfun.codecsnetty.client;

import com.tonfun.codecsnetty.bll.codec.DecoderHandler;
import com.tonfun.codecsnetty.bll.codec.EncoderHandler;
import com.tonfun.codecsnetty.bll.handler.CommonResponseHandler;
import com.tonfun.codecsnetty.bll.handler.HeartBetaHandler;
import com.tonfun.codecsnetty.bll.handler.LocationQueryResponseHandler;
import com.tonfun.codecsnetty.bll.handler.RegisterResponseHandler;
import com.tonfun.codecsnetty.consts.SystemConst;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

public class HandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(0, 0, 10));
        // 心跳
        pipeline.addLast(new HeartBetaHandler());
        // 分隔符编码器
        pipeline.addLast(new DelimiterBasedFrameDecoder(SystemConst.MAX_MESSAGE_LEN, Unpooled.wrappedBuffer(SystemConst.MESSAGE_DELIMITER),
                Unpooled.wrappedBuffer(SystemConst.MESSAGE_DELIMITER, SystemConst.MESSAGE_DELIMITER)));
        // 编码
        pipeline.addLast(new EncoderHandler());
        // 解码
        pipeline.addLast(new DecoderHandler());
        // 终端注册的应答处理器
        pipeline.addLast(new RegisterResponseHandler());
        // 位置查询的应答处理器
        pipeline.addLast(new LocationQueryResponseHandler());
        // 通用应答处理器
        pipeline.addLast(new CommonResponseHandler());
    }
}
