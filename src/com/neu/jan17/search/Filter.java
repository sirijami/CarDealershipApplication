package com.neu.jan17.search;

import com.neu.jan17.data.Vehicle;

public abstract class Filter {
	private String name;

	Filter(String name) {
		this.name = name;
	}
	
	/**
     * Get the name of the filter
     *
     * @param     
     * @return	name of the filter
     */
	public String getName() {
		return name;
	}
	
	public Object get(String attrName, Vehicle v) {
		switch(attrName) {
			case "year": return v.getYear();
			case "make": return v.getMake();
			case "model": return v.getModel();
			case "trim": return v.getTrim();
			case "category": return v.getCategory();
			case "bodyType": return v.getBodyType();
			case "price": return v.getPrice();
			case "dealerId": return v.getWebId();
			default: return new Object();
		}
	}
	
	/**
     * Test if a vehicle item should be listed in search results
     *
     * @param v	a vehicle
     * @return	if should be listed
     */
	abstract boolean matchVehicle(Vehicle v);
}
