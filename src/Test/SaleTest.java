package Test;

import DAO.SaleDAO;
import DAO.SaleDAOImpl;
import DTO.Sale;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleTest {
    public static void main(String[] args) {
        List<Sale> saleList;
        SaleDAO saleDAO = new SaleDAOImpl();
        boolean insertSale;

        Date dt = new java.util.Date();
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = sdf.format(dt);

        //saleDAO.insertSale(0, "D002", 1, currentTime, 10, (double) 50);

        /*
        saleList = saleDAO.getAllSaleList();
        for(int i=0; i<saleList.size(); i++)
        {
            System.out.println(saleList.get(i).getSaleId()+"\t"+saleList.get(i).getItemId()+"\t"+saleList.get(i).getUserId()+"\t"+saleList.get(i).getSaleDate()+"\t"+saleList.get(i).getQuantity()+"\t"+saleList.get(i).getTotalPrice());
        }

        System.out.println("\n*** Adding New List ***");
        List<Sale> insertSaleList = new ArrayList<>(2);
        Sale sale;
        sale = new Sale("D002",4, currentTime, 10, (double) 50);
        insertSaleList.add(sale);
        sale = new Sale("D003",2, currentTime, 5, (double) 25);
        insertSaleList.add(sale);

        insertSale = saleDAO.insertSale(insertSaleList);

        System.out.println("\n*** What it is? ***");
        if(insertSale == true) {
            System.out.println("Inserted : True");
        }
        else {
            System.out.println("Inserted: False");
        }


        for(int i=0; i<insertSaleList.size(); i++)
        {
            System.out.println(insertSaleList.get(i).getSaleId()+"\t"+insertSaleList.get(i).getItemId()+"\t"+insertSaleList.get(i).getUserId()+"\t"+insertSaleList.get(i).getSaleDate()+"\t"+insertSaleList.get(i).getQuantity()+"\t"+insertSaleList.get(i).getTotalPrice());
        }

        System.out.println("\n*** Get all the Sale List ***");
        saleList = saleDAO.getAllSaleList();
        for(int i=0; i<saleList.size(); i++)
        {
            System.out.println(saleList.get(i).getSaleId()+"\t"+saleList.get(i).getItemId()+"\t"+saleList.get(i).getUserId()+"\t"+saleList.get(i).getSaleDate()+"\t"+saleList.get(i).getQuantity()+"\t"+saleList.get(i).getTotalPrice());
        }

*/
        saleList = saleDAO.getSaleListByTotalAmount("2019-01-01 00:00:00" , currentTime);
        for (int x = 0; x < saleList.size(); x++) {
            //System.out.println(saleList.get(x).getSaleId()+ "\t" + saleList.get(x).getUserId()+ "\t" + saleList.get(x).getSaleDate() + "\t" + saleList.get(x).getSaleTotalPrice());
            //System.out.println(saleList.get(x).getSaleId() + "\t" + saleList.get(x).getSaleTotalPrice());
            System.out.println(saleList.get(x).getSaleId()+ "\t" + saleList.get(x).getUserName()+ "\t" + saleList.get(x).getSaleDate() + "\t" + saleList.get(x).getSaleTotalPrice());

        }
    }

}
