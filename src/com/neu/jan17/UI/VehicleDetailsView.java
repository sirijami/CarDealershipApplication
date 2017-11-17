package com.neu.jan17.UI;

import com.neu.jan17.data.Dealer;
import com.neu.jan17.data.Util;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class VehicleDetailsView extends VehicleDetailsViewerData{

    private Dealer dealer;

    public VehicleDetailsView(Dealer dealer){
        this.dealer = dealer;
        Util.setUIFont(new FontUIResource("Arial",Font.PLAIN,20));
        createFrame();

    }

    protected void createComponents() {
        pictureLabel = new JLabel();
        priceLabel = new JLabel("PRICE");
        makeLabel = new JLabel("MAKE :");
        yearLabel = new JLabel("YEAR :");
        trimLabel = new JLabel("TRIM :");
        categoryLabel = new JLabel("CATEGORY :");
        vehicleIDLabel = new JLabel("VEHICLE ID :");
        modelLabel = new JLabel("MODEL :");
        bodyTypeLabel = new JLabel("BODY TYPE :");
        dealerIDLabel = new JLabel("DEALER ID :");
        dealerLocationLabel = new JLabel("LOCATION :");
        dealerURLLabel = new JLabel("URL :");

        dealerInformationLabel = new JLabel("DEALER INFORMATION");
        dealerInformationLabel.setFont(new Font("",Font.BOLD,30));

        specificationsLabel = new JLabel("SPECIFICATIONS");
        specificationsLabel.setFont(new Font("",Font.BOLD,30));

        backButton = new JButton("Go Back to search");
        backButton.setPreferredSize(new Dimension(500,30));
        backButton.setFont(new Font("Arial",Font.PLAIN,20));
        backButton.setFocusPainted(false);

        placeOrder = new JButton("Place Order");
        placeOrder.setPreferredSize(new Dimension(500,50));
        placeOrder.setFont(new Font("Arial",Font.BOLD,30));
        placeOrder.setBackground(Color.RED);
        placeOrder.setForeground(Color.WHITE);
        placeOrder.setFocusPainted(false);



    }

    protected void createLayout() {

        Container container = mainFrame.getContentPane();

        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.DARK_GRAY);
        backPanel.add(backButton,BorderLayout.CENTER);


        JPanel placeOrderPanel = new JPanel();
        placeOrderPanel.setLayout(new BoxLayout(placeOrderPanel,BoxLayout.Y_AXIS));
        placeOrderPanel.add(Box.createVerticalGlue());
        placeOrderPanel.add(placeOrder);
        placeOrderPanel.add(Box.createRigidArea(new Dimension(0,40)));
        placeOrderPanel.add(priceLabel);


        JPanel picturePanel = new JPanel();
        JPanel box = new JPanel(new GridBagLayout());
        box.setMaximumSize(new Dimension(500,500));
        box.setPreferredSize(new Dimension(450,450));
        box.add(pictureLabel);
        picturePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        picturePanel.add(Box.createRigidArea(new Dimension(50,0)));
        picturePanel.add(box);
        picturePanel.add(Box.createRigidArea(new Dimension(550,0)));
        picturePanel.add(placeOrderPanel);


        JPanel viInfoPanel = new JPanel();
        viInfoPanel.setBackground(Color.cyan);
        viInfoPanel.setPreferredSize(new Dimension(1800,100));
        viInfoPanel.add(Box.createRigidArea(new Dimension(0,100)));
        viInfoPanel.add(specificationsLabel);


        JPanel viPanel = new JPanel(new GridLayout(0,2));
        viPanel.setBackground(Color.white);
        viPanel.setPreferredSize(new Dimension(1700,500));
        viPanel.add(makeLabel);
        viPanel.add(categoryLabel);
        viPanel.add(yearLabel);
        viPanel.add(vehicleIDLabel);
        viPanel.add(trimLabel);
        viPanel.add(modelLabel);
        viPanel.add(bodyTypeLabel);

        JPanel vehicleFinalPanel = new JPanel(new BorderLayout());
        vehicleFinalPanel.setBackground(Color.white);
        vehicleFinalPanel.add(Box.createRigidArea(new Dimension(50,0)),BorderLayout.WEST);
        vehicleFinalPanel.add(Box.createRigidArea(new Dimension(50,0)),BorderLayout.EAST);
        vehicleFinalPanel.add(viPanel);


        JPanel dealerInfoPanel = new JPanel();
        dealerInfoPanel.setBackground(Color.cyan);
        dealerInfoPanel.setPreferredSize(new Dimension(1800,100));
        dealerInfoPanel.add(Box.createRigidArea(new Dimension(0,100)));
        dealerInfoPanel.add(dealerInformationLabel);

        JPanel dealerPanel = new JPanel(new GridLayout(0,2));
        dealerPanel.setBackground(Color.white);
        dealerPanel.setPreferredSize(new Dimension(1700, 200));
        dealerPanel.add(dealerIDLabel);
        dealerPanel.add(dealerLocationLabel);
        dealerPanel.add(dealerURLLabel);

        JPanel dealerFinalPanel = new JPanel(new BorderLayout());
        dealerFinalPanel.setBackground(Color.white);
        dealerFinalPanel.add(Box.createRigidArea(new Dimension(50,0)),BorderLayout.EAST);
        dealerFinalPanel.add(Box.createRigidArea(new Dimension(50,0)),BorderLayout.WEST);
        dealerFinalPanel.add(dealerPanel);


        JPanel allComponentsPanel = new JPanel();
        allComponentsPanel.setLayout(new BoxLayout(allComponentsPanel,BoxLayout.PAGE_AXIS));
        allComponentsPanel.setPreferredSize(new Dimension(1800,1540));
        allComponentsPanel.setBackground(Color.DARK_GRAY);
        allComponentsPanel.add(picturePanel);
        allComponentsPanel.add(viInfoPanel);
        allComponentsPanel.add(vehicleFinalPanel);
        allComponentsPanel.add(dealerInfoPanel);
        allComponentsPanel.add(dealerFinalPanel);
        allComponentsPanel.add(backPanel);

        JScrollPane scrollPane = new JScrollPane(allComponentsPanel);
        container.add(scrollPane);

    }

    protected void addListeners() {
        placeOrder.addActionListener(e -> {
            int input = JOptionPane.showConfirmDialog(mainFrame,"We are placing your order",
                    "Placing Order",JOptionPane.OK_CANCEL_OPTION);
            if(input== JOptionPane.OK_OPTION){
                JOptionPane.showMessageDialog(mainFrame,"Order Placed!!", "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {

            mainFrame.dispose();
            new CustomerVehicleSearchScreen(dealer);
        });

    }





}
