package com.nhnacademy.jpa.service.resident;

import com.nhnacademy.jpa.dto.resident.ResidentAccountRegister;
import com.nhnacademy.jpa.dto.resident.ResidentDto;
import com.nhnacademy.jpa.dto.resident.ResidentRegisterRequest;
import com.nhnacademy.jpa.dto.resident.ResidentUpdateRequest;
import com.nhnacademy.jpa.entity.Household;
import com.nhnacademy.jpa.entity.HouseholdCompositionResident;
import com.nhnacademy.jpa.entity.Resident;
import com.nhnacademy.jpa.repository.household.HouseholdRepository;
import com.nhnacademy.jpa.repository.householdCompositionResident.HouseholdCompositionResidentRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final HouseholdRepository householdRepository;
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentServiceImpl(ResidentRepository residentRepository, HouseholdRepository householdRepository,
                               HouseholdCompositionResidentRepository householdCompositionResidentRepository,
                               PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.householdRepository = householdRepository;
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public int getResidentsTotalPages(Pageable pageable) {
        return residentRepository.getAllBy(pageable).getTotalPages();
    }

    @Override
    public List<Resident> getResidents(Pageable pageable) {
        return residentRepository.getAllBy(pageable).getContent();
    }

    @Override
    public ResidentDto getResident(Long serialNumber) {
        return residentRepository.getResident(serialNumber);
    }

    @Override
    public Resident registerResident(ResidentRegisterRequest residentRegisterRequest) {
        Resident resident = new Resident();
        resident.setResidentSerialNumber(residentRegisterRequest.getResidentSerialNumber());
        resident.setName(residentRegisterRequest.getName());
        resident.setResidentRegistrationNumber(residentRegisterRequest.getResidentRegistrationNumber());
        resident.setGenderCode(residentRegisterRequest.getGenderCode());
        resident.setBirthDate(residentRegisterRequest.getBirthDate());
        resident.setBirthPlaceCode(residentRegisterRequest.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentRegisterRequest.getRegistrationBaseAddress());
        resident.setDeathDate(residentRegisterRequest.getDeathDate());
        resident.setDeathPlaceCode(residentRegisterRequest.getDeathPlaceCode());
        resident.setDeathPlaceAddress(residentRegisterRequest.getDeathPlaceAddress());

        return residentRepository.save(resident);
    }

    @Override
    public ResidentDto modifyResident(Long serialNumber, ResidentUpdateRequest residentUpdateRequest) {
        residentRepository.updateResident(serialNumber,
                                            residentUpdateRequest.getName(),
                                            residentUpdateRequest.getDeathDate(),
                                            residentUpdateRequest.getDeathPlaceCode(),
                                            residentUpdateRequest.getDeathPlaceAddress());
        return residentRepository.getResident(serialNumber);
    }

    @Override
    public void deleteResident(Long serialNumber) {
        residentRepository.deleteByResidentSerialNumber(serialNumber);
    }

    @Transactional
    @Override
    public ResidentDto addAccount(Long serialNumber, ResidentAccountRegister residentAccountRegister) {
        residentRepository.addAccount(serialNumber,
                                    residentAccountRegister.getId(),
                                    passwordEncoder.encode(residentAccountRegister.getPassword()),
                                    residentAccountRegister.getEmail());

        return residentRepository.getResident(serialNumber);
    }

    @Override
    public Long getHouseholdSerialNumber(Long serialNumber) {
        return householdCompositionResidentRepository.getHouseholdSerialNumber(serialNumber);
    }

    @Override
    public List<HouseholdCompositionResident> getFamilies(Long householdSerialNumber) {
        return householdCompositionResidentRepository.getCompositionResidents(householdSerialNumber);
    }
}
