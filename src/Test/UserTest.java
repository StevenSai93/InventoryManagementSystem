package Test;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import DTO.User;

import java.util.List;

public class UserTest {
    public static void main(String[] args) {
        List<User> userList;
        boolean addUser;
        boolean deleteUser;
        boolean updateUser;

        System.out.println("*** Get ALL ManageUser ***");
        UserDAO user = new UserDAOImpl();
        user.getAllUser();


        System.out.println("\n*** Get ALL ManageUser List ***");
        userList = user.getUserList();
        System.out.println("ManageUser List Size  :"+userList.size());
        for(int i=0; i<userList.size();i++)
        {
            System.out.println(userList.get(i).getUserId()+","+ userList.get(i).getUserName()+","+userList.get(i).getUserLevelId()+","+userList.get(i).getPassword()+","+userList.get(i).getEmail()+"," +userList.get(i).getAddress()+","+userList.get(i).getPhNumber());

        }


        System.out.println("\n*** Add ManageUser ***");
        addUser = user.insertUser("ManageUser Test",1,"123","Test@gmail.com","Test Address" ,"Test Ph Number") ;
        if(addUser)
        {
            System.out.println("New ManageUser been added");
        }
        else
        {
            System.out.println("ManageUser is not added");
        }

        updateUser = user.updateUser(2,"ManageUser Update",1,"Update Password","Update@gmail.com","Update Address" ,"Update Ph Number") ;
        if(updateUser)
        {
            System.out.println("ManageUser Field Updated");
        }
        else
        {
            System.out.println("Can't Update ManageUser Field");
        }


        System.out.println("\n*** Get ManageUser By Id ***");
        userList = user.getUserById(2);
        System.out.println(userList.get(0).getUserId()+","+ userList.get(0).getUserName()+","+userList.get(0).getUserLevelId() +","+userList.get(0).getUserLevel() +","+userList.get(0).getPassword()+","+userList.get(0).getEmail()+"," +userList.get(0).getAddress()+","+userList.get(0).getPhNumber());

        System.out.println("\n*** Delete ManageUser ***");
        deleteUser = user.deleteUser(2);
        if(deleteUser)
        {

            System.out.println("ManageUser been deleted");
        }
        else
        {
            System.out.println("ManageUser is not deleted");
        }
    }
}
