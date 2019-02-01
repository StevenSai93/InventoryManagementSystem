package DAO;

import DTO.StoreLocation;

import java.sql.*;
import java.util.ArrayList;

public class StoreLocationDAOImpl implements StoreLocationDAO{
    @Override
    public void getAllStoreLocation() {
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * From storeLocation");
            while (rs.next())
            {
                System.out.println(rs.getInt("storeId")+".\t" +rs.getString("storePlace"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<StoreLocation> getStoreLocationList() {
        ArrayList<StoreLocation> storeLocationsList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from storeLocation");
            ResultSet rs = ps.executeQuery();
            StoreLocation storeLocation;
            while(rs.next())
            {
                storeLocation = new StoreLocation(rs.getInt("storeId"),rs.getString("storePlace"));
                storeLocationsList.add(storeLocation);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return storeLocationsList;
    }

    @Override
    public ArrayList<StoreLocation> getStoreLocationById(Integer storeId) {
        ArrayList<StoreLocation> storeLocationsList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from storeLocation where storeId = ?");
            ps.setInt(1, storeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                StoreLocation storeLocation = new StoreLocation(rs.getInt("storeId"),rs.getString("storePlace"));
                storeLocationsList.add(storeLocation);
                return storeLocationsList;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<StoreLocation> getStoreLocationByName(String name) {
        ArrayList<StoreLocation> storeLocationsList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from storeLocation where storePlace = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                StoreLocation storeLocation = new StoreLocation(rs.getInt("storeId"),rs.getString("storePlace"));
                storeLocationsList.add(storeLocation);
                return storeLocationsList;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertStoreLocation(String storePlace) {
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into storeLocation Values (null,?)");
            ps.setString(1, storePlace);
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
    public boolean updateStoreLocation(Integer storeId, String storePlace) {
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update storeLocation set storePlace=? where storeId=?");
            ps.setString(1, storePlace);
            ps.setInt(2,storeId);
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
    public boolean deleteStoreLocation(Integer storeId) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM storeLocation WHERE storeId=?");
            ps.setInt(1,storeId);
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
