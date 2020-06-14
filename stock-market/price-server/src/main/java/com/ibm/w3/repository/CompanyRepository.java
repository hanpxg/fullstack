package com.ibm.w3.repository;

import com.ibm.w3.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    CompanyEntity getById(Long id);
    CompanyEntity getByCompanyName(String companyName);
    @Query(name = "getCompanyName", nativeQuery = true, value = "SELECT entity.company_name from company entity")
    List<String> getCompanyName();
}
