package com.neu.jan17.data;

import java.io.IOException;

public interface InventoryManagerInterface {
    /**
     * Add a new vehicle to an inventory; to save changes to files,
     *
     * @param dealer dealer object
     * @param vehicle  vehicle object
     */
    void addVehicleToInventory(Dealer dealer, Vehicle vehicle) throws IllegalArgumentException;

    /**
     * <p>Remove an existing vehicle from an inventory; to save changes to files,
     *
     * @param dealer  dealer object
     * @param vehicle vehicle object
     */
    void removeVehicleFromInventory(Dealer dealer, Vehicle vehicle);

    /**
     * Get an inventory by its dealer .
     *
     * @param dealerId dealer id
     * @return the Inventory object of the dealer id
     */
    Inventory getInventoryByDealerId(String dealerId);

    /**
     * Update inventory file.
     *
     * @param inventory      inventory of the dealer to be saved
     * @throws IOException
     */
    void updateInventoryToFile(Inventory inventory) throws Exception;
}
