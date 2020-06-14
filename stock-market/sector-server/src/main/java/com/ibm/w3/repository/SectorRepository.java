package com.ibm.w3.repository;

import com.ibm.w3.entity.SectorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<SectorsEntity, Long> {
    public SectorsEntity getById(Long id);

}
