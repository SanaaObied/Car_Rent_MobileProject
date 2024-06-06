package com.example.activity1;

import org.json.JSONException;
import org.json.JSONObject;

public class Car {
    private int Id;
    private String Brand;
    private String Model;
    private int NumOfReservation;
    private boolean avalible;
    private String Image;
    private int Price;
    private String Transmissiontypes;

    // Constructor
    public Car(int id, String brand, String model, int numOfReservation, boolean available, String image, int price, String transmissiontypes) {
        Id = id;
        Brand = brand;
        Model = model;
        NumOfReservation = numOfReservation;
        avalible = available;
        Image = image;
        Price = price;
        Transmissiontypes = transmissiontypes;
    }

    public Car(String name, String image) {
        Brand=name;
        Image = image;
    }

    // Getters
    public int getId() {
        return Id;
    }

    public String getBrand() {
        return Brand;
    }

    public String getModel() {
        return Model;
    }

    public int getNumOfReservation() {
        return NumOfReservation;
    }

    public boolean isAvailable() {
        return avalible;
    }

    public String getImage() {
        return Image;
    }

    public int getPrice() {
        return Price;
    }

    public String getTransmissiontypes() {
        return Transmissiontypes;
    }

    // Setters - Optional, depending on your need to modify these attributes
    public void setId(int id) {
        Id = id;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setNumOfReservation(int numOfReservation) {
        NumOfReservation = numOfReservation;
    }

    public void setAvailable(boolean available) {
        avalible = available;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setTransmissiontypes(String transmissiontypes) {
        Transmissiontypes = transmissiontypes;
    }

    // Override toString method for debugging purposes
    @Override
    public String toString() {
        return "Car{" +
                "Id=" + Id +
                ", Brand='" + Brand + '\'' +
                ", Model='" + Model + '\'' +
                ", NumOfReservation=" + NumOfReservation +
                ", avalible=" + avalible +
                ", Image='" + Image + '\'' +
                ", Price=" + Price +
                ", Transmissiontypes='" + Transmissiontypes + '\'' +
                '}';
    }
    public static Car fromJson(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("Id");
        String brand = jsonObject.getString("Brand");
        String model = jsonObject.getString("Model");
        int numOfReservation = jsonObject.getInt("NumOfReservation");
        boolean available = jsonObject.getInt("avalible") == 1; // 1 for true, 0 for false
        String image = jsonObject.getString("Image");
        int price = jsonObject.getInt("Price");
        String transmissiontypes = jsonObject.getString("Transmissiontypes");

        return new Car(id, brand, model, numOfReservation, available, image, price, transmissiontypes);
    }
}

