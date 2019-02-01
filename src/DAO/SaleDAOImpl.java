package DAO;

import DTO.Sale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleDAOImpl implements SaleDAO {
    @Override
    public ArrayList<Sale> getAllSaleList() {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * From sale");
            Sale sale;
            while(rs.next())
            {
                sale = new Sale(rs.getString("saleId"),rs.getString("itemId"),rs.getInt("userId"),rs.getString("saleDate"), rs.getDouble("itemPrice"), rs.getInt("quantity"),rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListById(String saleId) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sale WHERE saleId = ?");
            ps.setString(1, saleId);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            while(rs.next())
            {
                sale = new Sale(rs.getString("saleId"),rs.getString("itemId"),rs.getInt("userId"),rs.getString("saleDate"), rs.getDouble("itemPrice"), rs.getInt("quantity"),rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListByProductId(String itemId) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sale WHERE itemId = ?");
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            if(rs.next())
            {
                sale = new Sale(rs.getString("saleId"),rs.getString("itemId"),rs.getInt("userId"),rs.getString("saleDate"), rs.getDouble("itemPrice"), rs.getInt("quantity"),rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListByUserId(Integer userId) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sale WHERE userId = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            if(rs.next())
            {
                sale = new Sale(rs.getString("saleId"),rs.getString("itemId"),rs.getInt("userId"),rs.getString("saleDate"), rs.getDouble("itemPrice"), rs.getInt("quantity"),rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListBySaleDate(String saleDate) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sale WHERE saleDate = ?");
            ps.setString(1, saleDate);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            if(rs.next())
            {
                sale = new Sale(rs.getString("saleId"),rs.getString("itemId"),rs.getInt("userId"),rs.getString("saleDate"),rs.getDouble("itemPrice"), rs.getInt("quantity"),rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListByDate(String starDate, String endDate) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sale WHERE saleDate > ? AND saleDate <?");
            ps.setString(1, starDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            while(rs.next())
            {
                sale = new Sale(rs.getString("saleId"),rs.getString("itemId"),rs.getInt("userId"),rs.getString("saleDate"), rs.getDouble("itemPrice"), rs.getInt("quantity"),rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public boolean insertSale(List<Sale> sale) {
        Connection connection = ConnectionFactory.getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement("INSERT INTO sale VALUES (?,?,?,?,?,?,?)");
            for(int i=0; i<sale.size(); i++)
            {
                ps.setString(1,sale.get(i).getSaleId());
                ps.setString(2, sale.get(i).getItemId());
                ps.setInt(3, sale.get(i).getUserId());
                ps.setString(4, sale.get(i).getSaleDate());
                ps.setDouble(5,sale.get(i).getItemPrice());
                ps.setInt(6, sale.get(i).getQuantity());
                ps.setDouble(7,sale.get(i).getTotalPrice());
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

 /*
    @Override
    public boolean insertSale(List<Sale> sale) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            connection.setAutoCommit(false);
            try {
                try (PreparedStatement ps = connection.prepareStatement("INSERT INTO sale VALUES (null,?,?,?,?,?)")) {
                    for (int i = 0; i < sale.size(); i++) {
                        ps.setString(1, sale.get(i).getItemId());
                        ps.setInt(2, sale.get(i).getUserId());
                        ps.setString(3, sale.get(i).getSaleDate());
                        ps.setInt(4, sale.get(i).getQuantity());
                        ps.setDouble(5, sale.get(i).getTotalPrice());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
*/
    @Override
    public boolean insertSale(String saleId, String itemId, Integer userId, String saleDate, Integer quantity, Double totalPrice) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO sale VALUES (?,?,?,?,?,?)");
            ps.setString(1, saleId);
            ps.setString(2, itemId);
            ps.setInt(3, userId);
            ps.setString(4, saleDate);
            ps.setInt(5, quantity);
            ps.setDouble(6,totalPrice);
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
    public boolean updateSale(String saleId, String itemId, Integer userId, String saleDate, Integer quantity, Double totalPrice) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE sale SET itemId=?, userId=?, saleDate=?, quantity=?, totalPrice=? WHERE saleId=?");

            ps.setString(1, itemId);
            ps.setInt(2, userId);
            ps.setString(3, saleDate);
            ps.setInt(4, quantity);
            ps.setDouble(5,totalPrice);
            ps.setString(6,saleId);
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
    public boolean deleteSaleByProduct(String saleId, String itemId) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM sale WHERE saleId=? AND itemId=?");
            ps.setString(1,saleId);
            ps.setString(2, itemId);
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
    public ArrayList<Sale> getSaleListByTotalAmount(String startDate, String endDate) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT saleId, userName, saleDate, SUM(totalPrice) as totalAmount FROM sale JOIN user on sale.userId = user.userId WHERE saleDate >= ? AND saleDate <=? GROUP BY saleId, username, saleDate");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            //ResultSet rs = stmt.executeQuery("SELECT saleId, SUM(totalPrice) as totalAmount FROM sale GROUP BY saleId");
            //ResultSet rs = stmt.executeQuery("SELECT saleId, userId, saleDate, SUM(totalPrice) as totalAmount FROM sale GROUP BY saleId, userId, saleDate");
            //ResultSet rs = stmt.executeQuery("SELECT saleId, userName, saleDate, SUM(totalPrice) as totalAmount FROM sale JOIN user on sale.userId = user.userId WHERE saleDate > ? AND saleDate <? GROUP BY saleId, username, saleDate");
            Sale sale;
            while(rs.next())
            {
                //sale = new Sale(rs.getString("saleId"), rs.getInt("userId"),rs.getString("saleDate") ,rs.getDouble("totalAmount"));
                //sale = new Sale(rs.getString("saleId"), rs.getDouble("totalAmount"));
                sale = new Sale(rs.getString("saleId"), rs.getString("userName"),rs.getString("saleDate") ,rs.getDouble("totalAmount"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListByTotalAmount(String startDate, String endDate, Integer userId) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT saleId, userName, saleDate, SUM(totalPrice) as totalAmount FROM sale JOIN user on sale.userId = user.userId WHERE saleDate >= ? AND saleDate <=? AND sale.userId =? GROUP BY saleId, username, saleDate");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setInt(3, userId);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            while(rs.next())
            {
                sale = new Sale(rs.getString("saleId"), rs.getString("userName"),rs.getString("saleDate") ,rs.getDouble("totalAmount"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListByItem(String startDate, String endDate) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            //PreparedStatement ps = connection.prepareStatement("SELECT saleId, userName, saleDate, itemId, item.itemName, quantity, itemPrice, totalPrice  FROM sale JOIN user on sale.userId = user.userId JOIN item on sale.itemId = item.itemId WHERE saleDate >= ? AND saleDate <=?");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sale JOIN user on sale.userId = user.userId JOIN item on sale.itemId = item.itemId WHERE saleDate >= ? AND saleDate <=?");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            while(rs.next())
            {
                sale = new Sale(rs.getString("saleId"), rs.getString("itemId"), rs.getString("itemName"), rs.getDouble("itemPrice"), rs.getString("userName"), rs.getString("saleDate"), rs.getInt("quantity"), rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }

    @Override
    public ArrayList<Sale> getSaleListByItem(String startDate, String endDate, String itemId) {
        ArrayList<Sale> saleList = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT *  FROM sale JOIN user on sale.userId = user.userId JOIN item on sale.itemId = item.itemId WHERE saleDate >= ? AND saleDate <=? AND sale.itemId =?");
            ps.setString(1, startDate);
            ps.setString(2, endDate);
            ps.setString(3, itemId);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            while(rs.next())
            {
                sale = new Sale(rs.getString("saleId"), rs.getString("itemId"), rs.getString("itemName"), rs.getDouble("itemPrice"), rs.getString("userName"), rs.getString("saleDate"), rs.getInt("quantity"), rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return saleList;
    }
}
//JOIN user on sale.userId = user.userId JOIN item on sale.itemId = item.itemId
//SELECT saleId, SUM(totalPrice) AS TotalAmount FROM sale GROUP BY saleId
/*try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM sale WHERE saleDate > ? AND saleDate <?");
            ps.setString(1, starDate);
            ps.setString(2, endDate);
            ResultSet rs = ps.executeQuery();
            Sale sale;
            while(rs.next())
            {
                sale = new Sale(rs.getString("saleId"),rs.getString("itemId"),rs.getInt("userId"),rs.getString("saleDate"),rs.getInt("quantity"),rs.getDouble("totalPrice"));
                saleList.add(sale);
            }
        }*/