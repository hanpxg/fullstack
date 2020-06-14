package com.ibm.w3.repository;

import com.ibm.w3.entity.StockPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockPriceRepository  extends JpaRepository<StockPriceEntity, Long> {
    @Query(name = "getPriceByTime", nativeQuery = true, value = "SELECT * from stock_price entity " +
            "where entity.company_name = :companyname and entity.exchange_name = :exchangename" +
            " and entity.date >= :fromdate and entity.date <= :todate order by entity.date limit 0,6")
    List<StockPriceEntity> getPriceByTime(String companyname, String exchangename, Date fromdate, Date todate);
}
