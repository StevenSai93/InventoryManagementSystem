package DTO;

public class EntryList {
    private Integer userId;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String itemId;
    private String itemName;
    private String entryDate;
    private Integer itemStock;
    private Integer quantity;

    public EntryList(Integer userId, String itemId, String entryDate, Integer quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.entryDate = entryDate;
        this.quantity = quantity;
    }
    public EntryList(Integer userId, String itemId, String itemName, Integer itemStock, Integer quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemStock = itemStock;
        this.quantity = quantity;
    }

    public EntryList(Integer userId, String userName, String itemId, String itemName, String entryDate, Integer quantity) {
        this.userId = userId;
        this.userName = userName;
        this.itemId = itemId;
        this.itemName = itemName;
        this.entryDate = entryDate;
        this.quantity = quantity;
    }

    public Integer getItemStock() {
        return itemStock;
    }

    public void setItemStock(Integer itemStock) {
        this.itemStock = itemStock;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
