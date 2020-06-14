package com.ibm.w3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String companyName;

    @Column
    private Float turnover;

    @Column
    private String ceo;

    @Column
    private String boardOfDirectors;

    @Column
    private String sectorName;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
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
