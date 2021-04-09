package com.tonfun.codecsnetty.bll.protocol.t808;

import com.tonfun.codecsnetty.bll.protocol.basics.Header;
import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;
import com.tonfun.codecsnetty.bll.protocol.commons.transform.ParameterConverter;

import java.util.Map;
import java.util.TreeMap;

import io.github.yezhihao.protostar.DataType;
import io.github.yezhihao.protostar.annotation.Convert;
import io.github.yezhihao.protostar.annotation.Field;
import io.github.yezhihao.protostar.annotation.Message;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
@Message(JT808.设置终端参数)
public class T8103 extends JTMessage {

    private int total;
    private Map<Integer, Object> parameters;

    public T8103() {
    }

    public T8103(String mobileNo) {
        super(new Header(mobileNo, JT808.设置终端参数));
    }

    @Field(index = 0, type = DataType.BYTE, desc = "参数总数")
    public int getTotal() {
        if (parameters != null)
            return parameters.size();
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Convert(converter = ParameterConverter.class)
    @Field(index = 1, type = DataType.MAP, desc = "参数项列表")
    public Map<Integer, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<Integer, Object> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(Integer key, Object value) {
        if (parameters == null)
            parameters = new TreeMap();
        parameters.put(key, value);
    }
}