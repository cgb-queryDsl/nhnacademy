package com.nhnacademy.jpa.controller;

import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.service.resident.ResidentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping("/resident")
    public String homeResident(Pageable pageable, Model model) {
        List<Resident> residents = residentService.getResidents(pageable);
        int totalPage = residentService.getResidentsTotalPages(pageable);
        List<Integer> pages = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            pages.add(i);
        }

        model.addAttribute("residents", residents);
        model.addAttribute("pages", pages);

        return "homeResident";
    }

    @GetMapping("/household/{residentId}")
    public String household(@PathVariable("residentId") Long residentId, Model model) {
        Long householdSerialNumber = residentService.getHouseholdSerialNumber(residentId);
        List<HouseholdCompositionResident> families = residentService.getFamilies(householdSerialNumber);
        model.addAttribute("families", families);

        return "userFamily";
    }
}