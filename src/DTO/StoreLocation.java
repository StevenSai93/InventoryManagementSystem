package DTO;

public class StoreLocation {
    private Integer storeId;
    private String storePlace;

    public StoreLocation() {
    }

    public StoreLocation(Integer storeId, String storePlace) {
        this.storeId = storeId;
        this.storePlace = storePlace;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStorePlace() {
        return storePlace;
    }

    public void setStorePlace(String storePlace) {
        this.storePlace = storePlace;
    }
}
