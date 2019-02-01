package GUI.Report;

import DAO.*;
import DTO.Items;
import DTO.Sale;
import DTO.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ReportByUserForm extends JFrame {
    private JFrame frame;
    private JScrollPane tablePane1;
    private JTable reportTable1;
    private JPanel startDate1;
    private JPanel endDate1;
    private JButton findButton1;
    private JButton detailButton;
    private JPanel panel1;
    private JComboBox userComboBox;
    private JTabbedPane tabbedPane;
    private JPanel userPanel;
    private JButton cancelButton;
    private JScrollPane tablePane2;
    private JTable reportTable2;
    private JPanel endDate2;
    private JPanel startDate2;
    private JPanel itemPanel;
    private JComboBox itemComboBox;
    private JButton findButton2;
    private JLabel totalLabel1;
    private JLabel totalLabel2;
    private JButton cancelButton2;

    private DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel byItemComboBoxModel = new DefaultComboBoxModel();
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private DefaultTableModel byItemTableModel =  new DefaultTableModel();

    private SaleDAO saleDAO = new SaleDAOImpl();
    private List<Sale> saleList;
    private ItemDAO itemDAO = new ItemDAOImpl();
    private List<Items> itemsList;
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

    private Double totalSaleAmount;

    private Calendar calendar = Calendar.getInstance();
    private JDateChooser startDateChooser1 = new JDateChooser(calendar.getTime());
    private JDateChooser endDateChooser1 = new JDateChooser(calendar.getTime());
    private JDateChooser startDateChooser2 = new JDateChooser(calendar.getTime());
    private JDateChooser endDateChooser2 = new JDateChooser(calendar.getTime());
    private SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ReportByUserForm(){

        //add(tabbedPane);
        frame = this;
        frame.setTitle("Report");
        frame.setMinimumSize(new Dimension(560, 300));
        tabbedPane = new JTabbedPane();

        tabbedPane.add("By Sale ID",userPanel);
        tabbedPane.add("By Item", itemPanel);
        add(tabbedPane);
        detailButton.setEnabled(false);

        userList = userDAO.getUserList();
        itemsList = itemDAO.getAllItemList();

        /**
         * For First Tabbed Panel
         */
        startDate1.add(startDateChooser1);
        endDate1.add(endDateChooser1);

        //defaultComboBoxModel = new DefaultComboBoxModel();
        userComboBox.setModel(defaultComboBoxModel);
        defaultComboBoxModel.addElement("All");
        for(int x =0; x<userList.size(); x++)
        {
            defaultComboBoxModel.addElement(userList.get(x).getUserName());
        }

        //String name = findUserName(1, userList);
        //System.out.println("Find User Name "+name);

        System.out.println(endDateChooser2);

        reportTable1 = new JTable(defaultTableModel);
        tablePane1 = new JScrollPane(reportTable1);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("Sale ID");
        defaultTableModel.addColumn("User Name");
        defaultTableModel.addColumn("Sale Date");
        defaultTableModel.addColumn("Total Amount");

        //reportTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
        reportTable1.getColumnModel().getColumn(0).setMaxWidth(50);
        reportTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        reportTable1.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        userPanel.add(tablePane1);

        /**
         * First Tabbed Panel Listener
         */
        reportTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row =  reportTable1.getSelectedRow();
                if(row == -1)
                {
                    detailButton.setEnabled(false);
                }
                else
                {
                    detailButton.setEnabled(true);
                    saleId = (reportTable1.getValueAt(reportTable1.getSelectedRow(),1).toString());
                    System.out.println(saleId);
                }
            }
        });

        findButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sDate = simpleDateFormat.format(startDateChooser1.getDate());
                eDate = simpleDateFormat.format(endDateChooser1.getDate());
                System.out.println("************** \nStart Date :"+sDate);
                System.out.println("************** \nEnd Date :"+eDate);
                String foundUserName = String.valueOf(userComboBox.getItemAt(userComboBox.getSelectedIndex()));
                /*
                //saleList = saleDAO.getSaleListByDate(sDate, eDate);
                System.out.println("Start Date :" +sDate);
                System.out.println("End Date :" +eDate);
                System.out.println(userComboBox.getItemAt(userComboBox.getSelectedIndex()));
                System.out.println(userComboBox.getItemAt(userComboBox.getSelectedIndex()).equals("All"));
                System.out.println(findUserId(foundUserName ,userList));
                */
                if(foundUserName.equals("All"))
                {
                    saleList = saleDAO.getSaleListByTotalAmount(sDate, eDate);
                }
                else
                {
                    saleList = saleDAO.getSaleListByTotalAmount(sDate, eDate, findUserId(foundUserName ,userList));
                }
                addDataToTable1();
            }
        });

        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportDialog reportDialog = new ReportDialog(saleId);
                reportDialog.pack();
                reportDialog.setLocationRelativeTo(frame);
                reportDialog.setVisible(true);
                reportDialog.setEnabled(false);
                detailButton.setEnabled(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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


        reportTable2.getColumnModel().getColumn(0).setPreferredWidth(20);
        reportTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
        reportTable2.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        reportTable2.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        reportTable2.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);

        itemPanel.add(tablePane2);


        findButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sDate = simpleDateFormat.format(startDateChooser2.getDate());
                eDate = simpleDateFormat.format(endDateChooser2.getDate());
                System.out.println("************** \nStart Date :"+sDate);
                System.out.println("************** \nEnd Date :"+eDate);
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
                totalLabel2.setText(String.format("%,.2f", totalSaleAmount));
            }
        });

        cancelButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        setSize(1050,600);
        setVisible(true);
        setLocationRelativeTo(null);



    }

    public void addDataToTable1(){
        defaultTableModel.setRowCount(0);
        totalSaleAmount = 0.0;
        for(int x = 0; x< saleList.size(); x++)
        {
            totalAmount = String.format("%,.2f", saleList.get(x).getSaleTotalPrice());
            defaultTableModel.addRow(new Object[]{x+1, saleList.get(x).getSaleId(), saleList.get(x).getUserName(), saleList.get(x).getSaleDate(), totalAmount});
            totalSaleAmount += saleList.get(x).getSaleTotalPrice();
        }
        totalLabel1.setText(String.format("%,.2f", totalSaleAmount));

    }

    public Integer findUserId(String userName, List<User> users){
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user.getUserId();
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
        ReportByUserForm reportByUserForm = new ReportByUserForm();
        reportByUserForm.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
