package GUI.Items;

import DAO.*;
import DTO.EntryList;
import DTO.Items;
import DTO.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryForm extends JFrame{
    private JFrame frame;
    private JPanel entryPanel;
    private JScrollPane tablePanel;
    private JTable entryTable;
    private JTextField idField;
    private JButton addButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JButton enterButton;
    private JButton closeButton;
    private JPanel fieldPanel;
    private JTextField nameField;
    private JSpinner quantitySpinner;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    private EntryList entryList;
    private Integer spinnerValue;
    private Integer quantity;
    private Items foundItem;
    private String currentTime;
    private boolean sameId= false;
    private boolean insertEntryList = false;

    private EntryListDAO entryListDAO = new EntryListDAOImpl();
    private List<EntryList> entryListList = new ArrayList<>();
    private List<EntryList> tableList = new ArrayList<>();
    private ItemDAO itemDAO = new ItemDAOImpl();
    private List<Items> itemsList;
    private List<Items> itemsFoundList = new ArrayList<>();
    private UserDAO userDAO = new UserDAOImpl();
    private List<User> userList;

    public EntryForm(Integer userId){
        frame = this;
        frame.setTitle("Entry Item Form");

        userList = userDAO.getUserById(userId);
        itemsList = itemDAO.getAllItemList();

        deleteButton.setEnabled(false);
        SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        entryTable = new JTable(defaultTableModel);
        tablePanel = new JScrollPane(entryTable);

        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("Item ID");
        defaultTableModel.addColumn("Item Name");
        defaultTableModel.addColumn("Quantity");
        //defaultTableModel.addColumn("User Name");
        //defaultTableModel.addColumn("Entry Date");

        entryTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        add(entryPanel);
        entryPanel.add(tablePanel);

        entryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = entryTable.getSelectedRow();
                if(row == -1)
                {
                    deleteButton.setEnabled(false);
                }
                else
                {
                    idField.setText(entryTable.getValueAt(entryTable.getSelectedRow(),1).toString());
                    nameField.setText(entryTable.getValueAt(entryTable.getSelectedRow(),2).toString());
                    quantitySpinner.setValue(tableList.get(row).getQuantity());
                    deleteButton.setEnabled(true);
                }
            }
        });

        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                foundItem = findItemName(idField.getText(), itemsList);
                if(foundItem == null)
                {
                    //idField.setText("");
                    nameField.setText("");
                    //JOptionPane.showMessageDialog(null, "Item Not Found");
                }
                else {
                    nameField.setText(foundItem.getItemName());
                    spinnerValue = (Integer)quantitySpinner.getValue();
                    System.out.println("Spinner Value :" + spinnerValue);
                }
            }
        });

        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                foundItem = findItemName(idField.getText(), itemsList);
                if(foundItem == null)
                {
                    //idField.setText("");
                    nameField.setText("");
                    //JOptionPane.showMessageDialog(null, "Item Not Found");
                }
                else {
                    nameField.setText(foundItem.getItemName());
                    spinnerValue = (Integer)quantitySpinner.getValue();
                    System.out.println("Spinner Value :" + spinnerValue);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                foundItem = findItemName(idField.getText(), itemsList);
                if(foundItem == null)
                {
                    nameField.setText("");
                }
                else {
                    nameField.setText(foundItem.getItemName());
                    spinnerValue = (Integer)quantitySpinner.getValue();
                    System.out.println("Spinner Value :" + spinnerValue);
                }
            }
        });

        idField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                foundItem = findItemName(idField.getText(), itemsList);
                if(!idField.getText().equals("")) {
                    if (foundItem == null) {
                        idField.setText("");
                        nameField.setText("");
                        JOptionPane.showMessageDialog(null, "Item Not Found");
                    } else {

                        nameField.setText(foundItem.getItemName());
                    }
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().equals("") || nameField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter All Field");
                }
                else
                {
                    quantity = (Integer) quantitySpinner.getValue();
                    if(tableList.size() == 0)
                    {
                        entryList = new EntryList(userId, foundItem.getItemId(), foundItem.getItemName(),foundItem.getItemStock(),quantity);
                        tableList.add(entryList);
                    }
                    else
                    {
                        for(int x = 0; x < tableList.size(); x++)
                        {
                            if(idField.getText().equalsIgnoreCase(tableList.get(x).getItemId()))
                            {
                                System.out.println("\n*** Same Item ID Found ***");
                                tableList.get(x).setQuantity(quantity);
                                sameId = true;
                                break;
                            }
                            else
                            {
                                sameId = false;
                            }
                        }
                        if (sameId == false)
                        {
                            System.out.println("\n*** New Item ID Added ***");
                            entryList = new EntryList(userId, foundItem.getItemId(), foundItem.getItemName(),foundItem.getItemStock(),quantity);
                            tableList.add(entryList);
                        }
                    }
                    System.out.println("\n***Entry Size :"+ tableList.size()+" ***");
                    addDataToTable();
                    idField.setText("");
                    nameField.setText("");
                    quantitySpinner.setValue(1);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entryTable.getSelectedRow();
                System.out.println(entryTable.getValueAt(entryTable.getSelectedRow(),1).toString());
                tableList.remove(entryTable.getSelectedRow());
                addDataToTable();
                deleteButton.setEnabled(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                nameField.setText("");
                quantitySpinner.setValue(1);
            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Do you want to add these data?", "", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION)
                {
                    Date date = new java.util.Date();
                    currentTime = simpleDateFormat.format(date);

                    EntryList entryList;
                    System.out.println("*** Insert Table ***");
                    for(int x = 0; x< tableList.size(); x++)
                    {
                        entryList = new EntryList(userId, tableList.get(x).getItemId(), currentTime ,tableList.get(x).getQuantity());
                        entryListList.add(entryList);
                        System.out.println("\n*** Adding Stock ***");
                        System.out.println(tableList.get(x).getItemName());
                        System.out.println("Before Adding Quantity :" +tableList.get(x).getItemStock());
                        System.out.println("Adding Quantity :" + tableList.get(x).getQuantity());
                        tableList.get(x).setItemStock(tableList.get(x).getItemStock() + tableList.get(x).getQuantity());
                        System.out.println("After Adding Quantity :" +tableList.get(x).getItemStock());
                    }
                    System.out.println("\n*** Add to Entry List ***");
                    insertEntryList = entryListDAO.insertEntryList(entryListList);
                    System.out.println("Added into EntryList :" + insertEntryList);
                    if(insertEntryList)
                    {
                        for(int x = 0; x < tableList.size(); x++)
                        {
                            itemDAO.updateStock(tableList.get(x).getItemId(), tableList.get(x).getItemStock());
                        }
                    }
                    tableList.clear();
                    entryListList.clear();
                    itemsList = itemDAO.getAllItemList();
                    defaultTableModel.setRowCount(0);
                }
                System.out.println("#######################################################\n");
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });


    }

    public void addDataToTable(){
        defaultTableModel.setRowCount(0);
        for(int x = 0; x< tableList.size(); x++)
        {
            //defaultTableModel.addRow(new Object[]{tableList.get(x).getItemId(), tableList.get(x).getItemName(), String.format("%,d", tableList.get(x).getQuantity()),String.format("%,.2f", tableList.get(x).getItemPrice()),String.format("%,.2f", tableList.get(x).getTotalPrice())});
            defaultTableModel.addRow(new Object[]{x+1 , tableList.get(x).getItemId(), tableList.get(x).getItemName(), String.format("%,d", tableList.get(x).getQuantity()), tableList.get(x).getEntryDate()});
            System.out.println(tableList.get(x).getItemId()+"\t"+ tableList.get(x).getItemName()+"\t"+ String.format("%,d", tableList.get(x).getQuantity())+"\t"+ tableList.get(x).getEntryDate());
        }
    }

    public Items findItemName(String id, List<Items> items){
        for(Items item :items){
            if(item.getItemId().equalsIgnoreCase(id)){
                return item;
            }
        }
        return null;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 1000000, 1);
        quantitySpinner = new JSpinner(spinnerNumberModel);

    }

    public static void main(String[] args) {
        EntryForm entryForm = new EntryForm(1);
        entryForm.pack();
        entryForm.setVisible(true);
        entryForm.setLocationRelativeTo(null);
        entryForm.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
