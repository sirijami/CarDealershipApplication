package com.neu.jan17.UI;

import com.neu.jan17.data.Dealer;
import com.neu.jan17.data.Inventory;
import com.neu.jan17.data.SortInventory;
import com.neu.jan17.data.Vehicle;
import com.neu.jan17.search.SearchTool;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CustomerVehicleSearchScreenData extends JFrame{

    protected JPanel leftPanel, vehicleDetailsPanel, topPanel, vehicleDetailsPane;
    protected JButton homeButton, viewDetails, searchButton, clearFilters;
    protected JLabel topPicture, modelFilterLabel, yearFilterLabel, makeFilterLabel, priceFilterLabel,
                        bodyTypeFilterLabel,categoryFilterLabel, sortLabel, vehicleImageLabel, vehicleModel, vehicleMake,
                        vehicleBodyType, vehiclePrice, vehicleCategory, vehicleYear,searchVehicle ;
    protected JComboBox modelFilter, yearFilter, makeFilter, priceFilter, bodyTypeFilter, categoryFilter, sort;
    protected JTextField searchBar;
    protected JScrollPane scrollPane;
    protected Container container;
    protected SearchTool searchTool;
    protected Inventory inventory;
    protected List<Vehicle> vehicleResult;
    protected Set<String> modelItems = new TreeSet<>(),
            makeItems = new TreeSet<>(),
            bodyTypeItems = new TreeSet<>(),
            yearItems = new TreeSet<>(),
            categoryItems = new TreeSet<>()
    ;
    protected Set<Float> priceItems = new TreeSet<>();
    protected boolean listenerFlag = true;

    protected String[] priceFilterItems;

    protected SortInventory sortInventory;
    protected Dealer dealer = new Dealer();




    protected CustomerVehicleSearchScreenData(){

        vehicleResult = new ArrayList<>();
        container = this.getContentPane();
        inventory = new Inventory();


        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel = new JPanel();
        vehicleDetailsPanel = new JPanel();


        this.setSize(1920,1080);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }


}
