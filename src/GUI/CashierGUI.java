package GUI;

import GUI.Items.ViewItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CashierGUI extends JFrame {

    private JMenuBar jMBar = new JMenuBar();

    public CashierGUI(Integer userId, Integer userLevel){

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        setJMenuBar(jMBar);
        setVisible(true);

        JMenu item_menu = new JMenu("Items");
        jMBar.add(item_menu);

        JMenuItem item_itemsList = new JMenuItem("Items List");
        item_menu.add(item_itemsList);

        item_itemsList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewItem viewItem = new ViewItem(userId, userLevel);
            }
        });

        this.setVisible(true);
    }
}
