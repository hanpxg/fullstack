package com.ibm.w3.controller;

import com.ibm.w3.entity.CompanyEntity;
import com.ibm.w3.entity.SectorsEntity;
import com.ibm.w3.entity.StockPriceEntity;
import com.ibm.w3.repository.SectorRepository;
import com.ibm.w3.view.CompaniesAndPrice;
import com.ibm.w3.view.PriceView;
import com.ibm.w3.view.SectorToCompanyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SectorHandler {
    @Autowired
    private SectorRepository repository;

    @GetMapping("/findCompaniesById/{id}")
    public List<CompanyEntity> getCompanyBySectorId(@PathVariable("id") Long id) {
        return repository.getById(id).getCompanies();
    }
    @GetMapping("/findPriceById/{id}")
    public SectorToCompanyView findPriceById(@PathVariable("id") Long id) {
        SectorsEntity entity = repository.getById(id);
        List<CompanyEntity> companyEntities = entity.getCompanies();
//             CompaniesAndPrice
        List<CompaniesAndPrice> companiesAndPrices = new ArrayList<CompaniesAndPrice>();
        for (int i = 0; i < companyEntities.size(); i++) {
            List<StockPriceEntity> stockPriceEntities = companyEntities.get(i).getPrices();
            List<PriceView> priceViews = new ArrayList<PriceView>();
            for (int j = 0; j < stockPriceEntities.size(); j++) {
                StockPriceEntity temp = stockPriceEntities.get(i);

                priceViews.add(new PriceView(stockPriceEntities.get(i).getId(),
                        stockPriceEntities.get(i).getDate(), stockPriceEntities.get(i).getPrice()));
            }
            companiesAndPrices.add(new CompaniesAndPrice(companyEntities.get(i).getId(),
                    companyEntities.get(i).getCompanyName(), priceViews));
        }
        SectorToCompanyView sectorToCompanyView = new SectorToCompanyView();
        sectorToCompanyView.setSectorId(entity.getId());
        sectorToCompanyView.setName(entity.getName());
        sectorToCompanyView.setViews(companiesAndPrices);
        return sectorToCompanyView;


    }

}
