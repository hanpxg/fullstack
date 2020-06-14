package com.ibm.w3.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompaniesAndPrice {
    private Long companyId;
    private String name;
    private List<PriceView> views;
}
