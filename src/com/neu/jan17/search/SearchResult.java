package com.neu.jan17.search;

import java.util.ArrayList;
import java.util.List;

import com.neu.jan17.data.Vehicle;

public class SearchResult {
	List<Vehicle> results;
	public SearchResult(List<Vehicle> results) {
		this.results = results;
	}
	
	public List<Vehicle> getResult() {
		return results == null ? new ArrayList<Vehicle>() : results;
	}

	public List<Vehicle> getResult(int pageIndex, int itemsPerPage) {
		int bIdx = pageIndex*itemsPerPage, eIdx = bIdx+itemsPerPage, len = results == null ? 0 : results.size();
		if(bIdx > len - 1) return new ArrayList<Vehicle>();
		return results.subList(bIdx, Math.min(len, eIdx));
	}
}
