package DAO;

import DTO.EntryList;

import java.util.ArrayList;
import java.util.List;

public interface EntryListDAO {
    ArrayList<EntryList> getAllEntryList();
    ArrayList<EntryList> getEntryListByUserId(Integer userId);
    ArrayList<EntryList> getEntryListByItemId(String itemId);
    ArrayList<EntryList> getEntryListByDate(String startDate, String endDate);
    ArrayList<EntryList> getEntryListByDate(String startDate, String endDate, String itemId);
    boolean insertEntryList(List<EntryList> entryListList);
    boolean insertEntryList(Integer userId,String itemId, String entryDate, Integer entryAmount);
    boolean updateEntryList(Integer userId,String itemId, String entryDate, Integer entryAmount);
    boolean deleteEntryList(Integer userId,String itemId, String entryDate);
}
