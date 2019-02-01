package GUI.Report;

import DAO.*;
import DTO.Items;
import DTO.Sale;
import DTO.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ReportDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable reportTable;
    private JScrollPane tablePane;
    private JLabel saleIdLabel;
    private JLabel userNameLabel;
    private JLabel totalPriceLabel;
    private JLabel dateLabel;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    private SaleDAO saleDAO = new SaleDAOImpl();
    private List<Sale> saleList;
    private ItemDAO itemDAO = new ItemDAOImpl();
    private List<Items> itemsList;
    private UserDAO userDAO = new UserDAOImpl();
    private List<User> userList;
    private Double totalPrice = 0.0;

    public ReportDialog(String saleId) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        reportTable = new JTable(defaultTableModel);
        tablePane = new JScrollPane(reportTable);

        userList = userDAO.getUserList();
        itemsList = itemDAO.getAllItemList();

        contentPane.add(tablePane);



        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        defaultTableModel.addColumn("Item ID");
        defaultTableModel.addColumn("Item Name");
        defaultTableModel.addColumn("Quantity");
        defaultTableModel.addColumn("Price");
        defaultTableModel.addColumn("Total");

        reportTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        reportTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        reportTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

        saleList = saleDAO.getSaleListById(saleId);
        System.out.println(saleList.size());
        for(int x =0; x< saleList.size(); x++)
        {
            Items foundItem = findItemName(saleList.get(x).getItemId(), itemsList);

            System.out.println(saleList.get(x).getSaleId());
            defaultTableModel.addRow(new Object[]{saleList.get(x).getItemId(), foundItem.getItemName(), saleList.get(x).getQuantity(), saleList.get(x).getItemPrice(), saleList.get(x).getTotalPrice()});
            totalPrice += (saleList.get(x).getTotalPrice());
        }

        //findUserName(userList.get(0).getUserId(), userList);
        saleIdLabel.setText(saleId);
        userNameLabel.setText(findUserName(saleList.get(0).getUserId(), userList));
        dateLabel.setText(saleList.get(0).getSaleDate());
        totalPriceLabel.setText(String.valueOf(totalPrice));


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

    private void onOK() {
        // add your code here

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Items findItemName(String id, List<Items> items){
        for(Items item :items){
            if(item.getItemId().equals(id)){
                return item;
            }
        }
        return null;
    }

    public String findUserName(Integer id, List<User> users){
        for (User user : users) {
            if (user.getUserId() == id) {
                return user.getUserName();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ReportDialog dialog = new ReportDialog("190111204029");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
