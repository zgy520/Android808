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
@Message(JT808.单条存储多媒体数据检索上传命令)
public class T8805 extends JTMessage {

    private int id;
    private int delete;

    public T8805() {
    }

    public T8805(String clientId, int id, int delete) {
        super(new Header(clientId, JT808.单条存储多媒体数据检索上传命令));
        this.id = id;
        this.delete = delete;
    }

    @Field(index = 0, type = DataType.DWORD, desc = "多媒体ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Field(index = 4, type = DataType.BYTE, desc = "删除标志:0.保留；1.删除；")
    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}