package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.birthDeathReportResident.BirthDeathReportDto;
import com.nhnacademy.jpa.dto.birthDeathReportResident.DeathReportRegisterRequest;
import com.nhnacademy.jpa.dto.birthDeathReportResident.DeathReportUpdateRequest;
import com.nhnacademy.jpa.service.birthDeathReportResident.BirthDeathReportResidentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeathReportController {

    private final BirthDeathReportResidentService birthDeathReportResidentService;

    public DeathReportController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping("/residents/{serialNumber}/death")
    public BirthDeathReportDto registerDeathReport(@PathVariable("serialNumber") Long reportResidentSerialNumber,
                                                   @RequestBody DeathReportRegisterRequest request) {
        return birthDeathReportResidentService.registerDeathReport(reportResidentSerialNumber, request);
    }

    @PutMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public BirthDeathReportDto modifyBirthReport(@PathVariable("serialNumber") Long reportResidentSerialNumber,
                                                 @PathVariable("targetSerialNumber") Long residentSerialNumber,
                                                 @RequestBody DeathReportUpdateRequest request) {
        return birthDeathReportResidentService.modifyDeathReport(reportResidentSerialNumber, residentSerialNumber, request);
    }

    @DeleteMapping("/residents/{serialNumber}/death/{targetSerialNumber}")
    public void deleteDeathReport(@PathVariable("serialNumber") Long reportResidentSerialNumber,
                                  @PathVariable("targetSerialNumber") Long residentSerialNumber) {
        birthDeathReportResidentService.deleteBirthDeathReport(reportResidentSerialNumber, residentSerialNumber);
    }
}
