package Test;

import DAO.StoreLocationDAO;
import DAO.StoreLocationDAOImpl;
import DTO.StoreLocation;

import java.util.List;

public class StoreLocationTest {
    public static void main(String[] args) {
        List<StoreLocation> storeLocationList;
        boolean addStoreLocation;
        boolean updateStoreLocation;
        boolean deleteStoreLocation;

        StoreLocationDAO storeLocation = new StoreLocationDAOImpl();

        System.out.println("\n*** Add New Store Location ***");
        storeLocation.insertStoreLocation("Store");
        storeLocation.insertStoreLocation("Store 2");

        System.out.println("\n*** Get All Store Location ***");
        storeLocation.getAllStoreLocation();

        System.out.println("\n*** Update Store Location ***");
        storeLocation.updateStoreLocation(1,"Store 1");

        System.out.println("\n*** Show List of Store Location ***");
        storeLocationList = storeLocation.getStoreLocationList();
        for(int i=0; i<storeLocationList.size(); i++)
        {
            System.out.println(storeLocationList.get(i).getStoreId()+"\t"+storeLocationList.get(i).getStorePlace());
        }

        System.out.println("\n*** Get Store Location By ID ***");
        storeLocation.getStoreLocationById(1);
        System.out.println(storeLocationList.get(0).getStoreId()+"\t"+storeLocationList.get(0).getStorePlace());

        System.out.println("\n*** Delete Store Location ***");
        deleteStoreLocation = storeLocation.deleteStoreLocation(2);
        if (deleteStoreLocation)
        {
            System.out.println("Store Location has been Deleted");
        }
        else
        {
            System.out.println("Can't not Delete the Store Location");
        }


        System.out.println("\n*** Get All Store Location ***");
        storeLocation.getAllStoreLocation();
    }
}
