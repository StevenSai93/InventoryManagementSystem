package GUI;

import DAO.ItemDAO;
import DAO.ItemDAOImpl;
import DAO.SaleDAO;
import DAO.SaleDAOImpl;
import DTO.Items;
import DTO.Sale;
import GUI.Items.ViewItem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleForm extends JFrame{
    private JFrame frame;
    private JTable saleTable;
    private JTextField idField;
    private JSpinner amountSpinner;
    private JButton addButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JPanel fieldPanel;
    private JTextField nameField;
    private JTextField priceField;
    private JScrollPane tablePanel;
    private JPanel salePanel;
    private JLabel totalPriceLabel;
    private JButton doneButton;
    private JButton viewItemListButton;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    private Sale sale;
    private String currentTime;
    private String saleId;
    private Integer spinnerValue;
    private Integer amount;
    private Double perPrice;
    private boolean sameId = false;
    private boolean insertSaleList = false;
    private boolean updateStock = false;
    private Double totalPrice;

    private SaleDAO saleDAO = new SaleDAOImpl();
    private List<Sale> tableList = new ArrayList<>();
    private List<Sale> saleList = new ArrayList<>();
    private ItemDAO itemDAO = new ItemDAOImpl();
    private List<Items> itemsList;


    public SaleForm(Integer userId, Integer userLevel){
        frame = this;
        frame.setTitle("Sale Form");
        frame.setMinimumSize(new Dimension(1000, 700));

        if(userLevel <3)
        {
            viewItemListButton.setVisible(false);
        }
        deleteButton.setEnabled(false);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat stringTime = new java.text.SimpleDateFormat("yyMMddHHmmss");

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        saleTable = new JTable(defaultTableModel);
        tablePanel = new JScrollPane(saleTable);

        defaultTableModel.addColumn("No");
        defaultTableModel.addColumn("Item ID");
        defaultTableModel.addColumn("Item Name");
        defaultTableModel.addColumn("Amount");
        defaultTableModel.addColumn("Per Price");
        defaultTableModel.addColumn("TotalPrice");
        //add(fieldPanel);

        saleTable.getColumnModel().getColumn(0).setMaxWidth(50);
        saleTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        saleTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        saleTable.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        add(salePanel);
        salePanel.add(tablePanel);

        saleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = saleTable.getSelectedRow();
                if(row == -1)
                {
                    deleteButton.setEnabled(false);
                }
                else
                {
                    idField.setText(saleTable.getValueAt(saleTable.getSelectedRow(),1).toString());
                    nameField.setText(saleTable.getValueAt(saleTable.getSelectedRow(),2).toString());

                    System.out.println(saleTable.getValueAt(saleTable.getSelectedRow(),3).toString());
                    amountSpinner.setValue(tableList.get(row).getQuantity());
                    priceField.setText(saleTable.getValueAt(saleTable.getSelectedRow(),4).toString());
                    deleteButton.setEnabled(true);
                }
            }
        });

        idField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyReleased(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    itemsList = itemDAO.getItemById(idField.getText());
                    if(itemsList.size() == 0)
                    {
                        idField.setText("");
                        nameField.setText("");
                        priceField.setText("");
                        JOptionPane.showMessageDialog(null, "Item Not Found");
                    }
                    else {
                        nameField.setText(itemsList.get(0).getItemName());
                        priceField.setText(String.valueOf(itemsList.get(0).getItemPrice()));
                        spinnerValue = (Integer)amountSpinner.getValue();
                        System.out.println("Spinner Value :" + spinnerValue);
                    }
                }
                else
                {
                    nameField.setText("");
                    priceField.setText("");
                }
            }
        });

        idField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                itemsList = itemDAO.getItemById(idField.getText());
                if(!idField.getText().equals("")) {
                    if (itemsList.size() == 0) {
                        idField.setText("");
                        nameField.setText("");
                        priceField.setText("");
                        JOptionPane.showMessageDialog(null, "Item Not Found");
                    } else {

                        nameField.setText(itemsList.get(0).getItemName());
                        priceField.setText(String.valueOf(itemsList.get(0).getItemPrice()));

                    }
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idField.getText().equals("") || nameField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter All Field");
                } else {
                    if (itemsList.get(0).getItemStock() < (Integer) amountSpinner.getValue()) {
                        JOptionPane.showMessageDialog(null, itemsList.get(0).getItemName() + " Left " + itemsList.get(0).getItemStock());
                    } else {
                        amount = (Integer) amountSpinner.getValue();
                        perPrice = Double.valueOf(priceField.getText());
                        if(tableList.size() == 0)
                        {
                            System.out.println("\n********************");
                            //sale = new Sale(time, idField.getText(), nameField.getText(), perPrice, userId, currentTime, amount, amount * perPrice);
                            sale = new Sale(itemsList.get(0).getItemId(), itemsList.get(0).getItemName(), perPrice, amount,itemsList.get(0).getItemStock(), amount * perPrice);
                            tableList.add(sale);
                        }
                        else {
                            for (int x = 0; x < tableList.size(); x++) {
                                if (idField.getText().equals(tableList.get(x).getItemId()) || nameField.getText().equalsIgnoreCase(tableList.get(x).getItemName())) {
                                    System.out.println("\n*** Same Item ID Found ***");
                                    tableList.get(x).setQuantity(amount);
                                    tableList.get(x).setTotalPrice(amount * perPrice);
                                    sameId = true;
                                    break;
                                }
                                else
                                {
                                    sameId = false;
                                }
                            }
                            if(sameId == false)
                            {
                                System.out.println("\n*** New Item ID Added ***");
                                sale = new Sale(itemsList.get(0).getItemId(), itemsList.get(0).getItemName(), perPrice, amount,itemsList.get(0).getItemStock(), amount * perPrice);
                                tableList.add(sale);
                            }
                        }

                        System.out.println("\n***Sale Size :"+ tableList.size()+" ***");
                        addDataToTable();
                        idField.setText("");
                        nameField.setText("");
                        amountSpinner.setValue(1);
                        priceField.setText("");
                        //System.out.println(idField.getText() + "\t" + nameField.getText() + "\t" + priceField.getText() + "\t" + amountSpinner.getValue());
                        //System.out.println(itemsList.get(0).getItemId() + "\t" + itemsList.get(0).getItemName() + "\t" + itemsList.get(0).getItemPrice() + "\t" + itemsList.get(0).getItemStock());

                    }
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saleTable.getSelectedRow();
                System.out.println(saleTable.getValueAt(saleTable.getSelectedRow(),0).toString());
                tableList.remove(saleTable.getSelectedRow());
                addDataToTable();
                deleteButton.setEnabled(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                nameField.setText("");
                amountSpinner.setValue(1);
                priceField.setText("");
            }
        });
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int reply = JOptionPane.showConfirmDialog(null, "Make Sale", "", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    Date dt = new java.util.Date();
                    currentTime = sdf.format(dt);
                    saleId = stringTime.format(dt);

                    Sale sale;
                    System.out.println("*** INSERT TABLE ***");
                    for (int x = 0; x < tableList.size(); x++) {
                        sale = new Sale(saleId, tableList.get(x).getItemId(), userId, currentTime,tableList.get(x).getItemPrice() , tableList.get(x).getQuantity(), tableList.get(x).getTotalPrice());
                        saleList.add(sale);
                        tableList.get(x).setItemStock(tableList.get(x).getItemStock() - tableList.get(x).getQuantity());
                        System.out.println(tableList.get(x).getItemStock() + " " + tableList.get(x).getItemName() + " left.");
                    }
                    for (int x = 0; x < saleList.size(); x++) {
                        System.out.println(saleList.get(x).getSaleId() + "\t" + saleList.get(x).getItemId() + "\t" + saleList.get(x).getUserId() + "\t" + saleList.get(x).getSaleDate() + "\t" + saleList.get(x).getQuantity() + "\t" + saleList.get(x).getTotalPrice());
                    }
                    insertSaleList = saleDAO.insertSale(saleList);
                    System.out.println("\n*** Sale List Inserted ***");
                    if (insertSaleList) {
                        for (int x = 0; x < tableList.size(); x++) {
                            itemDAO.updateStock(tableList.get(x).getItemId(), tableList.get(x).getItemStock());

                        }
                    }
                    tableList.clear();
                    saleList.clear();
                    defaultTableModel.setRowCount(0);
                }

            }
        });
        viewItemListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewItem viewItem = new ViewItem(userId, userLevel);
            }
        });
    }

    public void addDataToTable(){
        totalPrice = 0.0;
        defaultTableModel.setRowCount(0);
        for(int x = 0; x< tableList.size(); x++)
        {
            defaultTableModel.addRow(new Object[]{x+1, tableList.get(x).getItemId(), tableList.get(x).getItemName(), String.format("%,d", tableList.get(x).getQuantity()),String.format("%,.2f", tableList.get(x).getItemPrice()),String.format("%,.2f", tableList.get(x).getTotalPrice())});
            totalPrice += tableList.get(x).getTotalPrice();
            totalPriceLabel.setText(String.valueOf(totalPrice));
        }
    }

    public static void main(String[] args) {
        SaleForm saleForm = new SaleForm(1, 1);
        saleForm.pack();
        saleForm.setVisible(true);
        saleForm.setLocationRelativeTo(null);
        saleForm.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        SpinnerModel spinnerNumberModel = new SpinnerNumberModel(1, 1, 1000000, 1);
        amountSpinner = new JSpinner(spinnerNumberModel);

    }
}
