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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReportForm extends JFrame{

    private JFrame frame;
    private JPanel panel1;
    private JTable reportTable;
    private JButton findButton;
    private JPanel startDate;
    private JPanel endDate;
    private JScrollPane tablePane;
    private JButton cancelButton;
    private JButton detailButton;

    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    private SaleDAO saleDAO = new SaleDAOImpl();
    private List<Sale> saleList;
    private ItemDAO itemDAO = new ItemDAOImpl();
    private List<Items> itemsList;
    private UserDAO userDAO = new UserDAOImpl();
    private List<User> userList;

    private String sDate;
    private String eDate;
    private String saleId;

    private Calendar calendar = Calendar.getInstance();
    private JDateChooser dateChooser = new JDateChooser(calendar.getTime());
    private JDateChooser endDateChooser = new JDateChooser(calendar.getTime());
    private SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

    public ReportForm(){

        frame = this;
        frame.setMinimumSize(new Dimension(560, 300));
        add(panel1);
        detailButton.setEnabled(false);


        startDate.add(dateChooser);
        endDate.add(endDateChooser);

        userList = userDAO.getUserList();
        itemsList = itemDAO.getAllItemList();

        String name = findUserName(1, userList);
        System.out.println("Find User Name "+name);

        System.out.println(endDateChooser);

        reportTable = new JTable(defaultTableModel);
        tablePane = new JScrollPane(reportTable);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        defaultTableModel.addColumn("Sale ID");
        defaultTableModel.addColumn("User Name");
        defaultTableModel.addColumn("Sale Date");
        defaultTableModel.addColumn("Total Amount");

        reportTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        reportTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

        panel1.add(tablePane);

        reportTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row =  reportTable.getSelectedRow();
                if(row == -1)
                {
                    detailButton.setEnabled(false);
                }
                else
                {
                    detailButton.setEnabled(true);
                    saleId = (reportTable.getValueAt(reportTable.getSelectedRow(),0).toString());
                    System.out.println(saleId);
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sDate = simpleDateFormat.format(dateChooser.getDate());
                eDate = simpleDateFormat.format(endDateChooser.getDate());
                //saleList = saleDAO.getSaleListByDate(sDate, eDate);
                saleList = saleDAO.getSaleListByTotalAmount(sDate, eDate);
                System.out.println("Start Date :" +sDate);
                System.out.println("End Date :" +eDate);
                addDataToTable();
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

        setSize(900,600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void addDataToTable(){
        defaultTableModel.setRowCount(0);
        for(int x = 0; x< saleList.size(); x++)
        {
            //defaultTableModel.addRow(new Object[]{saleList.get(x).getSaleId(), saleList.get(x).getItemId(), findItemName(saleList.get(x).getItemId(), itemsList), findUserName(saleList.get(x).getUserId(), userList), saleList.get(x).getSaleDate(), saleList.get(x).getQuantity(), saleList.get(x).getTotalPrice()});
            defaultTableModel.addRow(new Object[]{saleList.get(x).getSaleId(), saleList.get(x).getUserName(), saleList.get(x).getSaleDate(), saleList.get(x).getSaleTotalPrice()});
        }

    }

    public String findUserName(Integer id, List<User> users){
        for (User user : users) {
            if (user.getUserId() == id) {
                return user.getUserName();
            }
        }
        return null;
    }

    public String findItemName(String id, List<Items> items){
        for(Items item :items){
            if(item.getItemId().equals(id)){
                return item.getItemName();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ReportForm reportForm = new ReportForm();
        reportForm.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
