package com.tonfun.codecsnetty.bll.protocol.t808;

import com.tonfun.codecsnetty.bll.protocol.basics.Header;
import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;

import io.github.yezhihao.protostar.DataType;
import io.github.yezhihao.protostar.annotation.Field;
import io.github.yezhihao.protostar.annotation.Message;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
@Message(JT808.查询指定终端参数)
public class T8106 extends JTMessage {

    private int total;
    private byte[] id;

    public T8106() {
    }

    public T8106(String mobileNo, byte... id) {
        super(new Header(mobileNo, JT808.查询指定终端参数));
        this.id = id;
        this.total = id.length;
    }

    @Field(index = 0, type = DataType.BYTE, desc = "参数总数")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Field(index = 1, type = DataType.BYTES, desc = "参数ID列表")
    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
        this.total = id.length;
    }
}