package GUI.Items;

import DAO.CategoryDAO;
import DAO.CategoryDAOImpl;
import DTO.Category;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddEditCategoryDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField categoryField;
    private JPanel idPanel;
    private JTextField idField;

    private CategoryDAO categoryDAO = new CategoryDAOImpl();
    private List<Category> categoryList;

    public AddEditCategoryDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        idPanel.setVisible(false);
        buttonOK.setText("Add");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCategory();
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
    }

    public AddEditCategoryDialog(Integer categoryId) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.setText("Update");

        categoryList = categoryDAO.getCategoryById(categoryId);
        idField.setText(String.valueOf(categoryId));
        categoryField.setText(categoryList.get(0).getCategoryName());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editCategory(categoryId);
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
    }

    private void addCategory() {
        // add your code here
        if (categoryField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else
        {
            categoryDAO.insertCategory(categoryField.getText());
            dispose();
        }
    }

    private void editCategory(Integer editId){
        if (categoryField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else
        {
            categoryDAO.updateCategory(editId, categoryField.getText());
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddEditCategoryDialog dialog = new AddEditCategoryDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
