package com.tonfun.codecsnetty.bll.protocol.t808;

import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;

import java.util.ArrayList;
import java.util.List;

import io.github.yezhihao.protostar.DataType;
import io.github.yezhihao.protostar.annotation.Field;
import io.github.yezhihao.protostar.annotation.Message;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 * 该消息2019版本已删除
 */
@Message(JT808.信息点播菜单设置)
public class T8303 extends JTMessage {

    /** @see org.yzh.protocol.commons.Action */
    private int type;
    private int total;
    private List<Item> items;

    @Field(index = 0, type = DataType.BYTE, desc = "设置类型")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Field(index = 1, type = DataType.BYTE, desc = "设置总数")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Field(index = 2, type = DataType.LIST, desc = "事件项列表")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(int id, String content) {
        if (this.items == null)
            this.items = new ArrayList();
        this.items.add(new Item(id, content));
        this.total = items.size();
    }

    public static class Item {
        private int id;
        private String content;

        public Item() {
        }

        public Item(int id, String content) {
            this.id = id;
            this.content = content;
        }

        @Field(index = 0, type = DataType.BYTE, desc = "事件ID")
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Field(index = 3, type = DataType.STRING, lengthSize = 2, desc = "信息名称")
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}