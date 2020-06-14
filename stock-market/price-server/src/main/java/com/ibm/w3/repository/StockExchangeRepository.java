package com.ibm.w3.repository;

import com.ibm.w3.entity.StockExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, Long> {
    StockExchangeEntity getByName(String name);
}
