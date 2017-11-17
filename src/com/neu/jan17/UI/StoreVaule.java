package com.neu.jan17.UI;

import com.neu.jan17.data.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoshiyao on 4/20/17.
 */
public class StoreVaule {
    public static List<Vehicle> ve = new ArrayList<>();

    public void setValue(List<Vehicle> vehicles){
        this.ve = vehicles;
    }

    public List<Vehicle> getVaule(){
        return this.ve;
    }

//
}
