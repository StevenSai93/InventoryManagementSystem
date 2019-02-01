package DAO;

import DTO.Items;

import java.util.ArrayList;
import java.util.List;

public interface ItemDAO {
    ArrayList<Items> getAllItemList();
    ArrayList<Items> getItemById(String itemId);
    ArrayList<Items> getItemByName(String itemName);
    ArrayList<Items> getItemByCategoryId(Integer categoryId);
    ArrayList<Items> getItemByStoreId(Integer storeId);
    ArrayList<Items> getLowStockItem();
    boolean insertItem(String itemId, String itemName, Double itemPrice, Integer itemStock, Integer categoryId, Integer storeId, Integer minimalStockLevel);
    boolean updateItem(String itemId, String itemName, Double itemPrice, Integer itemStock, Integer categoryId, Integer storeId, Integer minimalStockLevel);
    boolean deleteItem(String itemId);
    boolean updateStock(String itemId, Integer itemStock);
    boolean updateStockList(List<Items> itemList);
}
