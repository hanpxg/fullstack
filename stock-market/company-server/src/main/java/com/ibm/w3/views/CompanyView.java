package com.ibm.w3.views;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyView {

    private Long id;

    private String companyName;

    private Float turnover;

    private String iconUrl;

    private String exchange;
}
