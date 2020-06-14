package com.ibm.w3.views;

import lombok.Data;

import java.util.Date;
@Data
public class PriceDetailView {
    private String companyName;
    private String exchangeName;
    private Date fromDate;
    private Date toDate;
}
