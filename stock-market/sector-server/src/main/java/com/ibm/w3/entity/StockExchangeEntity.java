package com.ibm.w3.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "stock_exchange")
public class StockExchangeEntity {
    /*
    1.	Id
2.	Stock Exchange
3.	Brief
4.	Contact Address
5.	Remarks
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String brief;
    @Column
    private String address;
    @Column
    private String remark;
    @ManyToMany
    @JoinTable(name = "company_exchange", joinColumns = @JoinColumn(name = "exchange_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<CompanyEntity> companies;
    @OneToMany(mappedBy = "exchange",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<IPODetailEntity> details;

    @OneToMany(mappedBy = "exchange",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<StockPriceEntity> prices;
}
