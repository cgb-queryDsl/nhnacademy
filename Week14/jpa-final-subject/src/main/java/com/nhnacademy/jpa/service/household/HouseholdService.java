package com.nhnacademy.jpa.service.household;

import com.nhnacademy.jpa.dto.household.HouseholdDto;
import com.nhnacademy.jpa.dto.household.HouseholdRegisterRequest;
import org.springframework.transaction.annotation.Transactional;

public interface HouseholdService {
    @Transactional
    HouseholdDto registerHousehold(HouseholdRegisterRequest request);

    @Transactional
    void deleteHousehold(Long householdSerialNumber);
}
