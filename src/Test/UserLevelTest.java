package Test;

import DAO.UserLevelDAO;
import DAO.UserLevelDAOImpl;
import DTO.UserLevel;

import java.util.List;

public class UserLevelTest {
    public static void main(String[] args) {
        List<UserLevel> userLevelList;
        boolean addUseLevel;
        boolean updateUserLevel;
        boolean deleteUserLevel;

        UserLevelDAO userLevel = new UserLevelDAOImpl();

        System.out.println("\n*** Get All ManageUser Level ***");
        userLevel.getAllUserLevel();

        System.out.println("\n*** Get ManageUser Level By ID ***");
        userLevelList= userLevel.getUserLevelById(2);
        if(userLevelList == null)
        {
            System.out.println("ManageUser Level Doesn't Exit");
        }
        else {
            System.out.println(userLevelList.get(0).getUserLevelId() + "\t" + userLevelList.get(0).getUserLevel());
        }

        //userLevel.insertUserLevel("ManagerGUI");
        System.out.println("\n*** Get The List of ManageUser Level ***");
        userLevelList=userLevel.getUserLevelList();
        for(int i=0; i<userLevelList.size(); i++) {
            System.out.println(userLevelList.get(i).getUserLevelId() + "\t" + userLevelList.get(i).getUserLevel());
        }

        userLevel.updateUserLevel(2,"Update");
        userLevel.deleteUser(2);
        userLevel.getAllUserLevel();
    }
}
