package GUI.User;

import DAO.UserLevelDAO;
import DAO.UserLevelDAOImpl;
import DTO.UserLevel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ManageUserLevel extends JFrame{
    private JFrame frame;
    private JPanel levelPanel;
    private JTable levelTable;
    private JButton addButton;
    private JButton editButton;
    private JButton cancelButton;
    private JScrollPane tableSccroll;
    private JPanel buttonPanel;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private UserLevelDAO userLevelDAO = new UserLevelDAOImpl();
    private List<UserLevel> userLevelList;
    private Integer id;

    public ManageUserLevel(){
        frame = this;



        editButton.setEnabled(false);
        levelTable = new JTable(defaultTableModel);
        tableSccroll = new JScrollPane(levelTable);

        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("User Level");

        levelTable.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 13));
        levelTable.setGridColor(Color.gray);
        levelTable.setRowHeight(21);

        addDataToTable();

        frame.add(levelPanel);
        levelPanel.add(tableSccroll);
        frame.setSize(320,400);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

        levelTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = levelTable.getSelectedRow();
                if(row == -1 || row == 0)
                {
                    editButton.setEnabled(false);
                }
                else
                {
                    editButton.setEnabled(true);
                    id = Integer.valueOf(levelTable.getValueAt(levelTable.getSelectedRow(),0).toString());
                    System.out.println("ID :" +levelTable.getValueAt(levelTable.getSelectedRow(),0).toString());
                    System.out.println("Level :" +levelTable.getValueAt(levelTable.getSelectedRow(),1).toString());
                }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditUserLevelDialog addEditUserLevelDialog = new AddEditUserLevelDialog();
                addEditUserLevelDialog.setLocationRelativeTo(frame);
                addEditUserLevelDialog.setVisible(true);
                addEditUserLevelDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        addDataToTable();
                    }
                });

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditUserLevelDialog addEditUserLevelDialog = new AddEditUserLevelDialog(id);
                addEditUserLevelDialog.setLocationRelativeTo(frame);
                addEditUserLevelDialog.setVisible(true);
                addEditUserLevelDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        addDataToTable();
                        editButton.setEnabled(false);
                    }
                });
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void addDataToTable(){
        defaultTableModel.setRowCount(0);
        userLevelList = userLevelDAO.getUserLevelList();
        for(int x = 0; x < userLevelList.size(); x++)
        {
            defaultTableModel.addRow(new Object[]{userLevelList.get(x).getUserLevelId(), userLevelList.get(x).getUserLevel()});
        }
    }

    public static void main(String[] args) {
        ManageUserLevel manageUserLevel = new ManageUserLevel();
        manageUserLevel.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
