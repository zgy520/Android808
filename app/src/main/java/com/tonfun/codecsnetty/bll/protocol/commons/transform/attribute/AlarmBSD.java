package com.tonfun.codecsnetty.bll.protocol.commons.transform.attribute;


import java.util.Date;

import io.github.yezhihao.protostar.DataType;
import io.github.yezhihao.protostar.annotation.Field;

/**
 * 盲点监测
 */
public class AlarmBSD {

    public static final int id = 0x67;

    public static int id() {
        return id;
    }

    private long serialNo;
    private int state;
    private int type;
    private int speed;
    private int altitude;
    private int latitude;
    private int longitude;
    private Date dateTime;
    private int status;
//    private AlarmId alarmId;

    @Field(index = 0, type = DataType.DWORD, desc = "报警ID")
    public long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(long serialNo) {
        this.serialNo = serialNo;
    }

    @Field(index = 4, type = DataType.BYTE, desc = "标志状态")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Field(index = 5, type = DataType.BYTE, desc = "报警/事件类型")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Field(index = 6, type = DataType.BYTE, desc = "车速")
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Field(index = 7, type = DataType.WORD, desc = "高程")
    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    @Field(index = 9, type = DataType.DWORD, desc = "纬度")
    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    @Field(index = 13, type = DataType.DWORD, desc = "经度")
    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @Field(index = 17, type = DataType.BCD8421, length = 6, desc = "日期时间")
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Field(index = 23, type = DataType.WORD, desc = "车辆状态")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}