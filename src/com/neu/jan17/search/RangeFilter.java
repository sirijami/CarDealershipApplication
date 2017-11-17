package com.neu.jan17.search;

import com.neu.jan17.data.Vehicle;

public class RangeFilter extends Filter {
	private Comparable start, end;
	
	/**
     * Create a range filter
     *
     * @param name	name of the filter
     * @param start	the minimum value of the range
     * @param end	the maximum value of the range (excluded)
     */
	public RangeFilter(String name, Comparable start, Comparable end) {
		super(name);
		this.start = start;
		this.end = end;
	}
	
	@Override
	boolean matchVehicle(Vehicle v) {
		Comparable val = (Comparable)get(getName(), v);
		boolean matched;
		try {
			matched = val.compareTo(start) >= 0 && val.compareTo(end) < 0;
		} catch(Exception e) {
			matched = false;
		}
		return matched;
	}

}
