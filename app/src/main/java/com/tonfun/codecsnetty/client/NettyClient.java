package com.tonfun.codecsnetty.client;

import android.util.Log;

import com.tonfun.codecsnetty.NettyApplication;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private static final String TAG = "NettyClient";
    private int port;
    private String host;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000);
            bootstrap.option(ChannelOption.SO_REUSEADDR,true);
            bootstrap.handler(new HandlerInitializer());
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
            //添加监听器
            future.addListener((ChannelFuture futureListener) -> {
                final EventLoop eventLoop = futureListener.channel().eventLoop();
                if (!futureListener.isSuccess()) {
                    Log.i(TAG, "connect: 准备重连");
                } else {
                    Log.i(TAG, "netty连接成功");
                    Channel channel = futureListener.channel();
//                    channel.writeAndFlush(JT808Beans.H2013(JT808Beans.T0100()));
                    NettyApplication.channel = channel;
                }
            });
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
