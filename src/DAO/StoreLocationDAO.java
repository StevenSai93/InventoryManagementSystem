package DAO;

import DTO.StoreLocation;

import java.util.ArrayList;

public interface StoreLocationDAO {

    void getAllStoreLocation();
    ArrayList<StoreLocation> getStoreLocationList();
    ArrayList<StoreLocation> getStoreLocationById(Integer storeId);
    ArrayList<StoreLocation> getStoreLocationByName(String name);
    boolean insertStoreLocation(String storePlace);
    boolean updateStoreLocation(Integer storeId, String storePlace);
    boolean deleteStoreLocation(Integer storeId);
}
