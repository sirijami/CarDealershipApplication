package com.neu.jan17.UI;

import javax.swing.*;
import com.neu.jan17.data.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class DealerChooseUI extends JFrame implements ActionListener {

    private JPanel bottomPanel;
    private JLabel headline, dealerNameLabel;
    private JComboBox dealerItem;
    private JButton selectDealer;
    private static String getDealerID = null;
    private JPanel dealerChooseUI;

    public DealerChooseUI() {
        setLayout(new BorderLayout());
        URL imgPathUrl = getClass().getResource("Car.jpg");
        JLabel background = new JLabel(new ImageIcon(imgPathUrl.getPath()));
        add(background);
        background.setLayout(new FlowLayout());

        DealerData dd = new DealerData();
        String[] dealerID = new String[dd.getDealersData().length];
        for (int i = 0; i < dd.getDealersData().length; i++) {
            dealerID[i] = dd.getDealersData()[i].getId().substring(5, dd.getDealersData()[i].getId().length());
        }
        
        dealerItem = new JComboBox(dealerID);
        headline = new JLabel("Dealer Choose");

        dealerNameLabel = new JLabel("Choose your dealer:");
        Font font1 = new Font("SansSerif", Font.BOLD, 22);

        dealerNameLabel.setFont(font1);
        JButton selectDealer = new JButton("CONFIRM");
        selectDealer.setPreferredSize(new Dimension(100,50));
        JButton backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(100,50));
        backButton.addActionListener(this);
        backButton.setActionCommand("BACK");
        selectDealer.addActionListener(this);
        selectDealer.setActionCommand("CONFIRM");


        JPanel bottomPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel footerPanel = new JPanel();
        middlePanel.add(dealerNameLabel);
        middlePanel.add(dealerItem);
        bottomPanel.add(selectDealer);
        bottomPanel.add(backButton);
        footerPanel.add(middlePanel);
        footerPanel.add(bottomPanel);
        add(footerPanel,BorderLayout.NORTH);

        setSize(1500,1500);
        setVisible(true);

    }


    public String getDealerID(){
        return getDealerID;
    }

    public static void main(String[] args) {
        DealerChooseUI dealerChooseUI = new DealerChooseUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton backButton = (JButton) e.getSource();
        JButton selectDealer = (JButton)e.getSource();
        if (e.getActionCommand().equals("BACK")) {
            com.neu.jan17.UI.MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        } else if (e.getActionCommand().equals("CONFIRM")) {
            getDealerID = "gmps-" + dealerItem.getSelectedItem();
            Dealer dealer = new Dealer();
            dealer.setId(getDealerID());
            CustomerVehicleSearchScreen customerVehicleSearchScreen = new CustomerVehicleSearchScreen(dealer);
            dispose();
        }

    }
}
