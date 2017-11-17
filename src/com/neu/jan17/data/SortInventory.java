package com.neu.jan17.data;


import java.lang.reflect.Method;
import java.util.*;

// Sort function for Inventory
public class SortInventory {

    public void sort(SortInventory sortInventory, String selectedSort, List<Vehicle> result) throws Exception{
        selectedSort = selectedSort.replaceAll("\\s+","");
        Method m = this.getClass().getDeclaredMethod("sortVehiclesBy"+ selectedSort, List.class);
        m.invoke(sortInventory,result);
    }


    //List<Vehicle> test = new ArrayList<>();import java.lang.reflect.Method;

    public void sortVehiclesByYearAsc(List<Vehicle> vehicle) {
        vehicle.sort(Comparator.comparing(Vehicle::getYear));
    }

    public void sortVehiclesByYearDesc(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getYear).reversed());
    }

    public void sortVehiclesByModel(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getModel));
    }

    public void sortVehiclesByCategory(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getCategory));
    }

    public void sortVehiclesByPriceAsc(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getPrice));
    }

    public void sortVehiclesByPriceDesc(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getPrice).reversed());
    }

    public void sortVehiclesByMake(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getMake));
    }

    public void sortVehiclesById(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getId));
    }

    public void sortVehiclesByTrim(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getTrim));
    }

    public void sortVehiclesByBodyType(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getBodyType));
    }

    public void sortVehiclesByWebId(List<Vehicle> vehicle){
        vehicle.sort(Comparator.comparing(Vehicle::getWebId));
    }
}
