package com.tonfun.codecsnetty;

import android.app.Application;
import android.content.Context;

import io.netty.channel.Channel;

public class NettyApplication extends Application {
    public static Channel channel = null;
    public static Context nettyContext = null;
    // 终端鉴权码
    public static String token = "";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        Reflection.unseal(base);
        nettyContext = base;
    }
}
