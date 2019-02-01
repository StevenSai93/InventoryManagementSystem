package DAO;

import DTO.Category;

import java.util.ArrayList;

public interface CategoryDAO {
    void getAllCategory();
    ArrayList<Category> getCategoryList();
    ArrayList<Category> getCategoryById(Integer categoryId);
    ArrayList<Category> getCategoryByName(String name);
    boolean insertCategory(String category);
    boolean updateCategory(Integer categoryId, String categoryName);
    boolean deleteCategory(Integer categoryId);
}
