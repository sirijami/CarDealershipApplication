package com.neu.jan17.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class FetchInventoryFromWebsites {
	public static void main(String args[]) throws Exception {
		fetchInventoryAndStore();
	}

	private static void fetchInventoryAndStore() throws Exception {
		Collection<Dealer> dealers = getDealers();
		for (Dealer d : dealers) {
			Collection<Vehicle> vehicles = fetchInventory(d);
			storeVehicles(vehicles, d.webId);
		}

	}

	private static void storeVehicles(Collection<Vehicle> vehicles, String webId) throws Exception {
		File f = new File("data/" + webId);
		PrintWriter pw = new PrintWriter(new FileWriter(f));
		pw.println("id~webId~category~year~make~model~trim~type~price~photo");
		StringBuilder builder = new StringBuilder();
		boolean start = false;
		for (Vehicle v : vehicles) {
			if (start == true) {
				builder.append("\n");
			} else {
				start = true;
			}
			builder.append(v.toString());
		}
		pw.println(builder.toString());
		pw.flush();
		pw.close();
	}

	private static Collection<Vehicle> fetchInventory(Dealer d) throws Exception {
		URL u = new URL("http://" + d.url + "/searchVehiclesXML.ajax");
		HttpURLConnection uc = (HttpURLConnection) u.openConnection();
		Collection<Vehicle> vehicles = new ArrayList<Vehicle>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		reader.readLine();
		while (true) {
			String line = reader.readLine();
			// System.out.println(line);
			if (line == null)
				break;
			addVehicle(line, vehicles);
		}
		reader.close();

		return vehicles;
	}

	private static void addVehicle(String line, Collection<Vehicle> vehicles) {
		if (!line.contains("Vehicle id=")) {
			return;
		}
		vehicles.add(makeVehicle(line));
	}

	private static Vehicle makeVehicle(String line) {
		Vehicle v = new Vehicle();
		v.id = getString(line, "id");
		v.category = getString(line, "category");
		v.make = getString(line, "make");
		v.model = getString(line, "model");
		v.price = getString(line, "featuredPrice");
		v.photo = getString(line, "photo");
		v.trim = getString(line, "trim");
		v.webId = getString(line, "webId");
		v.year = getString(line, "year");
		v.type = getString(line, "bodyType");
		return v;

	}

	private static String getString(String x, String key) {
		String find = key + "=\"";
		int l = find.length();
		int start = x.indexOf(find) + l;
		int end = x.indexOf("\"", start);
		return x.substring(start, end);
	}

	private static Collection<Dealer> getDealers() throws Exception {
		File f = new File("data/dealers");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		Collection<Dealer> dealers = new ArrayList<Dealer>();
		// reader.readLine();
		while (true) {
			String line = reader.readLine();
			if (line == null)
				break;
			Dealer d = new Dealer();
			String x[] = line.split("\\\t");
			d.url = x[2];
			d.webId = x[0];
			dealers.add(d);
		}
		reader.close();
		return dealers;

	}

}

class Dealer {
	String webId;
	String url;

	public String toString() {
		return webId + " - " + url;
	}
}

class Vehicle {
	// id~webId~category~year~make~model~trim~type~price
	String id;
	String webId;
	String category;
	String year;
	String make;
	String model;
	String trim;
	String price;
	String photo;
	String type;

	public String toString() {
		return id + "~" + webId + "~" + category + "~" + year + "~" + make + "~" + model + "~" + trim + "~" + type + "~"
				+ price + "~" + photo;
	}
}
