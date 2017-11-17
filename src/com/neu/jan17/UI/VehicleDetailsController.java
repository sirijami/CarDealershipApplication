package com.neu.jan17.UI;


import com.neu.jan17.data.Dealer;
import com.neu.jan17.data.Vehicle;

public class VehicleDetailsController {


    /**
     *
     * @param vehicle is a vehicle object to be displayed
     */
    public  VehicleDetailsController(Vehicle vehicle, Dealer dealer){


       setDetails(new VehicleDetailsView(dealer),vehicle, dealer);



    }

    /**
     *
     * @param view is used as reference to VehicleDetailsView object which displays the frame
     * @param vehicle is vehicle object to be displayed
     * @param dealer is dealer object to be displaced
     */
    private void setDetails(VehicleDetailsView view, Vehicle vehicle, Dealer dealer){

        view.setPictureLabel(vehicle.getPhoto());
        view.setVehicleIDLabel("VEHICLE ID : "+ vehicle.getId() );
        view.setCategoryLabel("CATEGORY : "+ vehicle.getCategory());
        view.setMakeLabel("MAKE : "+ vehicle.getMake());
        view.setTrimLabel("TRIM : "+ vehicle.getTrim());
        view.setYearLabel("YEAR : "+ vehicle.getYear());
        view.setBodyTypeLabel("BODY TYPE : "+ vehicle.getBodyType());
        view.setModelLabel("MODEL : "+ vehicle.getModel());
        view.setPriceLabel("PRICE : "+vehicle.getPrice() );
        view.setDealerLocationLabel("LOCATION : " + dealer.getLocation());
        view.setDealerIDLabel("DEALER ID : "+ dealer.getId());
        view.setDealerURLLabel("URL : "+dealer.getUrl());

    }

}
