package com.tonfun.codecsnetty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tonfun.codecsnetty.bll.protocol.basics.Header;
import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageDecoder;
import com.tonfun.codecsnetty.bll.protocol.codec.JTMessageEncoder;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;
import com.tonfun.codecsnetty.client.JT808Beans;
import com.tonfun.codecsnetty.client.NettyClient;
import com.tonfun.codecsnetty.thread.ConnectThread;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dalvik.system.DexFile;
import io.github.yezhihao.protostar.util.ClassUtils;

import static io.github.yezhihao.protostar.util.ClassUtils.loadClass;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /*static {
            JTMessageAdapter messageAdapter = new JTMessageAdapter(
                    new JTMessageEncoder("org.yzh.protocol"),
                    new JTMessageDecoder("org.yzh.protocol")
            );
        }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new ConnectThread("192.168.1.47",7611)).start();
    }

    /**
     * 打开系统配置
     *
     * @param view 视图
     */
    public void openSysSetting(View view) {
        Log.i(TAG, "openSysSetting: 发送消息1");
        NettyApplication.channel.writeAndFlush(JT808Beans.H2013(JT808Beans.T0100()));
    }

    /**
     * 打开视频配置
     *
     * @param view 视图
     */
    public void openVideoSetting(View view) {
        Log.i(TAG, "openVideoSetting: 发送消息2");
        NettyApplication.channel.writeAndFlush(JT808Beans.H2013(JT808Beans.T0102_2013()));
    }
}