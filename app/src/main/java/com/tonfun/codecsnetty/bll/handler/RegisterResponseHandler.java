package com.tonfun.codecsnetty.bll.handler;

import android.util.Log;

import com.tonfun.codecsnetty.NettyApplication;
import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;
import com.tonfun.codecsnetty.bll.protocol.t808.T8100;

import io.netty.channel.ChannelHandlerContext;

public class RegisterResponseHandler extends SignalingHandler {
    private static final String TAG = "RegisterResponseHandler";

    public RegisterResponseHandler() {
        super(JT808.终端注册应答);
    }


    @Override
    protected void messageHandle(ChannelHandlerContext ctx, JTMessage msg) {
        Log.i(TAG, "messageHandle: 收到了终端注册的应答" + msg.toString());
        T8100 t8100 = (T8100)msg;
        Log.i(TAG, "获取到的token为: " + t8100.getToken());
        NettyApplication.token = t8100.getToken();
    }
}
