package GUI.User;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import DTO.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ManageUser extends JFrame{

    private JFrame frame;
    private JPanel user_Panel;
    private JTable user_table;
    private JScrollPane user_table_panel;
    private JButton addUserButton;
    private JButton editButton;
    private JButton closeButton;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();
    private UserDAO userDAO = new UserDAOImpl();

    private List<User> userList;

    private boolean selectRow = false;
    private Integer id;
    public String name;
    public String userLevel;
    public String password;
    public String address;
    public String phNumber;
    public String email;

    public ManageUser(){

        frame = this;

        userList = userDAO.getUserList();
        user_table = new JTable(defaultTableModel);
        user_table_panel = new JScrollPane(user_table);
        editButton.setEnabled(false);



        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.RIGHT );

        defaultTableModel.addColumn("User ID");
        defaultTableModel.addColumn("User Name");
        defaultTableModel.addColumn("User Level");
        defaultTableModel.addColumn("Password");
        defaultTableModel.addColumn("Address");
        defaultTableModel.addColumn("email");
        defaultTableModel.addColumn("Phone Number");

        user_table.getTableHeader().setFont(new Font("Dialog", Font.PLAIN, 13));
        user_table.setGridColor(Color.gray);
        user_table.setRowHeight(21);

        user_table.getColumnModel().getColumn(0).setPreferredWidth(10);
        user_table.getColumnModel().getColumn(1).setPreferredWidth(50);
        user_table.getColumnModel().getColumn(2).setPreferredWidth(20);
        user_table.getColumnModel().getColumn(3).setPreferredWidth(20);
        user_table.getColumnModel().getColumn(4).setPreferredWidth(100);
        user_table.getColumnModel().getColumn(5).setPreferredWidth(50);
        user_table.getColumnModel().getColumn(6).setPreferredWidth(50);
        for(int x=0; x<userList.size(); x++)
        {
            defaultTableModel.addRow(new Object[]{userList.get(x).getUserId(),userList.get(x).getUserName(),userList.get(x).getUserLevel(),userList.get(x).getPassword(), userList.get(x).getAddress(),userList.get(x).getEmail(), userList.get(x).getPhNumber()});
        }

        this.add(user_Panel);
        user_Panel.add(user_table_panel);

        this.setVisible(true);
        setSize(1200,600);
        this.setTitle("User List");
        setLocationRelativeTo(null);



        user_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = user_table.getSelectedRow();
                if(row == -1 || row == 0)
                {
                    editButton.setEnabled(false);
                }
                else
                {
                    editButton.setEnabled(true);
                    id = Integer.valueOf(user_table.getValueAt(user_table.getSelectedRow(), 0).toString());
                }

            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditUserDialog addEditUserDialog = new AddEditUserDialog();
                addEditUserDialog.setLocationRelativeTo(frame);
                addEditUserDialog.setVisible(true);
                addEditUserDialog.addWindowListener(new WindowAdapter() {
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
                    AddEditUserDialog addEditUserDialog = new AddEditUserDialog(id);
                    addEditUserDialog.setLocationRelativeTo(frame);
                    addEditUserDialog.setVisible(true);
                    addEditUserDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            super.windowClosed(e);
                            addDataToTable();
                            editButton.setEnabled(false);
                        }
                    });
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    /**
     *
     */
    public void addDataToTable()
    {
        defaultTableModel.setRowCount(0);
        userList = userDAO.getUserList();
        for(int x=0; x<userList.size(); x++)
        {
            defaultTableModel.addRow(new Object[]{userList.get(x).getUserId(),userList.get(x).getUserName(),userList.get(x).getUserLevel(),userList.get(x).getPassword(), userList.get(x).getAddress(),userList.get(x).getEmail(), userList.get(x).getPhNumber()});
        }

    }
    public static void main(String[] args) {
        ManageUser manageUser = new ManageUser();
        manageUser.setVisible(true);
        manageUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
