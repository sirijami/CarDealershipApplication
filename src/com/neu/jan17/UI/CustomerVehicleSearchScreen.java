package com.neu.jan17.UI;

import com.neu.jan17.data.*;
import com.neu.jan17.search.*;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class CustomerVehicleSearchScreen extends CustomerVehicleSearchScreenData
implements CustomerVehicleSearchInterface{


	public CustomerVehicleSearchScreen(Dealer dealer){
		super();
		this.dealer = dealer;
		setLayout();

	}

	@Override
	public void setRightPanel() {
		new BackgroundUICreator().execute();

	}

	@Override
	public void setLeftPanel() {


		makeFilterLabel = new JLabel("MAKE");
		modelFilterLabel = new JLabel("MODEL");
		bodyTypeFilterLabel = new JLabel("BODY TYPE");
		yearFilterLabel = new JLabel("YEAR");
		priceFilterLabel = new JLabel("PRICE");
		categoryFilterLabel = new JLabel("CATEGORY");
		searchVehicle = new JLabel("Search Vehicles");
		searchVehicle.setFont(new Font("Serif", Font.BOLD, 22));
		clearFilters = new JButton("CLEAR FILTERS");
		clearFilters.setPreferredSize(new Dimension(250,30));
		clearFilters.setMinimumSize(new Dimension(250,30));
		clearFilters.setMaximumSize(new Dimension(250,30));
		defineFilters();


		BoxLayout leftPanelLayout = new BoxLayout(leftPanel,BoxLayout.PAGE_AXIS);
		leftPanel.setLayout(leftPanelLayout);
		leftPanel.add(Box.createVerticalGlue());
		leftPanel.add(Box.createRigidArea(new Dimension(0,100)));
		leftPanel.add(searchVehicle);
		leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
		leftPanel.add(modelFilterLabel);
		leftPanel.add(modelFilter);
		leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
		leftPanel.add(makeFilterLabel);
		leftPanel.add(makeFilter);
		leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
		leftPanel.add(bodyTypeFilterLabel);
		leftPanel.add(bodyTypeFilter);
		leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
		leftPanel.add(yearFilterLabel);
		leftPanel.add(yearFilter);
		leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
		leftPanel.add(priceFilterLabel);
		leftPanel.add(priceFilter);
		leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
		leftPanel.add(categoryFilterLabel);
		leftPanel.add(categoryFilter);
		leftPanel.add(Box.createRigidArea(new Dimension(0,40)));
		leftPanel.add(clearFilters);

		leftPanel.setBorder(BorderFactory.createEmptyBorder(0,40,500,40));
		leftPanel.setBackground(new Color(127,179,213));

		leftPanel.setPreferredSize(new Dimension(300,0));
		leftPanel.setMaximumSize(new Dimension(300,0));
		leftPanel.setMinimumSize(new Dimension(300,0));

		container.add(leftPanel, BorderLayout.WEST);
	}

	@Override
	public void setTopPanel() {

		topPicture = new JLabel(new ImageIcon("data\\vehicleImage.jpg"));
		homeButton = new JButton("HOME");
		searchBar = new JTextField(50);
		searchButton = new JButton("search");
		sortLabel = new JLabel("SORT:");
		sort = new JComboBox(new String[]{"Year ascending","Year descending","Price low to high","Price high to low"});

		topPanel.add(Box.createRigidArea(new Dimension(100,0)));
		topPanel.add(topPicture);
		topPanel.add(homeButton);
		topPanel.add(Box.createRigidArea(new Dimension(100,0)));
		topPanel.add(searchBar);
		topPanel.add(Box.createRigidArea(new Dimension(10,0)));
		topPanel.add(searchButton);
		topPanel.add(Box.createRigidArea(new Dimension(300,0)));
		topPanel.add(sortLabel);
		topPanel.add(sort);
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		topPanel.setBackground(new Color(127,179,213));

		container.add(topPanel,BorderLayout.NORTH);
	}

	@Override
	public void setLayout() {

		try {
			searchTool = new SearchTool(dealer.getId());
			InventoryManager inventoryManager = new InventoryManager(dealer);
			inventory= inventoryManager.getInventoryByDealerId(dealer.getId());
			sortInventory = new SortInventory();
		}catch (Exception e){
			System.out.println("Inventory not found");
		}
		Util.setUIFont(new FontUIResource("",Font.BOLD,12));
		vehicleResult = (List<Vehicle>) inventory.getVehicles();
		setFilterItems(vehicleResult);
		setTopPanel();
		setLeftPanel();
		vehicleDetailsPane = new JPanel();
		BoxLayout layout = new BoxLayout(vehicleDetailsPane,BoxLayout.PAGE_AXIS);
		vehicleDetailsPane.setLayout(layout);
		vehicleDetailsPane.setBackground(Color.white);
		vehicleDetailsPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		scrollPane = new JScrollPane(vehicleDetailsPane);
		container.add(scrollPane);
		setRightPanel();
		addListeners();
		this.setVisible(true);
	}

	private void defineFilters() {
		makeFilter = new JComboBox(makeItems.toArray());
		
		modelFilter = new JComboBox(modelItems.toArray());
		
		yearFilter = new JComboBox(yearItems.toArray());
		
		bodyTypeFilter  = new JComboBox(bodyTypeItems.toArray());
		
		priceFilterItems = new String[]{"0-20000","20000-30000","30000-40000","40000-50000",
										"50000-60000","60000-70000",">70000"};
		
		priceFilter = new JComboBox(priceFilterItems);
		
		categoryFilter = new JComboBox(categoryItems.toArray());
	}

	private  JPanel setVehicleDetailsPanel(Vehicle vehicle){

		vehicleDetailsPanel = new JPanel();
		try {
			vehicleImageLabel = new JLabel(Util.getImageFromUrl(vehicle.getPhoto()));
		}catch (Exception e){
			vehicleImageLabel = new JLabel(new ImageIcon("data\\noImage.jpg"));
		}
		vehicleMake = new JLabel("MAKE: "+vehicle.getMake());
		vehicleModel = new JLabel("MODEL: "+ vehicle.getModel());
		vehicleCategory = new JLabel("CATEGORY: "+ vehicle.getCategory());
		vehicleBodyType = new JLabel("BODY TYPE: "+ vehicle.getBodyType());
		vehiclePrice = new JLabel("PRICE: "+ vehicle.getPrice());
		vehicleYear = new JLabel(String.valueOf(vehicle.getYear()));
		viewDetails = new JButton("VIEW DETAILS");
		viewDetails.setBackground(new Color(127,179,213));

		JPanel contentsPanel = new JPanel(new GridLayout(2,3));
		contentsPanel.add(vehicleYear);
		contentsPanel.add(vehicleCategory);
		contentsPanel.add(vehiclePrice);
		contentsPanel.add(vehicleBodyType);
		contentsPanel.add(vehicleModel);
		contentsPanel.add(vehicleMake);
		contentsPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		contentsPanel.setBackground(new Color(235,245,251));
		contentsPanel.setPreferredSize(new Dimension(1000,100));
		contentsPanel.setMaximumSize(new Dimension(1000,100));
		contentsPanel.setMinimumSize(new Dimension(1000,100));

		vehicleDetailsPanel.add(vehicleImageLabel);
		vehicleDetailsPanel.add(contentsPanel);
		vehicleDetailsPanel.add(viewDetails);

		vehicleDetailsPanel.setBackground(new Color(235,245,251));
		vehicleDetailsPanel.setBorder(BorderFactory.createEmptyBorder(2,5,2,5));
		vehicleDetailsPanel.setBorder(BorderFactory.createLineBorder(new Color(127,179,213)));

		vehicleDetailsPanel.setPreferredSize(new Dimension(1500,110));
		vehicleDetailsPanel.setMaximumSize(new Dimension(1500,110));

		viewDetails.addActionListener(e -> {new VehicleDetailsController(vehicle,dealer);this.dispose();});

		return vehicleDetailsPanel;
	}

	private void addListeners(){


		modelFilter.addItemListener(new ItemChangeListener());

		makeFilter.addItemListener(new ItemChangeListener());

		bodyTypeFilter.addItemListener(new ItemChangeListener());

		yearFilter.addItemListener(new ItemChangeListener());

		priceFilter.addItemListener(new ItemChangeListener());

		categoryFilter.addItemListener(new ItemChangeListener());

		clearFilters.addActionListener(e -> {
			vehicleResult = (List<Vehicle>) inventory.getVehicles();
			clearFilterItems();
			setFilterItems(vehicleResult);
			resetFilters();
			displayResults();

		});

		sort.addActionListener(e -> {
			if(sort.getSelectedItem().equals("Year ascending")){
				sortInventory.sortVehiclesByYearAsc(vehicleResult);
				displaySortResults();
			}else if(sort.getSelectedItem().equals("Year descending")){
				sortInventory.sortVehiclesByYearDesc(vehicleResult);
				displaySortResults();
			}else if(sort.getSelectedItem().equals("Price low to high")){
				sortInventory.sortVehiclesByPriceAsc(vehicleResult);
				displaySortResults();
			}else if(sort.getSelectedItem().equals("Price high to low")){
				sortInventory.sortVehiclesByPriceDesc(vehicleResult);
				displaySortResults();
			}
		});

		Action searchAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e)  {
				SearchResult result = searchTool.searchByKeyWord(inventory.getVehicles(),
						searchBar.getText());
				vehicleResult = result.getResult();
				clearFilterItems();
				setFilterItems(vehicleResult);
				updateFilters(new JComboBox());
				displayResults();
			}
		};

		searchButton.addActionListener(searchAction);

		searchBar.addActionListener(searchAction);

		homeButton.addActionListener(e -> {new MainPage();this.dispose();});

	}
	private JPanel setErrorMessagePanel(){
        JPanel messagePanel = new JPanel(new GridBagLayout());
        JLabel noResults = new JLabel("NO RESULTS FOUND");
        noResults.setFont(new Font("serif", Font.BOLD, 14));
        noResults.setForeground(Color.white);
        messagePanel.add(noResults);
        messagePanel.setPreferredSize(new Dimension(1600, 40));
        messagePanel.setMaximumSize(new Dimension(1600, 40));
        messagePanel.setMinimumSize(new Dimension(1600, 40));
        messagePanel.setBackground(Color.GRAY);
        return messagePanel;
    }

    private void displaySortResults() {
        vehicleDetailsPane.removeAll();
        if (vehicleResult.isEmpty()) {

            vehicleDetailsPane.add(setErrorMessagePanel());
        } else {
            vehicleResult.forEach(vehicle -> {
                vehicleDetailsPane.add(setVehicleDetailsPanel(vehicle));
                vehicleDetailsPane.add(Box.createRigidArea(new Dimension(0, 10)));
            });
        }
        vehicleDetailsPane.updateUI();
    }

    private void resetFilters(){
		makeFilter.setEnabled(true);
		modelFilter.setEnabled(true);
		bodyTypeFilter.setEnabled(true);
		priceFilter.setEnabled(true);
		categoryFilter.setEnabled(true);
		yearFilter.setEnabled(true);

		modelFilter.setModel(new DefaultComboBoxModel(modelItems.toArray()));
		makeFilter.setModel(new DefaultComboBoxModel(makeItems.toArray()));
		yearFilter.setModel(new DefaultComboBoxModel(yearItems.toArray()));
		bodyTypeFilter.setModel(new DefaultComboBoxModel(bodyTypeItems.toArray()));
		categoryFilter.setModel(new DefaultComboBoxModel(categoryItems.toArray()));
		priceFilter.setModel(new DefaultComboBoxModel(priceFilterItems));
	}

	private void updateFilters(JComboBox filter){
		if(!filter.equals(modelFilter)){
			modelFilter.setModel(new DefaultComboBoxModel(modelItems.toArray()));
		}
		if(!filter.equals(makeFilter)){
			makeFilter.setModel(new DefaultComboBoxModel(makeItems.toArray()));
		}
		if(!filter.equals(bodyTypeFilter)){
			bodyTypeFilter.setModel(new DefaultComboBoxModel(bodyTypeItems.toArray()));
		}
		if(!filter.equals(yearFilter)){
			yearFilter.setModel(new DefaultComboBoxModel(yearItems.toArray()));
		}
		if(!filter.equals(priceFilter)){
			priceFilter.setModel(new DefaultComboBoxModel<>(getPriceItems().toArray()));
		}
		if(!filter.equals(categoryFilter)){
			categoryFilter.setModel(new DefaultComboBoxModel(categoryItems.toArray()));
		}
	}

	private void clearFilterItems() {
		modelItems.clear();
		makeItems.clear();
		bodyTypeItems.clear();
		yearItems.clear();
		priceItems.clear();
		categoryItems.clear();
	}

	private void filterListener(JComboBox filter,  Object selectedItem) {

		SearchResult result = searchTool.searchByFilters(vehicleResult, getFiltersForSearchTool(filter, selectedItem));
		vehicleResult = result.getResult();
		clearFilterItems();
		setFilterItems(vehicleResult);
		updateFilters(filter);
		displayResults();

	}

	private void displayResults() {
		vehicleDetailsPane.removeAll();

		if (vehicleResult.isEmpty()) {

			vehicleDetailsPane.add(setErrorMessagePanel());
		} else {
			setRightPanel();
		}
		vehicleDetailsPane.updateUI();
	}

	private ArrayList<Filter> getFiltersForSearchTool(JComboBox filter, Object selectedItem) {

		ArrayList<Filter> filters = new ArrayList<>();

		if (filter.equals(priceFilter)) {
			String temp[] = getPriceValues((String) selectedItem);
			filters.add(new RangeFilter("price", Float.valueOf(temp[0]), Float.valueOf(temp[1])));
			getFilterName(filter);

		}else {
			filters.add(new LiteralFilter(getFilterName(filter), selectedItem));
		}

		return filters;

	}

	private String getFilterName(JComboBox filter){
		if(filter.equals(makeFilter)){
			makeFilter.setEnabled(false);
			return "make";
		}else if(filter.equals(modelFilter)){
			modelFilter.setEnabled(false);
			return "model";
		}else if(filter.equals(bodyTypeFilter)){
			bodyTypeFilter.setEnabled(false);
			return "bodyType";
		}else if(filter.equals(yearFilter)){
			yearFilter.setEnabled(false);
			return "year";
		}else if(filter.equals(priceFilter)){
			priceFilter.setEnabled(false);
			return "price";
		}else if(filter.equals(categoryFilter)){
			categoryFilter.setEnabled(false);
			return "category";
		}
		return null;
	}

	private String[] getPriceValues(String priceValue) {

		String[] prices = new String[2];

		if (priceValue.contains(">")) {
			prices[0] = "70000";
			prices[1] = String.valueOf(Double.POSITIVE_INFINITY);
		} else {
			prices = priceValue.split("-");
		}

		return prices;
	}

	private void setFilterItems(List<Vehicle> vehicles) {
		vehicles.forEach(vehicle -> {
			makeItems.add(vehicle.getMake());
			modelItems.add(vehicle.getModel());
			bodyTypeItems.add(vehicle.getBodyType());
			yearItems.add(String.valueOf(vehicle.getYear()));
			priceItems.add(vehicle.getPrice());
			categoryItems.add(vehicle.getCategory().toString());
		});
	}

	private TreeSet<String> getPriceItems(){
		TreeSet<String> result = new TreeSet<>();

		priceItems.forEach(item -> {
			if(item <= 20000){
				result.add(priceFilterItems[0]);
			}else if(item>20000 && item <= 30000){
				result.add(priceFilterItems[1]);
			}else if(item>30000 && item <= 40000){
				result.add(priceFilterItems[2]);
			}else if(item>40000 && item<= 50000){
				result.add(priceFilterItems[3]);
			}else if(item>50000 && item <= 60000){
				result.add(priceFilterItems[4]);
			}else if(item>60000 && item <=70000){
				result.add(priceFilterItems[5]);
			}else {
				result.add(priceFilterItems[6]);
			}
		});

		return result;
	}

	private class ItemChangeListener implements ItemListener {

		private void setAction(JComboBox selectedFilter, String selectedItem){
			if(selectedFilter.equals(makeFilter)){
				filterListener(makeFilter,selectedItem);
			}else if(selectedFilter.equals(modelFilter)){
				filterListener(modelFilter, selectedItem);
			}else if(selectedFilter.equals(bodyTypeFilter)){
				filterListener(bodyTypeFilter ,selectedItem);
			}else if(selectedFilter.equals(yearFilter)){
				filterListener(yearFilter, Integer.valueOf(selectedItem));
			}else if(selectedFilter.equals(priceFilter)){
				filterListener(priceFilter, selectedItem);
			}else if(selectedFilter.equals(categoryFilter)){
				filterListener(categoryFilter,Category.valueOf(selectedItem));
			}
		}
		@Override
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == ItemEvent.SELECTED && listenerFlag) {
				listenerFlag = false;
				JComboBox selectedFilter = (JComboBox) event.getSource();
				String selectedItem =  (String)event.getItem();
				setAction(selectedFilter, selectedItem);
				SwingUtilities.invokeLater(()->listenerFlag = true);
			}

		}
	}

	private class BackgroundUICreator extends SwingWorker<Void,JPanel>{

		@Override
		protected Void doInBackground() throws Exception {
			vehicleResult.forEach(vehicle -> publish(setVehicleDetailsPanel(vehicle)));
			return null;
		}

		@Override
		public void process(List<JPanel>chunks){
			chunks.forEach(jPanel -> {
			    synchronized (this) {
                    vehicleDetailsPane.add(jPanel);
                    vehicleDetailsPane.add(Box.createRigidArea(new Dimension(0, 10)));
                }
			});
			vehicleDetailsPane.revalidate();
		}
	}
	
}
