package com.ibm.w3.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectorToCompanyView {
    private Long sectorId;
    private String name;
    private List<CompaniesAndPrice> views;
//
//    public SectorToCompanyView(Long sectorId, String name, List<CompaniesAndPrice> views) {
//        this.sectorId = sectorId;
//        this.name = name;
//        this.views = views;
//    }
}
