package com.nhnacademy.jpa.repository.resident;

import com.nhnacademy.jpa.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ResidentRepository extends ResidentRepositoryCustom, JpaRepository<Resident, Long> {

    @Transactional
    @Modifying
    @Query("update Resident R SET R.name = ?2, " +
            "R.deathDate = ?3, R.deathPlaceCode = ?4, " +
            "R.deathPlaceAddress = ?5 " +
            "where R.residentSerialNumber = ?1")
    int updateResident(Long serialNumber, String name, LocalDateTime date, String deathPlaceCode, String deathPlaceAddress);

    @Transactional
    void deleteByResidentSerialNumber(Long serialNumber);

    Page<Resident> getAllBy(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Resident r set r.id = ?2, " +
            "r.password = ?3, " +
            "r.email = ?4 " +
            "where r.residentSerialNumber = ?1")
    int addAccount(Long serialNumber, String id, String password, String email);

    Resident findById(String id);

    @Query(value = "select * from resident where email = ?1", nativeQuery = true)
    Resident getResidentByEmail(String email);
}
