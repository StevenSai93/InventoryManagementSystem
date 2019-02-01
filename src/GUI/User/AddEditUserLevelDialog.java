package GUI.User;

import DAO.UserLevelDAO;
import DAO.UserLevelDAOImpl;
import DTO.UserLevel;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddEditUserLevelDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField levelField;
    private JTextField idField;
    private JPanel idPanel;

    private UserLevelDAO userLevelDAO = new UserLevelDAOImpl();
    private List<UserLevel> userLevelList;

    public AddEditUserLevelDialog() {
        setTitle("Add User Level");
        setResizable(false);
        idPanel.setVisible(false);
        buttonOK.setText("Add");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addLevel();
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
        pack();
    }

    public AddEditUserLevelDialog(Integer editId) {
        setTitle("Edit User Level");
        setResizable(false);
        buttonOK.setText("Update");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        userLevelList = userLevelDAO.getUserLevelById(editId);

        idField.setText(userLevelList.get(0).getUserLevelId().toString());
        levelField.setText(userLevelList.get(0).getUserLevel());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editLevel(editId);
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
        pack();
    }

    private void addLevel() {
        // add your code here
        if(levelField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else {
            userLevelDAO.insertUserLevel(levelField.getText());
            dispose();
        }
    }

    private void editLevel(Integer editId){
        if(levelField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else {
            userLevelDAO.updateUserLevel(editId, levelField.getText());
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddEditUserLevelDialog dialog = new AddEditUserLevelDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
