package com.tonfun.codecsnetty.bll.handler;

import android.util.Log;

import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;

import io.netty.channel.ChannelHandlerContext;

/**
 * 位置查询应答的处理器
 */
public class LocationQueryResponseHandler extends SignalingHandler{
    private static final String TAG = "LocationQueryReponseHandler";

    public LocationQueryResponseHandler() {
        super(JT808.位置信息查询应答);
    }

    @Override
    protected void messageHandle(ChannelHandlerContext ctx, JTMessage msg) {
        Log.i(TAG, "messageHandle: 位置信息查询的应答");
    }
}
