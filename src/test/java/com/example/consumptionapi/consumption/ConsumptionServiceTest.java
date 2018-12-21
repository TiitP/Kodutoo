package com.example.consumptionapi.consumption;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumptionServiceTest {

    @Autowired
    private ConsumptionService consumptionService;
    @Autowired
    private ConsumptionRepository consumptionRepository;

    private Consumption consumption;

    @Before
    public void setConsumption() {
        this.consumption = new Consumption("95", 10.0, 50, 1, LocalDate.parse("2018-12-01"));
        addConsumption();
    }

    @After
    public void clear() {
        consumptionRepository.deleteAll();
    }

    @Test
    public void addConsumption() {
        consumptionService.addConsumption(consumption);
        assertTrue(consumption.equals(consumptionService.getAllConsumptions().
                get(consumptionService.getAllConsumptions().indexOf(consumption))));
    }

    @Test
    public void getAllConsumptions() {
        assertTrue(!consumptionService.getAllConsumptions().isEmpty());
        assertTrue(consumptionService.getAllConsumptions().stream().findAny().get().equals(consumption));
    }

    @Test
    public void getConsumptionsTotalCost() {
        Map<String, Double> map = new HashMap<>();
        map.put(consumption.getMonthName(), consumption.getTotalPrice());
        assertTrue(consumptionService.getConsumptionsTotalCost().equals(map));
    }

    @Test
    public void getConsumptionsTotalCostByDriver() {
        Map<String, Double> map = new HashMap<>();
        map.put(consumption.getMonthName(), consumption.getTotalPrice());
        assertTrue(consumptionService.getConsumptionsTotalCostByDriver(consumption.getDriverId()).equals(map));
    }

    @Test
    public void getConsumptionRecordsForSpecifiedMonth() {

        assertTrue(Objects.equals(consumptionService.getConsumptionRecordsForSpecifiedMonth(consumption.getMonthName()).
                        stream().findAny().get(),
                new ConsumptionRecordsForSpecifiedMonth(consumption.getVolume(), consumption.getPrice(), consumption.getTotalPrice(),
                        consumption.getDriverId(), consumption.getDate(), consumption.getFuelType())));
    }


    @Test
    public void getConsumptionRecordsForSpecifiedMonthByDriver() {

        assertTrue(Objects.equals(consumptionService.
                        getConsumptionRecordsForSpecifiedMonthByDriver(consumption.getMonthName(), consumption.getDriverId()).
                        stream().findAny().get(),
                new ConsumptionRecordsForSpecifiedMonth(consumption.getVolume(), consumption.getPrice(), consumption.getTotalPrice(),
                        consumption.getDriverId(), consumption.getDate(), consumption.getFuelType())));
    }

    @Test
    public void getConsumptionStatisticsByMonth() {

        List<ConsumptionStatisticsByMonth> consumptions = consumptionService.getConsumptionStatisticsByMonth(consumption.getMonthName());
        double totalPrice = consumption.getTotalPrice();
        double averagePrice = consumption.getPrice();

        assertTrue(Objects.equals(consumptions.stream().findAny().get(),
                new ConsumptionStatisticsByMonth(consumption.getFuelType(), consumption.getVolume(), totalPrice, averagePrice)));
    }

    @Test
    public void getConsumptionStatisticsByMonthAndByDriver() {

        List<ConsumptionStatisticsByMonth> consumptions = consumptionService.
                getConsumptionStatisticsByMonthAndByDriver(consumption.getMonthName(), consumption.getDriverId());
        double totalPrice = consumption.getTotalPrice();
        double averagePrice = consumption.getPrice();

        assertTrue(Objects.equals(consumptions.stream().findAny().get(),
                new ConsumptionStatisticsByMonth(consumption.getFuelType(), consumption.getVolume(), totalPrice, averagePrice)));
    }


}