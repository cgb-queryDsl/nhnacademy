package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.household.HouseholdDto;
import com.nhnacademy.jpa.dto.household.HouseholdRegisterRequest;
import com.nhnacademy.jpa.service.household.HouseholdService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HouseholdController {

    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping("/household")
    public HouseholdDto registerHousehold(@RequestBody HouseholdRegisterRequest request) {
        return householdService.registerHousehold(request);
    }

    @DeleteMapping("/household/{householdSerialNumber}")
    public void deleteHousehold(@PathVariable("householdSerialNumber") Long householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);
    }
}
