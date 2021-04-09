package com.tonfun.codecsnetty.thread;

import com.tonfun.codecsnetty.client.NettyClient;

public class ConnectThread implements Runnable{
    private int port;
    private String ip;
    public ConnectThread(String ip,int port){
        this.ip = ip;
        this.port = port;
    }
    @Override
    public void run() {
        NettyClient nettyClient = new NettyClient("192.168.1.47",7611);
        nettyClient.connect();
    }
}
