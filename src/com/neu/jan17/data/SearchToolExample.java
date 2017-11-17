package com.neu.jan17.data;

import java.io.FileNotFoundException;
import java.util.*;

import com.neu.jan17.search.*;

public class SearchToolExample {
	public static void main(String[] args) {
		try {
			//create an object to help searching and pass in a dealerId(absolute or relative)
			SearchTool s = new SearchTool("gmps-aj-dohmann");
			
			
			/*----------------------- search by keyword ------------------------*/
			
			//search with keyword "dohma"
			SearchResult searchResult = s.searchByKeyWord("dohma");
			
			//50 items per page
			int itemPerPage = 50;
						
			//get the first page of results
			List<Vehicle> first50Items = searchResult.getResult(0, itemPerPage); 
			System.out.println(first50Items.size() + " items found.");
			
			//get the second page
			List<Vehicle> second50Items = searchResult.getResult(1, itemPerPage);
			System.out.println(second50Items.size() + " items found.");
			
			//get all results
			List<Vehicle> allResults = searchResult.getResult();
			System.out.println(allResults.size() + " items found.");
			
			
			/*----------------------- search by filters ------------------------*/
			
			//create filters
			List<Filter> filters = new ArrayList<Filter>();
			
			//create and add a range filter
			RangeFilter rf = new RangeFilter("year", 2005, 2012);
			filters.add(rf);
			//create and add a filter with single value
			LiteralFilter lf = new LiteralFilter("dealerId", "gmps-aj-dohmann");		
			filters.add(lf);
			
			//search for vehicles that are selling by dealer dohmann with year range of [2005, 2012) (2012 excluded)
			SearchResult searchResult2 = s.searchByFilters(filters);
			//get all results
			List<Vehicle> vehiclesListedByDealerDohmannWithYearRangeOf2005to2012 = searchResult2.getResult();
			System.out.println(vehiclesListedByDealerDohmannWithYearRangeOf2005to2012.size() + " items found.");
			
		} catch(FileNotFoundException e) {
			System.out.println("File does not exist.");
		}
	}
}
