package com.example.consumptionapi.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @RequestMapping("/consumptions")
    public List<Consumption> getAllConsumptions() {
        return consumptionService.getAllConsumptions();
    }

    @RequestMapping("/consumptions/monthstotalcost")
    public Map<String, Double> getConsumptionsTotalCost() {
        return consumptionService.getConsumptionsTotalCost();
    }

    @RequestMapping("/consumptions/monthstotalcost/{driverId}")
    public Map<String, Double> getConsumptionsTotalCostByDriver(@PathVariable int driverId) {
        return consumptionService.getConsumptionsTotalCostByDriver(driverId);
    }

    @RequestMapping("/consumptions/{month}")
    public List<ConsumptionRecordsForSpecifiedMonth> getConsumptionRecordsForSpecifiedMonth(@PathVariable String month) throws Exception {
        return consumptionService.getConsumptionRecordsForSpecifiedMonth(month.toUpperCase());
    }

    @RequestMapping("/consumptions/{month}/{driverId}")
    public List<ConsumptionRecordsForSpecifiedMonth> getConsumptionRecordsForSpecifiedMonthByDriver(@PathVariable String month, @PathVariable int driverId) {
        return consumptionService.getConsumptionRecordsForSpecifiedMonthByDriver(month.toUpperCase(), driverId);
    }

    @RequestMapping("/consumptions/statistics/{month}")
    public List<ConsumptionStatisticsByMonth> getConsumptionStatisticsByMonth(@PathVariable String month) {
        return consumptionService.getConsumptionStatisticsByMonth(month.toUpperCase());
    }

    @RequestMapping("/consumptions/statistics/{month}/{driverId}")
    public List<ConsumptionStatisticsByMonth> getConsumptionStatisticsByMonthAndByDriver(@PathVariable String month, @PathVariable int driverId) {
        return consumptionService.getConsumptionStatisticsByMonthAndByDriver(month.toUpperCase(), driverId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/consumption")
    public void addConsumption(@Valid @RequestBody Consumption consumption) throws Exception {
        if (!(consumption.getFuelType().equals("95") || consumption.getFuelType().equals("98") || consumption.getFuelType().toUpperCase().equals("D"))) {
            throw new Exception("Fueltype must be '95', '98' or 'D'");
        }
        consumptionService.addConsumption(consumption);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/consumptions")
    public void addConsumption(@Valid @RequestBody List<Consumption> consumptions) throws Exception {
        for (Consumption c : consumptions
                ) {
            if (!(c.getFuelType().equals("95") || c.getFuelType().equals("98") || c.getFuelType().toUpperCase().equals("D"))) {
                throw new Exception("Fueltype must be '95', '98' or 'D'");
            }
            consumptionService.addConsumption(c);
        }
    }
}
