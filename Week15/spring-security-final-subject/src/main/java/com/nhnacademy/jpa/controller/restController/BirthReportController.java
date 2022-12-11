package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.birthDeathReportResident.BirthDeathReportDto;
import com.nhnacademy.jpa.dto.birthDeathReportResident.BirthReportRegisterRequest;
import com.nhnacademy.jpa.dto.birthDeathReportResident.BirthReportUpdateRequest;
import com.nhnacademy.jpa.service.birthDeathReportResident.BirthDeathReportResidentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class BirthReportController {

    private final BirthDeathReportResidentService birthDeathReportResidentService;

    public BirthReportController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping("/residents/{serialNumber}/birth")
    public BirthDeathReportDto registerBirthReport(@PathVariable("serialNumber") Long reportResidentSerialNumber,
                                                   @RequestBody BirthReportRegisterRequest request) {
        return birthDeathReportResidentService.registerBirthReport(reportResidentSerialNumber, request);
    }

    @PutMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
    public BirthDeathReportDto modifyBirthReport(@PathVariable("serialNumber") Long reportResidentSerialNumber,
                                                 @PathVariable("targetSerialNumber") Long residentSerialNumber,
                                                 @RequestBody BirthReportUpdateRequest request) {
        return birthDeathReportResidentService.modifyBirthReport(reportResidentSerialNumber, residentSerialNumber, request);
    }

    @DeleteMapping("/residents/{serialNumber}/birth/{targetSerialNumber}")
    public void deleteBirthReport(@PathVariable("serialNumber") Long reportResidentSerialNumber,
                                  @PathVariable("targetSerialNumber") Long residentSerialNumber) {
        birthDeathReportResidentService.deleteBirthDeathReport(reportResidentSerialNumber, residentSerialNumber);
    }

}
