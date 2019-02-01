package DTO;

public class Items {
    private String itemId;
    private String itemName;
    private Double itemPrice;
    private Integer itemStock;
    private Integer categoryId;
    private String categoryName;
    private Integer storeId;
    private String storePlace;
    private Integer minimalStockLevel;

    public Items(String itemId, String itemName, Double itemPrice, Integer itemStock, Integer categoryId, String categoryName, Integer storeId, String storePlace, Integer minimalStockLevel) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.storeId = storeId;
        this.storePlace = storePlace;
        this.minimalStockLevel = minimalStockLevel;
    }

    public Items(String itemId, String itemName, Double itemPrice, Integer itemStock, Integer categoryId, Integer storeId, Integer minimalStockLevel) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.categoryId = categoryId;
        this.storeId = storeId;
        this.minimalStockLevel = minimalStockLevel;
    }

    public Items(String itemId, String itemName, Double itemPrice, Integer itemStock, String categoryName, String storePlace, Integer minimalStockLevel) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.categoryName = categoryName;
        this.storePlace = storePlace;
        this.minimalStockLevel = minimalStockLevel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStorePlace() {
        return storePlace;
    }

    public void setStorePlace(String storePlace) {
        this.storePlace = storePlace;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemStock() {
        return itemStock;
    }

    public void setItemStock(Integer itemStock) {
        this.itemStock = itemStock;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getMinimalStockLevel() {
        return minimalStockLevel;
    }

    public void setMinimalStockLevel(Integer minimalStockLevel) {
        this.minimalStockLevel = minimalStockLevel;
    }
}
