package com.tonfun.codecsnetty.bll.protocol.t808;


import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;

import io.github.yezhihao.protostar.DataType;
import io.github.yezhihao.protostar.annotation.Field;
import io.github.yezhihao.protostar.annotation.Message;


/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
@Message({JT808.平台RSA公钥, JT808.终端RSA公钥})
public class T0A00_8A00 extends JTMessage {

    private int e;
    private byte[] n;

    public T0A00_8A00() {
    }

    public T0A00_8A00(int e, byte[] n) {
        this.e = e;
        this.n = n;
    }

    @Field(index = 0, type = DataType.DWORD, desc = "RSA公钥{e,n}中的e")
    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    @Field(index = 4, type = DataType.BYTES, length = 128, desc = "RSA公钥{e,n}中的n")
    public byte[] getN() {
        return n;
    }

    public void setN(byte[] n) {
        this.n = n;
    }
}