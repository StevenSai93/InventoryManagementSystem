package DTO;

public class Sale {
    private String saleId;
    private String itemId;
    private String itemName;
    private Double itemPrice;
    private Integer itemStock;
    private Integer userId;
    private String userName;
    private String saleDate;
    private Integer quantity;
    private Double totalPrice;
    private Double saleTotalPrice;

    public Sale(String saleId, String itemId, String itemName, Double itemPrice, String userName, String saleDate, Integer quantity, Double totalPrice) {
        this.saleId = saleId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.userName = userName;
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Sale(String saleId, Integer userId, String saleDate, Double saleTotalPrice) {
        this.saleId = saleId;
        this.userId = userId;
        this.saleDate = saleDate;
        this.saleTotalPrice = saleTotalPrice;
    }
    public Sale(String saleId, String userName, String saleDate, Double saleTotalPrice) {
        this.saleId = saleId;
        this.userName = userName;
        this.saleDate = saleDate;
        this.saleTotalPrice = saleTotalPrice;
    }

    public Sale(String saleId, Double saleTotalPrice) {
        this.saleId = saleId;
        this.saleTotalPrice = saleTotalPrice;
    }

    public Sale() {
    }

    public Sale(String itemId, String itemName, Double itemPrice, Integer quantity, Integer itemStock, Double totalPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.itemStock = itemStock;
        this.totalPrice = totalPrice;
    }

    public Sale(String itemId, Integer userId, String saleDate, Integer quantity, Double totalPrice) {
        this.itemId = itemId;
        this.userId = userId;
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Sale(String saleId, String itemId, Integer userId, String saleDate, Double itemPrice, Integer quantity, Double totalPrice) {
        this.saleId = saleId;
        this.itemId = itemId;
        this.userId = userId;
        this.saleDate = saleDate;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
/*
    public Sale(String saleId, String itemId, String itemName, Double itemPrice, Integer userId, String saleDate, Integer quantity, Double totalPrice) {
        this.saleId = saleId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.userId = userId;
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }*/

    public Double getSaleTotalPrice() {
        return saleTotalPrice;
    }

    public void setSaleTotalPrice(Double saleTotalPrice) {
        this.saleTotalPrice = saleTotalPrice;
    }

    public Integer getItemStock() {
        return itemStock;
    }

    public void setItemStock(Integer itemStock) {
        this.itemStock = itemStock;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
