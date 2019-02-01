package GUI.Items;

import DAO.StoreLocationDAO;
import DAO.StoreLocationDAOImpl;
import DTO.StoreLocation;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AddEditStoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField storeField;
    private JPanel idPanel;
    private JTextField idField;

    private StoreLocationDAO storeLocationDAO = new StoreLocationDAOImpl();
    private List<StoreLocation> storeLocationList;

    public AddEditStoreDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        idPanel.setVisible(false);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStore();
            }
        });

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

        buttonOK.setText("Add");



    }
    public AddEditStoreDialog(Integer storeId) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        storeLocationList= storeLocationDAO.getStoreLocationById(storeId);

        idField.setText(String.valueOf(storeLocationList.get(0).getStoreId()));
        storeField.setText(storeLocationList.get(0).getStorePlace());

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editStore(storeId);
            }
        });

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

        buttonOK.setText("Update");



    }

    private void addStore() {
        // add your code here
        if (storeField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else
        {
            storeLocationDAO.insertStoreLocation(storeField.getText());
            dispose();
        }
    }

    private void editStore(Integer editId){
        if (storeField.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Enter all the Field");
        }
        else
        {
            storeLocationDAO.updateStoreLocation(editId, storeField.getText());
            dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        //AddEditStoreDialog dialog = new AddEditStoreDialog();
        //dialog.pack();
        //dialog.setVisible(true);
        //System.exit(0);
    }
}
