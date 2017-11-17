package com.neu.jan17.search;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.neu.jan17.data.Dealer;
import com.neu.jan17.data.Inventory;
import com.neu.jan17.data.InventoryManager;
import com.neu.jan17.data.Vehicle;

public class SearchTool implements SearchFunc {
		
	private Inventory inventory;
	
	/**
     * Create a searching helper
     *
     * @param folderPath	dealerId  
     */
	
	public SearchTool(String dealerId) throws FileNotFoundException {	
		Dealer d = new Dealer();
		d.setId(dealerId);
		InventoryManager reader = new InventoryManager(d);
		inventory = reader.getInventoryByDealerId(d.getId());
	}

	

	@Override
	public SearchResult searchByKeyWord(Collection<Vehicle> base, String kw) {
		return new SearchResult(base.stream().filter(v -> matchKeyWord(v, kw.toLowerCase())).collect(Collectors.toList()));
	}
	
	@Override
	public SearchResult searchByKeyWord(String kw) {
		return searchByKeyWord(inventory.getVehicles(), kw);
	}

	@Override
	public SearchResult searchByFilters(Collection<Vehicle> base, List<Filter> filters) {
		return new SearchResult(base.stream().filter(v -> matchFilters(v, filters)).collect(Collectors.toList()));
	}
	
	@Override
	public SearchResult searchByFilters(List<Filter> filters) {
		return searchByFilters(inventory.getVehicles(), filters);
	}
	
	@Override
	public Inventory getInventory() {
		return inventory;
	}

	private boolean matchKeyWord(Vehicle v, String kw) {
		return v.getMake().toLowerCase().indexOf(kw) >= 0 || v.getModel().toLowerCase().indexOf(kw) >= 0 || v.getBodyType().toLowerCase().indexOf(kw) >= 0;
	}
	
	private boolean matchFilters(Vehicle v, List<Filter> filters) {
		return filters.stream().map((f) -> f.matchVehicle(v)).reduce(true, (x, y) -> (x && y));
	}
	
	private void flatMapToList(Map<String, Inventory> vehicleMap, Collection<Vehicle> data) {
		for (Inventory inventory : vehicleMap.values()) {
		    for(Vehicle v : inventory.getVehicles()) {
		    	data.add(v);
		    }
		}
	}


}
