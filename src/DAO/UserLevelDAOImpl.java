package DAO;

import DTO.UserLevel;

import java.sql.*;
import java.util.ArrayList;

public class UserLevelDAOImpl implements UserLevelDAO {
    @Override
    public void getAllUserLevel() {
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * From userLevel");
            while (rs.next())
            {
                System.out.println(rs.getInt("userLevelId")+".\t" +rs.getString("userLevel"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<UserLevel> getUserLevelById(Integer userLevelId)
    {
        ArrayList<UserLevel> userLevelList = new ArrayList<UserLevel>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from userLevel where userLevelId = ?");
            ps.setInt(1, userLevelId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                UserLevel userLevel = new UserLevel(rs.getInt("userLevelId"),rs.getString("userLevel"));
                userLevelList.add(userLevel);
                return userLevelList;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<UserLevel> getUserLevelByName(String level) {
        ArrayList<UserLevel> userLevelList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from userLevel where userLevel = ?");
            ps.setString(1, level);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                UserLevel userLevel = new UserLevel(rs.getInt("userLevelId"),rs.getString("userLevel"));
                userLevelList.add(userLevel);
                return userLevelList;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public ArrayList<UserLevel> getUserLevelList()
    {
        ArrayList<UserLevel> userLevelList = new ArrayList<UserLevel>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from userLevel");
            ResultSet rs = ps.executeQuery();
            UserLevel userLevel;
            while(rs.next())
            {
                userLevel = new UserLevel(rs.getInt("userLevelId"),rs.getString("userLevel"));
                userLevelList.add(userLevel);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return userLevelList;
    }

    @Override
    public boolean insertUserLevel(String userLevel) {
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into userLevel Values (null,?)");
            ps.setString(1, userLevel);
            int i = ps.executeUpdate();
            if(i==1)
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

    @Override
    public boolean updateUserLevel(Integer userLevelId, String userLevel)
    {
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update userLevel set userLevel=? where userLevelId=?");

            ps.setString(1, userLevel);
            ps.setInt(2,userLevelId);
            int i = ps.executeUpdate();
            if(i==1)
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

    @Override
    public boolean deleteUser(Integer userLevelId)
    {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM userLevel WHERE userLevelId=?");
            ps.setInt(1,userLevelId);
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
