package com.example.consumptionapi.consumption;

import java.util.Objects;

public class ConsumptionStatisticsByMonth {


    private String fuelType;
    private double volume;
    private double totalPrice;
    private double averagePrice;

    public ConsumptionStatisticsByMonth() {

    }

    public ConsumptionStatisticsByMonth(String fuelType, double volume, double totalPrice, double averagePrice) {

        this.fuelType = fuelType;
        this.volume = volume;
        this.totalPrice = totalPrice;
        this.averagePrice = averagePrice;
    }


    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsumptionStatisticsByMonth)) return false;
        ConsumptionStatisticsByMonth that = (ConsumptionStatisticsByMonth) o;
        return Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                Double.compare(that.averagePrice, averagePrice) == 0 &&
                Objects.equals(fuelType, that.fuelType);
    }
}
