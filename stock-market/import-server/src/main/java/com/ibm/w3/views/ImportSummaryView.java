package com.ibm.w3.views;

import lombok.Data;

@Data
public class ImportSummaryView {
    private String companyname;

    private String stockexchange;

    private int numofimport;

    private String fromdate;

    private String todate;
}
