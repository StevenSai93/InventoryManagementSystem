package DTO;

public class UserLevel {
    private Integer userLevelId;
    private String userLevel;

    public UserLevel() {
    }

    public UserLevel(Integer userLevelId, String userLevel) {
        this.userLevelId = userLevelId;
        this.userLevel = userLevel;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
}
