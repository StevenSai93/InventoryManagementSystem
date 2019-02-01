package GUI.Items;

import DAO.ItemDAO;
import DAO.ItemDAOImpl;
import DTO.Items;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ViewItem extends JFrame{
    private JFrame frame;
    private JPanel itemPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton cancelButton;
    private JTable itemTable;
    private JScrollPane tablePane;
    private JButton addNewItemButton;
    private JButton viewLowStockButton;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    private ItemDAO itemDAO = new ItemDAOImpl();
    private List<Items> itemsList;
    private List<Items> foundItemList = new ArrayList<>();

    private String stock;
    private String minimalStock;
    private String price;
    private String itemId;

    public ViewItem(Integer userId, Integer userLevel){
        frame = this;
        frame.setMinimumSize(new Dimension(900, 600));
        editButton.setEnabled(false);

        if(userLevel >= 3)
        {
            editButton.setVisible(false);
            addNewItemButton.setVisible(false);
        }

        itemTable = new JTable(defaultTableModel);
        tablePane = new JScrollPane(itemTable);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );


        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("Item ID");
        defaultTableModel.addColumn("Item Name");
        defaultTableModel.addColumn("Category");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Current Stock");
        defaultTableModel.addColumn("Minimal Stock Level");
        defaultTableModel.addColumn("Store");

        itemTable.getColumnModel().getColumn(0).setMaxWidth(50);
        itemTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        itemTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        itemTable.getColumnModel().getColumn(6).setPreferredWidth(50);


        itemTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        itemTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        itemTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        itemTable.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        itemTable.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        addDataToTable();


        itemTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row =  itemTable.getSelectedRow();
                if(row == -1)
                {
                    editButton.setEnabled(false);
                }
                else
                {
                    editButton.setEnabled(true);
                    itemId = (itemTable.getValueAt(itemTable.getSelectedRow(),1).toString());
                    System.out.println(itemId);
                }
            }
        });

        frame.add(itemPanel);
        itemPanel.add(tablePane);
        frame.setTitle("Items List");
        this.setSize(900,600);
        this.setVisible(true);

        frame.setLocationRelativeTo(null);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntryForm entryForm = new EntryForm(userId);
                entryForm.pack();
                entryForm.setVisible(true);
                entryForm.setLocationRelativeTo(frame);
                entryForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        addDataToTable();
                    }
                });

            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditItemDialog addEditItemDialog = new AddEditItemDialog(itemId);
                addEditItemDialog.setLocationRelativeTo(frame);
                addEditItemDialog.setVisible(true);
                addDataToTable();
                editButton.setEnabled(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditItemDialog addEditItemDialog = new AddEditItemDialog();
                addEditItemDialog.setLocationRelativeTo(frame);
                addEditItemDialog.setVisible(true);
                addDataToTable();
            }
        });
        viewLowStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewLowStockDialog viewLowStockDialog = new ViewLowStockDialog();
                viewLowStockDialog.setLocationRelativeTo(frame);
                viewLowStockDialog.setVisible(true);
                addDataToTable();
            }
        });
    }

    public void addDataToTable()
    {
        defaultTableModel.setRowCount(0);
        itemsList = itemDAO.getAllItemList();
        for(int x=0; x<itemsList.size(); x++)
        {
            price = String.format("%,.2f", itemsList.get(x).getItemPrice());
            stock = String.format("%,d", itemsList.get(x).getItemStock());
            minimalStock = String.format("%,d", itemsList.get(x).getMinimalStockLevel());
            defaultTableModel.addRow(new Object[]{x+1, itemsList.get(x).getItemId(), itemsList.get(x).getItemName(), itemsList.get(x).getCategoryName(), price, stock, minimalStock, itemsList.get(x).getStorePlace()});
        }

    }
/*
    public void addFindDataToTable()
    {
        defaultTableModel.setRowCount(0);
        for(int x=0; x<foundItemList.size(); x++)
        {
            price = String.format("%,.2f", foundItemList.get(x).getItemPrice());
            stock = String.format("%,d", foundItemList.get(x).getItemStock());
            minimalStock = String.format("%,d", foundItemList.get(x).getMinimalStockLevel());
            defaultTableModel.addRow(new Object[]{foundItemList.get(x).getItemId(), foundItemList.get(x).getItemName(), foundItemList.get(x).getCategoryName(), price, stock, minimalStock, foundItemList.get(x).getStorePlace()});
        }

    }


    public List<Items> findItemName(String id, List<Items> items){
        for(Items item :items){
            if(item.getItemId().equals(id)){
                foundItemList.add(item);
            }
        }
        return foundItemList;
    }
*/
    public static void main(String[] args) {
        ViewItem viewItem = new ViewItem(1,2);
        viewItem.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
