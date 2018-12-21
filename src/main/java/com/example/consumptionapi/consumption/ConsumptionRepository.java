package com.example.consumptionapi.consumption;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumptionRepository extends CrudRepository<Consumption, Integer> {

    @Override
    List<Consumption> findAll();

    List<Consumption> findByDriverId(int driverId);

    List<Consumption> findByMonthName(String monthName);

    <T> List<T> findByMonthNameAndDriverId(String monthName, int driverId);

}

