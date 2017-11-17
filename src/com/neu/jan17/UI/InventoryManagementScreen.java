package com.neu.jan17.UI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.neu.jan17.data.*;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class InventoryManagementScreen extends JFrame {

    private JPanel topPanel, midPanel, leftPanel, rightPanel, bottomPanel;
    private dealerModel dm;
    private JTable inventoryData;
    private JScrollPane inventoryPane;
    private JCheckBox cb;
    private JLabel dealerNameLabel, dealerCategory, dealerYear, dealerMake, dealerModel, dealerBodytype;
    private JComboBox dealerItem, dealerCategorys, dealerYears, dealerMakes, dealerModels, dealerBodytypes;
    private JButton selectDealer;
    private JButton searchVehicle;
    private JButton filterButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton selectAllButton;
    private JButton clearAllButton;
    private JButton deleteConfirmButton;
    private JButton cancelButton;
    private JButton cancel2Button;

    protected Vehicles ve;
    protected Vehicles backup;

    public InventoryManagementScreen() {

        createComponent();
        createLayout();
        addListener();
        init();

    }

    public Inventory getVehicle(String id) throws Exception {
        Dealer d = new Dealer();
        d.setId(id);
        InventoryManagerInterface imi = new InventoryManager(d);
        Inventory inventory = imi.getInventoryByDealerId(id);
        return inventory;
    }

    public void openAddUI() {
        new AddVehicle(this);
    }

    public void openEditUI(Vehicle vehicle, int row) {
        new AddVehicle(this, vehicle, row);
    }

    public void changeFont(Component component, Font font) {

        component.setFont(font);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                changeFont(child, font);
            }
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 120; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 400)
                width = 400;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void createComponent() {

        dealerNameLabel = new JLabel("Please choose a dealer:");
        dealerItem = new JComboBox(generateDealerInformation());

        ve = new Vehicles();
        backup = new Vehicles();
        dm = new dealerModel(ve);
        inventoryData = new JTable(dm);
        inventoryData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resizeColumnWidth(inventoryData);
        setHorizontal(inventoryData);
        inventoryPane = new JScrollPane(inventoryData);

        dealerCategory = new JLabel("Category:");
        dealerYear = new JLabel("Year:");
        dealerMake = new JLabel("Make:");
        dealerModel = new JLabel("Model:");
        dealerBodytype = new JLabel("BodyType:");

        dealerCategorys = new JComboBox<Category>(Category.values());
        dealerCategorys.insertItemAt("", 0);
        dealerCategorys.setSelectedItem("");
        dealerYears = new JComboBox();
        dealerMakes = new JComboBox();
        dealerModels = new JComboBox();
        dealerBodytypes = new JComboBox();

        selectDealer = new JButton("Confirm");
        filterButton = new JButton("Filter");
        filterButton.setEnabled(false);
        searchVehicle = new JButton("Search");
        addButton = new JButton("Add");
        addButton.setEnabled(false);
        deleteButton = new JButton("Delete");
        deleteButton.setEnabled(false);
        updateButton = new JButton("Update");
        updateButton.setEnabled(false);
        selectAllButton = new JButton("Select All");
        clearAllButton = new JButton("Clear All");
        deleteConfirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        cancel2Button = new JButton("Cancel");

        topPanel = new JPanel();
        midPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();
    }

    public void createLayout() {

        topPanel.add(dealerNameLabel);
        topPanel.add(dealerItem);
        topPanel.add(selectDealer);
        midPanel.add(inventoryPane);
        leftPanel.add(dealerCategory);
        leftPanel.add(dealerCategorys);
        leftPanel.add(dealerYear);
        leftPanel.add(dealerYears);
        leftPanel.add(dealerMake);
        leftPanel.add(dealerMakes);
        leftPanel.add(dealerModel);
        leftPanel.add(dealerModels);
        leftPanel.add(dealerBodytype);
        leftPanel.add(dealerBodytypes);
        leftPanel.add(searchVehicle);
        leftPanel.add(cancel2Button);
        rightPanel.add(filterButton);
        rightPanel.add(addButton);
        rightPanel.add(deleteButton);
        rightPanel.add(updateButton);
        bottomPanel.add(selectAllButton);
        bottomPanel.add(clearAllButton);
        bottomPanel.add(deleteConfirmButton);
        bottomPanel.add(cancelButton);

        Font f1 = new Font("Meiryo UI", Font.PLAIN, 15);
        Font f2 = new Font("Meiryo UI", Font.PLAIN, 18);
        Font f3 = new Font("Meiryo UI", Font.PLAIN, 20);
        inventoryData.setRowHeight(25);
        inventoryData.setAutoCreateRowSorter(true);
        //inventoryData.setGridColor(Color.BLUE);
        Dimension tableSize = new Dimension(800, 600);
        inventoryPane.setPreferredSize(tableSize);

        Container con = getContentPane();
        setLayout(new BorderLayout(2, 2));
        con.add("North", topPanel);
        con.add("Center", midPanel);
        con.add("East", rightPanel);
        con.add("South", bottomPanel);
        con.add("West", leftPanel);

        topPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
        midPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 20, 50));
        rightPanel.setLayout(new GridLayout(10, 1, 10, 10));
        leftPanel.setLayout(new GridLayout(12, 2, 5, 5));
        leftPanel.setVisible(false);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));
        bottomPanel.setVisible(false);

        changeFont(con, f2);
        //dealerNameLabel.setFont(f2);
        addButton.setFont(f3);
        updateButton.setFont(f3);
        filterButton.setFont(f3);
        deleteButton.setFont(f3);


        Dimension boxsize = new Dimension(150, 30);
        dealerItem.setPreferredSize(boxsize);

        Dimension comboboxSize = new Dimension(150, 30);
        dealerCategorys.setPreferredSize(comboboxSize);
        dealerYears.setPreferredSize(comboboxSize);
        dealerMakes.setPreferredSize(comboboxSize);
        dealerModels.setPreferredSize(comboboxSize);
        dealerBodytypes.setPreferredSize(comboboxSize);

        Dimension buttonSize = new Dimension(100, 50);
        searchVehicle.setPreferredSize(buttonSize);
        cancel2Button.setPreferredSize(buttonSize);
        filterButton.setPreferredSize(buttonSize);
        addButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);

        Dimension buttonSizeBtm = new Dimension(100, 40);
        selectAllButton.setPreferredSize(buttonSizeBtm);
        clearAllButton.setPreferredSize(buttonSizeBtm);
        deleteConfirmButton.setPreferredSize(buttonSizeBtm);
        cancelButton.setPreferredSize(buttonSizeBtm);
    }

    public void addListener() {

        inventoryData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                    openEditUI(ve.showData(row), row);
                } else {
                    return;
                }
            }
        });

        selectDealer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getDealerID = "";
                getDealerID += "gmps-" + dealerItem.getSelectedItem();
                try {
                    ve.removeAll();
                    for (Vehicle v : getVehicle(getDealerID).getVehicles()) {
                        ve.addData(v);
                    }
                    ve.sort();
                    filterButton.setEnabled(true);
                    addButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    updateButton.setEnabled(true);
                    repaint();
                } catch (Exception unknowne) {
                }
            }
        });

        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftPanel.setVisible(true);
                filterButton.setEnabled(false);
                updateComboBox();
                updateButton.setEnabled(false);
            }
        });

        searchVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!backup.isEmpty()) {
                    ve.addDatas(backup);
                    backup.removeAll();
                }
                Category selectCategory = Category.NEW;
                Boolean cat = true;
                if (dealerCategorys.getSelectedItem().equals("")) {
                    cat = false;
                } else {
                    selectCategory = (Category) dealerCategorys.getSelectedItem();
                }
                String selectYear = (String) dealerYears.getSelectedItem();
                String selectMake = (String) dealerMakes.getSelectedItem();
                String selectModel = (String) dealerModels.getSelectedItem();
                String selectBodytype = (String) dealerBodytypes.getSelectedItem();

                int i = 0;
                if (cat) {
                    while (i < ve.getRows()) {
                        if (ve.showData(i).getCategory().equals(selectCategory)) {
                            i++;
                        } else {
                            backup.addData(ve.showData(i));
                            ve.removeData(i);
                        }
                    }
                }
                i = 0;
                if (!selectYear.equals("")) {
                    while (i < ve.getRows()) {
                        if (ve.showData(i).getYear() == Integer.parseInt(selectYear)) {
                            i++;
                        } else {
                            backup.addData(ve.showData(i));
                            ve.removeData(i);
                        }
                    }
                }
                i = 0;
                if (!selectMake.equals("")) {
                    while (i < ve.getRows()) {
                        if (ve.showData(i).getMake().equals(selectMake)) {
                            i++;
                        } else {
                            backup.addData(ve.showData(i));
                            ve.removeData(i);
                        }
                    }
                }
                i = 0;
                if (!selectModel.equals("")) {
                    while (i < ve.getRows()) {
                        if (ve.showData(i).getModel().equals(selectModel)) {
                            i++;
                        } else {
                            backup.addData(ve.showData(i));
                            ve.removeData(i);
                        }
                    }
                }
                i = 0;
                if (!selectBodytype.equals("")) {
                    while (i < ve.getRows()) {
                        if (ve.showData(i).getBodyType().equals(selectBodytype)) {
                            i++;
                        } else {
                            backup.addData(ve.showData(i));
                            ve.removeData(i);
                        }
                    }
                }
                ve.sort();
                updateComboBox();
                repaint();

            }
        });

        cancel2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterButton.setEnabled(true);
                leftPanel.setVisible(false);
                if (!backup.isEmpty()) {
                    ve.addDatas(backup);
                    backup.removeAll();
                }
                repaint();
                updateButton.setEnabled(true);
            }
        });

        MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
                    ve.changeStatus(row);
                    repaint();
                }
            }
        };

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.setEnabled(false);
                addButton.setEnabled(false);
                updateButton.setEnabled(false);
                dm.setHeader();
                ve.clearAllStatus();
                bottomPanel.setVisible(true);
                inventoryData.addMouseListener(ml);
                repaint();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddUI();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveData();
                } catch (Exception e1) {

                }
            }
        });

        selectAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ve.selectAllStatus();
                repaint();
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ve.clearAllStatus();
                repaint();
            }
        });

        deleteConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete", JOptionPane.OK_CANCEL_OPTION);
                if (n == 0) {
                    ve.clearSelectVehicle();
                }
                deleteButton.setEnabled(true);
                addButton.setEnabled(true);
                updateButton.setEnabled(true);
                dm.setHeader();
                bottomPanel.setVisible(false);
                inventoryData.removeMouseListener(ml);
                ve.sort();
                repaint();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteButton.setEnabled(true);
                addButton.setEnabled(true);
                updateButton.setEnabled(true);
                dm.setHeader();
                repaint();
                bottomPanel.setVisible(false);
                inventoryData.removeMouseListener(ml);
            }
        });
    }

    public void updateComboBox() {
        dealerYears.removeAllItems();
        dealerMakes.removeAllItems();
        dealerModels.removeAllItems();
        dealerBodytypes.removeAllItems();
        for (int i = 0; i < filterInformationYear().length; i++) {
            dealerYears.addItem(filterInformationYear()[i]);
        }
        for (int i = 0; i < filterInformationMake().length; i++) {
            dealerMakes.addItem(filterInformationMake()[i]);
        }
        for (int i = 0; i < filterInformationModel().length; i++) {
            dealerModels.addItem(filterInformationModel()[i]);
        }
        for (int i = 0; i < filterInformationBodyType().length; i++) {
            dealerBodytypes.addItem(filterInformationBodyType()[i]);
        }
    }

    public void setHorizontal(JTable table) {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();

        r.setHorizontalAlignment(JLabel.CENTER);
        hr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.getTableHeader().setDefaultRenderer(hr);
    }

    public void saveData() throws Exception {
        ve.updateVehicle();
    }

    public void init() {

        setVisible(true);
        setSize(1200, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String[] generateDealerInformation() {

        DealerData dd = new DealerData();
        String[] dealerID = new String[dd.getDealersData().length];
        for (int i = 0; i < dd.getDealersData().length; i++) {
            dealerID[i] = dd.getDealersData()[i].getId().substring(5, dd.getDealersData()[i].getId().length());
        }

        return dealerID;

    }

    class Vehicles {

        //Vector<Vehicle> data = new Vector<>();
        Vector<Object[]> comboData = new Vector<>();

        Vehicles() {
        }

        public void sort() {
            List<Vehicle> l = new ArrayList<>();

            for (Object[] o : comboData) {
                l.add((Vehicle) o[1]);
            }

            SortInventory si = new SortInventory();
            si.sortVehiclesByCategory(l);

            comboData.removeAllElements();
            for (Vehicle v : l) {
                Object[] temp = {false, v};
                comboData.add(temp);
            }

        }

        public void addData(Vehicle vehicle) {
            Object[] temp = {false, vehicle};
            comboData.add(temp);
            //data.add(vehicle);
        }

        public int getRows() {
            return comboData.size();
        }

        public Vehicle showData(int row) {
            return (Vehicle) comboData.get(row)[1];
        }

        public void removeData(int row) {
            //data.remove(row);
            comboData.remove(row);
        }

        public void removeAll() {
            //data.removeAllElements();
            comboData.removeAllElements();
        }

        public void changeData(int row, Vehicle vehicle) {
            //data.set(row, vehicle);
            Object[] temp = {false, vehicle};
            comboData.set(row, temp);
        }

        public Object showStatus(int row) {
            return comboData.get(row)[0];
        }

        public void changeStatus(int row) {
            if (comboData.get(row)[0].equals(false)) {
                Object[] temp = {true, comboData.get(row)[1]};
                comboData.set(row, temp);
            } else {
                Object[] temp = {false, comboData.get(row)[1]};
                comboData.set(row, temp);
            }
        }

        public void selectAllStatus() {
            for (int i = 0; i < comboData.size(); i++) {
                if (comboData.get(i)[0].equals(false)) {
                    Object[] temp = {true, comboData.get(i)[1]};
                    comboData.set(i, temp);
                }
            }
        }

        public void clearAllStatus() {
            for (int i = 0; i < comboData.size(); i++) {
                if (comboData.get(i)[0].equals(true)) {
                    Object[] temp = {false, comboData.get(i)[1]};
                    comboData.set(i, temp);
                }
            }
        }

        public void clearSelectVehicle() {
            int i = 0;
            while (i < comboData.size()) {
                if (comboData.get(i)[0].equals(true)) {
                    comboData.remove(i);
                } else {
                    i++;
                }
            }
        }

        public boolean isEmpty() {
            return comboData.isEmpty();
        }

        public void addDatas(Vehicles v) {
            for (Object[] o : v.comboData) {
                addData((Vehicle) o[1]);
            }
        }

        public void updateVehicle() throws Exception {

            ArrayList<Vehicle> a = new ArrayList<>();
            Dealer d = new Dealer();
            String getDealerID = "";
            getDealerID += "gmps-" + dealerItem.getSelectedItem();
            d.setId(getDealerID);
            d.setLocation("En-US");

            InventoryManager im = new InventoryManager(d);
            for (Object[] o : comboData) {
                a.add((Vehicle) o[1]);
                im.addVehicleToInventory(d, (Vehicle) o[1]);
            }
            Inventory in = new Inventory();
            in.setDealerId(getDealerID);
            in.setVehicles(a);
            im.updateInventoryToFile(in);
        }

    }

    class dealerModel extends AbstractTableModel {

        private Vehicles vehicle;
        private String[] name = {"Id", "Category", "Year", "Make", "Model", "Bodytype", "Price"};

        dealerModel(Vehicles vehicle) {
            this.vehicle = vehicle;
        }

        public int getRowCount() {
            return vehicle.comboData.size();
        }

        public int getColumnCount() {
            return name.length;
        }

        public Object getValueAt(int row, int col) {
            Vehicle veh = vehicle.showData(row);
            if (name.length == 8) {
                if (col == 1) {
                    return veh.getId();
                } else if (col == 2) {
                    return veh.getCategory();
                } else if (col == 3) {
                    return veh.getYear();
                } else if (col == 4) {
                    return veh.getMake();
                } else if (col == 5) {
                    return veh.getModel();
                } else if (col == 6) {
                    return veh.getBodyType();
                } else if (col == 7) {
                    return veh.getPrice();
                } else {
                    return vehicle.showStatus(row);
                }
            } else {
                if (col == 0) {
                    return veh.getId();
                } else if (col == 1) {
                    return veh.getCategory();
                } else if (col == 2) {
                    return veh.getYear();
                } else if (col == 3) {
                    return veh.getMake();
                } else if (col == 4) {
                    return veh.getModel();
                } else if (col == 5) {
                    return veh.getBodyType();
                } else {
                    return veh.getPrice();
                }
            }
        }

        public String getColumnName(int colIndex) {
            return name[colIndex];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            return false;
        }

        public void setHeader() {
            if (name.length == 7) {
                String[] temp = {"Status", "Id", "Category", "Year", "Make", "Model", "Bodytype", "Price"};
                name = temp;
                inventoryData.getColumnModel().getColumn(0).setHeaderValue("Status");
                inventoryData.getColumnModel().getColumn(1).setHeaderValue("Id");
                inventoryData.getColumnModel().getColumn(2).setHeaderValue("Category");
                inventoryData.getColumnModel().getColumn(3).setHeaderValue("Year");
                inventoryData.getColumnModel().getColumn(4).setHeaderValue("Make");
                inventoryData.getColumnModel().getColumn(5).setHeaderValue("Model");
                inventoryData.getColumnModel().getColumn(6).setHeaderValue("Bodytype");
            } else {
                String[] temp = {"Id", "Category", "Year", "Make", "Model", "Bodytype", "Price"};
                name = temp;
                inventoryData.getColumnModel().getColumn(0).setHeaderValue("Id");
                inventoryData.getColumnModel().getColumn(1).setHeaderValue("Category");
                inventoryData.getColumnModel().getColumn(2).setHeaderValue("Year");
                inventoryData.getColumnModel().getColumn(3).setHeaderValue("Make");
                inventoryData.getColumnModel().getColumn(4).setHeaderValue("Model");
                inventoryData.getColumnModel().getColumn(5).setHeaderValue("Bodytype");
                inventoryData.getColumnModel().getColumn(6).setHeaderValue("Price");
            }
        }
    }

    public String[] filterInformationYear() {
        ArrayList<Integer> info = new ArrayList<>();

        for (int i = 0; i < ve.getRows(); i++) {
            if (ve.showData(i).getYear() != null) {
                if (!info.contains(ve.showData(i).getYear())) {
                    info.add(ve.showData(i).getYear());
                }
            }
        }
        Collections.sort(info);
        String[] finalInfo = new String[info.size()];
        finalInfo[0] = "";
        for (int i = 1; i < info.size(); i++) {
            finalInfo[i] = String.valueOf(info.get(i - 1));
        }

        return finalInfo;
    }

    public String[] filterInformationMake() {
        ArrayList<String> info = new ArrayList<>();

        for (int i = 0; i < ve.getRows(); i++) {
            if (ve.showData(i).getMake() != null) {
                if (!info.contains(ve.showData(i).getMake())) {
                    info.add(ve.showData(i).getMake());
                }
            }
        }
        Collections.sort(info);
        String[] finalInfo = new String[info.size()];
        finalInfo[0] = "";
        for (int i = 1; i < info.size(); i++) {
            finalInfo[i] = info.get(i - 1);
        }

        return finalInfo;
    }

    public String[] filterInformationModel() {
        ArrayList<String> info = new ArrayList<>();

        for (int i = 0; i < ve.getRows(); i++) {
            if (ve.showData(i).getModel() != null) {
                if (!info.contains(ve.showData(i).getModel())) {
                    info.add(ve.showData(i).getModel());
                }
            }
        }
        Collections.sort(info);
        String[] finalInfo = new String[info.size()];
        finalInfo[0] = "";
        for (int i = 1; i < info.size(); i++) {
            finalInfo[i] = info.get(i - 1);
        }

        return finalInfo;
    }

    public String[] filterInformationBodyType() {
        ArrayList<String> info = new ArrayList<>();

        for (int i = 0; i < ve.getRows(); i++) {
            if (ve.showData(i).getBodyType() != null) {
                if (!info.contains(ve.showData(i).getBodyType())) {
                    info.add(ve.showData(i).getBodyType());
                }
            }
        }
        Collections.sort(info);
        String[] finalInfo = new String[info.size()];
        finalInfo[0] = "";
        for (int i = 1; i < info.size(); i++) {
            finalInfo[i] = info.get(i - 1);
        }

        return finalInfo;
    }

    public static void main(String[] args) {

        new InventoryManagementScreen();
    }
}
