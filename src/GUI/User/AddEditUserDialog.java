package GUI.User;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import DAO.UserLevelDAO;
import DAO.UserLevelDAOImpl;
import DTO.User;
import DTO.UserLevel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class AddEditUserDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel field_panel;
    private JTextField nameField;
    private JTextField passwordField;
    private JTextField addressField;
    private JTextField phNumberField;
    private JTextField emailField;
    private JComboBox levelComboBox;
    private DefaultComboBoxModel levelComboBoxModel;

    private String name;
    private Integer userLevel;
    private String password;
    private String address;
    private String phNumber;
    private String email;

    private UserDAO userDAO = new UserDAOImpl();
    private UserLevelDAO userLevelDAO = new UserLevelDAOImpl();
    private List<User> userList = new ArrayList<>();
    private List<UserLevel> userLevelList = new ArrayList<>();

    public AddEditUserDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        userLevelList = userLevelDAO.getUserLevelList();

        levelComboBoxModel = new DefaultComboBoxModel();
        levelComboBox.setModel(levelComboBoxModel);
        for(int x=0; x<userLevelList.size(); x++)
        {
            levelComboBoxModel.addElement(userLevelList.get(x).getUserLevel());
        }

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonOK.setText("Add");
        this.pack();
        this.setTitle("Add User");
    }

    public AddEditUserDialog(Integer editId) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        userList = userDAO.getUserById(editId);
        userLevelList = userLevelDAO.getUserLevelList();

        levelComboBoxModel = new DefaultComboBoxModel();
        levelComboBox.setModel(levelComboBoxModel);
        for(int x=0; x<userLevelList.size(); x++)
        {
            levelComboBoxModel.addElement(userLevelList.get(x).getUserLevel());
        }

        nameField.setText(userList.get(0).getUserName());
        levelComboBox.setSelectedItem(userList.get(0).getUserLevel());
        passwordField.setText(userList.get(0).getPassword());
        addressField.setText(userList.get(0).getAddress());
        phNumberField.setText(userList.get(0).getPhNumber());
        emailField.setText(userList.get(0).getEmail());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editUser(editId);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonOK.setText("Update");
        this.pack();
        this.setTitle("Edit User");
    }

    private void addUser() {
        // add your code here
        if(nameField.getText().equals("") || passwordField.getText().equals("") || addressField.getText().equals("") || phNumberField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else
        {

            userLevelList = userLevelDAO.getUserLevelByName(levelComboBoxModel.getSelectedItem().toString());
//                id = Integer.valueOf(idField.getText());
            name = nameField.getText();
            userLevel = userLevelList.get(0).getUserLevelId();
            password = passwordField.getText();
            address = addressField.getText();
            phNumber = phNumberField.getText();
            email = emailField.getText();


            System.out.println(name + "\t" + userLevel + "\t" + password + "\t" + address + "\t" + phNumber + "\t" + email);

            userDAO.insertUser(name,userLevel,password,email,address,phNumber);
            dispose();
        }
    }

    private void editUser(Integer editId)
    {
        if(nameField.getText().equals("") && passwordField.getText().equals("") && addressField.getText().equals("") && phNumberField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else
        {

            userLevelList = userLevelDAO.getUserLevelByName(levelComboBoxModel.getSelectedItem().toString());
//                id = Integer.valueOf(idField.getText());
            name = nameField.getText();
            userLevel = userLevelList.get(0).getUserLevelId();
            password = passwordField.getText();
            address = addressField.getText();
            phNumber = phNumberField.getText();
            email = emailField.getText();


            System.out.println(name + "\t" + userLevel + "\t" + password + "\t" + address + "\t" + email + "\t" + phNumber);

            userDAO.updateUser(editId,name,userLevel,password,email,address,phNumber);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddEditUserDialog dialog = new AddEditUserDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
