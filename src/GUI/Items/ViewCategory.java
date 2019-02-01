package GUI.Items;

import DAO.CategoryDAO;
import DAO.CategoryDAOImpl;
import DTO.Category;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ViewCategory extends JFrame{
    private JFrame frame;
    private JScrollPane tablePane;
    private JTable categoryTable;
    private JButton editButton;
    private JPanel categoryPanel;
    private JButton addButton;
    private JButton cancelButton;
    private DefaultTableModel defaultTableModel = new DefaultTableModel();

    private CategoryDAO categoryDAO = new CategoryDAOImpl();
    private List<Category> categoryList;
    private Integer categoryId;

    public ViewCategory(){
        frame = this;
        editButton.setEnabled(false);

        categoryTable = new JTable(defaultTableModel);
        tablePane = new JScrollPane(categoryTable);

        defaultTableModel.addColumn("ID");
        defaultTableModel.addColumn("Category");
        addDataToTable();

        frame.add(categoryPanel);
        categoryPanel.add(tablePane);
        frame.setTitle("Category List");
        frame.setVisible(true);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);

        categoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row =  categoryTable.getSelectedRow();
                if(row == -1)
                {
                    editButton.setEnabled(false);
                }
                else
                {
                    editButton.setEnabled(true);
                    categoryId = Integer.valueOf(categoryTable.getValueAt(categoryTable.getSelectedRow(),0).toString());
                    System.out.println(categoryId);
                }
            }
        });

        /**
         * Button Listener
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditCategoryDialog addEditCategoryDialog = new AddEditCategoryDialog();
                addEditCategoryDialog.setTitle("Add Category");
                addEditCategoryDialog.pack();
                addEditCategoryDialog.setLocationRelativeTo(frame);
                addEditCategoryDialog.setVisible(true);
                addEditCategoryDialog.addWindowListener(new WindowAdapter() {
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
                AddEditCategoryDialog addEditCategoryDialog = new AddEditCategoryDialog(categoryId);
                addEditCategoryDialog.setTitle("Edit Category");
                addEditCategoryDialog.pack();
                addEditCategoryDialog.setLocationRelativeTo(frame);
                addEditCategoryDialog.setVisible(true);
                addEditCategoryDialog.addWindowListener(new WindowAdapter() {
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
                frame.dispose();
            }
        });
    }

    public void addDataToTable(){
        defaultTableModel.setRowCount(0);
        categoryList = categoryDAO.getCategoryList();
        for(int x= 0; x<categoryList.size(); x++)
        {
            defaultTableModel.addRow(new Object[]{categoryList.get(x).getCategoryId(), categoryList.get(x).getCategoryName()});
        }
    }

    public static void main(String[] args) {
        ViewCategory viewCategory = new ViewCategory();
        viewCategory.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
