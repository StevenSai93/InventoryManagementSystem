package DAO;

import DTO.EntryList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryListDAOImpl implements EntryListDAO {
    @Override
    public ArrayList<EntryList> getAllEntryList() {
        ArrayList<EntryList> entryList_List = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * From entryList");
            EntryList entryList;
            while(rs.next())
            {
                entryList = new EntryList(rs.getInt("userId"),rs.getString("itemId"),rs.getString("entryDate"),rs.getInt("quantity"));
                entryList_List.add(entryList);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entryList_List;
    }

    @Override
    public ArrayList<EntryList> getEntryListByUserId(Integer userId) {
        ArrayList<EntryList> entryList_List = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM entryList userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            EntryList entryList;
            while(rs.next())
            {
                entryList = new EntryList(rs.getInt("userId"),rs.getString("itemId"),rs.getString("entryDate"),rs.getInt("quantity"));
                entryList_List.add(entryList);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return entryList_List;
    }

    @Override
    public ArrayList<EntryList> getEntryListByItemId(String itemId) {
        ArrayList<EntryList> entryList_List = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM entryList itemId = ?");
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();
            EntryList entryList;
            while(rs.next())
            {
                entryList = new EntryList(rs.getInt("userId"),rs.getString("itemId"),rs.getString("entryDate"),rs.getInt("quantity"));
                entryList_List.add(entryList);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return entryList_List;
    }

    @Override
    public ArrayList<EntryList> getEntryListByDate(String entryDate) {
        ArrayList<EntryList> entryList_List = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM entryList entryDate = ?");
            ps.setString(1, entryDate);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                EntryList entryList = new EntryList(rs.getInt("userId"),rs.getString("itemId"),rs.getString("entryDate"),rs.getInt("quantity"));
                entryList_List.add(entryList);
                return entryList_List;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertEntryList(List<EntryList> entryListList) {
        Connection connection = ConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO entryList VALUES (?,?,?,?)");
            for(int i=0; i<entryListList.size(); i++)
            {
                ps.setInt(1,entryListList.get(i).getUserId());
                ps.setString(2, entryListList.get(i).getItemId());
                ps.setString(3, entryListList.get(i).getEntryDate());
                ps.setInt(4, entryListList.get(i).getQuantity());
                ps.addBatch();

            }
            ps.executeBatch();
            //int[] count = ps.executeBatch();
            connection.commit();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean insertEntryList(Integer userId, String itemId, String entryDate, Integer quantity) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO entryList VALUES (?,?,?,?)");
            ps.setInt(1, userId);
            ps.setString(2, itemId);
            ps.setString(3, entryDate);
            ps.setInt(4, quantity);
            int i = ps.executeUpdate();
            if(i == 1)
            {
                return true;
            }
        }
        catch (SQLIntegrityConstraintViolationException duplicate)
        {
            duplicate.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateEntryList(Integer userId, String itemId, String entryDate, Integer quantity) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update entryList set entryAmoount =? where userId=? and itemId=? and enryDate=?");
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setString(3, itemId);
            ps.setString(4, entryDate);
            int i = ps.executeUpdate();
            if(i == 1)
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
    public boolean deleteEntryList(Integer userId, String itemId, String entryDate) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from entryList  where userId=? and itemId=? and enryDate=?");
            ps.setInt(1, userId);
            ps.setString(2, itemId);
            ps.setString(3, entryDate);
            int i = ps.executeUpdate();
            if(i == 1)
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
