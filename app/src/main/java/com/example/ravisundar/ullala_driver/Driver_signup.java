package com.example.ravisundar.ullala_driver;

public class Driver_signup {
    String username;
    String choose;
    String phonenumber;
    String vehiclenumber;
    Double latitude1 = 0.0, longitude1 = 0.0;

    public Driver_signup(String username, String phonenumber, String vehiclenumber, String choose, Double latitude1, Double longitude1) {

        this.username = username;
        this.choose= choose;
        this.phonenumber = phonenumber;
        this.vehiclenumber = vehiclenumber;
    }


    public Driver_signup() {

    }


    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public Double getLatitude1() {
        return latitude1;
    }

    public Double getLongitude1() {
        return longitude1;
    }

    public String getUsername() {
        return username;
    }

    public String getChoose() {
        return choose;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}



