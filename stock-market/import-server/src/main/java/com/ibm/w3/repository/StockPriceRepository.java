package com.ibm.w3.repository;

import com.ibm.w3.entity.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceRepository  extends JpaRepository<StockPriceEntity, Long> {
}
