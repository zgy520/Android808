package com.tonfun.codecsnetty.bll.handler;

import android.util.Log;

import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;

import io.netty.channel.ChannelHandlerContext;

public class CommonResponseHandler extends SignalingHandler{
    private static final String TAG = "CommonResponseHandler";

    public CommonResponseHandler() {
        super(JT808.平台通用应答);
    }

    @Override
    protected void messageHandle(ChannelHandlerContext ctx, JTMessage msg) {
//        Log.i(TAG, "messageHandle: 平台通用应答");
    }
}
