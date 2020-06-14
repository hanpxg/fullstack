package com.ibm.w3.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock_exchange")
public class StockExchangeEntity {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CompanyEntity> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyEntity> companies) {
        this.companies = companies;
    }

    public List<IPODetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<IPODetailEntity> details) {
        this.details = details;
    }

    public List<StockPriceEntity> getPrices() {
        return prices;
    }

    public void setPrices(List<StockPriceEntity> prices) {
        this.prices = prices;
    }

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
