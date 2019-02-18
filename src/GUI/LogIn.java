package GUI;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import DTO.Sale;
import DTO.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class LogIn extends JFrame{
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JButton logInButton;
    private JButton exitButton;
    private JPanel logInPanel;

    private UserDAO userDAO = new UserDAOImpl();
    private List<User> userList;

    public LogIn()
    {

        LogIn frame = this;
        add(logInPanel);
        setSize(390,180);
        setTitle("Log In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         *Log In Button Listener
         */
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = txtUserName.getText();
                String password = txtPassword.getText();

                /**
                 * Checking if the ManageUser Name Field is Empty or not
                 */
                if(userName.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Enter ManageUser Name");
                }
                /**
                 * Checking if the Password Field is Empty or not
                 */
                else if(password.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Enter Doesn't Passwrod");
                }

                else
                {
                    User user = userDAO.getUser(userName);
                    System.out.println("ManageUser Name: " +userName);
                    if (user == null)
                    {
                        JOptionPane.showMessageDialog(null, "User Doesn't Exit");
                    }
                    else {
                        if (password.equals(user.getPassword()))
                        {
                            System.out.println("Get Password");
                            if(user.getUserLevelId().equals(1))
                            {
                                AdminGUI adminGUI = new AdminGUI(user.getUserId(), user.getUserLevelId());
                                adminGUI.setVisible(true);
                                frame.dispose();
                                //JOptionPane.showMessageDialog(null, "Log In as Admin");
                            }
                            else if(user.getUserLevelId().equals(2))
                            {
                                AdminGUI adminGUI = new AdminGUI(user.getUserId(), user.getUserLevelId());
                                adminGUI.setVisible(true);
                                frame.dispose();
                                //JOptionPane.showMessageDialog(null, "Log In as ManagerGUI");
                            }
                            else if(user.getUserLevelId().equals(3))
                            {
                                frame.dispose();
                                SaleForm saleForm = new SaleForm(user.getUserId(), user.getUserLevelId());
                                saleForm.pack();
                                saleForm.setVisible(true);
                                saleForm.setLocationRelativeTo(null);
                                saleForm.setDefaultCloseOperation(EXIT_ON_CLOSE);
                                //JOptionPane.showMessageDialog(null, "Log In as Cashier");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Incorrect Password");
                        }
                        System.out.println("Log In Action "+user.getUserName() + "," + user.getPassword());
                    }
                }
            }
        });

        /**
         *Exit Button Listener
         */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                //System.exit(0);
            }
        });


    }
}