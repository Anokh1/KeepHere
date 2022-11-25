package com.example.keephere;

public class KeepHere5 {

    // String variable for storing fitting room number
    private String fittingRoomNumber;

    // String variable for storing item number
    private String itemNumber;

    // String variable for storing barcode number
    private String barcodeNumber;

    private String date;

    private String userEmail;

    // Empty constructor that is required when using Firebase Realtime Database
    public KeepHere5(){

    }

    // Constructor to pass the values in
    public KeepHere5(String fittingRoomNumber, String itemNumber, String barcodeNumber){
        this.fittingRoomNumber = fittingRoomNumber;
        this.itemNumber = itemNumber;
        this.barcodeNumber = barcodeNumber;
    }

    // Generate getter and setter for all the variables above
    public String getFittingRoomNumber() {
        return fittingRoomNumber;
    }

    public void setFittingRoomNumber(String fittingRoomNumber) {
        this.fittingRoomNumber = fittingRoomNumber;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String bacrcodeNumber) {
        this.barcodeNumber = bacrcodeNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
