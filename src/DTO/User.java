package DTO;

import java.util.List;

public class User {
    private Integer userId;
    private String userName;
    private Integer userLevelId;
    private String userLevel;
    private String password;
    private String email;
    private String address;
    private String phNumber;

    public User(){

    }

    public User(String userName, Integer userLevelId, String password, String email, String address, String phNumber) {
        this.userName = userName;
        this.userLevelId = userLevelId;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phNumber = phNumber;
    }

    public User(Integer userId, String userName, Integer userLevelId, String password, String email, String address, String phNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userLevelId = userLevelId;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phNumber = phNumber;
    }

    public User(Integer userId, String userName, Integer userLevelId, String userLevel, String password, String email, String address, String phNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userLevelId = userLevelId;
        this.userLevel = userLevel;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phNumber = phNumber;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}