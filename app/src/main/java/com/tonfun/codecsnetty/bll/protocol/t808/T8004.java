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
@Message(JT808.查询服务器时间应答)
public class T8004 extends JTMessage {

    private String dateTime;

    public T8004() {
    }

    public T8004(String dateTime) {
        this.dateTime = dateTime;
    }

    @Field(index = 0, type = DataType.BCD8421, length = 6, desc = "UTC时间")
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}