package com.neu.jan17.data;

import java.io.*;
import java.util.*;

/**
 * Class that implements InventoryManagerInterface and provides methods to read and modify inventory information
 */
public class InventoryManager implements InventoryManagerInterface {
    private Map<String , Inventory> inventoryMap = new HashMap<>();
    private Map<String, File> dealerFolderPathMap = new HashMap<>();


    public InventoryManager(Dealer dealer) throws FileNotFoundException {
        this.mapFilesToDealers("data");
        inventoryMap.put(dealer.getId(),getInventoryFromFile(dealerFolderPathMap.get(dealer.getId())));
    }

    /**
     * Read Inventory files from the data folder (except the dealers file)
     *
     * @param folderPath path to the directory of your data folder
     * @throws FileNotFoundException throws exception if there are no files in given directory
     */
    private void mapFilesToDealers(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile()) {
                if (fileEntry.getName().contains("gmps")) {
                    dealerFolderPathMap.put(fileEntry.getName(),fileEntry);
                }
            }
        }
    }

    /**
     * Read each Inventory from the file and construct Inventory Objects
     *
     * @param fileEntry file containing input
     * @return returns inventory
     * @throws FileNotFoundException exception is thrown if no file is found
     */
    private Inventory getInventoryFromFile(File fileEntry) throws FileNotFoundException {
        Inventory inventory = new Inventory();
        FileReader fileReader = new FileReader(fileEntry);
        Scanner fileIn = new Scanner(fileReader);
        fileIn.nextLine(); // skip the first line of each Inventory file
        String dealerId = "";
        Collection<Vehicle> vehicles = new ArrayList<>();
        while (fileIn.hasNextLine()) {
            String[] vehicleInfo = fileIn.nextLine().split("~");
            if (dealerId.isEmpty()) {
                dealerId = vehicleInfo[1];
            }
            Vehicle v = new Vehicle();
            v.setAllDetails(vehicleInfo[0],vehicleInfo[1],Category.valueOf(vehicleInfo[2].toUpperCase()),
                    Integer.parseInt(vehicleInfo[3]),vehicleInfo[4],vehicleInfo[5],vehicleInfo[6],vehicleInfo[7],
                    Float.parseFloat(vehicleInfo[8]),vehicleInfo[9]);
            vehicles.add(v);
        }
        inventory.setDealerId(dealerId);
        inventory.setVehicles(vehicles);
        fileIn.close();

        return inventory;
    }

    /**
     * {@inheritDoc}
     */
    public void addVehicleToInventory(Dealer dealer, Vehicle vehicle) throws IllegalArgumentException {
        Inventory targetInventory = inventoryMap.get(dealer.getId());
        Vehicle vehicleAdded = getVehicleById(targetInventory.getVehicles(), vehicle);
        if (vehicleAdded == null) {
            targetInventory.getVehicles().add(vehicle);
        } else {
            throw new IllegalArgumentException("Vehicle with the same id is already in the Inventory.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeVehicleFromInventory(Dealer dealer, Vehicle vehicle) {
        Collection<Vehicle> targetVehicles = inventoryMap.get(dealer.getId()).getVehicles();
        Vehicle vehicleToRemove = getVehicleById(targetVehicles, vehicle);
        if (vehicleToRemove != null) {
            targetVehicles.remove(vehicle);
        } else {
            throw new IllegalArgumentException(
                    String.format("Can not find the vehicle [%s] of dealer [%s]: ", vehicle.getId(), dealer.getId()));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Inventory getInventoryByDealerId(String dealerId) {
        return inventoryMap.get(dealerId);
    }

    /**
     * Get a vehicle from a collection by vehicle id
     *
     * @param vehicles collection of vehicleResult
     * @param vehicle vehicle object
     * @return returns a vehicle
     */
    private Vehicle getVehicleById(Collection<Vehicle> vehicles, Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (vehicle.getId().equals(v.getId())) {
                return v;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void updateInventoryToFile(Inventory inventory) throws Exception {

        String vehicleDetails = "id~webId~category~year~make~model~trim~type~price\n";
        StringBuilder stringBuilder = new StringBuilder();
        for (Vehicle vehicle : inventory.getVehicles()) {
            stringBuilder.append(vehicle.toString()).append("\n");
        }
        vehicleDetails = vehicleDetails+ stringBuilder.toString();
        // dealerId is the Inventory file name
        String inventoryFilePath = dealerFolderPathMap.get(inventory.getDealerId()).getAbsolutePath();
        Util.writeToFile(inventoryFilePath, vehicleDetails,false);
    }

    /**
     *
     * @return map of the whole inventory
     */
    public Map<String, Inventory> getInventoryMap() {
        return inventoryMap;
    }



}
