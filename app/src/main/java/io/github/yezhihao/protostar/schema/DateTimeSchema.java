package io.github.yezhihao.protostar.schema;

import io.netty.buffer.ByteBuf;
import io.github.yezhihao.protostar.Schema;
import io.github.yezhihao.protostar.util.Bcd;

import java.util.Date;


public class DateTimeSchema {

    public static class BCD implements Schema<Date> {
        public static final Schema INSTANCE = new BCD();

        private BCD() {
        }

        @Override
        public Date readFrom(ByteBuf input) {
            byte[] bytes = new byte[6];
            input.readBytes(bytes);
            return Bcd.toOldDateTime(bytes);
        }

        @Override
        public Date readFrom(ByteBuf input, int length) {
            byte[] bytes = new byte[length];
            input.readBytes(bytes);
            return Bcd.toOldDateTime(bytes);
        }

        @Override
        public void writeTo(ByteBuf output, Date value) {
            output.writeBytes(Bcd.fromOldDateTime(value));
        }

        @Override
        public void writeTo(ByteBuf output, int length, Date value) {
            output.writeBytes(Bcd.fromOldDateTime(value));
        }
    }
}