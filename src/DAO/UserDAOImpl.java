package DAO;

import DTO.User;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO{
    @Override
    public void getAllUser() {
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            //ResultSet myRs = stmt.executeQuery("SELECT user.userId, user.userName, userLevel.userLevel, user.password, user.email, user.address, user.phNumber FROM user INNER JOIN userLevel ON user.userLevelId = userLevel.userLevelID");
            ResultSet myRs = stmt.executeQuery("SELECT * From user INNER JOIN userLevel ON user.userLevelId = userLevel.userLevelId");
            while (myRs.next()) {
                //System.out.println(myRs.getString("userId")+ " , "+myRs.getString("userName")+"," +myRs.getString("password")+ " , "+myRs.getString("email"));
                System.out.println(myRs.getInt("userId")+" , "+myRs.getString("userName")+" , " +myRs.getString("userLevel")+" , "+myRs.getString("password")+" , "+ myRs.getString("email")+" , "+ myRs.getString("address")+" , "+myRs.getString("phNumber"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * From user INNER JOIN userLevel ON user.userLevelId = userLevel.userLevelId");
            User user;
            while(rs.next())
            {
                user = new User(rs.getInt("userId"),rs.getString("userName"),rs.getInt("userLevelId"),rs.getString("userLevel"),rs.getString("password"),rs.getString("email"),rs.getString("address"),rs.getString("phNumber"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }


    @Override
    public ArrayList<User> getUserById(Integer userId) {
        ArrayList<User> userList = new ArrayList<User>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user INNER JOIN userLevel ON user.userLevelId = userLevel.userLevelId WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                User user = new User(rs.getInt("userId"),rs.getString("userName"),rs.getInt("userLevelId"),rs.getString("userLevel"),rs.getString("password"),rs.getString("email"),rs.getString("address"),rs.getString("phNumber"));
                userList.add(user);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUser(String userName) {
        ArrayList<User> userList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM user JOIN userLevel ON user.userLevelId = userLevel.userLevelId WHERE userName = ?");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                User user = new User(rs.getInt("userId"),rs.getString("userName"),rs.getInt("userLevelId"),rs.getString("userLevel"),rs.getString("password"),rs.getString("email"),rs.getString("address"),rs.getString("phNumber"));
                return user;

            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertUser(String userName, Integer userLevelId, String password, String email, String address, String phNumber) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (null,?,?,?,?,?,?)");
            ps.setString(1, userName);
            ps.setInt(2, userLevelId);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5,address);
            ps.setString(6,phNumber);
            int i = ps.executeUpdate();
            if(i == 1)
            {
                return true;
            }
        }
        catch (SQLIntegrityConstraintViolationException duplicate)
        {
            duplicate.printStackTrace();
            JOptionPane.showMessageDialog(null, "ManageUser ID is already exist");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(Integer userId, String userName, Integer userLevelId, String password, String email, String address, String phNumber) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET userName=?, userLevelId=?, password=?, email=?, address=?, phNumber=? WHERE userId=?");

            ps.setString(1, userName);
            ps.setInt(2, userLevelId);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5,address);
            ps.setString(6,phNumber);
            ps.setInt(7, userId);
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM user WHERE userId=?");
            ps.setInt(1,userId);
            int i = ps.executeUpdate();
            if (i == 1)
            {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
}