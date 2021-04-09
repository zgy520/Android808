package com.tonfun.codecsnetty.bll.protocol.commons.transform.parameter;

import java.time.LocalTime;

import io.github.yezhihao.protostar.util.Bcd;
import io.netty.buffer.ByteBuf;

/**
 * 终端休眠唤醒模式设置数据格式
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
public class ParamSleepWake {

    public static final int id = 0x007C;

    public static int id() {
        return id;
    }

    private int mode;
    private int condition;
    private int dayOfWeek;
    private int timeFlag;
    private LocalTime wakeTime1;
    private LocalTime sleepTime1;
    private LocalTime wakeTime2;
    private LocalTime sleepTime2;
    private LocalTime wakeTime3;
    private LocalTime sleepTime3;
    private LocalTime wakeTime4;
    private LocalTime sleepTime4;

    public ParamSleepWake() {
    }

    public static int getId() {
        return id;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(int timeFlag) {
        this.timeFlag = timeFlag;
    }

    public LocalTime getWakeTime1() {
        return wakeTime1;
    }

    public void setWakeTime1(LocalTime wakeTime1) {
        this.wakeTime1 = wakeTime1;
    }

    public LocalTime getSleepTime1() {
        return sleepTime1;
    }

    public void setSleepTime1(LocalTime sleepTime1) {
        this.sleepTime1 = sleepTime1;
    }

    public LocalTime getWakeTime2() {
        return wakeTime2;
    }

    public void setWakeTime2(LocalTime wakeTime2) {
        this.wakeTime2 = wakeTime2;
    }

    public LocalTime getSleepTime2() {
        return sleepTime2;
    }

    public void setSleepTime2(LocalTime sleepTime2) {
        this.sleepTime2 = sleepTime2;
    }

    public LocalTime getWakeTime3() {
        return wakeTime3;
    }

    public void setWakeTime3(LocalTime wakeTime3) {
        this.wakeTime3 = wakeTime3;
    }

    public LocalTime getSleepTime3() {
        return sleepTime3;
    }

    public void setSleepTime3(LocalTime sleepTime3) {
        this.sleepTime3 = sleepTime3;
    }

    public LocalTime getWakeTime4() {
        return wakeTime4;
    }

    public void setWakeTime4(LocalTime wakeTime4) {
        this.wakeTime4 = wakeTime4;
    }

    public LocalTime getSleepTime4() {
        return sleepTime4;
    }

    public void setSleepTime4(LocalTime sleepTime4) {
        this.sleepTime4 = sleepTime4;
    }

    public static class Schema implements io.github.yezhihao.protostar.Schema<ParamSleepWake> {

        public static final Schema INSTANCE = new Schema();

        private Schema() {
        }

        @Override
        public ParamSleepWake readFrom(ByteBuf input) {
            ParamSleepWake message = new ParamSleepWake();
            message.mode = input.readByte();
            message.condition = input.readByte();
            message.dayOfWeek = input.readByte();
            message.timeFlag = input.readByte();
            message.wakeTime1 = Bcd.readTime2(input);
            message.sleepTime1 = Bcd.readTime2(input);
            message.wakeTime2 = Bcd.readTime2(input);
            message.sleepTime2 = Bcd.readTime2(input);
            message.wakeTime3 = Bcd.readTime2(input);
            message.sleepTime3 = Bcd.readTime2(input);
            message.wakeTime4 = Bcd.readTime2(input);
            message.sleepTime4 = Bcd.readTime2(input);
            return message;
        }

        @Override
        public void writeTo(ByteBuf output, ParamSleepWake message) {
            output.writeByte(message.mode);
            output.writeByte(message.condition);
            output.writeByte(message.dayOfWeek);
            output.writeByte(message.timeFlag);
            Bcd.writeTime2(output, message.wakeTime1);
            Bcd.writeTime2(output, message.sleepTime1);
            Bcd.writeTime2(output, message.wakeTime2);
            Bcd.writeTime2(output, message.sleepTime2);
            Bcd.writeTime2(output, message.wakeTime3);
            Bcd.writeTime2(output, message.sleepTime3);
            Bcd.writeTime2(output, message.wakeTime4);
            Bcd.writeTime2(output, message.sleepTime4);
        }
    }
}