package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.householdAddress.HouseholdMovementAddressDto;
import com.nhnacademy.jpa.dto.householdAddress.HouseholdMovementRegisterRequest;
import com.nhnacademy.jpa.dto.householdAddress.HouseholdMovementUpdateRequest;
import com.nhnacademy.jpa.service.householdMovementAddress.HouseholdMovementAddressService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class MovementController {

    private final HouseholdMovementAddressService householdMovementAddressService;

    public MovementController(HouseholdMovementAddressService householdMovementAddressService) {
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping("/household/{householdSerialNumber}/movement")
    public HouseholdMovementAddressDto registerHouseholdAddress(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                                                @RequestBody HouseholdMovementRegisterRequest request) {
        return householdMovementAddressService.registerAddress(householdSerialNumber, request);
    }

    @PutMapping("/household/{householdSerialNumber}/movement/{reportDate}")
    public HouseholdMovementAddressDto modifyHouseholdAddress(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                                              @PathVariable("reportDate") String reportDate,
                                                              @RequestBody HouseholdMovementUpdateRequest request) throws ParseException {
        return householdMovementAddressService.modifyAddress(householdSerialNumber, reportDate, request);
    }

    @DeleteMapping ("/household/{householdSerialNumber}/movement/{reportDate}")
    public void deleteHouseholdAddress(@PathVariable("householdSerialNumber") Long householdSerialNumber,
                                       @PathVariable("reportDate") String reportDate) throws ParseException {
        householdMovementAddressService.deleteAddress(householdSerialNumber, reportDate);
    }

}
