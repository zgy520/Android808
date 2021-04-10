package com.tonfun.codecsnetty.bll.handler;

import com.tonfun.codecsnetty.NettyApplication;
import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;

import org.apache.commons.lang3.StringUtils;

import io.netty.channel.Channel;

/**
 * 消息发送类
 */
public class MessageSendUtils {
    /**
     * 所有消息发送的统一入口
     * @param channel
     * @param message
     */
    public static void sendMessage(Channel channel, JTMessage message){
        if (channel != null && StringUtils.isNotBlank(NettyApplication.token)){
            channel.writeAndFlush(message);
        }
    }
}
