package com.nhnacademy.jpa.repository.householdMovementAddress;

import com.nhnacademy.jpa.dto.householdAddress.HouseholdMovementAddressDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;

@NoRepositoryBean
public interface HouseholdMovementAddressRepositoryCustom {
    HouseholdMovementAddressDto getHouseMovementAddress(Long householdSerialNumber, Date reportDate);
}
