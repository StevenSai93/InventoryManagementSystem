package DAO;

import DTO.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public void getAllCategory() {
        try{
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * From category");
            while (rs.next())
            {
                System.out.println(rs.getInt("categoryId")+".\t" +rs.getString("categoryName"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Category> getCategoryList() {
        ArrayList<Category> categoryList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from category");
            ResultSet rs = ps.executeQuery();
            Category category;
            while(rs.next())
            {
                category = new Category(rs.getInt("categoryId"),rs.getString("categoryName"));
                categoryList.add(category);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public ArrayList<Category> getCategoryById(Integer categoryId) {
        ArrayList<Category> categoryList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from category where categoryId = ?");
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            Category category;
            if(rs.next())
            {
                category = new Category(rs.getInt("categoryId"),rs.getString("categoryName"));
                categoryList.add(category);
                return categoryList;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Category> getCategoryByName(String name) {
        ArrayList<Category> categoryList = new ArrayList<>();
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from category where categoryName = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            Category category;
            if(rs.next())
            {
                category = new Category(rs.getInt("categoryId"),rs.getString("categoryName"));
                categoryList.add(category);
                return categoryList;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertCategory(String category) {
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into category Values (null,?)");
            ps.setString(1, category);
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
    public boolean updateCategory(Integer categoryId, String categoryName) {
        try{
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("update category set categoryName=? where categoryId=?");
            ps.setString(1, categoryName);
            ps.setInt(2,categoryId);
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
    public boolean deleteCategory(Integer categoryId) {
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM category WHERE categoryId=?");
            ps.setInt(1,categoryId);
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
