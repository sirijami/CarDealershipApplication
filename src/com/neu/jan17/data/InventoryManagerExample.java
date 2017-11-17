package com.neu.jan17.data;

import java.io.FileNotFoundException;

public class InventoryManagerExample {
    /**
     * This method shows how to interact with InventoryManagerInterface.
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws Exception {
        // InventoryManagerInterface is an interface, InventoryManager implements InventoryManagerInterface
        // The constructor of InventoryManager takes the path to the data folder, and loads all inventory from files
        Dealer d = new Dealer();
        d.setId("gmps-aj-dohmann");
        InventoryManagerInterface inventoryManager = new InventoryManager(d);

        // Get an inventory by its dealer id
        Inventory inventory = inventoryManager.getInventoryByDealerId("gmps-aj-dohmann");

        // Create a new vehicle and add to an inventory
        Vehicle vehicle = new Vehicle();
        vehicle.setAllDetails("2345","gmps-aj-dohmann",  Category.NEW,2014, "Chevrolet", "Silverado", "Crew Cab", "Short TRUCK", 37692.0f,"");

        // Create a new Dealer and add to an inventory

        Dealer dealer = new Dealer();
        dealer.setId("gmps-aj-dohmann");
        dealer.setLocation("En-US");
        try {
            inventoryManager.addVehicleToInventory(dealer, vehicle);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // Remove a vehicle from an inventory given dealer id and vehicle id
        try {
            inventoryManager.removeVehicleFromInventory(dealer, vehicle);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        // Save changes to an inventory to its file
        inventoryManager.updateInventoryToFile(inventory);
    }
}
