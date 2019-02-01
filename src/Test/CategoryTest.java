package Test;

import DAO.CategoryDAO;
import DAO.CategoryDAOImpl;
import DTO.Category;

import java.util.List;

public class CategoryTest {
    public static void main(String[] args) {
        List<Category> categoryList;
        boolean addCategory;
        boolean updateCategory;
        boolean deleteCategory;

        CategoryDAO category = new CategoryDAOImpl();

        System.out.println("\n*** Add New Category ***");
        category.insertCategory("Drink");

        System.out.println("\n*** Get All Category ***");
        category.getAllCategory();

        System.out.println("\n*** Update Category ***");
        category.updateCategory(2,"Food");

        System.out.println("\n*** Show List of Category ***");
        categoryList = category.getCategoryList();
        for(int i=0; i<categoryList.size(); i++)
        {
            System.out.println(categoryList.get(i).getCategoryId()+"\t"+categoryList.get(i).getCategoryName());
        }

        System.out.println("\n*** Get Category By ID ***");
        categoryList = category.getCategoryById(1);
        System.out.println(categoryList.get(0).getCategoryId()+"\t"+categoryList.get(0).getCategoryName());

        System.out.println("\n*** Delete Category ***");
        deleteCategory = category.deleteCategory(2);
        if (deleteCategory)
        {
            System.out.println("Category has been Deleted");
        }
        else
        {
            System.out.println("Can't not Delete the Category");
        }

        System.out.println("\n*** Get All Category ***");
        category.getAllCategory();
    }
}
