package GUI;

import DAO.*;
import DTO.Items;
import DTO.Sale;
import DTO.User;
import GUI.Items.ViewCategory;
import GUI.Items.ViewItem;
import GUI.Items.ViewStore;
import GUI.Report.ReportByUserForm;
import GUI.User.ManageUser;
import GUI.User.ManageUserLevel;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdminGUI extends JFrame{

    private JMenuBar jMBar = new JMenuBar();
    private JTable table2;
    private JTable table1;
    private JScrollPane tablePane2;
    private JScrollPane tablePane1;
    private JTabbedPane tabbedPane;
    private JPanel reportPane1;
    private JPanel reportPane2;
    private JPanel tableJPanel1;
    private JPanel tableJPanel2;
    private JPanel startDate1;
    private JPanel endDate1;
    private JButton findButton1;
    private JComboBox userComboBox;
    private JLabel totalLabel1;
    private JLabel totalLabel2;
    private JPanel startDate2;
    private JPanel endDate2;
    private JButton findButton2;
    private JComboBox itemComboBox;
    private JTable reportTable2;
    private JButton cancelButton2;
    private JLabel byItemTotalLabel;
    private JScrollPane lowStockTablePane;
    private JTable lowStockTable;
    private JPanel lowStockPane;

    private DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel byItemComboBoxModel = new DefaultComboBoxModel();
    private DefaultTableModel tableModel1 = new DefaultTableModel();
    private DefaultTableModel tableModel2 = new DefaultTableModel();
    private DefaultTableModel byItemTableModel =  new DefaultTableModel();
    private DefaultTableModel lowStockTableModel = new DefaultTableModel();

    private SaleDAO saleDAO = new SaleDAOImpl();
    private List<Sale> saleList;
    private ItemDAO itemDAO = new ItemDAOImpl();
    private List <Items> itemsList;
    private List<Items> lowStockItemsList;
    private UserDAO userDAO = new UserDAOImpl();
    private List<User> userList;

    private String sDate;
    private String eDate;
    private String saleId;
    private String price;
    private String stock;
    private String quantity;
    private String total;
    private String totalAmount;

    private int lowStockCount = 0;

    private Double totalSaleAmount;
    private Double totalPrice;

    private Calendar calendar = Calendar.getInstance();
    private JDateChooser startDateChooser1 = new JDateChooser(calendar.getTime());
    private JDateChooser endDateChooser1 = new JDateChooser(calendar.getTime());
    private JDateChooser startDateChooser2 = new JDateChooser(calendar.getTime());
    private JDateChooser endDateChooser2 = new JDateChooser(calendar.getTime());
    private SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AdminGUI(Integer userId, Integer userLevel)
    {
        //Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        //System.setProperty("com.apple.mrj.application.apple.menu.about.name", "WikiTeX");



        //add(adminPanel);
        //setSize(dimension);
        setSize(1650, 1080);
        setMinimumSize(new Dimension(1650, 1080));
        setTitle("Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setJMenuBar(jMBar);

        JMenu user_menu = new JMenu("User");
        JMenu item_menu = new JMenu("Items");
        JMenu sale_menu = new JMenu("Sale");

        jMBar.add(user_menu);
        jMBar.add(item_menu);
        jMBar.add(sale_menu);

        JMenuItem user_manageUser = new JMenuItem("Manage User");
        JMenuItem user_manageLevel = new JMenuItem("Manage User Level");
        user_menu.add(user_manageUser);
        user_menu.add(user_manageLevel);

        JMenuItem item_itemsList = new JMenuItem("Manage Item");
        JMenuItem item_category = new JMenuItem("Mange Category");
        JMenuItem item_store = new JMenuItem("Manage Store");
        item_menu.add(item_itemsList);
        item_menu.add(item_store);
        item_menu.add(item_category);

        JMenuItem sale_makeSale = new JMenuItem("Make Sale");
        sale_menu.add(sale_makeSale);

        lowStockItemsList = itemDAO.getLowStockItem();
        lowStockCount = lowStockItemsList.size();
        if(lowStockCount != 0)
        {
            JOptionPane.showMessageDialog(null, "There are " + lowStockCount + " item(s) need to restock");
        }



        user_manageUser.addActionListener(e -> {
            ManageUser manageUser = new ManageUser();
        });

        user_manageLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageUserLevel manageUserLevel = new ManageUserLevel();
            }
        });

        item_itemsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewItem viewItem = new ViewItem(userId, userLevel);
            }
        });

        item_category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCategory viewCategory = new ViewCategory();
            }
        });

        item_store.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStore viewStore = new ViewStore();
            }
        });

        sale_makeSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaleForm saleForm = new SaleForm(userId, userLevel);
                saleForm.pack();
                saleForm.setVisible(true);
                saleForm.setLocationRelativeTo(null);
            }
        });


        tabbedPane = new JTabbedPane();
        tabbedPane.add("By Sale ID", reportPane1);
        tabbedPane.add("By Item", reportPane2);
        tabbedPane.add("Low Stock", lowStockPane);
        add(tabbedPane);

        userList = userDAO.getUserList();
        itemsList = itemDAO.getAllItemList();

        startDate1.add(startDateChooser1);
        endDate1.add(endDateChooser1);

        /**
         *For First Tabbed Panel
         */

        userComboBox.setModel(defaultComboBoxModel);
        defaultComboBoxModel.addElement("All");
        for(int x =0; x<userList.size(); x++)
        {
            defaultComboBoxModel.addElement(userList.get(x).getUserName());
        }


        table1 = new JTable(tableModel1);
        tablePane1 = new JScrollPane(table1);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        tableModel1.addColumn("No");
        tableModel1.addColumn("Sale ID");
        tableModel1.addColumn("User Name");
        tableModel1.addColumn("Sale Date");
        tableModel1.addColumn("Total Amount");

        //reportTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        table1.getColumnModel().getColumn(0).setMaxWidth(50);
        table1.getColumnModel().getColumn(3).setPreferredWidth(100);
        table1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        //table1.setShowHorizontalLines(true);
        table1.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 13));
        table1.setGridColor(Color.gray);
        table1.setRowHeight(21);


        table2 = new JTable(tableModel2);
        tablePane2 = new JScrollPane(table2);

        tableModel2.addColumn("No");
        tableModel2.addColumn("Item ID");
        tableModel2.addColumn("Item Name");
        tableModel2.addColumn("Quantity");
        tableModel2.addColumn("Price");
        tableModel2.addColumn("Total");

        table1.getColumnModel().getColumn(0).setMaxWidth(50);
        table2.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table2.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table2.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table2.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 13));
        table2.setGridColor(Color.gray);
        table2.setRowHeight(21);


        tableJPanel1.setMinimumSize(new Dimension(800, 900));
        tableJPanel2.setMinimumSize(new Dimension(800, 900));
        tableJPanel1.add(tablePane1);
        tableJPanel2.add(tablePane2);


        //To appear the Frame at the center of the screen!!
        setLocationRelativeTo(null);

        findButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sDate = simpleDateFormat.format(startDateChooser1.getDate());
                endDateChooser1 = new JDateChooser(Calendar.getInstance().getTime());
                eDate = simpleDateFormat.format(endDateChooser1.getDate());
                System.out.println("************** \nStart Date :"+sDate);
                System.out.println("************** \nEnd Date :"+eDate);
                String foundUserName = String.valueOf(userComboBox.getItemAt(userComboBox.getSelectedIndex()));
                if(foundUserName.equals("All"))
                {
                    saleList = saleDAO.getSaleListByTotalAmount(sDate, eDate);
                }
                else
                {
                    saleList = saleDAO.getSaleListByTotalAmount(sDate, eDate, findUserId(foundUserName ,userList));
                }
                addDataToReportByIDTable1();
                tableModel2.setRowCount(0);
            }
        });

        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row =  table1.getSelectedRow();
                if(row == -1)
                {
                    tableModel2.setRowCount(0);
                }
                else
                {
                    addDataToReportByIDTable2();
                }
            }
        });

        /**
         * For Second Tabbed Panel
         */
        startDate2.add(startDateChooser2);
        endDate2.add(endDateChooser2);

        itemComboBox.setModel(byItemComboBoxModel);
        byItemComboBoxModel.addElement("All");
        for(int x =0; x<itemsList.size(); x++)
        {
            byItemComboBoxModel.addElement(itemsList.get(x).getItemName());
        }

        reportTable2 = new JTable(byItemTableModel);
        tablePane2 = new JScrollPane(reportTable2);

        byItemTableModel.addColumn("No");
        byItemTableModel.addColumn("Sale ID");
        byItemTableModel.addColumn("Staff");
        byItemTableModel.addColumn("Sale Date");
        byItemTableModel.addColumn("Item ID");
        byItemTableModel.addColumn("Item Name");
        byItemTableModel.addColumn("Quantity");
        byItemTableModel.addColumn("Price");
        byItemTableModel.addColumn("Total");


        reportTable2.getColumnModel().getColumn(0).setMaxWidth(50);
        reportTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
        reportTable2.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        reportTable2.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        reportTable2.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        reportTable2.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 13));
        reportTable2.setGridColor(Color.gray);
        reportTable2.setRowHeight(21);

        reportPane2.add(tablePane2);


        findButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sDate = simpleDateFormat.format(startDateChooser2.getDate());
                endDateChooser2 = new JDateChooser(Calendar.getInstance().getTime());
                eDate = simpleDateFormat.format(endDateChooser2.getDate());
                System.out.println("************** \nStart Date :"+sDate);
                System.out.println("************** \nEnd Date :"+eDate);

                addDataToReportByItemTable();
            }
        });

        itemComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               addDataToReportByItemTable();
            }
        });

        /**
         * Low Stock Tabbed Pane
         */
        lowStockTable = new JTable(lowStockTableModel);
        lowStockTablePane = new JScrollPane(lowStockTable);

        lowStockTableModel.addColumn("No");
        lowStockTableModel.addColumn("Item ID");
        lowStockTableModel.addColumn("Item Name");
        lowStockTableModel.addColumn("Category");
        lowStockTableModel.addColumn("Current Stock");
        lowStockTableModel.addColumn("Minimal Stock");
        lowStockTableModel.addColumn("Store");

        lowStockTable.getColumnModel().getColumn(0).setMaxWidth(100);
        lowStockTable.getColumnModel().getColumn(1).setMinWidth(100);
        lowStockTable.getColumnModel().getColumn(2).setMinWidth(150);
        lowStockTable.getColumnModel().getColumn(3).setMinWidth(100);
        lowStockTable.getColumnModel().getColumn(4).setMinWidth(100);
        lowStockTable.getColumnModel().getColumn(5).setMinWidth(100);
        lowStockTable.getColumnModel().getColumn(6).setMinWidth(100);
        lowStockTable.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 13));
        lowStockTable.setGridColor(Color.gray);
        lowStockTable.setRowHeight(21);


        lowStockTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        lowStockTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        lowStockTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        lowStockTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        addDataToLowStockTable();
        lowStockPane.add(lowStockTablePane);

        tabbedPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addDataToLowStockTable();
            }
        });
    }

    public void addDataToReportByItemTable(){
        String foundItemName = String.valueOf(itemComboBox.getItemAt(itemComboBox.getSelectedIndex()));
        System.out.println("Found :"+ foundItemName);
        System.out.println("Found ID :"+ findItemId(foundItemName, itemsList));
        if(foundItemName.equals("All"))
        {
            saleList = saleDAO.getSaleListByItem(sDate, eDate);
        }
        else
        {
            saleList = saleDAO.getSaleListByItem(sDate, eDate, findItemId(foundItemName, itemsList));
        }
        byItemTableModel.setRowCount(0);
        totalSaleAmount = 0.0;
        for(int x = 0; x < saleList.size(); x++)
        {
            quantity = String.format("%,d", saleList.get(x).getQuantity());
            price = String.format("%,.2f", saleList.get(x).getItemPrice());
            total = String.format("%,.2f", saleList.get(x).getTotalPrice());
            byItemTableModel.addRow(new Object[]{x+1, saleList.get(x).getSaleId(), saleList.get(x).getUserName(), saleList.get(x).getSaleDate(), saleList.get(x).getItemId(), saleList.get(x).getItemName(), quantity, price, total});
            totalSaleAmount += saleList.get(x).getTotalPrice();
        }
        byItemTotalLabel.setText(String.format("%,.2f", totalSaleAmount));
    }

    public void addDataToReportByIDTable1(){
        tableModel1.setRowCount(0);
        totalSaleAmount = 0.0;
        for(int x = 0; x< saleList.size(); x++)
        {
            totalAmount = String.format("%,.2f", saleList.get(x).getSaleTotalPrice());
            tableModel1.addRow(new Object[]{x+1, saleList.get(x).getSaleId(), saleList.get(x).getUserName(), saleList.get(x).getSaleDate(), totalAmount});
            totalSaleAmount += saleList.get(x).getSaleTotalPrice();
        }
        totalLabel1.setText(String.format("%,.2f", totalSaleAmount));

    }

    public void addDataToReportByIDTable2(){
        saleList = saleDAO.getSaleListById(table1.getValueAt(table1.getSelectedRow(),1).toString());
        System.out.println(saleList.size());
        tableModel2.setRowCount(0);
        totalPrice = 0.0;
        for(int x =0; x< saleList.size(); x++)
        {
            Items foundItem = findItemName(saleList.get(x).getItemId(), itemsList);

            System.out.println(saleList.get(x).getSaleId());
            tableModel2.addRow(new Object[]{x+1, saleList.get(x).getItemId(), foundItem.getItemName(), saleList.get(x).getQuantity(), saleList.get(x).getItemPrice(), saleList.get(x).getTotalPrice()});
            totalPrice += (saleList.get(x).getTotalPrice());
            System.out.println(saleList.get(x).getItemId() +"\t"+ foundItem.getItemName()+"\t"+ saleList.get(x).getQuantity()+"\t"+ saleList.get(x).getItemPrice()+"\t"+ saleList.get(x).getTotalPrice());
            System.out.println("*********************************\n");
        }
        totalLabel2.setText(String.format("%,.2f", totalPrice));
    }

    public void addDataToLowStockTable()
    {
        String minimalStock;
        lowStockTableModel.setRowCount(0);
        lowStockItemsList = itemDAO.getLowStockItem();
        for(int x=0; x<lowStockItemsList.size(); x++)
        {
            stock = String.format("%,d", lowStockItemsList.get(x).getItemStock());
            minimalStock = String.format("%,d", itemsList.get(x).getMinimalStockLevel());
            lowStockTableModel.addRow(new Object[]{x+1, lowStockItemsList.get(x).getItemId(), lowStockItemsList.get(x).getItemName(), lowStockItemsList.get(x).getCategoryName(), stock, minimalStock, lowStockItemsList.get(x).getStorePlace()});
        }

    }

    public Integer findUserId(String userName, List<User> users){
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user.getUserId();
            }
        }
        return null;
    }

    public Items findItemName(String id, List<Items> items){
        for(Items item :items){
            if(item.getItemId().equals(id)){
                return item;
            }
        }
        return null;
    }

    public String findItemId(String itemName, List<Items> items){
        for(Items item :items){
            if(item.getItemName().equals(itemName)){
                return item.getItemId();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AdminGUI adminGUI = new AdminGUI(1, 1);
        adminGUI.setVisible(true);
    }
}
