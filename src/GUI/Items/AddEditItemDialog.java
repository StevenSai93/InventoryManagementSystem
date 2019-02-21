package GUI.Items;

import DAO.*;
import DTO.Category;
import DTO.Items;
import DTO.StoreLocation;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddEditItemDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonAdd;
    private JButton buttonCancel;
    private JTextField idField;
    private JTextField minimalStockField;
    private JTextField nameField;
    private JComboBox categoryBox;
    private JComboBox storeBox;
    private JTextField priceField;
    private JPanel field_panel;
    private DefaultComboBoxModel categoryBoxModel;
    private DefaultComboBoxModel storeBoxModel;

    private ItemDAO itemDAO = new ItemDAOImpl();
    private StoreLocationDAO storeLocationDAO = new StoreLocationDAOImpl();
    private CategoryDAO categoryDAO = new CategoryDAOImpl();
    private List<Items> itemsList;
    private List<StoreLocation> storeLocationList;
    private List<Category> categoryList;

    private String id;
    private String name;
    private Double price;
    private Integer minimal;
    private String category;
    private String store;
    private boolean insertItem = false;

    public AddEditItemDialog() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAdd);

        storeLocationList = storeLocationDAO.getStoreLocationList();
        categoryList = categoryDAO.getCategoryList();

        categoryBoxModel = new DefaultComboBoxModel();
        categoryBox.setModel(categoryBoxModel);
        for(int x=0; x<categoryList.size(); x++)
        {
            categoryBoxModel.addElement(categoryList.get(x).getCategoryName());
        }

        storeBoxModel = new DefaultComboBoxModel();
        storeBox.setModel(storeBoxModel);
        for(int x=0; x< storeLocationList.size(); x++)
        {
            storeBoxModel.addElement(storeLocationList.get(x).getStorePlace());
        }


        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
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

        this.setTitle("Add New Item");
        this.setResizable(false);
        pack();
    }

    public AddEditItemDialog(String editId) {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAdd);

        itemsList = itemDAO.getItemById(editId);
        storeLocationList = storeLocationDAO.getStoreLocationList();
        categoryList = categoryDAO.getCategoryList();

        int stockLevel = itemsList.get(0).getItemStock();

        categoryBoxModel = new DefaultComboBoxModel();
        categoryBox.setModel(categoryBoxModel);
        for(int x=0; x<categoryList.size(); x++)
        {
            categoryBoxModel.addElement(categoryList.get(x).getCategoryName());
        }

        storeBoxModel = new DefaultComboBoxModel();
        storeBox.setModel(storeBoxModel);
        for(int x=0; x< storeLocationList.size(); x++)
        {
            storeBoxModel.addElement(storeLocationList.get(x).getStorePlace());
        }


        idField.setText(itemsList.get(0).getItemId());
        nameField.setText(itemsList.get(0).getItemName());
        priceField.setText(String.valueOf(itemsList.get(0).getItemPrice()));
        categoryBoxModel.setSelectedItem(itemsList.get(0).getCategoryName());
        storeBoxModel.setSelectedItem(itemsList.get(0).getStorePlace());
        minimalStockField.setText(String.valueOf(itemsList.get(0).getMinimalStockLevel()));

        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editItem(stockLevel);
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

        this.setTitle("Edit Item");
        this.setResizable(false);
        buttonAdd.setText("Update");
        idField.setEnabled(false);
        pack();
    }

    private void addItem() {
        // add your code here
        try {
            if (idField.getText().equals("") || nameField.getText().equals("") || priceField.getText().equals("") || minimalStockField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter all the Field");
            } else {
                id = idField.getText();
                name = nameField.getText();
                price = Double.valueOf(priceField.getText());
                category = categoryBoxModel.getSelectedItem().toString();
                store = storeBoxModel.getSelectedItem().toString();
                minimal = Integer.valueOf(minimalStockField.getText());

                categoryList = categoryDAO.getCategoryByName(category);
                storeLocationList = storeLocationDAO.getStoreLocationByName(store);

                insertItem = itemDAO.insertItem(id, name, price, 0, categoryList.get(0).getCategoryId(), storeLocationList.get(0).getStoreId(), minimal);
                if (insertItem) {
                    dispose();
                }
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null ,"Price Field and Minimal Stock Field Must be Number!");
        }


    }

    private void editItem(int stockLevel){
        try {
            if (idField.getText().equals("") || nameField.getText().equals("") || priceField.getText().equals("") || minimalStockField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Enter all the Field");
            } else {
                id = idField.getText();
                name = nameField.getText();
                price = Double.valueOf(priceField.getText());
                category = categoryBoxModel.getSelectedItem().toString();
                store = storeBoxModel.getSelectedItem().toString();
                minimal = Integer.valueOf(minimalStockField.getText());

                categoryList = categoryDAO.getCategoryByName(category);
                storeLocationList = storeLocationDAO.getStoreLocationByName(store);

                itemDAO.updateItem(id, name, price, stockLevel, categoryList.get(0).getCategoryId(), storeLocationList.get(0).getStoreId(), minimal);
                dispose();
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null ,"Price Field and Minimal Stock Field Must be Number!");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        AddEditItemDialog dialog = new AddEditItemDialog();
        dialog.pack();
        dialog.setVisible(true);
        //System.exit(0);
    }
}
