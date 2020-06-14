package com.ibm.w3.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sector")
public class SectorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "sector",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<CompanyEntity> companies;
}
