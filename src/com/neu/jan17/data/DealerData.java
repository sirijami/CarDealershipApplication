package com.neu.jan17.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.TreeSet;

// Read Dealers Info from Dealers.txt
public class DealerData {

    private final TreeSet<String> sortedDealerInfo = new TreeSet<String>();

    // Read data from file
    private void DealersReader() {
        try {
            URL pathUrl = getClass().getResource("dealers.txt");
            File dealersFile = new File(pathUrl.getPath());
            BufferedReader reader = new BufferedReader(new FileReader(dealersFile));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sortedDealerInfo.add(line);
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    // Store Info in the Dealer Object
    public Dealer[] getDealersData() {
        this.DealersReader();
        String[][] data = new String[sortedDealerInfo.size()][3];
        Dealer[] dealers = new Dealer[sortedDealerInfo.size()];
        int i = 0;
        for (String s : sortedDealerInfo) {
            data[i] = s.split("\\s+");
            i++;
        }
        for(i = 0; i < dealers.length; i++){
            dealers[i] = new Dealer();
            dealers[i].setId(data[i][0]);
            dealers[i].setLocation(data[i][1]);
            dealers[i].setUrl(data[i][2]);
        }
        return dealers;
    }
}
