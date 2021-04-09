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
@Message({JT808.终端补传分包请求, JT808.服务器补传分包请求})
public class T8003 extends JTMessage {

    private int serialNo;
    private int total;
    private byte[] items;

    public T8003() {
    }

    public T8003(Header header) {
        super(header);
    }

    @Field(index = 0, type = DataType.WORD, desc = "原始消息流水号")
    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    @Field(index = 4, type = DataType.BYTE, desc = "重传包总数")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Field(index = 5, type = DataType.BYTES, desc = "重传包ID列表")
    public byte[] getItems() {
        return items;
    }

    public void setItems(byte[] items) {
        this.items = items;
        this.total = items.length;
    }
}