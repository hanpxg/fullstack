package com.ibm.w3.views;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ResponsePriceView {
    private List<Float> prices;
    private List<Date> dates;
}
