package DAO;

import DTO.Items;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Items> getAllItemList() {
        ArrayList<Items> itemsList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from item join category on item.categoryId = category.categoryId join storeLocation on item.storeId  = storeLocation.storeId");
            Items items;
            while(rs.next())
            {
                items = new Items(rs.getString("itemId"),rs.getString("itemName"),rs.getDouble("itemPrice"),rs.getInt("itemStock"), rs.getInt("categoryId"), rs.getString("categoryName"), rs.getInt("storeId"), rs.getString("storePlace"), rs.getInt("minimalStockLevel"));
                itemsList.add(items);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public ArrayList<Items> getItemById(String itemId) {
        ArrayList<Items> itemsList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from item join category on item.categoryId = category.categoryId join storeLocation on item.storeId  = storeLocation.storeId where itemId = ?");
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();
            Items items;
            while(rs.next())
            {
                items = new Items(rs.getString("itemId"),rs.getString("itemName"),rs.getDouble("itemPrice"),rs.getInt("itemStock"), rs.getInt("categoryId"), rs.getString("categoryName"), rs.getInt("storeId"), rs.getString("storePlace"), rs.getInt("minimalStockLevel"));
                itemsList.add(items);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public ArrayList<Items> getItemByName(String itemName) {
        ArrayList<Items> itemsList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from item join category on item.categoryId = category.categoryId join storeLocation on item.storeId  = storeLocation.storeId where itemName = ?");
            ps.setString(1, itemName);
            ResultSet rs = ps.executeQuery();
            Items items;
            while(rs.next())
            {
                items = new Items(rs.getString("itemId"),rs.getString("itemName"),rs.getDouble("itemPrice"),rs.getInt("itemStock"), rs.getInt("categoryId"), rs.getString("categoryName"), rs.getInt("storeId"), rs.getString("storePlace"), rs.getInt("minimalStockLevel"));
                itemsList.add(items);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public ArrayList<Items> getItemByCategoryId(Integer categoryId) {
        ArrayList<Items> itemsList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from item join category on item.categoryId = category.categoryId join storeLocation on item.storeId  = storeLocation.storeId where item.categoryId = ?");
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            Items items;
            while(rs.next())
            {
                items = new Items(rs.getString("itemId"),rs.getString("itemName"),rs.getDouble("itemPrice"),rs.getInt("itemStock"), rs.getInt("categoryId"), rs.getString("categoryName"), rs.getInt("storeId"), rs.getString("storePlace"), rs.getInt("minimalStockLevel"));
                itemsList.add(items);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public ArrayList<Items> getItemByStoreId(Integer storeId) {
        ArrayList<Items> itemsList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from item join category on item.categoryId = category.categoryId join storeLocation on item.storeId  = storeLocation.storeId where item.storeId = ?");
            ps.setInt(1, storeId);
            ResultSet rs = ps.executeQuery();
            Items items;
            while(rs.next())
            {
                items = new Items(rs.getString("itemId"),rs.getString("itemName"),rs.getDouble("itemPrice"),rs.getInt("itemStock"), rs.getInt("categoryId"), rs.getString("categoryName"), rs.getInt("storeId"), rs.getString("storePlace"), rs.getInt("minimalStockLevel"));
                itemsList.add(items);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public ArrayList<Items> getLowStockItem() {
        ArrayList<Items> itemsList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from item join category on item.categoryId = category.categoryId join storeLocation on item.storeId  = storeLocation.storeId where itemStock <= minimalStockLevel");
            ResultSet rs = ps.executeQuery();
            Items items;
            while(rs.next())
            {
                items = new Items(rs.getString("itemId"),rs.getString("itemName"),rs.getDouble("itemPrice"),rs.getInt("itemStock"), rs.getInt("categoryId"), rs.getString("categoryName"), rs.getInt("storeId"), rs.getString("storePlace"), rs.getInt("minimalStockLevel"));
                itemsList.add(items);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public boolean insertItem(String itemId, String itemName, Double itemPrice, Integer itemStock, Integer categoryId, Integer storeId, Integer minimalStockLevel) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, itemId);
            ps.setString(2, itemName);
            ps.setDouble(3, itemPrice);
            ps.setInt(4, itemStock);
            ps.setInt(5, categoryId);
            ps.setInt(6,storeId);
            ps.setInt(7,minimalStockLevel);
            int i = ps.executeUpdate();
            if(i == 1)
            {
                return true;
            }
        }
        catch (SQLIntegrityConstraintViolationException duplicate)
        {
            duplicate.printStackTrace();
            JOptionPane.showMessageDialog(null, "Item ID is Already Existed1! Please Add Another ID");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateItem(String itemId, String itemName, Double itemPrice, Integer itemStock, Integer categoryId, Integer storeId, Integer minimalStockLevel) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update item set itemName =?, itemPrice=?, itemStock=?, categoryId=?, storeId=?, minimalStockLevel=? where itemId=?");

            ps.setString(1, itemName);
            ps.setDouble(2, itemPrice);
            ps.setInt(3, itemStock);
            ps.setInt(4, categoryId);
            ps.setInt(5,storeId);
            ps.setInt(6,minimalStockLevel);
            ps.setString(7, itemId);
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
    public boolean deleteItem(String itemId) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM item WHERE itemId=?");
            ps.setString(1,itemId);
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

    @Override
    public boolean updateStock(String itemId, Integer itemStock) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update item set itemStock=? where itemId=?");
            ps.setInt(1, itemStock);
            ps.setString(2, itemId);
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
    public boolean updateStockList(List<Items> itemList) {
        Connection connection = ConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("update item set itemStock=? where itemId=?");
            for(int i=0; i<itemList.size(); i++)
            {
                ps.setInt(1, itemList.get(i).getItemStock());
                ps.setString(2, itemList.get(i).getItemId());
                ps.addBatch();

            }
            ps.executeBatch();
            //int[] count = ps.executeBatch();
            connection.commit();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
