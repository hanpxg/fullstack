package com.ibm.w3.controller;

import com.ibm.w3.entity.CompanyEntity;
import com.ibm.w3.entity.StockExchangeEntity;
import com.ibm.w3.entity.StockPriceEntity;
import com.ibm.w3.repository.CompanyRepository;
import com.ibm.w3.repository.StockPriceRepository;
import com.ibm.w3.views.PriceDetailView;
import com.ibm.w3.views.ResponsePriceView;
import com.ibm.w3.views.ResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PriceHandler {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private StockPriceRepository stockPriceRepository;
    @GetMapping("/company_name")
    public List<ResponseView> companyName() {
        List<String> companyName = companyRepository.getCompanyName();
        List<ResponseView> views = new ArrayList<>();
        for (int i = 0; i < companyName.size(); i++) {
            views.add(new ResponseView(companyName.get(i), companyName.get(i)));
        }
        return views;
    }
    @GetMapping("/exchange_name/{company_name}")
    public List<ResponseView> exchangeName(@PathVariable("company_name") String company_name) {
        CompanyEntity entity = companyRepository.getByCompanyName(company_name);
        List<StockExchangeEntity> exchangeEntities = entity.getExchanges();
        List<ResponseView> views = new ArrayList<>();
        for (int i = 0; i < exchangeEntities.size(); i++) {
            views.add(new ResponseView(exchangeEntities.get(i).getName(), exchangeEntities.get(i).getName()));
        }
        return views;
    }

    @GetMapping("/price_detail1")
    public PriceDetailView priceDetail1() {
        PriceDetailView view = new PriceDetailView();
        view.setCompanyName("111");
        view.setExchangeName("qqq");
        view.setFromDate(new Date());
        view.setToDate(new Date());
        return view;
    }

    @PostMapping("/price_detail")
    public ResponsePriceView priceDetail(@RequestBody PriceDetailView view) {
        System.out.println("11111111");
        System.out.println(view);
        List<Float> prices = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        List<StockPriceEntity> entities = stockPriceRepository.getPriceByTime(view.getCompanyName(), view.getExchangeName(),
                view.getFromDate(), view.getToDate());
        for (int i = 0; i < entities.size(); i++) {
            prices.add(entities.get(i).getPrice());
            dates.add(entities.get(i).getDate());
        }
        ResponsePriceView priceView = new ResponsePriceView();
        priceView.setDates(dates);
        priceView.setPrices(prices);
        return priceView;
//        return null;
    }
}
