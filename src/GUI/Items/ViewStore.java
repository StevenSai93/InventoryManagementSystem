package GUI.Items;

import DAO.StoreLocationDAO;
import DAO.StoreLocationDAOImpl;
import DTO.StoreLocation;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewStore extends JFrame{
    private JFrame frame;
    private JPanel storePanel;
    private JTable storeTable;
    private JScrollPane tablePane;
    private JButton addButton;
    private JButton updateButton;
    private JButton cancelButton;
    private Integer storeId;

    private StoreLocationDAO storeLocationDAO = new StoreLocationDAOImpl();
    private List<StoreLocation> storeLocationList;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    public ViewStore(){

        frame = this;
        updateButton.setEnabled(false);

        storeTable = new JTable(defaultTableModel);
        tablePane = new JScrollPane(storeTable);

        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Store Place");

        storeTable.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 13));
        storeTable.setGridColor(Color.gray);
        storeTable.setRowHeight(21);

        addDataToTable();

        frame.add(storePanel);
        storePanel.add(tablePane);
        frame.setTitle("Store List");
        frame.setVisible(true);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);


        storeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row =  storeTable.getSelectedRow();
                if(row == -1)
                {
                    updateButton.setEnabled(false);
                }
                else
                {
                    updateButton.setEnabled(true);
                    storeId = Integer.valueOf(storeTable.getValueAt(storeTable.getSelectedRow(),0).toString());
                    System.out.println(storeId);
                }
            }
        });

        /**
         * Button Listener
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditStoreDialog storeLocationDialog = new AddEditStoreDialog();
                storeLocationDialog.setTitle("Add Store Location");
                storeLocationDialog.pack();
                storeLocationDialog.setLocationRelativeTo(frame);
                storeLocationDialog.setVisible(true);
                storeLocationDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        addDataToTable();
                    }
                });

            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditStoreDialog storeLocationDialog = new AddEditStoreDialog(storeId);
                storeLocationDialog.setTitle("Edit Store Location");
                storeLocationDialog.pack();
                storeLocationDialog.setLocationRelativeTo(frame);
                storeLocationDialog.setVisible(true);
                addDataToTable();
                updateButton.setEnabled(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void addDataToTable()
    {
        defaultTableModel.setRowCount(0);
        storeLocationList = storeLocationDAO.getStoreLocationList();
        for(int x= 0; x<storeLocationList.size(); x++)
        {
            defaultTableModel.addRow(new Object[]{storeLocationList.get(x).getStoreId(), storeLocationList.get(x).getStorePlace()});
        }
    }

    public static void main(String[] args) {
        ViewStore viewStore = new ViewStore();
        viewStore.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
