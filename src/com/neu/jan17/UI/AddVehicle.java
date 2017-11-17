package com.neu.jan17.UI;

import javax.swing.*;

import com.neu.jan17.data.Category;
import com.neu.jan17.data.Inventory;
import com.neu.jan17.data.Vehicle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVehicle extends JFrame {

    private Category category;
    private JPanel panel, headPanel, bottomPanel;
    private JLabel headline, idLabel, webIdLabel, categoryLabel, yearLabel, makeLabel, modelLabel, trimLabel, typeLabel,
            priceLabel, photoLabel;
    private JComboBox<Category> categoryCB;
    private JTextField idText, webIdText, yearText, makeText, modelText, trimText, typeText, priceText, photoText;
    private JButton clearButton, confirmButton;

    public AddVehicle(InventoryManagementScreen parent) {
    	initialize(parent);
        addComponents();
        createLayout();
        display();
    }

    public AddVehicle(InventoryManagementScreen parent, Vehicle vehicle, int row) {
    	initialize(parent, vehicle, row);
        addComponents();
        createLayout();
        display();
    }

    public void createComponents(){
    	panel = new JPanel();
        headPanel = new JPanel();
        bottomPanel = new JPanel();
        headline = new JLabel("ADD VEHICLE");
        idLabel = new JLabel("ID");
        webIdLabel = new JLabel("WebId");
        categoryLabel = new JLabel("Category");
        yearLabel = new JLabel("Year");
        makeLabel = new JLabel("Make");
        modelLabel = new JLabel("Model");
        trimLabel = new JLabel("Trim");
        typeLabel = new JLabel("BodyType");
        priceLabel = new JLabel("Price");
        photoLabel = new JLabel("Photo URL");
        idText = new JTextField(5);
        webIdText = new JTextField(5);
        yearText = new JTextField(5);
        makeText = new JTextField(5);
        modelText = new JTextField(5);
        trimText = new JTextField(5);
        typeText = new JTextField(5);
        priceText = new JTextField(5);
        photoText = new JTextField(5);
        categoryCB = new JComboBox<Category>(category.values());
    }
    
    public void setDefalt(Vehicle vehicle){
    	idText.setText(vehicle.getId());
        webIdText.setText(vehicle.getWebId());
        yearText.setText(String.valueOf(vehicle.getYear()));
        makeText.setText(vehicle.getMake());
        modelText.setText(vehicle.getModel());
        trimText.setText(vehicle.getTrim());
        typeText.setText(vehicle.getBodyType());
        priceText.setText(String.valueOf(vehicle.getPrice()));
        photoText.setText(vehicle.getPhoto());
        categoryCB.setSelectedItem(vehicle.getCategory());
    }
    
    public void initialize(InventoryManagementScreen parent) {

        createComponents();
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idText.setText("");
                webIdText.setText("");
                categoryCB.setSelectedItem((Category) Category.NEW);
                yearText.setText("");
                makeText.setText("");
                modelText.setText("");
                trimText.setText("");
                typeText.setText("");
                priceText.setText("");
                photoText.setText("");
            }
        });
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicle v = new Vehicle();
                v.setAllDetails(idText.getText(), webIdText.getText(), (Category) categoryCB.getSelectedItem(), Integer.parseInt(yearText.getText()), makeText.getText(), modelText.getText(), trimText.getText(), typeText.getText(), Float.parseFloat(priceText.getText()), photoText.getText());
                parent.ve.addData(v);
                parent.repaint();
                dispose();
            }
        });
    }

    public void initialize(InventoryManagementScreen parent, Vehicle vehicle, int row) {

    	createComponents();
        setDefalt(vehicle);

        clearButton = new JButton("Delete");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.ve.removeData(row);
                parent.repaint();
                dispose();
            }
        });
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicle v = new Vehicle();
                v.setAllDetails(idText.getText(), webIdText.getText(), (Category) categoryCB.getSelectedItem(), Integer.parseInt(yearText.getText()), makeText.getText(), modelText.getText(), trimText.getText(), typeText.getText(), Float.parseFloat(priceText.getText()), photoText.getText());
                parent.ve.changeData(row, v);
                parent.repaint();
                dispose();
            }
        });
    }

    public void addComponents() {
    	
        headPanel.add(headline);
        panel.add(idLabel);
        panel.add(idText);
        panel.add(webIdLabel);
        panel.add(webIdText);
        panel.add(categoryLabel);
        panel.add(categoryCB);
        panel.add(yearLabel);
        panel.add(yearText);
        panel.add(makeLabel);
        panel.add(makeText);
        panel.add(modelLabel);
        panel.add(modelText);
        panel.add(trimLabel);
        panel.add(trimText);
        panel.add(typeLabel);
        panel.add(typeText);
        panel.add(priceLabel);
        panel.add(priceText);
        panel.add(photoLabel);
        panel.add(photoText);
        bottomPanel.add(clearButton);
        bottomPanel.add(confirmButton);

    }

    public void createLayout() {

        setLayout(new BorderLayout(5, 5));

        Container con = getContentPane();
        con.add("Center", panel);
        con.add("North", headPanel);
        con.add("South", bottomPanel);

        GridLayout grid = new GridLayout(0, 2);
        FlowLayout flow = new FlowLayout();
        panel.setLayout(grid);
        bottomPanel.setLayout(flow);

        panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        headPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 80, 100));

        Font f1 = new Font("Meiryo UI", Font.PLAIN, 15);
        Font f2 = new Font("Meiryo UI", Font.PLAIN, 25);
        Font f3 = new Font("Meiryo UI", Font.PLAIN, 20);

        changeFont(con, f1);
        headline.setFont(f2);
        categoryCB.setBackground(Color.WHITE);
        clearButton.setFont(f3);
        confirmButton.setFont(f3);

    }

    public void changeFont(Component component, Font font) {

        component.setFont(font);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                changeFont(child, font);
            }
        }
    }
    

    public void display() {

        setSize(700, 700);
        setVisible(true);

    }

}
