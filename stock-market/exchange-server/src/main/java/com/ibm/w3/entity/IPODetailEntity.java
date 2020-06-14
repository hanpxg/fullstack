package com.ibm.w3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name="iop_detail")
@Entity
public class IPODetailEntity {
    /*
    1.	id
    2.	Company Name
    3.	Stock Exchange
    4.	Price per share
    5.	Total number of Shares
    6.	Open Date Time
    7.	Remarks
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
    private float pricePerShare;

    @Column
    private Long totalOfShare;

    @Column
    private Date openTime;

    @Column
    private String remark;
}
