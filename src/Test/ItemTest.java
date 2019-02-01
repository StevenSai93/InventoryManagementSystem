package Test;

import DAO.ItemDAO;
import DAO.ItemDAOImpl;
import DTO.Items;

import java.util.List;

public class ItemTest {
    public static void main(String[] args) {
        List<Items> itemsList;

        ItemDAO item = new ItemDAOImpl();

        System.out.println("\n*** Adding New Items ***");
        //item.insertItem("D002" ,"Bread", (double) 5, 100, 2,2,100);
        item.insertItem("D002" ,"Latte", (double) 5, 100, 1,2,50);
        item.insertItem("D003" ,"Cappuccino", (double) 5, 100, 1,2,30);
        item.insertItem("D004" ,"Espresso", (double) 5, 100, 1,2,50);
        item.insertItem("D005" ,"Mochaccino", (double) 5, 100, 1,2,20);


        System.out.println("\n*** Update Items ***");
        item.updateItem("D001" ,"Americano", (double) 5, 60, 1,2,100);

        System.out.println("\n*** Get List of Items ***");
        itemsList = item.getAllItemList();
        for(int i = 0; i< itemsList.size(); i++)
        {
            System.out.println(itemsList.get(i).getItemId()+"\t"+ itemsList.get(i).getItemName()+"\t"+ itemsList.get(i).getItemPrice()+"\t"+ itemsList.get(i).getItemStock()+"\t"+ itemsList.get(i).getCategoryName()+"\t"+ itemsList.get(i).getStorePlace()+"\t"+ itemsList.get(i).getMinimalStockLevel());
        }

        System.out.println("\n*** Delete Items ***");
        //item.deleteItem("D002");

        System.out.println("\n*** Get List of Items By Items Name ***");
        itemsList = item.getItemByName("Coffee");
        for(int i = 0; i< itemsList.size(); i++)
        {
            System.out.println(itemsList.get(i).getItemId()+"\t"+ itemsList.get(i).getItemName()+"\t"+ itemsList.get(i).getItemPrice()+"\t"+ itemsList.get(i).getItemStock()+"\t"+ itemsList.get(i).getCategoryName()+"\t"+ itemsList.get(i).getStorePlace()+"\t"+ itemsList.get(i).getMinimalStockLevel());
        }

        System.out.println("\n*** Get List of Items By Store ID ***");
        itemsList = item.getItemByStoreId(1);
        for(int i = 0; i< itemsList.size(); i++)
        {
            System.out.println(itemsList.get(i).getItemId()+"\t"+ itemsList.get(i).getItemName()+"\t"+ itemsList.get(i).getItemPrice()+"\t"+ itemsList.get(i).getItemStock()+"\t"+ itemsList.get(i).getCategoryName()+"\t"+ itemsList.get(i).getStorePlace()+"\t"+ itemsList.get(i).getMinimalStockLevel());
        }

        System.out.println("\n*** Get List of Items By Category ID ***");
        itemsList = item.getItemByCategoryId(2);
        for(int i = 0; i< itemsList.size(); i++)
        {
            System.out.println(itemsList.get(i).getItemId()+"\t"+ itemsList.get(i).getItemName()+"\t"+ itemsList.get(i).getItemPrice()+"\t"+ itemsList.get(i).getItemStock()+"\t"+ itemsList.get(i).getCategoryName()+"\t"+ itemsList.get(i).getStorePlace()+"\t"+ itemsList.get(i).getMinimalStockLevel());
        }

        System.out.println("\n*** Get List of Items That Need to Restock ***");
        itemsList = item.getLowStockItem();
        for(int i = 0; i< itemsList.size(); i++)
        {
            System.out.println(itemsList.get(i).getItemId()+"\t"+ itemsList.get(i).getItemName()+"\t"+ itemsList.get(i).getItemPrice()+"\t"+ itemsList.get(i).getItemStock()+"\t"+ itemsList.get(i).getCategoryName()+"\t"+ itemsList.get(i).getStorePlace()+"\t"+ itemsList.get(i).getMinimalStockLevel());
        }
    }
}
