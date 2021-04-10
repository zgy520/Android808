package com.tonfun.codecsnetty.bll.protocol.codec;


import android.util.Log;

import com.tonfun.codecsnetty.bll.protocol.basics.Header;
import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.Bin;
import com.tonfun.codecsnetty.bll.protocol.commons.JTUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.yezhihao.protostar.ProtostarUtil;
import io.github.yezhihao.protostar.Schema;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;

/**
 * JT协议解码器
 *
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public class JTMessageDecoder {


    private static final String TAG = "JTMessageDecoder";

    private Map<Integer, Schema<Header>> headerSchemaMap;

    public JTMessageDecoder(String basePackage) {
        ProtostarUtil.initial(basePackage);
        this.headerSchemaMap = ProtostarUtil.getSchema(Header.class);
    }

    public JTMessage decode(ByteBuf buf) {
        buf = unescape(buf);

        boolean verified = verify(buf);
        if (!verified)
            Log.e(TAG, "校验码错误:" + ByteBufUtil.hexDump(buf));

        int properties = buf.getUnsignedShort(2);

        //缺省值为2013版本
        Integer version = 0;
        //识别2019版本
        if (Bin.get(properties, 14)) {
            version = (int) buf.getUnsignedByte(4);
        }

        int headLen;
        boolean isSubpackage = Bin.get(properties, 13);
        headLen = JTUtils.headerLength(version, isSubpackage);

        Schema<? extends Header> headerSchema = headerSchemaMap.get(version);

        Header header = headerSchema.readFrom(buf.slice(0, headLen));
        header.setVerified(verified);

        JTMessage message;
        Schema<? extends JTMessage> bodySchema = ProtostarUtil.getSchema(header.getMessageId(), version);
        if (bodySchema != null) {
            int bodyLen = header.getBodyLength();

            if (isSubpackage) {

                byte[] bytes = new byte[bodyLen];
                buf.getBytes(headLen, bytes);

                byte[][] packages = addAndGet(header, bytes);
                if (packages == null)
                    return null;

                ByteBuf bodyBuf = Unpooled.wrappedBuffer(packages);
                message = bodySchema.readFrom(bodyBuf);

            } else {
                message = bodySchema.readFrom(buf.slice(headLen, bodyLen));
            }
        } else {
            message = new JTMessage();
            Log.d(TAG, "未找到对应的Schema:" + header);
        }

        message.setHeader(header);
        return message;
    }

    protected byte[][] addAndGet(Header header, byte[] bytes) {
        return null;
    }

    /**
     * 校验
     */
    public static boolean verify(ByteBuf buf) {
        byte checkCode = buf.getByte(buf.readableBytes() - 1);
        buf = buf.slice(0, buf.readableBytes() - 1);
        byte calculatedCheckCode = JTUtils.bcc(buf);

        return checkCode == calculatedCheckCode;
    }

    /**
     * 反转义
     */
    public static ByteBuf unescape(ByteBuf source) {
        int low = source.readerIndex();
        int high = source.writerIndex();
        int last = high - 1;

        if (source.getByte(0) == 0x7e)
            low = low + 1;

        if (source.getByte(last) == 0x7e)
            high = last;

        int mark = source.indexOf(low, high, (byte) 0x7d);
        if (mark == -1) {
            if (low > 0 || high == last)
                return source.slice(low, high - low);
            return source;
        }

        List<ByteBuf> bufList = new ArrayList<>(3);

        int len;
        do {

            len = mark + 2 - low;
            bufList.add(slice(source, low, len));
            low += len;

            mark = source.indexOf(low, high, (byte) 0x7d);
        } while (mark > 0);

        bufList.add(source.slice(low, high - low));

        return new CompositeByteBuf(UnpooledByteBufAllocator.DEFAULT, false, bufList.size(), bufList);
    }

    /**
     * 截取转义前报文，并还原转义位
     */
    protected static ByteBuf slice(ByteBuf byteBuf, int index, int length) {
        byte second = byteBuf.getByte(index + length - 1);
        if (second == 0x02) {
            byteBuf.setByte(index + length - 2, 0x7e);
        }
        return byteBuf.slice(index, length - 1);
    }
}