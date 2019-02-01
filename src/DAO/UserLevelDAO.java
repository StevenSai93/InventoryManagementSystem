package DAO;

import DTO.UserLevel;

import java.util.ArrayList;

public interface UserLevelDAO {
    void getAllUserLevel();
    ArrayList<UserLevel> getUserLevelById(Integer userLevelId);
    ArrayList<UserLevel> getUserLevelByName(String level);
    ArrayList<UserLevel> getUserLevelList();
    boolean insertUserLevel(String userLevel);
    boolean updateUserLevel(Integer userLevelId, String userLevel);
    boolean deleteUser(Integer userLevelId);
}
