package DAO;

import DTO.Sale;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface SaleDAO {
    ArrayList<Sale> getAllSaleList();
    ArrayList<Sale> getSaleListById(String saleId);
    ArrayList<Sale> getSaleListByProductId(String itemId);
    ArrayList<Sale> getSaleListByUserId(Integer userId);
    ArrayList<Sale> getSaleListBySaleDate(String saleDate);
    ArrayList<Sale> getSaleListByDate(String starDate, String endDate);
    boolean insertSale(List<Sale> sale);
    boolean insertSale(String saleId, String itemId, Integer userId, String saleDate, Integer saleAmount,Double totalPrice);
    boolean updateSale(String saleId, String itemId, Integer userId, String saleDate, Integer saleAmount,Double totalPrice);
    boolean deleteSaleByProduct(String saleId, String itemId);
    ArrayList<Sale> getSaleListByTotalAmount(String startDate, String endDate);
    ArrayList<Sale> getSaleListByTotalAmount(String startDate, String endDate, Integer userId);
    ArrayList<Sale> getSaleListByItem(String startDate, String endDate);
    ArrayList<Sale> getSaleListByItem(String startDate, String endDate, String itemId);
}
