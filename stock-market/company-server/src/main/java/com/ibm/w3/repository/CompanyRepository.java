package com.ibm.w3.repository;

import com.ibm.w3.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    public CompanyEntity getById(Long id);
}
