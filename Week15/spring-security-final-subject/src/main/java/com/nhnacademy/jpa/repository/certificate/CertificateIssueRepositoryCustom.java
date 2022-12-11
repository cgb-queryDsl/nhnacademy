package com.nhnacademy.jpa.repository.certificate;

import com.nhnacademy.jpa.dto.certificate.FamilyRelationAndNumber;

import java.util.List;

public interface CertificateIssueRepositoryCustom {

    List<FamilyRelationAndNumber> getRelationAndNumber(Long serialNumber);
}
