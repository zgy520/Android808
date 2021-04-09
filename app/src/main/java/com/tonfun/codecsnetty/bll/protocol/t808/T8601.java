package com.tonfun.codecsnetty.bll.protocol.t808;

import com.tonfun.codecsnetty.bll.protocol.basics.Header;
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
 */
@Message({JT808.删除圆形区域, JT808.删除矩形区域, JT808.删除多边形区域, JT808.删除路线})
public class T8601 extends JTMessage {

    private int total;
    private List<Item> items;

    public T8601() {
    }

    public T8601(Header header) {
        super(header);
    }

    @Field(index = 0, type = DataType.BYTE, desc = "区域总数")
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Field(index = 1, type = DataType.LIST, desc = "区域列表")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        this.total = items.size();
    }

    public void addItem(int id) {
        if (items == null)
            items = new ArrayList();
        items.add(new Item(id));
        total = items.size();
    }

    public static class Item {
        private int id;

        public Item() {
        }

        public Item(int id) {
            this.id = id;
        }

        @Field(index = 0, type = DataType.DWORD, desc = "区域ID")
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}