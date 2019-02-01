package GUI.Items;

import DAO.ItemDAO;
import DAO.ItemDAOImpl;
import DTO.Items;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewLowStockDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable itemTable;
    private JScrollPane tablePane;
    private DefaultTableModel lowStockTableModel = new DefaultTableModel();

    private ItemDAO itemDAO = new ItemDAOImpl();
    private List<Items> itemsList;

    private String stock;
    private String minimalStock;

    public ViewLowStockDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        itemTable = new JTable(lowStockTableModel);
        tablePane = new JScrollPane(itemTable);

        lowStockTableModel.addColumn("Item ID");
        lowStockTableModel.addColumn("Item Name");
        lowStockTableModel.addColumn("Category");
        lowStockTableModel.addColumn("Current Stock");
        lowStockTableModel.addColumn("Minimal Stock");
        lowStockTableModel.addColumn("Store");

        itemTable.getColumnModel().getColumn(0).setMinWidth(100);
        itemTable.getColumnModel().getColumn(1).setMinWidth(150);
        itemTable.getColumnModel().getColumn(2).setMinWidth(100);
        itemTable.getColumnModel().getColumn(3).setMinWidth(100);
        itemTable.getColumnModel().getColumn(4).setMinWidth(100);
        itemTable.getColumnModel().getColumn(5).setMinWidth(100);


        itemTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        itemTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        itemTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        itemTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        addDataToLowStockTable();

        contentPane.add(tablePane);
        setTitle("Items Low Stock List");
        setMinimumSize(new Dimension(700, 500));


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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void addDataToLowStockTable()
    {
        lowStockTableModel.setRowCount(0);
        itemsList = itemDAO.getLowStockItem();
        for(int x=0; x<itemsList.size(); x++)
        {
            stock = String.format("%,d", itemsList.get(x).getItemStock());
            minimalStock = String.format("%,d", itemsList.get(x).getMinimalStockLevel());
            lowStockTableModel.addRow(new Object[]{itemsList.get(x).getItemId(), itemsList.get(x).getItemName(), itemsList.get(x).getCategoryName(), stock, minimalStock, itemsList.get(x).getStorePlace()});
        }

    }

    public static void main(String[] args) {
        ViewLowStockDialog dialog = new ViewLowStockDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
