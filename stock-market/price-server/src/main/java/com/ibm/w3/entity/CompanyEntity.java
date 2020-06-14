package com.ibm.w3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "company")
public class CompanyEntity {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Float getTurnover() {
        return turnover;
    }

    public void setTurnover(Float turnover) {
        this.turnover = turnover;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getBoardOfDirectors() {
        return boardOfDirectors;
    }

    public void setBoardOfDirectors(String boardOfDirectors) {
        this.boardOfDirectors = boardOfDirectors;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public SectorsEntity getSector() {
        return sector;
    }

    public void setSector(SectorsEntity sector) {
        this.sector = sector;
    }

    public String getBriefWriteup() {
        return briefWriteup;
    }

    public void setBriefWriteup(String briefWriteup) {
        this.briefWriteup = briefWriteup;
    }

    public List<StockExchangeEntity> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<StockExchangeEntity> exchanges) {
        this.exchanges = exchanges;
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
    1.	Company Name
    2.	Turnover
    3.	CEO
    4.	Board of Directors
    5.	Listed in Stock Exchanges
    6.	Sector
    7.	Brief writeup
    8.	Company Stock code in each Stock Exchange

         */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String companyName;

    @Column
    private Float turnover;

    @Column
    private String iconUrl;

    @Column
    private String ceo;

    @Column
    private String boardOfDirectors;

    @Column
    private String sectorName;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="sector_id")
    @JsonIgnore
    private SectorsEntity sector;

    @Column
    private String briefWriteup;

    @ManyToMany(mappedBy = "companies")
    @JsonIgnore
    private List<StockExchangeEntity> exchanges;

    @OneToMany(mappedBy = "company",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonIgnore
    private List<IPODetailEntity> details;

    @OneToMany(mappedBy = "company",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonIgnore
    private List<StockPriceEntity> prices;
}
