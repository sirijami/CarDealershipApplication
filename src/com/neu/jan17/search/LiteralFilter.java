package com.neu.jan17.search;

import com.neu.jan17.data.Vehicle;

public class LiteralFilter extends Filter {
	private Object value;
	
	/**
     * Create a filter with single value
     *
     * @param name	name of the filter
     * @param value	value of the filter
     */
	public LiteralFilter(String name, Object value) {
		super(name);
		this.value = value;
	}
	
	@Override
	boolean matchVehicle(Vehicle v) {
		return get(getName(), v).equals(value);
	}

}
