package com.neu.jan17.UI;

import com.neu.jan17.data.Util;

import javax.swing.*;

public abstract class VehicleDetailsViewerData {

    protected JLabel pictureLabel, priceLabel, makeLabel, yearLabel, trimLabel,
            categoryLabel, vehicleIDLabel, modelLabel, bodyTypeLabel,
            dealerIDLabel, dealerLocationLabel, dealerURLLabel,
            specificationsLabel, dealerInformationLabel;


    protected JButton backButton, placeOrder;

    protected JFrame mainFrame;



    protected void createFrame() {

        mainFrame = new JFrame("Vehicle Details");

        mainFrame.setSize(1920,1080);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents();
        createLayout();
        addListeners();

        mainFrame.setVisible(true);


    }

    protected abstract void addListeners();

    protected abstract void createLayout();

    protected abstract void createComponents();


    // setters for the components
    public void setMakeLabel(String make){
        makeLabel.setText(make);
    }

    public void setYearLabel(String year){
        yearLabel.setText(year);
    }

    public void setTrimLabel(String trim){
        trimLabel.setText(trim);
    }

    public void setCategoryLabel(String category){
        categoryLabel.setText(category);
    }

    public void setVehicleIDLabel(String vehicleID){
        vehicleIDLabel.setText(vehicleID);
    }

    public void setBodyTypeLabel(String bodyType){
        bodyTypeLabel.setText(bodyType);
    }

    public void setModelLabel(String model){
        modelLabel.setText(model);
    }

    public void setPriceLabel(String price){
        priceLabel.setText(price);
    }

    public void setPictureLabel(String url){
        try {
            pictureLabel.setIcon(Util.getImageFromUrl(url));
        }catch (Exception e){
            pictureLabel.setIcon(new ImageIcon("data\\VehicleDetailsNoImage.jpg"));
        }

    }

    public void setDealerLocationLabel(String dealerName){
        dealerLocationLabel.setText(dealerName);
    }

    public void setDealerIDLabel(String dealerID){
        dealerIDLabel.setText(dealerID);
    }

    public void setDealerURLLabel(String dealerURL){
        dealerURLLabel.setText(dealerURL);
    }

}
