package com.nhnacademy.jpa.controller.restController;

import com.nhnacademy.jpa.dto.familyRelationship.RelationshipDto;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipRegisterRequest;
import com.nhnacademy.jpa.dto.familyRelationship.RelationshipUpdateRequest;
import com.nhnacademy.jpa.service.familyRelationship.FamilyRelationshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FamilyRelationshipRestController {

    private final FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipRestController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @GetMapping("/residents/{serialNumber}/relationship")
    public List<RelationshipDto> getRelationship(@PathVariable("serialNumber") Long serialNumber) {
        return familyRelationshipService.getRelationships(serialNumber);
    }

    @PostMapping("/residents/{serialNumber}/relationship")
    public RelationshipDto registerRelationship(@PathVariable("serialNumber") Long baseSerialNumber,
                                                @RequestBody RelationshipRegisterRequest request) {
        return familyRelationshipService.registerRelationshipAndGet(baseSerialNumber, request);
    }

    @PutMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public RelationshipDto modifyRelationship(@PathVariable("serialNumber") Long baseSerialNumber,
                                              @PathVariable("familySerialNumber") Long familySerialNumber,
                                              @RequestBody RelationshipUpdateRequest request) {
        familyRelationshipService.modifyRelationship(baseSerialNumber, familySerialNumber, request);
        return familyRelationshipService.getRelationship(baseSerialNumber, familySerialNumber);
    }


    @DeleteMapping("/residents/{serialNumber}/relationship/{familySerialNumber}")
    public void deleteRelationship(@PathVariable("serialNumber") Long baseSerialNumber,
                                   @PathVariable("familySerialNumber") Long familySerialNumber) {
        familyRelationshipService.deleteRelationship(baseSerialNumber, familySerialNumber);
    }

}
