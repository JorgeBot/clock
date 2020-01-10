package com.example.clock.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "sign.parameter")
public class SignParameter {
    private List<String> loginUserNumber;
    private List<String> phoneNumber;
    private List<String> deviceId;
    private List<String> deviceType;
    private List<String> address;
    private List<String> longitude;
    private List<String> latitude;
    private List<Boolean> expatriateFlag;
    private List<String> expatriateComment;

    public List<String> getLoginUserNumber() {
        return loginUserNumber;
    }

    public void setLoginUserNumber(List<String> loginUserNumber) {
        this.loginUserNumber = loginUserNumber;
    }

    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(List<String> deviceId) {
        this.deviceId = deviceId;
    }

    public List<String> getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(List<String> deviceType) {
        this.deviceType = deviceType;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getLongitude() {
        return longitude;
    }

    public void setLongitude(List<String> longitude) {
        this.longitude = longitude;
    }

    public List<String> getLatitude() {
        return latitude;
    }

    public void setLatitude(List<String> latitude) {
        this.latitude = latitude;
    }

    public List<Boolean> getExpatriateFlag() {
        return expatriateFlag;
    }

    public void setExpatriateFlag(List<Boolean> expatriateFlag) {
        this.expatriateFlag = expatriateFlag;
    }

    public List<String> getExpatriateComment() {
        return expatriateComment;
    }

    public void setExpatriateComment(List<String> expatriateComment) {
        this.expatriateComment = expatriateComment;
    }
}
