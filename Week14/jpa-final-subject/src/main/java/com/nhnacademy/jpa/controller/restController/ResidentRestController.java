package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentRegisterRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.service.resident.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ResidentRestController {

    private final ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/residents/{serialNumber}")
    public ResidentDto getResident(@PathVariable("serialNumber") Long serialNumber) {
        return residentService.getResident(serialNumber);
    }

    @PostMapping("/residents")
    @ResponseStatus(HttpStatus.CREATED)
    public Resident registerResident(@RequestBody ResidentRegisterRequest residentRegisterRequest) {
        return residentService.registerResident(residentRegisterRequest);
    }

    @PutMapping("/residents/{serialNumber}")
    public ResidentDto modifyResident(@PathVariable("serialNumber") Long serialNumber,
                                    @RequestBody ResidentUpdateRequest residentUpdateRequest) {
        return residentService.modifyResident(serialNumber, residentUpdateRequest);
    }

    @DeleteMapping("/residents/{serialNumber}")
    public void deleteResident(@PathVariable("serialNumber") Long serialNumber) {
        residentService.deleteResident(serialNumber);
    }
}
