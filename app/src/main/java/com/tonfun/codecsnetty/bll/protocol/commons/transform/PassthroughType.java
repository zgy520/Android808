package com.tonfun.codecsnetty.bll.protocol.commons.transform;


import com.tonfun.codecsnetty.bll.protocol.commons.transform.passthrough.PeripheralStatus;
import com.tonfun.codecsnetty.bll.protocol.commons.transform.passthrough.PeripheralSystem;

import io.github.yezhihao.protostar.IdStrategy;
import io.github.yezhihao.protostar.PrepareLoadStrategy;


/**
 * 透传消息注册
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public class PassthroughType extends PrepareLoadStrategy {

    public static final IdStrategy INSTANCE = new PassthroughType();

    @Override
    protected void addSchemas(PrepareLoadStrategy schemaRegistry) {
        schemaRegistry
                .addSchema(PeripheralStatus.ID, PeripheralStatus.Schema.INSTANCE)
                .addSchema(PeripheralSystem.ID, PeripheralSystem.Schema.INSTANCE);
    }
}