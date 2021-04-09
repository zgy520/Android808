package com.tonfun.codecsnetty.bll.protocol.t808;

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
@Message(JT808.查询终端参数应答)
public class T0104 extends JTMessage {

    private int serialNo;
    private int total;
    private Map<Integer, Object> parameters;

    @Field(index = 0, type = DataType.WORD, desc = "应答流水号")
    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    @Field(index = 2, type = DataType.BYTE, desc = "应答参数个数")
    public int getTotal() {
        if (parameters != null)
            return parameters.size();
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Convert(converter = ParameterConverter.class)
    @Field(index = 3, type = DataType.MAP, desc = "参数项列表")
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