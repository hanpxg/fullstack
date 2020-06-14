package com.ibm.w3.repository;

import com.ibm.w3.entity.IPODetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPORepository extends JpaRepository<IPODetailEntity, Long> {
    public IPODetailEntity getById(Long id);
}
