package com.neu.jan17.UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.neu.jan17.data.Dealer;
import com.neu.jan17.data.DealerData;
import com.neu.jan17.data.UrlHandle;

// Second Page
// After clicking Dealers Button on the MainPage.
class DealerInfoTable extends JFrame implements ActionListener {
    private JTable table;
    private Dealer dealer = new Dealer();
    private final UrlHandle urlHandle = new UrlHandle();
    private JPanel MainPage = new JPanel();
    private final JPanel dealerInfoTable = new JPanel();


    public DealerInfoTable() {
        setSize(700, 700);
        setLayout(new FlowLayout());
        String[] columnNames = {"NAME", "LANGUAGE", "URL"};
        DealerData dealerData = new DealerData();
        Dealer[] dealersInfo = dealerData.getDealersData();
        String[][] dealersInfoArr = new String[dealersInfo.length][3];
        for(int i = 0; i < dealersInfo.length; i++){
            dealersInfoArr[i][0] = dealersInfo[i].getId();
            dealersInfoArr[i][1] = dealersInfo[i].getLocation();
            dealersInfoArr[i][2] = dealersInfo[i].getUrl();
        }
        JLabel label = new JLabel("You could find the most sutiable dealer for you here!");
        table = new JTable();
        table = new JTable(dealersInfoArr, columnNames);
        JButton backButton = new JButton("Back");
        JButton manageButton = new JButton("Manage Vehicles");

        backButton.addActionListener(this);
        backButton.setActionCommand("Back");
        manageButton.addActionListener(this);
        manageButton.setActionCommand("Manage");

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(new Point(e.getX(), e.getY()));
                int col = table.columnAtPoint(new Point(e.getX(), e.getY()));
                String url = (String) table.getModel().getValueAt(row, col);

                try {
                    URL res = new URL("http://" + url);
                    urlHandle.openWebpage(res);
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int col = table.columnAtPoint(new Point(e.getX(), e.getY()));
                if (col == 2) {
                    table.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int col = table.columnAtPoint(new Point(e.getX(), e.getY()));
                if (col != 2) {
                    table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        add(new JScrollPane(table));

        table.getColumnModel().getColumn(2).setCellRenderer(new TableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, final Object value, boolean arg2,
                                                           boolean arg3, int arg4, int arg5) {
                return new JLabel("<html><a href=\"" + value + "\">" + value + "</a>");
            }
        });

        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(label);
        add(scrollPane);
        JPanel bottomButton = new JPanel();
        bottomButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomButton.add(backButton);
        bottomButton.add(manageButton);
        add(bottomButton,BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        JButton backButton = (JButton) e.getSource();
        JButton manageButton = (JButton) e.getSource();
        if (e.getActionCommand().equals("Back")) {
            // redirect to the main page
            dealerInfoTable.setVisible(false);
            dispose();
            com.neu.jan17.UI.MainPage mainPage = new MainPage();


        } else if (e.getActionCommand().equals("Manage")) {
            // redirect to team 4's page
            dispose();
            InventoryManagementScreen inventoryManagementScreen = new InventoryManagementScreen();
             inventoryManagementScreen.setVisible(true);
            
        }
    }

}
