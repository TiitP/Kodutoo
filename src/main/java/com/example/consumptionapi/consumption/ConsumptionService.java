package com.example.consumptionapi.consumption;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<Consumption> getAllConsumptions() {
        return consumptionRepository.findAll();
    }

    public void addConsumption(Consumption consumption) {
        consumption.setTotalPrice(consumption.getPrice(), consumption.getVolume());
        consumption.setMonthName(consumption.getDate());
        consumptionRepository.save(consumption);
    }

    public Map<String, Double> getConsumptionsTotalCost() {
        List<Consumption> consumptions = new ArrayList<>(consumptionRepository.findAll());
        return consumptions.stream().
                collect(Collectors.groupingBy(Consumption::getMonthName, Collectors.summingDouble(Consumption::getTotalPrice)));
    }

    public Map<String, Double> getConsumptionsTotalCostByDriver(int driverId) {
        List<Consumption> consumptions = consumptionRepository.findByDriverId(driverId);
        return consumptions.stream().
                collect(Collectors.groupingBy(Consumption::getMonthName, Collectors.summingDouble(Consumption::getTotalPrice)));
    }

    public List<ConsumptionRecordsForSpecifiedMonth> getConsumptionRecordsForSpecifiedMonth(String month) {
        return getMapped(consumptionRepository.findByMonthName(month), ConsumptionRecordsForSpecifiedMonth.class);
    }

    public List<ConsumptionRecordsForSpecifiedMonth> getConsumptionRecordsForSpecifiedMonthByDriver(String month, int driverId) {
        return getMapped(consumptionRepository.findByMonthNameAndDriverId(month, driverId), ConsumptionRecordsForSpecifiedMonth.class);
    }

    public List<ConsumptionStatisticsByMonth> getConsumptionStatisticsByMonth(String month) {
        return sortByFuelType(consumptionRepository.findByMonthName(month));
    }

    public List<ConsumptionStatisticsByMonth> getConsumptionStatisticsByMonthAndByDriver(String month, int driverId) {
        return sortByFuelType(consumptionRepository.findByMonthNameAndDriverId(month, driverId));
    }

    private double getAveragePriceByMonthAndFuelType(List<Consumption> consumptions, String fuelType) {
        return consumptions.stream().filter(c -> fuelType.equals(c.getFuelType())).collect(Collectors.toList()).
                stream().collect(Collectors.averagingDouble(Consumption::getPrice));
    }

    private double getTotalPriceByMonthAndFuelType(List<Consumption> consumptions, String fuelType) {
        return consumptions.stream().filter(c -> fuelType.equals(c.getFuelType())).collect(Collectors.toList()).
                stream().mapToDouble(Consumption::getTotalPrice).sum();
    }

    private List<ConsumptionStatisticsByMonth> sortByFuelType(List<Consumption> consumptions) {
        Map<String, Double> map = consumptions.stream().collect(Collectors.
                groupingBy(Consumption::getFuelType, Collectors.summingDouble(Consumption::getVolume)));

        List<ConsumptionStatisticsByMonth> list = new ArrayList<>();

        map.forEach((k, v) -> list.add(new ConsumptionStatisticsByMonth
                (k, v, getTotalPriceByMonthAndFuelType(consumptions, k),
                        getAveragePriceByMonthAndFuelType(consumptions, k))));
        return list;
    }

    private <T> List<T> getMapped(List<Consumption> primaryList, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        for (Consumption c : primaryList) {
            T t = clazz.cast(modelMapper.map(c, clazz));
            list.add(t);
        }
        return list;
    }
}
