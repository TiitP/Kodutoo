package com.example.consumptionapi.consumption;


import java.time.LocalDate;
import java.util.Objects;

public class ConsumptionRecordsForSpecifiedMonth {

    private double volume;
    private double price;
    private double totalPrice;
    private int driverId;
    private LocalDate date;
    private String fuelType;

    public ConsumptionRecordsForSpecifiedMonth() {

    }

    public ConsumptionRecordsForSpecifiedMonth(double volume, double price, double totalPrice, int driverId, LocalDate date, String fuelType) {
        this.volume = volume;
        this.price = price;
        this.totalPrice = totalPrice;
        this.driverId = driverId;
        this.date = date;
        this.fuelType = fuelType;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumptionRecordsForSpecifiedMonth)) return false;
        ConsumptionRecordsForSpecifiedMonth that = (ConsumptionRecordsForSpecifiedMonth) o;
        return Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                driverId == that.driverId &&
                Objects.equals(date, that.date) &&
                Objects.equals(fuelType, that.fuelType);
    }

}

