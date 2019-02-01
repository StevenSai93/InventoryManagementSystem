package DAO;

import DTO.User;

import java.util.ArrayList;

public interface UserDAO {
    void getAllUser();
    ArrayList<User> getUserList();
    ArrayList<User> getUserById(Integer userId);
    User getUser(String userName);
    boolean insertUser(String userName, Integer userLevelId, String password, String email, String address, String phNumber);
    boolean updateUser(Integer userId, String userName, Integer userLevelId, String password, String email, String address, String phNumber);
    boolean deleteUser(Integer userId);
}