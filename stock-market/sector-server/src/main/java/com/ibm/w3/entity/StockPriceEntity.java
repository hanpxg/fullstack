package com.ibm.w3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="stock_price")
@Entity
public class StockPriceEntity {
    /*
    1.	Company Code
    2.	Stock Exchange
    3.	Current Price
    4.	Date
    5.	Time
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String companyName;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="company_id")
    @JsonIgnore
    private CompanyEntity company;

    @Column
    private String exchangeName;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name="exchange_id")
    @JsonIgnore
    private StockExchangeEntity exchange;

    @Column
    private float price;

    @Column
    private Date date;

}
