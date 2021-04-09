package com.tonfun.codecsnetty.bll.protocol.t808;

import com.tonfun.codecsnetty.bll.protocol.basics.JTMessage;
import com.tonfun.codecsnetty.bll.protocol.commons.JT808;
import com.tonfun.codecsnetty.bll.protocol.commons.transform.AttributeConverter;

import java.util.Date;
import java.util.Map;

import io.github.yezhihao.protostar.DataType;
import io.github.yezhihao.protostar.annotation.Convert;
import io.github.yezhihao.protostar.annotation.Field;
import io.github.yezhihao.protostar.annotation.Message;

/**
 * @author yezhihao
 * @home https://gitee.com/yezhihao/jt808-server
 */
@Message(JT808.位置信息汇报)
public class T0200 extends JTMessage {

    private int warningMark;
    private int status;
    private int latitude;
    private int longitude;
    private int altitude;
    private int speed;
    private int direction;
    private Date dateTime;
    private Map<Integer, Object> attributes;

    @Field(index = 0, type = DataType.DWORD, desc = "报警标志")
    public int getWarningMark() {
        return warningMark;
    }

    public void setWarningMark(int warningMark) {
        this.warningMark = warningMark;
    }

    @Field(index = 4, type = DataType.DWORD, desc = "状态")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Field(index = 8, type = DataType.DWORD, desc = "纬度")
    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Field(index = 12, type = DataType.DWORD, desc = "经度")
    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @Field(index = 16, type = DataType.WORD, desc = "海拔")
    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    @Field(index = 18, type = DataType.WORD, desc = "速度")
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Field(index = 20, type = DataType.WORD, desc = "方向")
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Field(index = 22, type = DataType.BCD8421, length = 6, desc = "时间")
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Convert(converter = AttributeConverter.class)
    @Field(index = 28, type = DataType.MAP, desc = "位置附加信息")
    public Map<Integer, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Integer, Object> attributes) {
        this.attributes = attributes;
    }
}