package com.example.consumptionapi.consumption;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "consumptions")
public class Consumption {

    @Id
    @GeneratedValue
    private int id;

    @NotNull(message = "Driver id should have atleast 1 characters")
    private int driverId;

    @NotNull
    @Size(min = 1, max = 2, message = "Fuel type must be '95', '98', or 'D'")
    private String fuelType;

    @NotNull(message = "Volume can't be empty")
    private double volume;

    @NotNull(message = "Price can't be empty")
    private double price;

    private double totalPrice;

    @NotNull(message = "Date can't be empty. Date format 'yyyy-mm-dd")
    private LocalDate date;
    private String monthName;

    public Consumption() {
    }

    public Consumption(String fuelType, double price, double volume, int driverId, LocalDate date) {
        this.fuelType = fuelType;
        this.price = price;
        this.volume = volume;
        this.date = date;
        this.driverId = driverId;
        this.totalPrice = price * volume;
        this.monthName = date.getMonth().name();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setLocalDate(LocalDate date) {
        this.date = date;
        this.monthName = getMonthName();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double price, double volume) {
        this.totalPrice = price * volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(LocalDate date) {
        this.monthName = date.getMonth().name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumption that = (Consumption) o;
        return id == that.id &&
                driverId == that.driverId &&
                Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.price, price) == 0 &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                Objects.equals(fuelType, that.fuelType) &&
                Objects.equals(date, that.date) &&
                Objects.equals(monthName, that.monthName);
    }
}